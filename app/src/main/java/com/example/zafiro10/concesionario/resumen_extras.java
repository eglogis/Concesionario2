package com.example.zafiro10.concesionario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class resumen_extras extends AppCompatActivity {

    public TextView extrasSelct;
    public String aux = "";
    public TextView nombre;
    public TextView precioextra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_extras);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(MainActivity.VehiculosDetalles.getMarca());
        setSupportActionBar(toolbar);

        nombre = (TextView) findViewById(R.id.txvnombre);
        nombre.setText(MainActivity.VehiculosDetalles.getMarca());

        precioextra = (TextView) findViewById(R.id.txvprecio);
        precioextra.setText(Float.toString(extras_selecionado.sumaprecio));

        extrasSelct = (TextView) findViewById(R.id.extraSelect);

        for(int i=0; i<extras_selecionado.arrayExtrasDialog.size(); i++){

            //extrasSelct.setText(extras_selecionado.arrayExtrasDialog.get(i).getNombre() + "\n");

            if(extras_selecionado.arrayExtrasDialog.get(i).getSelect() == true) {
                aux += extras_selecionado.arrayExtrasDialog.get(i).getNombre() + "\n";
            }
        }

        extrasSelct.setText(aux);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
