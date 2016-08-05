//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.timing;

import com.java.alerts.Alerts;
import com.java.puzzle.Puzzle;

import java.text.DecimalFormat;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnología: Java JDK 1.7.0_21
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 3/01/14,  Hora: 22:11 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Información Adicional.
 *
 * Objetivo Del Código:
 * Temporizador de duracion de juego.  ( ejecutado por la clase Puzzle.java en el botón 'b16' )
 *
 * Licencia: Personal, no comercial.
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Timing extends Thread {
    int hrs, mins, seconds;
    public static boolean flag;
    boolean master;
    Puzzle object;
    DecimalFormat DF = new DecimalFormat( "00" );
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public Timing( Puzzle object ) {
        this.object = object;
        hrs = 0;
        mins = 0;
        seconds = - 1;
        master = true;
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void killProcess() {
        this.master = false;
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public String getTotalTime() {
        return "Final: " + hrs + " : " + DF.format( mins ) + " : " + DF.format( seconds );
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void run() {
        boolean showAlert = false;

        while( master ) {
            try {
                Thread.sleep( 1000 );

                if( flag ) {
                    seconds++;

                    if( seconds == 7 ) {
                        Puzzle.status.setText( "  Suerte!" );
                    }

                    if( seconds == 9 ) {
                        Puzzle.status.setVisible( false );
                    }

                    if( seconds == 60 ) {
                        seconds = 0;
                        mins++;
                        showAlert = true;
                    }

                    if( ( mins >= 3 ) && showAlert ) {
                        new Alerts( object ).start();
                        showAlert = false;
                    }

                    if( mins == 60 ) {
                        mins = 0;
                        hrs++;
                    }

                    Puzzle.time.setText( "   " + hrs + ":" + DF.format( mins ) + ":" + DF.format( seconds ) );
                }
            } catch( Exception e ) {
                e.printStackTrace();
                break;
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------------------------------------------------------------------