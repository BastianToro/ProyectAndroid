package com.example.bastian.prueba1.controllers.Gets;

import android.os.AsyncTask;
import android.util.Log;

import com.example.bastian.prueba1.models.Usuario;
import com.example.bastian.prueba1.utilities.JsonHandler;
import com.example.bastian.prueba1.views.Registro;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by bastian on 05-06-16.
 */
public class RegistroGet extends AsyncTask<String, Void, String> {

    private Usuario user;
    private Registro activity;
    private String[] correos;

    public RegistroGet(Registro activity,Usuario user){
        this.activity = activity;
        this.user = user;
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

    @Override
    protected void onPostExecute(String result) {
        JsonHandler jh = new JsonHandler();
        correos = jh.getCorreos(result);
        if(comprobarMail()==false){
            activity.registrarUsuario();
        } else{

        }
    }

    protected boolean comprobarMail(){
        for(int i= 0;i<correos.length;i++){
            if(correos[i].equals(user.getCorreo())){
                return true;
            }
        }
        return false;
    }


}
