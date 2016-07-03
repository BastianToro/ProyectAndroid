package com.example.bastian.prueba1.controllers.Puts;

import android.os.AsyncTask;
import android.util.Log;

import com.example.bastian.prueba1.views.perfilUsuario;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bastian on 03-07-16.
 */
public class DeshabilitarPut extends AsyncTask<String, Void, String> {
    private perfilUsuario activity;

    public DeshabilitarPut(perfilUsuario activity){
        this.activity = activity;
    }
    @Override
    protected String doInBackground(String... parametros) {
        try {
             
            String json = parametros[1];
            URL u = new URL(parametros[0]);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setFixedLengthStreamingMode(parametros[1].getBytes().length);
            //connection.setRequestMethod("PUT");
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            out.write(parametros[1]);
            out.close();
            return "OK";
        } catch (Exception e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return "ERROR";
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("OK")) {
            activity.mensajeExito();
        } else {
            activity.mensajeFracaso();
        }
    }
}
