package com.example.bastian.prueba1.models;

import java.io.Serializable;

/**
 * Created by bastian on 27-05-16.
 */
public class Usuario implements Serializable {
    /* id_usuario           int not null auto_increment,
   id_tipoestado        int not null,
   nombre_usuario       varchar(50),
   apellido_usuario     varchar(50),
   correo_usuario       varchar(50),
   carrera_usuario      varchar(50),
   contrasenha_usuario  varchar(50),
   esadministrador_     bool,
   primary key (id_usuario)*/
    private int id;
    private int idEstado;
    private String nombre;
    private String apellido;
    private String correo;
    private String carrera;
    private String pass;
    private boolean esadministrador;

    public Usuario(int id,int idEstado,String nombre,String apellido,String correo,String carrera,
                        String pass,boolean esadministrador){
        this.setId(id);
        this.setIdEstado(idEstado);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setCarrera(carrera);
        this.setPass(pass);
        this.setEsadministrador(esadministrador);
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isEsadministrador() {
        return esadministrador;
    }

    public void setEsadministrador(boolean esadministrador) {
        this.esadministrador = esadministrador;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
}
