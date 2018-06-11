package com.mehow.holidayledger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mehow.holidayledger.model.CurrencyViewAdapter;
import com.mehow.holidayledger.model.DBManager;
import com.mehow.holidayledger.model.entities.Currency;

import java.util.List;

import butterknife.BindView;

public class CurrenciesActivity extends AppCompatActivity {

    @BindView(R.id.currencyText)TextView currencyText;
    @BindView(R.id.currencyRateText)TextView rateText;
    @BindView(R.id.currencyImage)ImageView image;
    @BindView(R.id.allCurrenciesView) RecyclerView recyclerView;
    private CurrencyViewAdapter currencyViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.allCurrenciesView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        List<Currency> currencies = com.mehow.holidayledger.model.DBManager.getInstance().getCurrencyList();
        currencyViewAdapter = new CurrencyViewAdapter(getApplicationContext(),currencies,getResources());
        recyclerView.setAdapter(currencyViewAdapter);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
