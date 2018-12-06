package com.example.zafiro10.concesionario;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class extras_selecionado extends AppCompatActivity {

    public ArrayList<Extras> arrayExtras = new ArrayList<Extras>();
    public static ArrayList<Extras> arrayExtrasDialog = new ArrayList<Extras>();
    public adaptador_lista_extra adapterExtras;
    public ConectorBaseDatos databaseAccess;
    public ListView listView;
    public static float sumaprecio = 0;
    public TextView sumatorioprecio;
    public Toolbar toolbar;
    Boolean hola = false;
    Boolean click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras_selecionado);

        listView = (ListView)findViewById(R.id.listview);
        sumatorioprecio = (TextView)findViewById(R.id.txvsumaprecio);
        toolbar = (Toolbar)findViewById(R.id.toolbar2);
        toolbar.setTitle(MainActivity.VehiculosDetalles.getMarca());
        //setSupportActionBar(toolbar);

        databaseAccess = ConectorBaseDatos.getInstance(this);
        databaseAccess.AbrirConexion();
        arrayExtras = databaseAccess.todos_los_extras();
        databaseAccess.CerrarConexcion();

        adapterExtras = new adaptador_lista_extra(this, arrayExtras);
        listView.setAdapter(adapterExtras);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Extras extraSeleccionado = arrayExtras.get(position);

                if (!extraSeleccionado.getSelect()){
                    extraSeleccionado.setSelect(true);
                    sumaprecio = sumaprecio + extraSeleccionado.getPrecio();
                    sumatorioprecio.setText(Float.toString(sumaprecio));
                }
                else {

                    sumaprecio = sumaprecio - extraSeleccionado.getPrecio();
                    sumatorioprecio.setText(Float.toString(sumaprecio));
                    extraSeleccionado.setSelect(false);
                }

                arrayExtras.set(position,extraSeleccionado);
                adapterExtras.updateRecords(arrayExtras);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                click = !click;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                    Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                            android.R.interpolator.fast_out_slow_in);

                    view.animate()
                            .rotation(click ? 360f : 0)
                            .setInterpolator(interpolador)
                            .start();
                }

                for(int i=0; i<arrayExtras.size(); i++) {

                    if(arrayExtras.get(i).getSelect() == true){

                        arrayExtrasDialog.add(arrayExtras.get(i));

                    }
                    else{

                        arrayExtrasDialog.remove(arrayExtras.get(i));
                    }

                }
                Intent intent = new Intent(getApplicationContext(), resumen_extras.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        arrayExtrasDialog.clear();
    }
}
