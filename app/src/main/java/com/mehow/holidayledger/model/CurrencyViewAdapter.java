package com.mehow.holidayledger.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mehow.holidayledger.R;
import com.mehow.holidayledger.model.entities.Currency;
import com.mehow.holidayledger.model.entities.enums.CurrencyShortcut;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mehow.holidayledger.R.drawable.czk;

public class CurrencyViewAdapter extends RecyclerView.Adapter<CurrencyViewAdapter.CurrencyViewHolder> {

    private List<Currency> currencyList;
    private Context mContext;
    Resources resources;

    public CurrencyViewAdapter(Context context, List<Currency> currencies,Resources resources)
    {
        currencyList=currencies;
        mContext=context;
        this.resources=resources;
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.currencyText)TextView currencyText;
        @BindView(R.id.currencyRateText)TextView rateText;
        @BindView(R.id.currencyImage)ImageView image;

        public CurrencyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext=itemView.getContext();
        }

        public void bindCurrency(Currency currency) {
            currencyText.setText(currency.getName()+"("+currency.getCurrencyShortcut().name()+")");
            rateText.setText(String.format("%.2f",currency.getRateToPLN()));
            image.setImageResource(bindImage(currency.getCurrencyShortcut()));
        }

        private int bindImage(CurrencyShortcut shortcut)
        {
            switch (shortcut)
            {
                case CZK: {return R.drawable.czk;}
                case EUR: {return R.drawable.eur;}
                case GBP: {return R.drawable.gbp;}
                case PLN: {return R.drawable.pln;}
                case USD: {return R.drawable.usd;}
            }
            return 0;
        }
    }



    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_currency, parent, false);
        CurrencyViewHolder viewHolder = new CurrencyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        holder.bindCurrency(currencyList.get(position));
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }



}
