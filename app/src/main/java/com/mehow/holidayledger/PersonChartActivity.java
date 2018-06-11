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
import com.anychart.anychart.CartesianSeriesColumn;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.EnumsAlign;
import com.anychart.anychart.EnumsAnchor;
import com.anychart.anychart.HoverMode;
import com.anychart.anychart.LegendLayout;
import com.anychart.anychart.Pie;
import com.anychart.anychart.Position;
import com.anychart.anychart.TooltipPositionMode;
import com.anychart.anychart.ValueDataEntry;
import com.mehow.holidayledger.model.DBManager;
import com.mehow.holidayledger.model.entities.Person;
import com.mehow.holidayledger.model.entities.Purchase;
import com.mehow.holidayledger.model.entities.enums.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PersonChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Cartesian cartesian = AnyChart.column();

        List<Purchase> purchases = DBManager.getInstance().getJourney(1).getPurchaseList();
        List<Person> personList = DBManager.getInstance().getJourney(1).getPersonList();
        List<Category> categories = Arrays.asList(Category.values());
        List<DataEntry> data = new ArrayList<>();
        List<com.mehow.holidayledger.model.entities.Currency> currencies = DBManager.getInstance().getCurrencyList();
        Map<String,Double> map = new TreeMap<>();
        for (Person c:personList
                ) {
            map.put(c.getName(), (double) 0);
        }
        for (Purchase p:purchases
                ) {
            for (com.mehow.holidayledger.model.entities.Currency c:currencies
                    ) {
                if(p.getCurrencyShortcut().equals(c.getCurrencyShortcut()))
                {
                    map.put(p.getPerson().getName(), (Double.valueOf(p.getSum())*c.getRateToPLN())+map.get(p.getPerson().getName()));
                    break;
                }
            }
        }
        for (Person c:personList
                ) {
            data.add(new ValueDataEntry(c.getName(), map.get(c.getName())));
        }

        AnyChartView anyChartView =  findViewById(R.id.persons_chart_view);
        CartesianSeriesColumn column = cartesian.column(data);


        column.getTooltip()
                .setTitleFormat("{%X}")
                .setPosition(Position.CENTER_BOTTOM)
                .setAnchor(EnumsAnchor.CENTER_BOTTOM)
                .setOffsetX(0d)
                .setOffsetY(5d)
                .setFormat("{%Value}{groupsSeparator: }PLN");

        cartesian.setAnimation(true);
        cartesian.setTitle("Suma wydatków osób");
        cartesian.setLabels(true);

        cartesian.getYScale().setMinimum(0d);

        cartesian.getYAxis().getLabels().setFormat("{%Value}{groupsSeparator: }PLN");

        cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);
        cartesian.getInteractivity().setHoverMode(HoverMode.BY_X);

        cartesian.getXAxis().setTitle("Osoba");
        cartesian.getYAxis().setTitle("Suma wydatków");

        anyChartView.setChart(cartesian);




    }


}
