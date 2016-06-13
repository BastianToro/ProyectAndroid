package com.example.bastian.prueba1.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.controllers.Gets.ContadorGet;
import com.example.bastian.prueba1.controllers.Gets.HttpGet;
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
import java.util.concurrent.ExecutionException;

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
    private String URL_GET;
    private int idUser;

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

        Bundle extras = getIntent().getExtras();
        idUser = extras.getInt("idUser");
        //new LugarGet(eventoNuevo.this,2).execute("http://10.0.2.2:8080/EventoUsachJava/lugares");
        //new TipoGet(eventoNuevo.this).execute("http://10.0.2.2:8080/EventoUsachJava/tipos");
        //new ContadorGet(eventoNuevo.this,2).execute("http://10.0.2.2:8080/EventoUsachJava/eventos");

        JsonHandler jh = new JsonHandler();
        //obtener los lugares
        URL_GET = "http://10.0.2.2:8080/EventoUsachJava/lugares";
        HttpGet d=new HttpGet(this.getApplicationContext());
        d.execute(URL_GET);
        String item = null;
        try{
            item = d.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        lugar_aux = jh.getLugares(item);
        //obtener el id del nombre del lugar ingresado.
        //idLugar = buscarLugar(lugar.getText().toString());
        //obtenes los tipos de evento
        URL_GET = "http://10.0.2.2:8080/EventoUsachJava/tipos";
        HttpGet c=new HttpGet(this.getApplicationContext());
        c.execute(URL_GET);
        String item2 = null;
        try{
            item2 = c.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        tipo_aux = jh.getTipos(item2);
        //Toast.makeText(getApplicationContext(), tipo_aux[0].getDescripcion(), Toast.LENGTH_SHORT).show();
        //obtener el id del tipo de evento ingresado.
        //idTipo = buscarTipo(tipoEvento.getText().toString(),tipo_aux);

        //id del evento
        URL_GET = "http://10.0.2.2:8080/EventoUsachJava/eventos";
        HttpGet t=new HttpGet(this.getApplicationContext());
        t.execute(URL_GET);
        try{
            item = t.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        idEvento = jh.getContadorEU(item);

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

    public int buscarTipo(String nombreTipo){
        for(int i=0;i<tipo_aux.length;i++){
            if(nombreTipo.equals("Exposicion")){
                if("Exposición".equals(tipo_aux[i].getTipo())){
                    return tipo_aux[i].getId();
                }
            }
            /*else if(nombreTipo.equals("Ceremonia de titulacion") || nombreTipo.equals("Ceremonia de titulación")){
                if(tipo[i].getTipo().toString().equals("Ceremonia de titulaciÃ³n")){
                    return tipo[i].getId();
                }
            }*/
            else if(nombreTipo.equals(tipo_aux[i].getTipo())){
                return tipo_aux[i].getId();
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
                ,idLugar,idTipo,idUser);
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
            this.idTipo = buscarTipo(tipoEvento.getText().toString());


            if (idTipo != 0 && idLugar != 0) {
                agregarEvento();
                JsonHandler jhand = new JsonHandler();
                JSONObject jo = jhand.setEvento(evento);
                new crearEventoPost(this).execute("http://10.0.2.2:8080/EventoUsachJava/eventos",jo.toString());
            } else {
                Toast.makeText(getApplicationContext(), "Rellene los datos correctamente", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            camposVacios();
        }
    }

}
