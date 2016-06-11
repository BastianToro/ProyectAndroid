package com.example.bastian.prueba1.models;

import java.io.Serializable;

/**
 * Created by bastian on 27-05-16.
 */
public class Tipo implements Serializable {
    /*    id_tipo              int not null auto_increment,
   tipo_evento          varchar(50),
   descripcion          text,*/
    private int id;
    private String tipo;
    private String descripcion;

    public void Tipo(int id,String tipo,String descripcion){
        this.setId(id);
        this.setTipo(tipo);
        this.setDescripcion(descripcion);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
