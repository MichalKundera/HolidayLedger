package com.mehow.holidayledger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.EnumsAlign;
import com.anychart.anychart.LegendLayout;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;
import com.mehow.holidayledger.model.DBManager;
import com.mehow.holidayledger.model.entities.Purchase;
import com.mehow.holidayledger.model.entities.enums.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CategoriesChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Pie pie = AnyChart.pie();
        List<Purchase> purchases = DBManager.getInstance().getJourney(1).getPurchaseList();
        List< Category> categories = Arrays.asList(Category.values());
        List<DataEntry> data = new ArrayList<>();
        List<com.mehow.holidayledger.model.entities.Currency> currencies = DBManager.getInstance().getCurrencyList();

        Map<String,Double> map = new TreeMap<>();
        for (Category c:categories
             ) {
            map.put(c.name(), (double) 0);
        }
        for (Purchase p:purchases
             ) {
            for (com.mehow.holidayledger.model.entities.Currency c:currencies
                    ) {
                if(p.getCurrencyShortcut().equals(c.getCurrencyShortcut()))
                {
                    map.put(p.getCategory().name(), (Double.valueOf(p.getSum())*c.getRateToPLN())+map.get(p.getCategory().name()));
                    break;
                }
            }
        }
        for (Category c:categories
                ) {
            data.add(new ValueDataEntry(c.name() +" - "+ String.format("%.2f", map.get(c.name())) + " zl" , map.get(c.name())));
        }
        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.categories_chart_view);
        pie.setTitle("Statystyki - Kategorie");
        pie.setData(data);
        pie.getLegend().getTitle().setEnabled(true);
        pie.getLegend().getTitle()
                .setText("Kategoria")
                .setPadding(0d, 0d, 10d, 0d);
        pie.getLegend()
                .setItemsLayout(LegendLayout.VERTICAL_EXPANDABLE)
                .setAlign(EnumsAlign.CENTER).setPosition("center-left");
        pie.setLabel(true);
        pie.setAnimation(true);

        anyChartView.setChart(pie);



    }

}
