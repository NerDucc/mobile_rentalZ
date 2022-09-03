package com.example.rentalz.data;

import java.util.ArrayList;
import java.util.List;

public class SampleDataProvider {
    public static List<ApartmentEntity> getApartment() {
        List<ApartmentEntity> apartments = new ArrayList<>();
        apartments.add(new ApartmentEntity("1", "1", "Hello", "Hello", 2, "Hello", "Hello", "Hello"));
        apartments.add(new ApartmentEntity("2", "house", "Bonjour", "Hello", 3, "Hello", "Hello", "Hello"));
        apartments.add(new ApartmentEntity("3", "house", "Salut", "Hello", 4, "Hello", "Hello", "Hello"));
        apartments.add(new ApartmentEntity("4", "house", "Salut", "Hello", 4, "Hello", "Hello", "Hello"));

        return apartments;
    }
}
