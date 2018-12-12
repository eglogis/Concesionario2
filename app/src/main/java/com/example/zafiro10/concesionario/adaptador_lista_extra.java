/*
 * Realizado por: Samuel Bautista Sanchez
 * DNI: 20227866X
 * Asignatura: Desarrollo de Aplicaciones Multiplataforma
 * */

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

public class adaptador_lista_extra extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Extras> items;
    LayoutInflater inflater;

    public adaptador_lista_extra(Activity activity) {

        this.activity = activity;
    }

    public adaptador_lista_extra(Activity activity, ArrayList<Extras> items) {
        this.activity = activity;
        this.items = items;
        inflater        = activity.getLayoutInflater();
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

        ViewHolder holder = null;

        if (convertView == null) {

            /*LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.layout_extras_seleccionados, null);*/

            convertView = inflater.inflate(R.layout.layout_extras_seleccionados, parent, false);

            holder = new ViewHolder();
            holder.nombre = (TextView)convertView.findViewById(R.id.nombre);
            holder.precio = (TextView)convertView.findViewById(R.id.precio);
            holder.descripcion = (TextView)convertView.findViewById(R.id.descripcion);
            holder.ivCheckBox = (ImageView)convertView.findViewById(R.id.imgcheck);

            convertView.setTag(holder);

        }else{

            holder = (ViewHolder)convertView.getTag();
        }


            Extras dir = items.get(position);

            holder.nombre.setText(dir.getNombre());

            //TextView precio = (TextView)v.findViewById(R.id.txvprecio);
            holder.precio.setText(Float.toString(dir.getPrecio()));

            //TextView descripcion = (TextView)v.findViewById(R.id.txvdescripcion);
            holder.descripcion.setText(dir.getDescripcion());

            //ImageView check = (ImageView)v.findViewById(R.id.imgcheck);

            if(dir.getSelect()){

                holder.ivCheckBox.setImageResource(R.drawable.ic_check_box_black_24dp);

            }
            else{

                holder.ivCheckBox.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
            }

        return convertView;
    }

    public void updateRecords(ArrayList<Extras> extra){
        this.items = extra;
        notifyDataSetChanged();
    }

    class ViewHolder{

        TextView nombre;
        TextView precio;
        TextView descripcion;
        ImageView ivCheckBox;

    }

}
