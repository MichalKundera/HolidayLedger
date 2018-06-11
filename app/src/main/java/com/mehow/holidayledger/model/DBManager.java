package com.mehow.holidayledger.model;

import com.google.android.gms.maps.model.LatLng;
import com.mehow.holidayledger.model.entities.Currency;
import com.mehow.holidayledger.model.entities.Journey;
import com.mehow.holidayledger.model.entities.Person;
import com.mehow.holidayledger.model.entities.Purchase;
import com.mehow.holidayledger.model.entities.enums.Category;
import com.mehow.holidayledger.model.entities.enums.CurrencyShortcut;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBManager {
    private static final DBManager ourInstance = new DBManager();

    public static DBManager getInstance() {
        return ourInstance;
    }

    private DBManager() {
        initialize();
    }

    public Person getLogged()
    {
        return person1;
    }
    List <Journey> journeyList ;
    Journey journey ;

    private void initialize()
    {
        journeyList  = new ArrayList<>();
        journey = new Journey();
        journey.setPersonList(getPersonList(1));
        journey.setPurchaseList(getPurchaseList(1));
        journey.setDateFrom(new Date("02/08/2018"));
        journey.setDateTo(new Date("28/08/2018"));
        journey.setDescription("Wycieczka nr 1 test heheheh");
        journey.setName("Wycieczka nr 1");
        journey.setId(1);
        journeyList.add(journey);
    }

    public List<Journey> getJourneyList()
    {
        return  journeyList;
    }

    public Journey getJourney(int id)
    {
        return journey;
    }

    public void  addPurchase(Purchase p)
    {
        journey.addPurchase(p);
    }

    private List<Purchase> getPurchaseList(int journeyID)
    {
        List<Purchase> listPurchase = new ArrayList<>();
        listPurchase.add(purchase1);
        listPurchase.add(purchase2);
        listPurchase.add(purchase3);
        listPurchase.add(purchase4);
        listPurchase.add(purchase5);
        listPurchase.add(purchase6);
        listPurchase.add(purchase7);
        listPurchase.add(purchase8);
        listPurchase.add(purchase9);
        listPurchase.add(purchase10);
        return listPurchase;
    }


    Person person1 = new Person(1,"Artur","abc", 1440.9f);
    Person person2 = new Person(2,"Marek","dgf", 1005.9f);
    Person person3 = new Person(3,"Ania","dgf", 805.9f);
    Person person4 = new Person(4,"Kasia","dgf", 905.9f);
    Purchase purchase1 = new Purchase(1,person1, CurrencyShortcut.EUR,20.3f,"Drink na brzegu morza",
            new Date ("08/06/2018"), Category.DRINK, new LatLng(41.730152, 12.274317));
    Purchase purchase2 = new Purchase(2,person2, CurrencyShortcut.EUR,40.3f,"Wloska pizza",
            new Date ("08/06/2018"), Category.FOOD,new LatLng(41.872180, 12.527684));
    Purchase purchase3 = new Purchase(3,person3, CurrencyShortcut.EUR,150.3f,"Mieszkanie",
            new Date ("06/06/2018"), Category.ACCOMMODATION,new LatLng(41.897976, 12.476742));
    Purchase purchase4 = new Purchase(4,person4, CurrencyShortcut.EUR,23.6f,"Paliwo",
            new Date ("07/06/2018"), Category.FUEL,new LatLng(41.900590, 12.473426));
    Purchase purchase5 = new Purchase(5,person2, CurrencyShortcut.EUR,18.3f,"Rollercoster",
            new Date ("05/06/2018"), Category.FUN,new LatLng(41.87320, 12.5543284));
    Purchase purchase6 = new Purchase(6,person4, CurrencyShortcut.EUR,5.6f,"innne",
            new Date ("09/06/2018"), Category.OTHER,new LatLng(41.843180, 12.557684));
    Purchase purchase7 = new Purchase(7,person1, CurrencyShortcut.EUR,12.1f,"Spaghetti",
            new Date ("10/06/2018"), Category.FOOD,new LatLng(41.83560, 12.56584));
    Purchase purchase8 = new Purchase(8,person3, CurrencyShortcut.EUR,5.3f,"Wisiorek",
            new Date ("09/06/2018"), Category.SOUVENIR,new LatLng(41.872180, 12.545684));
    Purchase purchase9 = new Purchase(9,person2, CurrencyShortcut.EUR,30.3f,"Kebab",
            new Date ("06/06/2018"), Category.FOOD,new LatLng(41.903766, 12.469392));
    Purchase purchase10 = new Purchase(10,person3, CurrencyShortcut.EUR,15.3f,"Klub",
            new Date ("07/06/2018"), Category.FUN,new LatLng(41.901935, 12.457095));

    public List<Person> getPersonList(int journeyID) {
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        return list;
    }
    public List<Currency> getCurrencyList() {
        List<Currency> CurrencyList = new ArrayList<>();
        CurrencyList.add(new Currency(CurrencyShortcut.EUR,4.32f,"Euro"));
        CurrencyList.add(new Currency(CurrencyShortcut.PLN,1,"Polski Złoty"));
        CurrencyList.add(new Currency(CurrencyShortcut.USD,3.66f,"Dolar Amerykański"));
        CurrencyList.add(new Currency(CurrencyShortcut.CZK,0.16f,"Korona Czeska"));
        CurrencyList.add(new Currency(CurrencyShortcut.GBP,4.92f,"Funt Brytyjski"));
        return CurrencyList;
    }

    public int getNextPurchaseID()
    {
       return getJourney(1).getPurchaseList().size()+1;
    }


}
