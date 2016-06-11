package com.example.bastian.prueba1.controllers.Post;

import android.os.AsyncTask;
import android.util.Log;

import com.example.bastian.prueba1.views.Registro;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bastian on 05-06-16.
 */
public class RegistroPost extends AsyncTask<String, Void, String> {

    private Registro activity;

    public RegistroPost(Registro activity){this.activity = activity;}

    @Override
    protected String doInBackground(String...parametros) {
        try {
            String json = parametros[1];
            URL u = new URL(parametros[0]);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setFixedLengthStreamingMode(json.getBytes().length);
            connection.connect();
            OutputStream os = new BufferedOutputStream(connection.getOutputStream());
            os.write(json.getBytes());
            os.flush();
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
