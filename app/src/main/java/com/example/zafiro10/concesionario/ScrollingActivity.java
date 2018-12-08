package com.example.zafiro10.concesionario;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.MarkerOptions;

public class ScrollingActivity extends AppCompatActivity {

    private AppBarLayout app_bar;
    private EditText marca;
    private EditText modelo;
    private EditText precio;
    private EditText descripcion;
    private TextView extras;
    private String aux = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(MainActivity.VehiculosOcasionDetalles.getMarca());
        setSupportActionBar(toolbar);

        Drawable d = new BitmapDrawable(getResources(), MainActivity.VehiculosOcasionDetalles.getImagenBitmap());
        app_bar = (AppBarLayout)findViewById(R.id.app_bar);
        app_bar.setBackground(d);

        marca = (EditText)findViewById(R.id.edtMarca);
        marca.setText(MainActivity.VehiculosOcasionDetalles.getMarca());
        marca.setEnabled(false);

        modelo = (EditText)findViewById(R.id.edtModelo);
        modelo.setText(MainActivity.VehiculosOcasionDetalles.getModelo());
        modelo.setEnabled(false);

        precio = (EditText)findViewById(R.id.edtPrecio);
        precio.setText(Float.toString(MainActivity.VehiculosOcasionDetalles.getPrecio()));
        precio.setEnabled(false);

        descripcion = (EditText)findViewById(R.id.edtDescripcion);
        descripcion.setText(MainActivity.VehiculosOcasionDetalles.getDescripcion());
        descripcion.setEnabled(false);

        extras = (TextView)findViewById(R.id.txvExtras);

        MainActivity.databaseAccess.AbrirConexion();
        MainActivity.arrayExtrasOcasion = MainActivity.databaseAccess.todos_los_extras_ocasion(MainActivity.VehiculosOcasionDetalles.getIdNuevo());
        MainActivity.databaseAccess.CerrarConexcion();

        for(int i=0; i<MainActivity.arrayExtrasOcasion.size(); i++){

            aux += MainActivity.arrayExtrasOcasion.get(i).getNombre() + "\n";
        }

        extras.setText(aux);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.databaseAccess.AbrirConexion();
                MainActivity.databaseAccess.modificarCocheOcasion(MainActivity.VehiculosOcasionDetalles.getIdNuevo(), marca.getText().toString(), modelo.getText().toString(), Float.parseFloat(precio.getText().toString()), descripcion.getText().toString());
                MainActivity.databaseAccess.CerrarConexcion();
                finish();



                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coche_ocasion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuModificar) {

            marca.setEnabled(true);
            modelo.setEnabled(true);
            precio.setEnabled(true);
            descripcion.setEnabled(true);
            return true;
        }
        if (id == R.id.menuBorrar) {

            MainActivity.databaseAccess.AbrirConexion();
            MainActivity.databaseAccess.borrarCocheOcasion(MainActivity.VehiculosOcasionDetalles.getIdNuevo());
            MainActivity.databaseAccess.CerrarConexcion();
            finish();
            return true;
        }
        if (id == R.id.menuAdquisicion) {


            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
