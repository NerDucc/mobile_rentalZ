package com.example.rentalz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rentalz.data.ApartmentEntity;
import com.example.rentalz.data.SampleDataProvider;
import com.example.rentalz.databinding.FragmentMainBinding;

import java.util.Optional;

public class MainFragment extends Fragment implements ApartmentListAdapter.ListApartmentListener{

    private MainViewModel mViewModel;
    private FragmentMainBinding binding;
    private ApartmentListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        AppCompatActivity aca = (AppCompatActivity) getActivity();
        aca.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = FragmentMainBinding.inflate(inflater, container,  false);

        RecyclerView rv = binding.recyclerView;
        rv.setHasFixedSize(true);  //each row has equal size regardless of its content
        rv.addItemDecoration(new DividerItemDecoration(
                getContext(),
                (new LinearLayoutManager(getContext())).getOrientation())
        );

        mViewModel.apartmentList.observe(
                getViewLifecycleOwner(),
                apartmentList -> {
                    System.out.println("#Size: " +apartmentList);
                    adapter = new ApartmentListAdapter(apartmentList, this);
                    binding.recyclerView.setAdapter(adapter);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }

        );
        return binding.getRoot();
    }


    @Override
    public void onItemClick(String apartmentId) {
        //Khi list item duoc click, chuyen sang editor voi ID
        Optional<ApartmentEntity> be = mViewModel.apartmentList.getValue().stream()
                .filter(b -> b.getId() == apartmentId).findFirst();
        if(be.isPresent()){
            Bundle bundle = new Bundle();
            bundle.putString("Apartment Id", apartmentId);
            Navigation.findNavController(getView()).navigate(R.id.editorFragment, bundle);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.i(this.getClass().getName(), "onResume");
        mViewModel.getApartments();
    }
}