package com.example.bastian.prueba1.views;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bastian.prueba1.MainActivity;
import com.example.bastian.prueba1.R;
import com.example.bastian.prueba1.controllers.Gets.HttpGet;
import com.example.bastian.prueba1.controllers.Gets.verEventosGet;
import com.example.bastian.prueba1.models.Lugar;
import com.example.bastian.prueba1.utilities.JsonHandler;
import com.example.bastian.prueba1.utilities.PositionGPS;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.ExecutionException;

public class mapaUsuario extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitud;
    private double longitud;
    private double[] coord;
    Lugar lugar_ver[];
    private String URL_GET;
    private int idPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_usuario);
        //new verEventosGet(this).execute("http://10.0.2.2:8080/EventoUsachJava/lugares");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle extras = getIntent().getExtras();
        idPos = extras.getInt("idPosicion");
        PositionGPS pos = new PositionGPS();
        coord = pos.escogerPosicion(idPos);
        URL_GET = "http://10.0.2.2:8080/EventoUsachJava/lugares/" +
                "GPS?latitud="+coord[0]+"&longitud="+coord[1];
        //HTTPGET
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
        lugar_ver = jh.getLugares(item);
        //Toast.makeText(getApplicationContext(), "que pasa aquiii"+lugar_ver[0].getNombre(), Toast.LENGTH_SHORT).show();



    }
    public void obtenerLugares_ac(Lugar[] lugar){
        this.lugar_ver = lugar;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //new verEventosGet(mapaUsuario.this).execute("http://10.0.2.2:8080/EventoUsachJava/lugares/" +
         //       "GPS?latitud="+coord[0]+"&longitud="+coord[1]);

        //new verEventosGet(mapaUsuario.this).execute("http://10.0.2.2:8080/EventoUsachJava/lugares/GPS?latitud=-33.4499073&longitud=-70.6870482");
        LatLng posicion = new LatLng(coord[0],coord[1]);
        mMap.addMarker(new MarkerOptions().position(posicion).title("Posicion actual").snippet(""+coord[0]+"holaaa"+""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicion));
        LatLng[] post = new LatLng[lugar_ver.length];
        for(int i = 0;i<lugar_ver.length;i++){
            post[i] = new LatLng(lugar_ver[i].getLatitud(),lugar_ver[i].getLongitud());
            mMap.addMarker(new MarkerOptions().position(post[i]).title(lugar_ver[i].getNombre()));
        }
    }



}
