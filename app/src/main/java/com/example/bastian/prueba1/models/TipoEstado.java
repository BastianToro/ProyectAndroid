package com.example.bastian.prueba1.models;

import java.io.Serializable;

/**
 * Created by bastian on 27-05-16.
 */
public class TipoEstado implements Serializable {
    /* id_tipoestado        int not null auto_increment,
   nombre_estado        varchar(50),
   descripcion_estado   text,*/
    private int id;
    private String nombre;
    private String descripcion;

    public void TipoEstado(int id,String nombre,String descripcion){
        this.setId(id);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
