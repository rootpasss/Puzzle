//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.cheat;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnologia: Java JDK 1.7.0_21
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 27/01/14,  Hora: 17:43 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Informacion Adicional.
 *
 * Objetivo Del Codigo:
 * Desplegar mensaje para informar que se ha alcanzado el numero limite permitido para intercambio de posiciones con el
 * programa 'Cheat'.
 *
 * Licencia: Personal, no comercial.
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Limit extends Thread {
    boolean alternate = false;
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void run() {
        while( true ) {
            try {
                if( alternate ) {
                    alternate = false;
                    Cheat.alert.setText( "" );
                } else {
                    alternate = true;
                    Cheat.alert.setText( "Cheat limit reached!" );
                    Cheat.swapb.setText( "x     X     x" );
                }

                Thread.sleep( 500 );
            } catch( Exception e ) {
                e.printStackTrace();
                break;
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------------------------------------------------------------------