package com.example.bastian.prueba1.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.controllers.Gets.ContadorGet;
import com.example.bastian.prueba1.controllers.Gets.LugarGet;
import com.example.bastian.prueba1.controllers.Gets.TipoGet;
import com.example.bastian.prueba1.controllers.Post.crearEventoPost;
import com.example.bastian.prueba1.models.Evento;
import com.example.bastian.prueba1.models.Lugar;
import com.example.bastian.prueba1.models.Tipo;
import com.example.bastian.prueba1.utilities.JsonHandler;

import org.json.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class eventoNuevo extends AppCompatActivity {

    private int idEvento;
    private int idLugar;
    private int idTipo;
    private EditText titulo;
    private EditText descripcion;
    private EditText tipoEvento;
    private EditText lugar;
    private EditText fecha;
    private EditText hora_inicio;
    private EditText hora_final;
    private Evento evento;
    Lugar lugar_aux[];
    Tipo tipo_aux[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_nuevo);
        titulo = (EditText) findViewById(R.id.tituloEvento);
        descripcion = (EditText) findViewById(R.id.editText);
        tipoEvento = (EditText) findViewById(R.id.editText2);
        lugar = (EditText) findViewById(R.id.editText8);
        fecha = (EditText) findViewById(R.id.editText9);
        hora_inicio = (EditText) findViewById(R.id.editText10);
        hora_final = (EditText) findViewById(R.id.editText12);


        new LugarGet(eventoNuevo.this,2).execute("http://10.0.2.2:8080/EventoUsachJava/lugares");
        new TipoGet(eventoNuevo.this).execute("http://10.0.2.2:8080/EventoUsachJava/tipos");
        new ContadorGet(eventoNuevo.this,2).execute("http://10.0.2.2:8080/EventoUsachJava/eventos");



    }



    public void obteneridLugar(Lugar[] lugar){
        this.lugar_aux = lugar;
    }

    public int buscarLugar(String nombreLugar){
        for(int i=0;i<lugar_aux.length;i++){
            if(nombreLugar.equals(lugar_aux[i].getNombre())) {
                return lugar_aux[i].getId();
            }
        }
        return 0;
    }

    public void obteneridTipo(Tipo[] tipo){
        this.tipo_aux = tipo;
    }

    public int buscarTipo(String nombreTipo,Tipo tipo[]){
        for(int i=0;i<tipo.length;i++){
            if(nombreTipo.equals("Exposicion") || nombreTipo.equals("Exposición")){
                if(tipo[i].getTipo().toString().equals("ExposiciÃ³n")){
                    return tipo[i].getId();
                }
            }
            else if(nombreTipo.equals("Ceremonia de titulacion") || nombreTipo.equals("Ceremonia de titulación")){
                if(tipo[i].getTipo().toString().equals("Ceremonia de titulaciÃ³n")){
                    return tipo[i].getId();
                }
            }
            else if(tipo[i].getTipo().toString().equals(nombreTipo)){
                return tipo[i].getId();
            }
        }
        return 0;
    }

    public void obtenerIdEvento(int id){
        this.idEvento = id;
    }
    public void agregarEvento(){

        evento = new Evento(idEvento,titulo.getText().toString(),hora_inicio.getText().toString(),
                hora_final.getText().toString(),fecha.getText().toString(),descripcion.getText().toString()
                ,idLugar,idTipo,9);
    }

    public boolean comprobarCamposVacios(){
        if(titulo.getText().length() == 0 || descripcion.getText().length() == 0 ||
                tipoEvento.getText().length() == 0 || lugar.getText().length() == 0 ||
                fecha.getText().length() == 0 || hora_inicio.getText().length() == 0 ||
                hora_final.getText().length() == 0){
            return false;
        } else {
            return true;
        }
    }

    public void camposVacios(){
        titulo.setText("");
        descripcion.setText("");
        tipoEvento.setText("");
        lugar.setText("");
        fecha.setText("");
        hora_inicio.setText("");
        hora_final.setText("");
    }

    public void mensajeExito(){
        Toast.makeText(getApplicationContext(), "Evento creado exitosamente, espere " +
                "que el administrador acepte la solicitud.", Toast.LENGTH_SHORT).show();}

    public void mensajeFracaso(){
        Toast.makeText(getApplicationContext(), "Datos erroneos", Toast.LENGTH_SHORT).show();}

    public void onclickcrearEvento(View v){

        if(comprobarCamposVacios()) {
            this.idLugar = buscarLugar(lugar.getText().toString());
            this.idTipo = buscarTipo(tipoEvento.getText().toString(),tipo_aux);


            //if (idTipo != 0 && idLugar != 0) {
                agregarEvento();
                JsonHandler jhand = new JsonHandler();
                JSONObject jo = jhand.setEvento(evento);
                new crearEventoPost(this).execute("http://10.0.2.2:8080/EventoUsachJava/eventos",jo.toString());
            //} else {
             //   Toast.makeText(getApplicationContext(), "Rellene los datos correctamente", Toast.LENGTH_SHORT).show();
            //}
        } else {
            Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            camposVacios();
        }
    }

}
