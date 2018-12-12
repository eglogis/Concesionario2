/*
 * Realizado por: Samuel Bautista Sanchez
 * DNI: 20227866X
 * Asignatura: Desarrollo de Aplicaciones Multiplataforma
 * */

package com.example.zafiro10.concesionario;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class resumen_extras extends AppCompatActivity {

    public TextView extrasSelct;
    public String aux = "";
    public TextView nombre;
    public TextView precioextra;
    public static String mensaje = "HOLA" + "soy samuel";

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
        precioextra.setText(Float.toString(extras_selecionado.sumaprecio + MainActivity.VehiculosDetalles.getPrecio()));

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
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(resumen_extras.this);
                View mView = getLayoutInflater().inflate(R.layout.dialogo_personalizado, null);
                final EditText mNombre = (EditText) mView.findViewById(R.id.edtNombre);
                mNombre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(mNombre.getText().toString().equals("- Introduce nombre -")) {
                            mNombre.setText("");
                        }

                    }
                });
                final EditText mApellidos = mView.findViewById(R.id.edtApellido);
                mApellidos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(mApellidos.getText().toString().equals("- Introduce Apellido -")) {
                            mApellidos.setText("");
                        }

                    }
                });

                final EditText mEmail = (EditText) mView.findViewById(R.id.edtEmail);
                mEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(mEmail.getText().toString().equals("- Introduce Email -")) {
                            mEmail.setText("");
                        }

                    }
                });
                final EditText mTelefono = (EditText) mView.findViewById(R.id.edtTelefono);
                mTelefono.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mTelefono.getText().toString().equals("- Introduce telefono -")) {
                            mTelefono.setText("");
                        }
                    }
                });
                final EditText mDireccion = (EditText) mView.findViewById(R.id.edtDireccion);
                mDireccion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(mDireccion.getText().toString().equals("- Introduce direccion -")) {
                            mDireccion.setText("");
                        }

                    }
                });
                final EditText mPoblacion = (EditText) mView.findViewById(R.id.edtPoblacion);
                mPoblacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(mPoblacion.getText().toString().equals("- Introduce poblacion -")) {
                            mPoblacion.setText("");
                        }

                    }
                });

                final Button mAceptar = (Button) mView.findViewById(R.id.btnAceptar);


                mAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if(mNombre.getText().toString().isEmpty() || mApellidos.getText().toString().isEmpty() || mEmail.getText().toString().isEmpty() || mDireccion.getText().toString().isEmpty() || mPoblacion.getText().toString().isEmpty() || mTelefono.getText().toString().isEmpty()){

                            Toast toast1 = Toast.makeText(getApplicationContext(), "Es obligatorio que ponga todos los campos", Toast.LENGTH_SHORT);
                            toast1.show();
                        }
                        else {

                            Snackbar.make(view, "Se te redigira a una aplicacion de correo", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();


                            Intent emailIntent = new Intent(Intent.ACTION_SEND);
                            emailIntent.setData(Uri.parse("mailto:"));
                            //Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","samu6333@gmail.com", null));
                            emailIntent.setType("text/plain");
                            emailIntent.putExtra(Intent.EXTRA_EMAIL, "samu6333@gmail.com");
                            emailIntent.putExtra(Intent.EXTRA_CC, "");
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Formulario del cliente con su pedido");

                            emailIntent.putExtra(Intent.EXTRA_TEXT, "-------- NOMBRE --------" + "\n" + "\n" + mNombre.getText().toString() + " " + mApellidos.getText().toString() + "\n" + "\n" + "-------- TELEFONO --------" + "\n" + "\n" + mTelefono.getText().toString() + "\n" + "\n" + "-------- CORREO ELECTRONICO --------" + "\n" + "\n" + mEmail.getText().toString() + "\n" + "\n" + "-------- DIRECCION --------" + "\n" + "\n" + mDireccion.getText().toString() +
                                    "\n" + "\n" + "-------- POBLACION --------" + "\n" + "\n" + mPoblacion.getText().toString() + "\n" + "\n" + "-------- FECHA --------" + "\n" + "\n" + Calendar.getInstance().getTime() + "\n" + "\n" + "-------- NOMBRE DEL COCHE --------" + " \n " + "\n" + MainActivity.VehiculosDetalles.getMarca() + " " + MainActivity.VehiculosDetalles.getModelo() + "\n" + "\n" + "-------- EXTRAS --------" + " \n " + "\n" + aux + "\n" + "\n" + "-------- PRECIO --------" + " \n " + "\n" + precioextra.getText().toString() + "€");

                            startActivity(Intent.createChooser(emailIntent, "Formulario del cliente con su pedido"));
                            //finish();
                        }
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

}
