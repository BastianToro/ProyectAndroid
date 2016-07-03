package com.example.bastian.prueba1.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bastian.prueba1.MainActivity;
import com.example.bastian.prueba1.R;

public class sesionCerrada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_cerrada);
        try {
            Thread.sleep(2000);
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onclickvolverInicio(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
