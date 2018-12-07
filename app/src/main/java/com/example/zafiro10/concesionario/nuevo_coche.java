package com.example.zafiro10.concesionario;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class nuevo_coche extends AppCompatActivity {

    ImageView imagenCamara;
    TextView textoCambiarImagen;
    EditText marcaNueva;
    EditText modeloNuevo;
    EditText precioNuevo;
    EditText descripcionNuevo;
    byte[] imagenbyte;
    private final String CARPETA_RAIZ="misImagenesPrueba/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";
    Bitmap bitmap;
    private final int COD_CAMARA=10;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_coche);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Coche nuevo");
        setSupportActionBar(toolbar);

        marcaNueva = (EditText)findViewById(R.id.edtMarcaNueva);
        modeloNuevo = (EditText)findViewById(R.id.edtModeloNueva);
        precioNuevo = (EditText)findViewById(R.id.edtPrecioNueva);
        descripcionNuevo = (EditText)findViewById(R.id.edtDescripcionNueva);

        textoCambiarImagen = (TextView)findViewById(R.id.txvCambiar);
        textoCambiarImagen.setAlpha(0.0f);

        imagenCamara = (ImageView) findViewById(R.id.imgCamara);
        imagenCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Se abrira la camara", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                tomarfoto();
                textoCambiarImagen.setAlpha(1.0f);


            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);
                imagenbyte = stream.toByteArray();



                MainActivity.databaseAccess.AbrirConexion();
                MainActivity.databaseAccess.insertarCocheNuevo(marcaNueva.getText().toString(), modeloNuevo.getText().toString(), imagenbyte, Float.parseFloat(precioNuevo.getText().toString()), descripcionNuevo.getText().toString());
                MainActivity.databaseAccess.CerrarConexcion();
                finish();

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    private void tomarfoto() {

        File fileImagen=new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean isCreate = fileImagen.exists();
        String nombre = "";

        if(isCreate == false){
            isCreate = fileImagen.mkdirs();

        }
        if(isCreate == true){
            nombre = (System.currentTimeMillis()/100)+".jpg";

        }

        path=Environment.getExternalStorageDirectory() + File.separator+RUTA_IMAGEN+File.separator+nombre;

        File imagen=new File(path);
        Intent intentCamara=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamara.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        startActivityForResult(intentCamara, COD_CAMARA);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == RESULT_OK)){

            if(COD_CAMARA == 10){

                MediaScannerConnection.scanFile(this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String s, Uri uri) {
                        Log.i("RUTA DE ALMACENAMIENTO", "Path: "+path);

                    }
                });

                bitmap = BitmapFactory.decodeFile(path);
                imagenCamara.setImageBitmap(bitmap);


            }

        }
    }



}