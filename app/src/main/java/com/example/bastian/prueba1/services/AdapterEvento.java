package com.example.bastian.prueba1.services;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.models.Evento;

/**
 * Created by bastian on 27-05-16.
 */
public class AdapterEvento extends BaseAdapter{

    Activity activity;
    Evento evento[];


    public AdapterEvento(Activity activity,Evento[] evento){
        this.activity = activity;
        this.evento = evento;
    }

    @Override
    public int getCount() {
        return evento.length;
    }

    @Override
    public String getItem(int i) {
        return evento[i].toString();
    }

    @Override
    public long getItemId(int i) {
        return evento[i].getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(view == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.itemlista,null);
        }
        Evento e = evento[i];

        TextView textView = (TextView) v.findViewById(R.id.textview_item);
        textView.setText(e.getTitulo());

        return v;

        /*LayoutInflater inf=activity.getLayoutInflater();
        View v=inf.inflate(R.layout.itemlista, null,true);

        Evento e = evento[i];

        TextView textView = (TextView) v.findViewById(R.id.textview_item);
        textView.setText(e.getTitulo());*/

        //return v;
    }
}
