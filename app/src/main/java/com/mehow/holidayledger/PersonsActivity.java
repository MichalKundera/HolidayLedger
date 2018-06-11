package com.mehow.holidayledger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mehow.holidayledger.model.DBManager;
import com.mehow.holidayledger.model.PersonViewAdapter;
import com.mehow.holidayledger.model.entities.Person;

import java.util.List;

import butterknife.BindView;

public class PersonsActivity extends AppCompatActivity {

    @BindView(R.id.nameText)TextView nameText;
    @BindView(R.id.personImage)ImageView personImage;
    @BindView(R.id.allPersonsView) RecyclerView recyclerView;

    private PersonViewAdapter personViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.allPersonsView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        List<Person> personList = DBManager.getInstance().getJourney(1).getPersonList();

        personViewAdapter = new PersonViewAdapter(getApplicationContext(),personList,getResources());
        recyclerView.setAdapter(personViewAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_person, menu);
        return true;
    }

}
