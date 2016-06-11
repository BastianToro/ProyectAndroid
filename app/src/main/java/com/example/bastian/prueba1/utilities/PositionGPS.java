package com.example.bastian.prueba1.utilities;

/**
 * Created by bastian on 11-06-16.
 */
public class PositionGPS {

    //private double latitud;
    //private double longitud;
    private int id;

    public PositionGPS(){
        //this.latitud = latitud;
        //this.longitud = longitud;
        //this.id = id;
    }

    public double[] escogerPosicion(int id){
        double[] coor = new double[2];
        if(id == 1){ // patio de los sapos
            coor[0] = -33.4501472;
            coor[1] =-70.6868557;
            return coor;
        }
        else if(id == 2){ //frente paiep
            coor[0] = -33.4500173;
            coor[1] = -70.6853062;
            return coor;
        }
        else if(id == 3){ //frente al mall
            coor[0] = -33.4491105;
            coor[1] = -70.6829772;
            return coor;
        }
        else if(id == 4){ //cerca citecamp
            coor[0] = -33.446918;
            coor[1] =-70.6832542;
            return coor;
        }
        else if(id == 5){ //pastos de ciencias
            coor[0] = -33.4475257;
            coor[1] = -70.6835117;
            return coor;
        }
        else{ //pastos humanidades
            coor[0] = -33.4515275;
            coor[1] = -70.6869589;
            return coor;
        }
    }

}
