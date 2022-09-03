package com.example.rentalz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.rentalz.data.ApartmentEntity;
import com.example.rentalz.databinding.FragmentEditorBinding;

public class EditorFragment extends Fragment {

    private EditorViewModel mViewModel;
    private FragmentEditorBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        AppCompatActivity app = (AppCompatActivity)getActivity();
        ActionBar ab = app.getSupportActionBar();

        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_outline_save_24);
        setHasOptionsMenu(true);

        mViewModel = new ViewModelProvider(this).get(EditorViewModel.class);
        binding = FragmentEditorBinding.inflate(inflater, container, false);

        String apartmentId = getArguments().getString("Apartment Id");

        mViewModel.apartment.observe(
                getViewLifecycleOwner(),
                be -> {
                    binding.editProperty.setText(be.getProperty());
                    binding.editBedRoom.setText(be.getBedrooms());
                    binding.editDate.setText(be.getDate());
                    binding.editFurniture.setText(be.getFurniture());
                    binding.editNotes.setText(be.getNotes());
                    binding.editPrice.setText(Integer.toString(be.getPrice()));
                    binding.editReporter.setText(be.getReporter());
                }
        );
        mViewModel.getApartmentById(apartmentId);

//        binding.editProperty.setText(apartmentId);

        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                return saveAndReturn();
            default: return super.onOptionsItemSelected(item);
        }
    }

    private boolean saveAndReturn() {
        Log.i(this.getClass().getName(), "save and return");
        String id = mViewModel.apartment.getValue().getId();
        String property = binding.editProperty.getText().toString();
        String bedroom = binding.editBedRoom.getText().toString();
        String date = binding.editDate.getText().toString();
        String furniture = binding.editFurniture.getText().toString();
        String note = binding.editNotes.getText().toString();
        String reporter = binding.editReporter.getText().toString();
        int price = Integer.parseInt(binding.editPrice.getText().toString());

        ApartmentEntity updateApartment
                = new ApartmentEntity(id != null ? id : Constants.New_Apartment_ID ,
                property, bedroom, date, price, furniture, note, reporter);



        mViewModel.updateApartment(updateApartment);



        Navigation.findNavController(getView()).navigateUp();
        return true;
    }

}