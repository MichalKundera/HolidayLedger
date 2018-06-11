package com.mehow.holidayledger.model;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mehow.holidayledger.R;
import com.mehow.holidayledger.model.entities.Currency;
import com.mehow.holidayledger.model.entities.Person;
import com.mehow.holidayledger.model.entities.enums.CurrencyShortcut;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonViewAdapter extends RecyclerView.Adapter<PersonViewAdapter.PersonViewHolder> {

    private List<Person> personList;
    private Context mContext;
    Resources resources;

    public PersonViewAdapter(Context context, List<Person> persons, Resources resources)
    {
        personList=persons;
        mContext=context;
        this.resources=resources;
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.nameText)TextView nameText;
        @BindView(R.id.personImage)ImageView personImage;

        public PersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext=itemView.getContext();
        }

        public void bindPerson(Person person) {
            nameText.setText(person.getName());

            personImage.setImageResource(bindImage(person.getName().toLowerCase()+person.getId()));
        }

        private int bindImage(String shortcut)
        {
            switch (shortcut)
            {
                case "artur1": {return R.drawable.artur1;}
                case "marek2": {return R.drawable.marek2;}
                case "ania3": {return R.drawable.ania3;}
                case "kasia4": {return R.drawable.kasia4;}
            }
            return 0;
        }
    }



    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_person, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.bindPerson(personList.get(position));
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }



}
