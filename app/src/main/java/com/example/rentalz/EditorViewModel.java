package com.example.rentalz;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rentalz.data.ApartmentEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class EditorViewModel extends ViewModel {

    MutableLiveData<ApartmentEntity> apartment = new MutableLiveData<ApartmentEntity>();

    public void getApartmentById(String id){
        if(id == null || id == Constants.New_Apartment_ID) {
            apartment.setValue(new ApartmentEntity());
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(Constants.FS_APARTMENT_SET).document(id);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Log.d(Constants.FIRE_STORE, "DocumentSnapShot data: " + document.getData());

                        ApartmentEntity a = ApartmentEntity.getApartmentFromFireStore(document);
                        apartment.setValue(a);
                    }
                    else{
                        Log.d(Constants.FIRE_STORE, "No such document");
                    }
                }
                else{
                    Log.d(Constants.FIRE_STORE, "Get failed with id" + id, task.getException());
                }
            }
        });
    }

    public void updateApartment(ApartmentEntity updateApartment) {
        Map<String, Object> bMap = updateApartment.getMapWithoutId();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(Constants.FS_APARTMENT_SET).document(updateApartment.getId())
                .set(bMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.FIRE_STORE, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(Constants.FIRE_STORE, "Error writing document", e);
                    }
                });
    }
}