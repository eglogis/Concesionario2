package com.example.zafiro10.concesionario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ConectorBaseDatos{

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static ConectorBaseDatos instance;

    private ConectorBaseDatos(Context context) {
        this.openHelper = new ConectorOpenHelper(context);
    }

    public static ConectorBaseDatos getInstance(Context context) {
        if (instance == null) {
            instance = new ConectorBaseDatos(context);
        }
        return instance;
    }

    public void AbrirConexion() {
        this.database = openHelper.getWritableDatabase();
    }

    public void CerrarConexcion() {
        if (database != null) {
            this.database.close();
        }
    }

    //METODO PARA RECUPERAR TODOS LOS COCHES NUEVO DE LA BASE DE DATOS
    ArrayList<Vehiculos> todas_las_provincias()
    {
        Cursor c;
        //Array donde se devuelven todos los coches
        ArrayList<Vehiculos> arrayVehiculos = new ArrayList<Vehiculos>();


        //definimos la sentencia sql en una cadena
        // String[] valores_recuperar = {"cod_provincia", "nombre_provincia", "num_habitantes"};

        //Ejecutamos la cadena
        //c = databases.query("provincia", valores_recuperar,null, null, null, null, null, null);
        c = database.rawQuery(" SELECT * FROM vehiculos_nuevos", null);

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                arrayVehiculos.add(new Vehiculos(c.getInt(0),c.getString(1),c.getString(2), c.getBlob(3), c.getFloat(4), c.getString(5)));

            } while(c.moveToNext());
        }
        //cerramos el cursor
        c.close();

        //devolvemos el array
        return arrayVehiculos;


    }//fin numero_de_libros



}
