package com.mehow.holidayledger.model;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mehow.holidayledger.R;
import com.mehow.holidayledger.model.entities.Currency;
import com.mehow.holidayledger.model.entities.Person;
import com.mehow.holidayledger.model.entities.Purchase;
import com.mehow.holidayledger.model.entities.enums.Category;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseViewAdapter extends RecyclerView.Adapter<PurchaseViewAdapter.PurchaseViewHolder> {

    private List<Purchase> purchaseList;
    private Context mContext;
    Resources resources;
    Bundle savedInstanceState;

    private GoogleMap googleMap;

    public PurchaseViewAdapter(Context context, List<Purchase> purchases, Resources resources, Bundle savedInstanceState) {
        purchaseList = purchases;
        mContext = context;
        this.resources = resources;
        this.savedInstanceState = savedInstanceState;
    }

    public class PurchaseViewHolder extends RecyclerView.ViewHolder {
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

        private List<Currency> currencies = DBManager.getInstance().getCurrencyList();






        public PurchaseViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindPurchase(Purchase purchase) {
            nameText.setText(purchase.getPerson().getName());
            categoriesText.setText(purchase.getCategory().name());
            singleDateText.setText(new SimpleDateFormat("dd/MM/yyyy").format( purchase.getDate()));
            singleSumText.setText(purchase.getSum().toString()+purchase.getCurrencyShortcut().name());
            singleDescription.setText(purchase.getDescription());
            for (Currency c:currencies
                 ) {
                if(c.getCurrencyShortcut().equals(purchase.getCurrencyShortcut()))
                {
                    singleSumPlnText.setText(((int) (purchase.getSum()*c.getRateToPLN())+"zł"));
                    break;
                }
            }



        }


    }


        @NonNull
        @Override
        public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_purchase, parent, false);
            PurchaseViewHolder viewHolder = new PurchaseViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {
            holder.bindPurchase(purchaseList.get(position));
        }

        @Override
        public int getItemCount() {
            return purchaseList.size();
        }


}