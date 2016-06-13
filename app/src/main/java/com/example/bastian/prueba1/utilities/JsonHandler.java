package com.example.bastian.prueba1.utilities;

import android.util.Log;

import com.example.bastian.prueba1.models.Evento;
import com.example.bastian.prueba1.models.Lugar;
import com.example.bastian.prueba1.models.Tipo;
import com.example.bastian.prueba1.models.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by bastian on 29-05-16.
 */
public class JsonHandler {

    public String [] getMailPass(String usuario) {
        try{
            JSONArray ja = new JSONArray(usuario);
            String[] result = new String[ja.length()];
            String user;
            for(int i=0; i<ja.length();i++){
                JSONObject row = ja.getJSONObject(i);
                user =" " + row.getString("correoUsuario")+" "+row.getString("contrasenhaUsuario");
                result[i]=user;
            }
            return result;

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());
        }
        return null;

    }// getMailPass(String usuarios)


    public JSONObject setUsuario(Usuario usuario) {
        // build jsonObject
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("administrador", usuario.isEsadministrador());
            jsonObject.accumulate("apellidoUsuario", usuario.getApellido());
            jsonObject.accumulate("carreraUsuario",usuario.getCarrera());
            jsonObject.accumulate("contrasenhaUsuario",usuario.getPass());
            jsonObject.accumulate("correoUsuario",usuario.getCorreo());
            jsonObject.accumulate("idTipoEstado",usuario.getIdEstado());
            jsonObject.accumulate("idUsuario",usuario.getId());
            jsonObject.accumulate("nombreUsuario",usuario.getNombre());
            return jsonObject;

        }catch(JSONException je){
            Log.e("ERROR",this.getClass().toString()+ " - "+ je.getMessage());
        }
        return null;
    }

    public JSONObject setUsuarioEvento(int idEvento, int idUsuario){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("idEvento", idEvento);
<<<<<<< HEAD
            //jsonObject.accumulate("idEventoUsuario",tam);
=======
//            jsonObject.accumulate("idEventoUsuario",tam);
>>>>>>> ce38cd3f367dc08895ea991f2a7e9583497c59b3
            jsonObject.accumulate("idUsuario", idUsuario);

            return jsonObject;

        }catch(JSONException je){
            Log.e("ERROR",this.getClass().toString()+ " - "+ je.getMessage());
        }
        return null;
    }

    public JSONObject setEvento(Evento evento){
        JSONObject jsonObject = new JSONObject();
        String horainicio = parsearFecha(evento.getFecha(),evento.getInicio());
        String horafinal = parsearFecha(evento.getFecha(),evento.getFin());
        try{
            jsonObject.accumulate("descripcionEvento",evento.getDescripcion());
            jsonObject.accumulate("finEvento",horafinal);
            jsonObject.accumulate("idEvento",evento.getId());
            jsonObject.accumulate("idLugar",evento.getIdLugar());
            jsonObject.accumulate("idTipo",evento.getIdTipo());
            jsonObject.accumulate("idUsuario",evento.getIdUsuario());
            jsonObject.accumulate("inicioEvento",horainicio);
            jsonObject.accumulate("tituloEvento",evento.getTitulo());
            return jsonObject;
        }catch(JSONException je){
            Log.e("ERROR",this.getClass().toString()+ " - "+ je.getMessage());
        }
        return null;
    }

    public String parsearFecha(String fecha,String hora) {
        //SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        String str = fecha+"T"+hora+"-03:00";
        //Date date = (Date) sf.parse(str);
        return str;
    }

    public String[] getCorreos(String json) {
        try {
            JSONArray ja = new JSONArray(json);
            String[] correos = new String[ja.length()];
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                correos[i]=row.getString("correoUsuario");
            }
            return correos;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
            return null;
        }
    }

    //Método para obtener el email de un usuario según su correo
    public int getIdUsuario(String usuario, String correo){
        try{
            JSONArray ja = new JSONArray(usuario);
            int idUser;
            for(int i=0; i<ja.length();i++){
                JSONObject row = ja.getJSONObject(i);
                if(row.getString("correoUsuario").equals(correo)) {
                    idUser = row.getInt("idUsuario");
                    return idUser;
                }
            }

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());

        }return 0;

    } // fin getIdUsuario

    public int contadorIdEventos(String eventoUsuario,int idUsuario){
        try{
            JSONArray ja = new JSONArray(eventoUsuario);
            int cont = 0;
            for(int i=0; i<ja.length();i++){
                JSONObject row = ja.getJSONObject(i);
                if(row.getInt("idUsuario") == idUsuario){
                    cont++;
                }
            }
            return cont;

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());
        }return 0;
    }
    public int[] getIdEventos(String eventoUsuario, int idUsuario, int tam){
        try{
            JSONArray ja = new JSONArray(eventoUsuario);
            int[] idEventos = new int[tam];
            int cont =0;
            for(int i=0; i<ja.length();i++){
                JSONObject row = ja.getJSONObject(i);
                if(row.getInt("idUsuario") == idUsuario){
                    idEventos[cont]= row.getInt("idEvento");
                    cont++;
                }
            }
            return idEventos;

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());
        }return null;
    }

    public Evento[] getEventos(String Eventos,int[] idEventos) {
        try{
            JSONArray ja = new JSONArray(Eventos);
            Evento[] eventos = new Evento[idEventos.length];
            int cont = 0;
            for(int i=0; i<ja.length();i++){
                JSONObject row = ja.getJSONObject(i);
                Evento event = new Evento();
                if(buscarElemento(row.getInt("idEvento"),idEventos)){
                    event.setId(row.getInt("idEvento"));
                    event.setTitulo(row.getString("tituloEvento"));
                    event.setInicio(row.getString("inicioEvento"));
                    event.setDescripcion(row.getString("descripcionEvento"));
                    eventos[cont]=event;
                    cont++;
                }
            }
            return eventos;

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());
        }
        return null;
    }

    public boolean buscarElemento(int x,int[] y){
        for(int i=0; i < y.length;i++){
            if(y[i] == x){
                return true;
            }
        }
        return false;
    }

    public Lugar[] getLugares(String json){
        try {
            JSONArray ja = new JSONArray(json);
            Lugar[] lugar = new Lugar[ja.length()];
            int largo = ja.length()-1;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(largo);
                Lugar aux = new Lugar();
                aux.setId(row.getInt("idLugar"));
                aux.setLatitud(row.getDouble("latitud"));
                aux.setLongitud(row.getDouble("longitud"));
                aux.setNombre(row.getString("nombreLugar"));
                lugar[i] = aux;
                largo--;
            }
            return lugar;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
            return null;
        }
    }

    public Usuario getUsuario(String usuario, String correo){
        try{
            JSONArray ja = new JSONArray(usuario);
            Usuario user = new Usuario();
            for(int i=0; i<ja.length();i++){
                JSONObject row = ja.getJSONObject(i);
                if(row.getString("correoUsuario").equals(correo)) {
                    user.setId(row.getInt("idUsuario"));
                    user.setEsadministrador(row.getBoolean("administrador"));
                    user.setApellido(row.getString("apellidoUsuario"));
                    user.setCarrera(row.getString("carreraUsuario"));
                    user.setPass(row.getString("contrasenhaUsuario"));
                    user.setCorreo(row.getString("correoUsuario"));
                    user.setIdEstado(row.getInt("idTipoEstado"));
                    user.setNombre(row.getString("nombreUsuario"));
                    return user;
                }
            }

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());

        }return null;

    } // fin getUsuario

    public Evento[] getEventosdeLugares(String json,Lugar[] lugares){
        try {
            JSONArray ja = new JSONArray(json);
            Evento[] evento = new Evento[ja.length()];
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                Evento aux = new Evento();
                if(buscareventosenlugares(row.getInt("idLugar"),lugares)) {
                    aux.setId(row.getInt("idEvento"));
                    aux.setTitulo(row.getString("tituloEvento"));
                    aux.setInicio(row.getString("inicioEvento"));
                    aux.setFin(row.getString("finEvento"));
                    aux.setDescripcion(row.getString("descripcionEvento"));
                    aux.setIdLugar(row.getInt("idLugar"));
                    aux.setIdTipo(row.getInt("idTipo"));
                    aux.setIdUsuario(row.getInt("idUsuario"));
                    evento[i] = aux;
                }
            }
            return evento;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } return null;
    }

    public boolean buscareventosenlugares(int idEvento,Lugar[] lugar){
        int tamLugar = lugar.length;
        for(int i = 0; i < tamLugar;i++){
            if(lugar[i].getId() == idEvento){
                return true;
            }
        }
        return false;
    }

    public Lugar getLugarEvento(String json,int idLugar){
        try{
            JSONArray ja = new JSONArray(json);
            Lugar aux = new Lugar();
            for(int i=0; i<ja.length();i++){
                JSONObject row = ja.getJSONObject(i);
                if(row.getInt("idLugar") == idLugar) {
                    aux.setId(row.getInt("idLugar"));
                    aux.setLatitud(row.getDouble("latitud"));
                    aux.setLongitud(row.getDouble("longitud"));
                    aux.setNombre(row.getString("nombreLugar"));;
                    return aux;
                }
            }

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());

        }return null;

    }

    public Tipo getTipoEvento(String json, int idTipo){
        try{
            JSONArray ja = new JSONArray(json);
            Tipo aux = new Tipo();
            for(int i=0; i<ja.length();i++){
                JSONObject row = ja.getJSONObject(i);
                if(row.getInt("idTipo") == idTipo) {
                    aux.setId(row.getInt("idTipo"));
                    aux.setTipo(row.getString("tipoEvento"));
                    aux.setDescripcion(row.getString("descripcionTipo"));;
                    return aux;
                }
            }

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());

        }return null;
    }

    public Tipo[] getTipos(String json){
        try {
            JSONArray ja = new JSONArray(json);
            Tipo[] tipo = new Tipo[ja.length()];
            int largo = ja.length()-1;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(largo);
                Tipo aux = new Tipo();
                aux.setId(row.getInt("idTipo"));
                aux.setTipo(row.getString("tipoEvento"));
                aux.setDescripcion(row.getString("descripcionTipo"));
                tipo[i] = aux;
                largo--;
            }
            return tipo;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
            return null;
        }
    }

    public int getContadorEU(String json){
        try{
            JSONArray ja = new JSONArray(json);

            return ja.length()+1;

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());

        }return 0;
    }

}
