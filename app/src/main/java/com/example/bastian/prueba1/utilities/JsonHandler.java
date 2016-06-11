package com.example.bastian.prueba1.utilities;

import android.util.Log;

import com.example.bastian.prueba1.models.Evento;
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
                if(row.get("correoUsuario").equals(correo)) {
                    idUser = row.getInt("idUsuario");
                    return idUser;
                }
            }

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());
        }return 0;

    } // fin getIdUsuario

    public int[] getIdEventos(String eventoUsuario, int idUsuario){
        try{
            JSONArray ja = new JSONArray(eventoUsuario);
            int[] idEventos = new int[ja.length()];
            for(int i=0; i<ja.length();i++){
                JSONObject row = ja.getJSONObject(i);
                if(row.getInt("idUsuario") == idUsuario){
                    idEventos[i]= row.getInt("idEvento");
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
            Evento[] eventos = new Evento[ja.length()];
            Evento event = new Evento();
            for(int i=0; i<idEventos.length;i++){
                JSONObject row = ja.getJSONObject(i);
                if(idEventos[i]==row.getInt("idEvento")){
                    event.setTitulo(row.getString("tituloEvento"));
                    event.setInicio(row.getString("inicioEvento"));
                    event.setDescripcion(row.getString("descripcionEvento"));
                    //event= +"\n"+row.get("inicioEvento")+"\n"+;
                    eventos[i]=event;
                }
            }
            return eventos;

        }catch(JSONException e){
            Log.e("Error",this.getClass().toString());
        }
        return null;
    }
}
