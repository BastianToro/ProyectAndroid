package com.example.bastian.prueba1.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.controllers.Puts.DeshabilitarPut;
import com.example.bastian.prueba1.utilities.JsonHandler;

import org.json.JSONObject;

public class editarPerfil extends AppCompatActivity {

    private String username;
    private EditText correo;
    private EditText pass;
    private EditText nombre;
    private EditText apellido;
    private EditText carrera;
    private CheckBox concierto;
    private CheckBox asamblea;
    private CheckBox simposio;
    private CheckBox obra;
    private CheckBox titulacion;
    private CheckBox expo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        username = getIntent().getStringExtra("Username");

        correo = (EditText) findViewById(R.id.editText3);
        pass = (EditText) findViewById(R.id.editText4);
        nombre = (EditText) findViewById(R.id.editText5);
        apellido = (EditText) findViewById(R.id.editText6);
        carrera = (EditText) findViewById(R.id.editText7);
        concierto = (CheckBox) findViewById(R.id.chbxconcierto);
        asamblea = (CheckBox) findViewById(R.id.chbxasamblea);
        simposio = (CheckBox) findViewById(R.id.chbxsimposio);
        obra = (CheckBox) findViewById(R.id.chbxobrateatro);
        titulacion = (CheckBox) findViewById(R.id.chbxtitulacion);
        expo = (CheckBox) findViewById(R.id.chbxexposicion);

    }

    public boolean confirmarEspaciosVacios(){
        if(correo.getText().length() == 0 && pass.getText().length() == 0 && nombre.getText().length() == 0 &&
                apellido.getText().length() == 0 && carrera.getText().length() == 0 && concierto.isChecked() == false &&
                asamblea.isChecked() == false && simposio.isChecked() == false && obra.isChecked() == false &&
                titulacion.isChecked() == false && expo.isChecked() == false){
            return true;
        }
        return false;
    }

    public void onclickEditarPerfil(View v){
        if(confirmarEspaciosVacios() == true){
            Intent i = new Intent(this,perfilUsuario.class);
            i.putExtra("Username",username);
            startActivity(i);
        }
        else{
            new AlertDialog.Builder(this)
                    .setTitle("Editar Perfil")
                    .setMessage("¿Estas seguro de realizar estos cambios?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(editarPerfil.this,perfilUsuario.class);
                            i.putExtra("Username",username);
                            startActivity(i);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
