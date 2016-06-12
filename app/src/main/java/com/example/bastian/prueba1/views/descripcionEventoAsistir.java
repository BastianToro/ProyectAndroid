package com.example.bastian.prueba1.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.controllers.Gets.HttpGet;
import com.example.bastian.prueba1.controllers.Post.eventoUsuarioPost;
import com.example.bastian.prueba1.models.Evento;
import com.example.bastian.prueba1.models.Lugar;
import com.example.bastian.prueba1.models.Tipo;
import com.example.bastian.prueba1.utilities.JsonHandler;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class descripcionEventoAsistir extends AppCompatActivity {

    private Evento evento;
    private String URL_GET;
    private Lugar lugar;
    private Tipo tipo;
    private String fecha;
    private String hora_ini;
    private String hora_fin;
    private TextView titulo_text;
    private TextView tipo_text;
    private TextView lugar_text;
    private TextView fecha_text;
    private TextView horario_text;
    private TextView descripcion_text;
    private int idUser;
    private int tamEU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_evento_asistir);

        evento = (Evento) getIntent().getSerializableExtra("evento");
        Bundle extras = getIntent().getExtras();
        idUser = extras.getInt("idUser");

        JsonHandler jh = new JsonHandler();

        //Obtener el nombre del lugar
        URL_GET = "http://10.0.2.2:8080/EventoUsachJava/lugares";
        HttpGet f=new HttpGet(this.getApplicationContext());// Obtenemos los eventos
        f.execute(URL_GET);
        String  item2= null;
        try{
            item2 = f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        lugar = jh.getLugarEvento(item2,evento.getIdLugar());

        //Obtener el tipo de evento
        URL_GET = "http://10.0.2.2:8080/EventoUsachJava/tipos";
        HttpGet d=new HttpGet(this.getApplicationContext());// Obtenemos los eventos
        d.execute(URL_GET);
        try{
            item2 = d.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        tipo = jh.getTipoEvento(item2,evento.getIdTipo());

        //parsear fecha
        parsearFecha(evento.getInicio(),evento.getFin());

        //obtener los datos del layout
        titulo_text = (TextView) findViewById(R.id.textTittle);
        tipo_text = (TextView) findViewById(R.id.textTipoE);
        lugar_text = (TextView) findViewById(R.id.textLugar);
        fecha_text = (TextView) findViewById(R.id.textFecha);
        horario_text = (TextView) findViewById(R.id.textHorario);
        descripcion_text = (TextView) findViewById(R.id.textDescripcion);

        titulo_text.setText(evento.getTitulo());
        tipo_text.setText(tipo.getTipo());
        lugar_text.setText(lugar.getNombre());
        fecha_text.setText(fecha);
        horario_text.setText(hora_ini+" - "+hora_fin);
        descripcion_text.setText(evento.getDescripcion());

    }

    public void parsearFecha(String fecha_inicio,String fecha_final){
        String[] separador1 = fecha_inicio.split("T");
        fecha = separador1[0];
        String[] separador2 = separador1[1].split("-");
        hora_ini = separador2[0];
        String[] sep1 = fecha_final.split("T");
        String[] sep2 = sep1[1].split("-");
        hora_fin = sep2[0];
    }

    public void onclickAsistirAceptar(View v){
        //obtener el id del evento y el id del usuario y hacer un httppost en eventosusuarios.
        URL_GET = "http://10.0.2.2:8080/EventoUsachJava/eventosusuarios";
        HttpGet d=new HttpGet(this.getApplicationContext());// Obtenemos los eventos
        d.execute(URL_GET);
        String  item2= null;
        try{
            item2 = d.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }

        JsonHandler jh = new JsonHandler();
//        tamEU = jh.getContadorEU(item2);
        //Toast.makeText(getApplicationContext(), "que pasa aquiii"+tamEU, Toast.LENGTH_SHORT).show();

        JSONObject jo = jh.setUsuarioEvento(evento.getId(),idUser);
        Log.d("d",jo.toString());
        new eventoUsuarioPost(descripcionEventoAsistir.this).execute("http://10.0.2.2:8080/EventoUsachJava/eventosusuarios",jo.toString());



    }

    public void mensajeExito(){
        Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();}

    public void mensajeFracaso(){
        Toast.makeText(getApplicationContext(), "Operacioon fallida", Toast.LENGTH_SHORT).show();}
}
