package com.example.bastian.prueba1.controllers.Gets;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bastian.prueba1.MainActivity;
import com.example.bastian.prueba1.models.Evento;

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
 * Created by bastian on 26-05-16.
 */
public class EventosGet extends AsyncTask<String, Void, String> {

    private Evento evento[];
    private MainActivity activity;

    public EventosGet(MainActivity activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
    }

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

    public void getEventos(String json){
        try {
            JSONArray ja = new JSONArray(json);
            evento = new Evento[ja.length()];
            int largo = ja.length()-1;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(largo);
                Evento aux = new Evento();
                aux.setId(row.getInt("idEvento"));
                aux.setTitulo(row.getString("tituloEvento"));
                aux.setInicio(row.getString("inicioEvento"));
                aux.setFin(row.getString("finEvento"));
                aux.setDescripcion(row.getString("descripcionEvento"));
                aux.setIdLugar(row.getInt("idLugar"));
                aux.setIdTipo(row.getInt("idTipo"));
                aux.setIdUsuario(row.getInt("idUsuario"));
                evento[i]=aux;
                largo--;
            }
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }

    }

    @Override
    protected void onPostExecute(String result) {
        getEventos(result);
        activity.listarEventos(evento);
    }
}
