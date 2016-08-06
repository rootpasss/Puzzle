//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.saveStats;

import java.text.DecimalFormat;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnologia: Java.
 * Version: Java Development Kit 1.7.0_21, Standard Edition.
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 26/03/14,  Hora: 12:07 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Informacion Adicional.
 *
 * Objetivo Del Codigo:
 * Seleccionar el mejor tiempo jugado y el promedio de todos los tiempos de partidas finalizadas.
 *
 * Licencia: Personal, no comercial.
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Process {
    int hrs, mins, secs, totalTime = 0, best = 10000, tSeconds;
    DecimalFormat DF = new DecimalFormat( "00" );
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void extractValues( String time ) {
        hrs = Integer.parseInt( time.substring( 0, 1 ) );
        mins = Integer.parseInt( time.substring( 4, 6 ) );
        secs = Integer.parseInt( time.substring( 9, 11 ) );

        tSeconds = ( mins * 60 ) + secs;
        totalTime += ( mins * 60 ) + secs;

        if( tSeconds <= best ) {
            best = tSeconds;
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public String reformatTime( int time ) {
        hrs = time / 3600;
        mins = time / 60;

        if( mins == 0 ) {
            secs = time;
        } else {
            secs = time / mins - 60;
        }

        return hrs + " : " + DF.format( mins ) + " : " + DF.format( secs );
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public String getBest() {
        return reformatTime( best );
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public String getAverage( int elements ) {
        return reformatTime( totalTime / elements );
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------------------------------------------------------------------