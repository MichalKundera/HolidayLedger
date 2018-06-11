package com.mehow.holidayledger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Cartesian;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.HoverMode;
import com.anychart.anychart.SeriesBar;
import com.anychart.anychart.TooltipDisplayMode;
import com.anychart.anychart.TooltipPositionMode;
import com.anychart.anychart.ValueDataEntry;
import com.mehow.holidayledger.model.DBManager;
import com.mehow.holidayledger.model.entities.Person;
import com.mehow.holidayledger.model.entities.Purchase;
import com.mehow.holidayledger.model.entities.enums.Category;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DayChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Purchase> purchases = DBManager.getInstance().getJourney(1).getPurchaseList();
        List<Person> personList = DBManager.getInstance().getJourney(1).getPersonList();
        List<Category> categories = Arrays.asList(Category.values());
        List<DataEntry> data = new ArrayList<>();
        List<com.mehow.holidayledger.model.entities.Currency> currencies = DBManager.getInstance().getCurrencyList();
        Map<String,Double> map = new TreeMap<>();
        AnyChartView anyChartView = findViewById(R.id.day_chart_view);
        Cartesian vertical = AnyChart.vertical();
        vertical.setAnimation(true).setTitle("Wydatki w dniach");

        for (Purchase c:purchases
                ) {
            map.put(new SimpleDateFormat("dd/MM/yyyy").format(c.getDate()), (double) 0);
        }
        for (Purchase p:purchases
                ) {
            for (com.mehow.holidayledger.model.entities.Currency c:currencies
                    ) {
                if(p.getCurrencyShortcut().equals(c.getCurrencyShortcut()))
                {
                    map.put(new SimpleDateFormat("dd/MM/yyyy").format(p.getDate()), (Double.valueOf(p.getSum())*c.getRateToPLN())+
                            map.get(new SimpleDateFormat("dd/MM/yyyy").format(p.getDate())));
                    break;
                }
            }
        }
        for (Map.Entry<String,Double> entry : map.entrySet()
                ) {
            data.add(new ValueDataEntry(entry.getKey(), entry.getValue()));
        }

        SeriesBar bar = vertical.bar(data);
        bar.getLabels().setFormat("{%Value} PLN");


        vertical.setXAxis(true);
        vertical.setYAxis(true);
        vertical.setLabels(true);
        vertical.getInteractivity().setHoverMode(HoverMode.BY_X);

        anyChartView.setChart(vertical);



    }

}
