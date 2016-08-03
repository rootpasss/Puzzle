//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.alerts;

import com.java.puzzle.Puzzle;

import java.awt.*;
import java.util.Random;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnología: Java JDK 1.7.0_21
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 20/01/14,  Hora: 20:20 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Información Adicional.
 *
 * Objetivo Del Código:
 * Desplegar alertas de juego en el tablero cada 60 segundos. ( codigo usado por la clase Timing.java en la linea 68 ).
 *
 * Licencia: Personal, no comercial.
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Alerts extends Thread {
    Puzzle object;
    int counter = 0;
    boolean alternate = true;
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public Alerts( Puzzle object ) {
        this.object = object;
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void run() {
        String selectedAlert = randomAlert();

        while( counter < 70 ) {
            try {
                counter++;

                if( alternate ) {
                    Puzzle.alerts.setText( selectedAlert );
                    Puzzle.alerts.setForeground( Color.RED );
                    object.setSize( 266, 366 );
                    alternate = false;
                } else {
                    Puzzle.alerts.setText( "" );
                    alternate = true;
                }

                if( counter == 35 ) {
                    Thread.sleep( 1500 );
                }

                Thread.sleep( 35 );
            } catch( Exception e ) {
                e.printStackTrace();
                break;
            }
        }

        Puzzle.alerts.setText( "" );
        object.setSize( 266, 310 );
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public String randomAlert() {
        String[] alerts = { "Press 'C' for cheats", "Hurry Up!", "Vamos! mas rapido!!", "Mira el tiempo...", "Dificil?",
                "Eres lento", "No acabas hoy?", "Whats Up Man?!", "Time is ticking...", "Aburrido?",
                "Tecla 'H': Historial" };

        return alerts[ new Random().nextInt( 10 ) ];
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------------------------------------------------------------------