/*
 * Realizado por: Samuel Bautista Sanchez
 * DNI: 20227866X
 * Asignatura: Desarrollo de Aplicaciones Multiplataforma
 * */

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

public class Detalles extends AppCompatActivity {

    private AppBarLayout app_bar;
    private EditText marca;
    private EditText modelo;
    private EditText precio;
    private EditText descripcion;
    private FloatingActionButton fab;
    Boolean modificar = false;

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

        marca = (EditText) findViewById(R.id.edtMarca);
        marca.setText(MainActivity.VehiculosDetalles.getMarca());
        marca.setEnabled(false);

        modelo = (EditText) findViewById(R.id.edtModelo);
        modelo.setText(MainActivity.VehiculosDetalles.getModelo());
        modelo.setEnabled(false);

        precio = (EditText) findViewById(R.id.edtPrecio);
        precio.setText(Float.toString(MainActivity.VehiculosDetalles.getPrecio()));
        precio.setEnabled(false);

        descripcion = (EditText) findViewById(R.id.edtDescripcion);
        descripcion.setText(MainActivity.VehiculosDetalles.getDescripcion());
        descripcion.setEnabled(false);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_form);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.databaseAccess.AbrirConexion();
                MainActivity.databaseAccess.modificarCoche(MainActivity.VehiculosDetalles.getIdNuevo(), marca.getText().toString(), modelo.getText().toString(), Float.parseFloat(precio.getText().toString()), descripcion.getText().toString());
                MainActivity.databaseAccess.CerrarConexcion();
                finish();


                fab.setImageResource(R.drawable.ic_form);
                descripcion.setEnabled(false);
                precio.setEnabled(false);
                modelo.setEnabled(false);
                marca.setEnabled(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalles, menu);
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

            fab.setImageResource(R.drawable.ic_add_black_24dp);
            marca.setEnabled(true);
            modelo.setEnabled(true);
            precio.setEnabled(true);
            descripcion.setEnabled(true);
            return true;
        }
       if (id == R.id.menuBorrar) {

           MainActivity.databaseAccess.AbrirConexion();
           MainActivity.databaseAccess.borrarCoche(MainActivity.VehiculosDetalles.getIdNuevo());
           MainActivity.databaseAccess.CerrarConexcion();
           finish();
           return true;
       }
       if (id == R.id.menuInforme) {

           Intent intent = new Intent(this, extras_selecionado.class);
           startActivity(intent);
           return true;
       }
        return super.onOptionsItemSelected(item);
    }
}
