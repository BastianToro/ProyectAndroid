package com.example.bastian.prueba1.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.controllers.Gets.ContadorGet;
import com.example.bastian.prueba1.controllers.Gets.RegistroGet;
import com.example.bastian.prueba1.controllers.Post.RegistroPost;
import com.example.bastian.prueba1.models.Usuario;
import com.example.bastian.prueba1.utilities.JsonHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.StringTokenizer;

public class Registro extends AppCompatActivity {

    private int idUser;
    private Usuario user;
    private EditText correo;
    private EditText pass;
    private EditText nombre;
    private EditText apellido;
    private EditText carrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        correo = (EditText) findViewById(R.id.editText3);
        pass = (EditText) findViewById(R.id.editText4);
        nombre = (EditText) findViewById(R.id.editText5);
        apellido = (EditText) findViewById(R.id.editText6);
        carrera = (EditText) findViewById(R.id.editText7);



    }


    public void obtenerId(int numero){
        idUser = numero;
    }

    public void agregarDatosUsuario(){
        /*user.setEsadministrador(false);
        user.setApellido(apellido.getText().toString());
        user.setCarrera(carrera.getText().toString());
        user.setPass(pass.getText().toString());
        user.setCorreo(correo.getText().toString());
        user.setIdEstado(1);
        user.setId(idUser);
        user.setNombre(nombre.getText().toString());*/
        user = new Usuario(idUser,1,nombre.getText().toString(),apellido.getText().toString(),
                correo.getText().toString(),carrera.getText().toString(),
                pass.getText().toString(),false);
    }

    public boolean comprobarCorreo(String email){
        /*StringTokenizer tokens = new StringTokenizer(email, "\\@");
        String nombre =tokens.nextToken();
        String dominio = tokens.nextToken();*/
        String[] separated = email.split("\\@");
        String dominio = separated[1] ;
        if(dominio.equals("usach.cl")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean comprobarCamposVacios(){
        if(correo.getText().toString().length() == 0 || pass.getText().toString().length() == 0 ||
                nombre.getText().toString().length() == 0 || apellido.getText().toString().length() == 0 ||
                carrera.getText().toString().length() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public void setCamposVacios(){
        correo.setText("");
        pass.setText("");
        nombre.setText("");
        apellido.setText("");
        carrera.setText("");
    }

    public void registrarUsuario(){
        JsonHandler jhand = new JsonHandler();
        JSONObject jh = jhand.setUsuario(user);
        new RegistroPost(this).execute("http://10.0.2.2:8080/EventoUsachJava/usuarios",jh.toString());
    }

    public void mensajeExito(){
        Toast.makeText(getApplicationContext(), "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();}

    public void mensajeFracaso(){
        Toast.makeText(getApplicationContext(), "Datos erroneos", Toast.LENGTH_SHORT).show();}


    public void onclickRegistrar(View v){
        if(comprobarCamposVacios()==true) {
            if (comprobarCorreo(correo.getText().toString()) == true) {
                new ContadorGet(this,1).execute("http://10.0.2.2:8080/EventoUsachJava/usuarios");
                agregarDatosUsuario();
                new RegistroGet(this,user).execute("http://10.0.2.2:8080/EventoUsachJava/usuarios");
            } else {
                Toast.makeText(getApplicationContext(), "El correo no corresponde a la usach", Toast.LENGTH_SHORT).show();
            }
            //new ContadorGet(this).execute("http://10.0.2.2:8080/EventoUsachJava/usuarios");
            //agregarDatosUsuario();
            //new RegistroGet(this,user).execute("http://10.0.2.2:8080/EventoUsachJava/usuarios");
        }
        else{
            Toast.makeText(getApplicationContext(), "Rellene los datos correctamente", Toast.LENGTH_SHORT).show();
            setCamposVacios();
        }
    }
}
