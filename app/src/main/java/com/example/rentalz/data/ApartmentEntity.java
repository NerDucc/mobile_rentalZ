package com.example.rentalz.data;

import com.example.rentalz.Constants;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class ApartmentEntity {
    private String id;
    private String property;
    private String bedrooms;
    private String date;
    private int price;
    private String furniture;
    private String notes;
    private String reporter;

    public ApartmentEntity() {
        this(
                Constants.New_Apartment_ID,
                Constants.Empty_String,
                Constants.Empty_String,
                Constants.Empty_String,
                0,
                Constants.Empty_String,
                Constants.Empty_String,
                Constants.Empty_String
        );
    }
    public ApartmentEntity(String property, String bedrooms, String date, int price, String furniture,
                           String notes, String reporter) {
        this(Constants.New_Apartment_ID, property, bedrooms, date, price, furniture, notes, reporter);
    }

    public ApartmentEntity(String id, String property, String bedrooms, String date,
                           int price, String furniture, String notes, String reporter) {
        setId(id);
        setProperty(property);
        setBedrooms(bedrooms);
        setDate(date);
        setPrice(price);
        setFurniture(furniture);
        setNotes(notes);
        setReporter(reporter);
    }

    @Override
    public String toString(){
        return "Apartment{" +
                "Id ='" +id+'\'' +
                ", type ='" + property + '\''+
                ", bedroom ='" +bedrooms+'\'' +
                ", date ='" +date+'\'' +
                ", monthly price ='" +price+'\'' +
                ", furniture ='" +furniture+'\'' +
                ", note ='" +notes+'\'' +
                ", reporter name ='" +reporter+'\'' +
                '}';
    }

    public static ApartmentEntity getApartmentFromFireStore(DocumentSnapshot doc) {
        if (doc.exists()) {
            Map<String, Object> data = doc.getData();

            String id = doc.getId();
            String property = data.get("property").toString();
            String furniture = data.get("furniture").toString();
            String bedroom = data.get("bedrooms").toString();
            String note = data.get("notes").toString();
            String reporter = data.get("reporter").toString();
            int price = Integer.parseInt(data.get("price").toString());
            String date = data.get("date").toString();

            return new ApartmentEntity(id, property, bedroom, date, price, furniture, note, reporter);

        } else {
//            System.out.println("The doc does not exist");
            return new ApartmentEntity();
        }
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getReporter() {
        return reporter;
    }

    public Map<String, Object> getMapWithoutId() {
        Map<String, Object> bMap = new HashMap<>();
        bMap.put("property", this.property);
        bMap.put("bedrooms", this.bedrooms);
        bMap.put("date", this.date);
        bMap.put("price", this.price);
        bMap.put("furniture", this.furniture);
        bMap.put("notes", this.notes);
        bMap.put("reporter", this.reporter);
        return bMap;
    }
}
