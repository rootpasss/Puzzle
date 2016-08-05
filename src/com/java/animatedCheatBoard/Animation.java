//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.animatedCheatBoard;

import com.java.cheat.Cheat;

import java.awt.*;
import java.util.Random;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnologia: Java.
 * Version: Java Development Kit 1.7.0_21, Standard Edition.
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 9/02/14,  Hora: 8:33 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Informacion Adicional.
 *
 * Objetivo Del Codigo:
 * Agregar efecto de animacion de colores al tablero de 'cheats'.
 *
 * Licencia: Personal, no comercial.
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Animation extends Thread {
    int R, G, B;
    boolean redFlag, greenFlag, blueFlag;
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public Animation() {
        R = new Random().nextInt( 254 );
        G = new Random().nextInt( 254 );
        B = new Random().nextInt( 254 );

        redFlag = new Random().nextBoolean();
        greenFlag = new Random().nextBoolean();
        blueFlag = new Random().nextBoolean();
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void run() {
        while( true ) {
            try {
                Thread.sleep( 25 );

                if( redFlag ) {
                    R++;
                } else {
                    R--;
                }

                if( greenFlag ) {
                    G++;
                } else {
                    G--;
                }

                if( blueFlag ) {
                    B++;
                } else {
                    B--;
                }

                Cheat.contentPane.setBackground( new Color( R, G, B ) );

                if( R == 255 ) {
                    redFlag = false;
                }

                if( R == 0 ) {
                    redFlag = true;
                }

                if( G == 255 ) {
                    greenFlag = false;
                }

                if( G == 0 ) {
                    greenFlag = true;
                }

                if( B == 255 ) {
                    blueFlag = false;
                }

                if( B == 0 ) {
                    blueFlag = true;
                }
            } catch( Exception e ) {
//                e.printStackTrace();  NOTHING TO PRINT
                break;
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------------------------------------------------------------------