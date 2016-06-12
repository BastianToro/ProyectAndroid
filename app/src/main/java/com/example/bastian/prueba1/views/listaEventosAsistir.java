package com.example.bastian.prueba1.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.controllers.Gets.HttpGet;
import com.example.bastian.prueba1.models.Evento;
import com.example.bastian.prueba1.models.Lugar;
import com.example.bastian.prueba1.services.AdapterEvento;
import com.example.bastian.prueba1.utilities.JsonHandler;
import com.example.bastian.prueba1.utilities.PositionGPS;

import java.util.concurrent.ExecutionException;

public class listaEventosAsistir extends AppCompatActivity {

    private int idPos;
    private int idUser;
    private ListView listView;
    private String URL_GET;
    private Lugar[] lugar;
    private Evento[] eventos;
    private double[] coord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos_asistir);

        Bundle extras = getIntent().getExtras();
        idPos = extras.getInt("idPosicion");
        idUser = extras.getInt("idUser");
        listView = (ListView) findViewById(R.id.listaEventosAsistir);

        PositionGPS pos = new PositionGPS();
        coord = pos.escogerPosicion(idPos);
        //Toast.makeText(getApplicationContext(), "que pasa aquiii"+coord[0]+","+coord[1], Toast.LENGTH_SHORT).show();

        //obtener los lugares cercanos a las coordenadas ingresadas.
        URL_GET = "http://10.0.2.2:8080/EventoUsachJava/lugares/" +
                "GPS?latitud="+coord[0]+"&longitud="+coord[1];
        //HTTPGET
        HttpGet c=new HttpGet(this.getApplicationContext());
        c.execute(URL_GET);
        String  item= null;
        try{
            item = c.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        JsonHandler jh = new JsonHandler();
        lugar = jh.getLugares(item);
        //Toast.makeText(getApplicationContext(), "que pasa aquiii"+lugar[0].getLatitud()+","+lugar[0].getLongitud(), Toast.LENGTH_SHORT).show();
        //todos los eventos que se ubiquen en los lugares cercanos
        URL_GET = "http://10.0.2.2:8080/EventoUsachJava/eventos";

        HttpGet d=new HttpGet(this.getApplicationContext());
        d.execute(URL_GET);
        try{
            item = d.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        eventos = jh.getEventosdeLugares(item,lugar);

        //desplegar eventos en la listview
        AdapterEvento ae = new AdapterEvento(this,eventos);

        listView.setAdapter(ae);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //evento[position].getIdLugar()
                //Toast.makeText(MainActivity.this,"HOLAA", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(listaEventosAsistir.this, descripcionEventoAsistir.class);
                i.putExtra("evento",eventos[position]);
                i.putExtra("idUser",idUser);
                startActivity(i);


            }
        });

    }



}
