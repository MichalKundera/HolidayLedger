package com.mehow.holidayledger.model.entities;

import com.google.android.gms.maps.model.LatLng;
import com.mehow.holidayledger.model.entities.enums.Category;
import com.mehow.holidayledger.model.entities.enums.CurrencyShortcut;

import java.util.Date;

public class Purchase {
    int id;
    Person person;
    CurrencyShortcut currencyShortcut;
    Float sum;
    String description;
    Date date;
    Category category;
    LatLng location;

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }



    public Purchase() {
    }



    public Purchase(int id, Person person, CurrencyShortcut currencyShortcut, Float sum, String description, Date date, Category category) {
        this.id = id;
        this.person = person;
        this.currencyShortcut = currencyShortcut;
        this.sum = sum;
        this.description = description;
        this.date = date;
        this.category = category;
    }



    public Purchase(int id, Person person, CurrencyShortcut currencyShortcut, Float sum, String description, Date date, Category category,LatLng location) {
        this.id = id;
        this.person = person;
        this.currencyShortcut = currencyShortcut;
        this.sum = sum;
        this.description = description;
        this.date = date;
        this.category = category;
        this.location=location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public CurrencyShortcut getCurrencyShortcut() {
        return currencyShortcut;
    }

    public void setCurrencyShortcut(CurrencyShortcut currencyShortcut) {
        this.currencyShortcut = currencyShortcut;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
