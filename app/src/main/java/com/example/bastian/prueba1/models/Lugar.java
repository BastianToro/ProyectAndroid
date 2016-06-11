package com.example.bastian.prueba1.models;

import java.io.Serializable;

/**
 * Created by bastian on 27-05-16.
 */
public class Lugar implements Serializable {
    /*
    * id_lugar             int not null auto_increment,
   nombre_lugar         varchar(50),
   latitud         float,
   longitud         float,*/
    private int id;
    private String nombre;
    private double latitud;
    private double longitud;

    public void Lugar(int id,String nombre, double latitud, double longitud){
        this.setId(id);
        this.setNombre(nombre);
        this.setLatitud(latitud);
        this.setLongitud(longitud);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
