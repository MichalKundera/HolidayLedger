package com.mehow.holidayledger;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mehow.holidayledger.model.DBManager;
import com.mehow.holidayledger.model.entities.Currency;
import com.mehow.holidayledger.model.entities.Person;
import com.mehow.holidayledger.model.entities.Purchase;
import com.mehow.holidayledger.model.entities.enums.CurrencyShortcut;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setProgressBarInfo();



    }

    public void setProgressBarInfo()
    {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        int lowColor = ContextCompat.getColor(this,R.color.fundsLow);
        int midColor = ContextCompat.getColor(this,R.color.fundsMid);
        int highColor = ContextCompat.getColor(this,R.color.fundsHigh);

        List<Purchase> purchaseList = DBManager.getInstance().getJourney(1).getPurchaseList();
        Person logged = DBManager.getInstance().getLogged();
        float funds = logged.getAccountBalance();
        float spend =0;
        List<Currency> currencies= DBManager.getInstance().getCurrencyList();
        for (Purchase p:purchaseList
             ) {
            if(p.getPerson().equals(logged))
            {
                CurrencyShortcut currencyShortcut = p.getCurrencyShortcut();
                for (Currency c: currencies
                     ) {
                    if(c.getCurrencyShortcut().equals(currencyShortcut)){
                        spend+=p.getSum()*c.getRateToPLN();
                        break;
                    }
                }

            }
        }



        float fundsPercentage = ((funds-spend)/funds)*100;// from db

        TextView percentageText = findViewById(R.id.percentageText);
        TextView textFunds = findViewById(R.id.textFunds);
        progressBar.setProgress(0);
        progressBar.setSecondaryProgress((int)fundsPercentage);
        percentageText.setText(String.format("%.1f",fundsPercentage) + "%");
        textFunds.setText(String.format("%.2fzl z %.2fzl",funds-spend,funds));

        if(fundsPercentage<30){
        progressBar.getProgressDrawable().setColorFilter(lowColor, PorterDuff.Mode.SRC_IN);
        }
        else
        if(fundsPercentage<60){
            progressBar.getProgressDrawable().setColorFilter(midColor, PorterDuff.Mode.SRC_IN);}
        else
            {
            progressBar.getProgressDrawable().setColorFilter(highColor, PorterDuff.Mode.SRC_IN);
        }
        if(fundsPercentage<0)
        {
            fundsPercentage=0;
        }
        ObjectAnimator progressAnimator;
        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0,(int)fundsPercentage);
        progressAnimator.setDuration(2000);
        progressAnimator.start();

    }

    private void progressBarOnClick()
    {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OnClick(View view) {
        Intent intent;
        switch (view.getId())
        {
            case R.id.personsButton:{
                intent = new Intent(MainActivity.this, PersonsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.currenciesButton:{
                intent = new Intent(MainActivity.this, CurrenciesActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.mapsButton:{
                intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.statisticsButton:{
                intent = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(intent);
                break;
            }
            
            case R.id.addButton:{
                intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                break;
            }



        }
    }
}
