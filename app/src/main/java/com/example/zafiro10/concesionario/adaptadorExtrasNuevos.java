package com.example.zafiro10.concesionario;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adaptadorExtrasNuevos extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Extras> items;

    public adaptadorExtrasNuevos(Activity activity, ArrayList<Extras> items) {
        this.activity = activity;
        this.items = items;
    }











    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Extras> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.layoutextras, null);
        }

        Extras dir = items.get(position);

        TextView nombre = (TextView)v.findViewById(R.id.txvNombre);
        nombre.setText(dir.getNombre());
        TextView descripcion = (TextView)v.findViewById(R.id.txvDescripcion);
        descripcion.setText(dir.getDescripcion());
        TextView precio = (TextView)v.findViewById(R.id.txvPrecio);
        precio.setText(Float.toString(dir.getPrecio()) + "€");

        return v;
    }
}
