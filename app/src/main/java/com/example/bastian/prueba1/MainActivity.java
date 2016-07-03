package com.example.bastian.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bastian.prueba1.controllers.Gets.EventosGet;
import com.example.bastian.prueba1.controllers.Gets.LugarGet;
import com.example.bastian.prueba1.models.Evento;
import com.example.bastian.prueba1.models.Lugar;
import com.example.bastian.prueba1.services.AdapterEvento;
import com.example.bastian.prueba1.utilities.PositionGPS;
import com.example.bastian.prueba1.views.Login;
import com.example.bastian.prueba1.views.MapsActivity;
import com.example.bastian.prueba1.views.Registro;

public class MainActivity extends AppCompatActivity {

    Evento evento[];
    Lugar lugarEvento[];
    ListView listView;
    int idL;
    private double[] coord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PositionGPS pos = new PositionGPS();
        coord = pos.escogerPosicion(1);

        listView = (ListView) findViewById(R.id.listView);
        //Toast.makeText(this,evento.length , Toast.LENGTH_SHORT).show();

        //Toast.makeText(MainActivity.this, "HOLANDAA"+evento[0].getIdLugar(), Toast.LENGTH_SHORT).show();
        new EventosGet(this).execute("http://10.0.2.2:8080/EventoUsachJava/eventos");
        new LugarGet(MainActivity.this,1).execute("http://10.0.2.2:8080/EventoUsachJava/lugares/" +
                "GPS?latitud="+coord[0]+"&longitud="+coord[1]);



        //Toast.makeText(this,evento.length , Toast.LENGTH_SHORT).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //evento[position].getIdLugar()
                //Toast.makeText(MainActivity.this,"HOLAA", Toast.LENGTH_SHORT).show();
                idL = encontrarLugar(lugarEvento,evento[position]);
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                i.putExtra("evento",evento[position]);
                i.putExtra("lugarEvento",lugarEvento[idL]);
                startActivity(i);

            }
        });

    }


    public void listarEventos(Evento[] evento){
        this.evento = evento;
        AdapterEvento ae = new AdapterEvento(this,evento);

        listView.setAdapter(ae);
    }

    public void obtenerLugarEvento(Lugar[] lugar){
        this.lugarEvento = lugar;
    }

    public int encontrarLugar(Lugar[] lugar,Evento evento){
        int largo = lugar.length;
        for(int i=0;i<largo;i++){
            if(evento.getIdLugar() == lugar[i].getId()){
                return i;
            }
        }
        return -1;
    }

    public void onClickLogin(View v){
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }

    public void onCLickRegistro(View v){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }
}
