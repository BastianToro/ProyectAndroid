package com.example.bastian.prueba1.controllers.Gets;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bastian.prueba1.models.Evento;
import com.example.bastian.prueba1.models.Usuario;
import com.example.bastian.prueba1.views.Registro;
import com.example.bastian.prueba1.views.eventoNuevo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by bastian on 04-06-16.
 */
public class ContadorGet extends AsyncTask<String, Void, String> {

    private int number;
    private Registro activity;
    private eventoNuevo eventoActivity;
    private int idActivity;

    public ContadorGet(Registro activity,int id){ this.activity = activity;
    this.idActivity = id;}
    public ContadorGet(eventoNuevo activity,int id){this.eventoActivity = activity;
    this.idActivity = id;}

    @Override
    protected String doInBackground(String... url) {
        try {
            URL u = new URL(url[0]);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            return new Scanner(connection.getInputStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (MalformedURLException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } catch (ProtocolException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } catch (IOException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }

    public int getIdActual(String json){
        try {
            JSONArray ja = new JSONArray(json);
            int largo = ja.length();
            return largo;

        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
            return -1;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(idActivity == 1){
            number = getIdActual(result);
            activity.obtenerId(number+1);
        }
        else if(idActivity == 2){
            number = getIdActual(result);
            eventoActivity.obtenerIdEvento(number+1);
        }

    }
}
