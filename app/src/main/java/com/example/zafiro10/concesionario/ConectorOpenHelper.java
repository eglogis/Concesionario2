/*
 * Realizado por: Samuel Bautista Sanchez
 * DNI: 20227866X
 * Asignatura: Desarrollo de Aplicaciones Multiplataforma
 * */


package com.example.zafiro10.concesionario;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ConectorOpenHelper extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "concesionario.db";
    private static final int DATABASE_VERSION = 1;

    public ConectorOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
