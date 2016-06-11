package com.example.bastian.prueba1.views;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.controllers.Gets.HttpGet;
import com.example.bastian.prueba1.utilities.JsonHandler;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /*public void text_msg(View view){
        Intent intento = new Intent(this,recuperarContrasena.class);
        startActivity(intento);
    }*/

    private BroadcastReceiver br = null;
    private final String URL_GET = "http://10.0.2.2:8080/EventoUsachJava/usuarios";

    public void onButtonClick(View v) throws ExecutionException, InterruptedException {
        if(v.getId() == R.id.buttonLogin) {
            HttpGet c=new HttpGet(this.getApplicationContext());
            c.execute(URL_GET);
            Bundle arguments = new Bundle();
            String  item= null;
            try{
                item = c.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e){
                e.printStackTrace();
            }
            EditText correo = (EditText) findViewById(R.id.editTextCorreo);
            String corr = correo.getText().toString();
            EditText contrasena = (EditText) findViewById(R.id.editTextPass);
            String pass = contrasena.getText().toString();
            String usuario = " "+ corr + " "+ pass;
            Log.d("Deb2",item);
            JsonHandler jh = new JsonHandler();
            String[] userList = jh.getMailPass(item);
            int j=0;
            while(j<userList.length){
                if(userList[j].equals(usuario)){
                    j=userList.length;
                    Intent i = new Intent(this, perfilUsuario.class);
                    startActivity(i);
                }
                else{
                    j++;
                }
            }
        }
    }
}
