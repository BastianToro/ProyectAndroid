package com.example.bastian.prueba1.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.controllers.Gets.HttpGet;
import com.example.bastian.prueba1.models.Evento;
import com.example.bastian.prueba1.services.AdapterEvento;
import com.example.bastian.prueba1.utilities.JsonHandler;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class misEventos extends AppCompatActivity {

    private final String URL_GET2 = "http://10.0.2.2:8080/EventoUsachJava/eventos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_eventos);

        //Bundle extras = getIntent().getExtras();
        //int[] idEventos = extras.getIntArray("numbers");
        int[] idEventos = getIntent().getIntArrayExtra("numbers");
        //Toast.makeText(getApplicationContext(), "que pasa aquiii"+idEventos[0]+","+idEventos[1], Toast.LENGTH_SHORT).show();

        JsonHandler jh = new JsonHandler();
        HttpGet f=new HttpGet(this.getApplicationContext());// Obtenemos los eventos
        f.execute(URL_GET2);
        String  item2= null;
        try{
            item2 = f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        Evento[] eventos_m =jh.getEventos(item2,idEventos);

        //Toast.makeText(getApplicationContext(), "que pasa aquiii"+eventos_m[0].getTitulo(), Toast.LENGTH_SHORT).show();
        ListView listaEventos = (ListView)findViewById(R.id.listaDeEventos);

        AdapterEvento ae = new AdapterEvento(this,eventos_m);

        listaEventos.setAdapter(ae);

        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, (List<String>) listaEventos);

        //listaEventos.setAdapter(adaptador);

    }

}
