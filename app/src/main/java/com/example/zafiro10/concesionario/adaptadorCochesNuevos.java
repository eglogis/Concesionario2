package com.example.zafiro10.concesionario;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adaptadorCochesNuevos extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Vehiculos> items;

    public adaptadorCochesNuevos(Activity activity, ArrayList<Vehiculos> items) {
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

    public void addAll(ArrayList<Vehiculos> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.layoutcochesnuevos, null);
        }

        Vehiculos dir = items.get(i);

        ImageView imagenCoche = (ImageView) v.findViewById(R.id.imgCoche);
        imagenCoche.setImageBitmap(dir.getImagenBitmap());

        TextView marca = (TextView) v.findViewById(R.id.txvMarca);
        marca.setText(dir.getMarca());

        TextView modelo = (TextView) v.findViewById(R.id.txvModelo);
        modelo.setText(dir.getModelo());

        TextView precio = (TextView) v.findViewById(R.id.txvPrecio);
        precio.setText(Float.toString(dir.getPrecio())+ "€");

        TextView descripcion = (TextView) v.findViewById(R.id.txvDescripcion);
        descripcion.setText(dir.getDescripcion());

        return v;
    }
}
