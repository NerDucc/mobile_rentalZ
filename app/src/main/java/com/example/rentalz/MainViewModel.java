package com.example.rentalz;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rentalz.data.ApartmentEntity;
import com.example.rentalz.data.SampleDataProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    MutableLiveData<List<ApartmentEntity>> apartmentList = new MutableLiveData<List<ApartmentEntity>>();
    {
//        apartmentList.setValue(SampleDataProvider.getApartment());

        getApartments();
    }

    public void getApartments() {

        List<ApartmentEntity> bList = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(Constants.FS_APARTMENT_SET)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(Constants.FIRE_STORE, document.getId() + " => " + document.getData());
                                ApartmentEntity b = ApartmentEntity.getApartmentFromFireStore(document);
                                bList.add(b);
                            }
                            apartmentList.setValue(bList);
                        } else {
                            Log.w(Constants.FIRE_STORE, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}