/*
 * Realizado por: Samuel Bautista Sanchez
 * DNI: 20227866X
 * Asignatura: Desarrollo de Aplicaciones Multiplataforma
 * */

package com.example.zafiro10.concesionario;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;

public class Vehiculos {

    private int id;
    private String marca;
    private String modelo;
    private byte[] imagen;
    private Bitmap imagenBitmap;
    private float precio;
    private String descripcion;


    public Vehiculos(int id, String marca, String modelo, byte[] imagen, float precio, String descripcion) {

        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.imagen = imagen;
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(this.imagen);
        this.imagenBitmap = BitmapFactory.decodeStream(arrayInputStream);
        //this.imagenBitmap = BitmapFactory.decodeByteArray(this.imagen,0,this.imagen.length);
        this.precio = precio;
        this.descripcion = descripcion;
    }



    public int getIdNuevo() {
        return id;
    }

    public void setIdNuevo(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagenBitmap() {
        return imagenBitmap;
    }

    public void setImagenBitmap(Bitmap imagenBitmap) {
        this.imagenBitmap = imagenBitmap;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
