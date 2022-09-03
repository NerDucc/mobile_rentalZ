package com.example.rentalz;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rentalz.databinding.FragmentEditorBinding;

public class EditorFragment extends Fragment {

    private EditorViewModel mViewModel;
    private FragmentEditorBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

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
                    binding.editReporter.setText(be.getReporterName());
                }
        );
        mViewModel.getApartmentById(apartmentId);

//        binding.editProperty.setText(apartmentId);

        return binding.getRoot();
    }


}