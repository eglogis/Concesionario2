/*
 * Realizado por: Samuel Bautista Sanchez
 * DNI: 20227866X
 * Asignatura: Desarrollo de Aplicaciones Multiplataforma
 * */

package com.example.zafiro10.concesionario;

public class Extras {

    private int id_extra;
    private String nombre;
    private String descripcion;
    private float precio;
    private Boolean isSelect;

    public Extras(Boolean isSelect, int id_extra, String nombre, String descripcion, float precio) {

        this.isSelect = isSelect;
        this.id_extra = id_extra;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }


    public Extras(int id_extra, String nombre, String descripcion, float precio) {
        this.id_extra = id_extra;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Extras(String nombre, String descripcion, float precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Extras(String nombre) {
        this.nombre = nombre;
    }

    public int getId_extra() {
        return id_extra;
    }

    public void setId_extra(int id_extra) {
        this.id_extra = id_extra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }
}
