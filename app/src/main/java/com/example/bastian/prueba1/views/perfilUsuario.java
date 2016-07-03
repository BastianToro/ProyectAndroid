package com.example.bastian.prueba1.views;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bastian.prueba1.MainActivity;
import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.controllers.Gets.HttpGet;
import com.example.bastian.prueba1.models.Evento;
import com.example.bastian.prueba1.models.Lugar;
import com.example.bastian.prueba1.models.Usuario;
import com.example.bastian.prueba1.services.AdapterEvento;
import com.example.bastian.prueba1.utilities.JsonHandler;

import java.util.concurrent.ExecutionException;

public class perfilUsuario extends AppCompatActivity {

    private BroadcastReceiver br = null;
    private String URL_GET = "http://10.0.2.2:8080/EventoUsachJava/usuarios";
    private String URL_GET1 = "http://10.0.2.2:8080/EventoUsachJava/eventosusuarios"; // Obtenemos los id de los eventos asociados a un usuario.
    private String username;
    private Usuario usuario; //usuario actual
    private int idPosicion;
    private ListView listaSugerencias;
    private String URL_GET2;
    private int[] idTipos;
    private Evento[] sug_eventos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        // Apartado para poner el correo electrónico en la vista de perfil de usuario
        username = getIntent().getStringExtra("Username"); //username almacena el email del usuario para obtener el id.
        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);

        HttpGet a=new HttpGet(this.getApplicationContext());
        a.execute(URL_GET);
        String  item= null;
        try{
            item = a.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        JsonHandler jh = new JsonHandler();
        usuario = jh.getUsuario(item,username);

        idPosicion = 1; //posicion por default. Sin GPS

        //Sugerencia de eventos.
        listaSugerencias = (ListView) findViewById(R.id.listaSugerencia);

        URL_GET2 = "http://10.0.2.2:8080/EventoUsachJava/preferencias";
        HttpGet b=new HttpGet(this.getApplicationContext());
        b.execute(URL_GET2);
        String  item2= null;
        try{
            item2 = b.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        idTipos = jh.getPreferencias(item2,usuario.getId());

        URL_GET2 = "http://10.0.2.2:8080/EventoUsachJava/eventos";
        HttpGet c=new HttpGet(this.getApplicationContext());
        c.execute(URL_GET2);
        String  item3= null;
        try{
            item3 = c.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        sug_eventos = jh.getEventoPreferencias(item3,idTipos);
        AdapterEvento ae = new AdapterEvento(this,sug_eventos);

        listaSugerencias.setAdapter(ae);

        listaSugerencias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //evento[position].getIdLugar()
                //Toast.makeText(MainActivity.this,"HOLAA", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(perfilUsuario.this, descripcionEventoAsistir.class);
                i.putExtra("evento",sug_eventos[position]);
                i.putExtra("idUser",usuario.getId());
                startActivity(i);


            }
        });
    }


    public void onButtonClick(View v){

        //String username = getIntent().getStringExtra("Username");

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
        JsonHandler jh = new JsonHandler();
        int idUsuario = jh.getIdUsuario(item,username); //Se obtiene el id del usuario según su correo


        HttpGet d=new HttpGet(this.getApplicationContext());
        d.execute(URL_GET1);
        String item1= null;
        try{
            item1 = d.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        int tam = jh.contadorIdEventos(item1,idUsuario);
        int[] idEventos=jh.getIdEventos(item1,idUsuario,tam);
        //Toast.makeText(getApplicationContext(), "que pasa aquiii"+idEventos[0]+" numero "+idEventos[1], Toast.LENGTH_SHORT).show();
        Intent intento = new Intent(this,misEventos.class);
        intento.putExtra("numbers",idEventos);
        startActivity(intento);
    }

    public void onclickverEventos(View v){
        Intent i = new Intent(this,mapaUsuario.class);
        i.putExtra("idPosicion",idPosicion);
        startActivity(i);
    }

    public void onclickcreateEvent(View v){
        Intent i = new Intent(this,eventoNuevo.class);
        i.putExtra("idUser",usuario.getId());
        startActivity(i);
    }

    public void onclickEventoAsistir(View v){
        Intent i = new Intent(this,listaEventosAsistir.class);
        i.putExtra("idPosicion",idPosicion);
        i.putExtra("idUser",usuario.getId());
        startActivity(i);
    }
    public void onclickEditarPerfil(View v){
        Intent i = new Intent(this,editarPerfil.class);
        startActivity(i);
    }

    public void onclickCerrarSesion(View v){
        Intent i = new Intent(this,sesionCerrada.class);
        startActivity(i);
    }

}
