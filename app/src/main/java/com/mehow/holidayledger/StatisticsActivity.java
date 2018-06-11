package com.mehow.holidayledger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.mehow.holidayledger.model.DBManager;
import com.mehow.holidayledger.model.PurchaseViewAdapter;
import com.mehow.holidayledger.model.entities.Purchase;

import java.util.List;

import butterknife.BindView;

public class StatisticsActivity extends AppCompatActivity {

    @BindView(R.id.singleNameText)
    TextView nameText;
    @BindView(R.id.singleCategoriesText)
    TextView categoriesText;

    @BindView(R.id.singleSumText)
    TextView singleSumText;
    @BindView(R.id.singleDateText)
    TextView singleDateText;
    @BindView(R.id.singleSumPlnText)
    TextView singleSumPlnText;
    @BindView(R.id.singleDescription)
    TextView singleDescription;
    @BindView(R.id.purchaseRecyclerView)
    RecyclerView recyclerView;

    private PurchaseViewAdapter purchaseViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.purchaseRecyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        List<Purchase> personList = DBManager.getInstance().getJourney(1).getPurchaseList();

        PurchaseViewAdapter purchaseViewAdapter = new PurchaseViewAdapter(getApplicationContext(),personList,getResources(),savedInstanceState);
        recyclerView.setAdapter(purchaseViewAdapter);


    }

    public void OnClickStatistics(View view) {
        Intent intent;
        switch (view.getId())
        {

            case R.id.categoriesStatButton:{
                intent = new Intent(StatisticsActivity.this, CategoriesChartActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.personsStatButton:{
                intent = new Intent(StatisticsActivity.this, PersonChartActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.daysStatButton:{
                intent = new Intent(StatisticsActivity.this, DayChartActivity.class);
                startActivity(intent);
                break;
            }


        }}

}
