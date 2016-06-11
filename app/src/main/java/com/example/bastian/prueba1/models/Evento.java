package com.example.bastian.prueba1.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by bastian on 27-05-16.
 */
public class Evento implements Serializable {
    /*id_evento            int not null auto_increment,
    id_lugar             int not null,
    id_tipo              int not null,
    id_usuario           int not null,
    titulo_evento        varchar(50),
    inicio_evento        time,
    fin_evento           time,
    fecha_evento         date,
    descripcion_evento   text,*/
    private int id;
    private String titulo;
    private String inicio;
    private String fin;
    private String fecha;
    private String descripcion;
    private int idLugar;
    private int idTipo;
    private int idUsuario;

    public Evento(){}

    public Evento(int id,String titulo,String inicio,String fin,String fecha,String descripcion,
                       int lugar,int tipo,int usuario){
        this.setId(id);
        this.setTitulo(titulo);
        this.setInicio(inicio);
        this.setFin(fin);
        this.setFecha(fecha);
        this.setDescripcion(descripcion);
        this.setIdLugar(lugar);
        this.setIdTipo(tipo);
        this.setIdUsuario(usuario);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
