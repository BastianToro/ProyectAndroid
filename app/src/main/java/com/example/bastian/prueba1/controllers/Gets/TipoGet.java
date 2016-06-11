package com.example.bastian.prueba1.controllers.Gets;

import android.os.AsyncTask;
import android.util.Log;

import com.example.bastian.prueba1.models.Tipo;
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
 * Created by bastian on 09-06-16.
 */
public class TipoGet extends AsyncTask<String, Void, String> {

    private eventoNuevo activity;
    private Tipo tipo[];

    public TipoGet(eventoNuevo activity){
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

    public void getTipo(String json){
        try {
            JSONArray ja = new JSONArray(json);
            tipo = new Tipo[ja.length()];
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
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
    }
    @Override
    protected void onPostExecute(String result) {
        getTipo(result);
        activity.obteneridTipo(tipo);
    }
}
