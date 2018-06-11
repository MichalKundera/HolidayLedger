package com.mehow.holidayledger.model.entities;

import java.util.Date;
import java.util.List;

/*
Class represents single journey
 */
public class Journey {
    int id;
    List<Person> personList;
    List<Purchase> purchaseList;
    String name;
    String description;
    Date dateFrom;
    Date dateTo;

    public Journey() {
    }

    public boolean addPerson(Person person)
    {
        if(!personList.contains(person)) {
            personList.add(person);
            return true;
        }
        return false;
    }
    public boolean addPurchase(Purchase purchase)
    {
        if(!purchaseList.contains(purchase)) {
            purchaseList.add(purchase);
            return true;
        }
        return false;
    }

    public Journey(int id, List<Person> personList, List<Purchase> purchaseList, String name, String description, Date dateFrom, Date dateTo) {
        this.id = id;
        this.personList = personList;
        this.purchaseList = purchaseList;
        this.name = name;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
