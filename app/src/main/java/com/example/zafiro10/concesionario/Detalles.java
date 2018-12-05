package com.example.zafiro10.concesionario;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Detalles extends AppCompatActivity {

    private AppBarLayout app_bar;
    private TextView marca;
    private TextView modelo;
    private TextView precio;
    private TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(MainActivity.VehiculosDetalles.getMarca());

        setSupportActionBar(toolbar);

        Drawable d = new BitmapDrawable(getResources(), MainActivity.VehiculosDetalles.getImagenBitmap());

        app_bar = (AppBarLayout)findViewById(R.id.app_bar);
        app_bar.setBackground(d);

        marca = (TextView)findViewById(R.id.txvMarca);
        marca.setText(MainActivity.VehiculosDetalles.getMarca());

        modelo = (TextView)findViewById(R.id.txvModelo);
        modelo.setText(MainActivity.VehiculosDetalles.getModelo());

        precio = (TextView)findViewById(R.id.txvPrecio);
        precio.setText(Float.toString(MainActivity.VehiculosDetalles.getPrecio()) + "€");

        descripcion = (TextView)findViewById(R.id.txvDescripcion);
        descripcion.setText(MainActivity.VehiculosDetalles.getDescripcion());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalles, menu);
        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}
