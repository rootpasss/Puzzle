//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.mess;

import com.java.puzzle.Puzzle;
import com.java.timing.Timing;

import java.awt.*;
import java.util.Random;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnología: Java JDK 1.7.0_21
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 2/01/14,  Hora: 20:49 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Información Adicional.
 *
 * Objetivo Del Código:
 * Desordenar las posiciones de las fichas del tablero.
 *
 * Licencia: Personal, no comercial.
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Mess extends Thread {
    int counter = 0, pointerX, pointerY, X, Y;
    String temp;
    boolean altern = true;
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void messLocations() {
        X = new Random().nextInt( 4 );
        Y = new Random().nextInt( 4 );

        pointerX = new Random().nextInt( 4 );
        pointerY = new Random().nextInt( 4 );

        temp = Puzzle.matrix[ X ][ Y ];
        Puzzle.matrix[ X ][ Y ] = Puzzle.matrix[ pointerX ][ pointerY ];
        Puzzle.matrix[ pointerX ][ pointerY ] = temp;

        Puzzle.setButtons();
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void run() {
        Puzzle.status.setText( "  Desordenando..." );

        while( true ) {
            try {
                counter++;

                if( altern ) {
                    Puzzle.status.setForeground( Color.RED );
                    altern = false;
                } else {
                    Puzzle.status.setForeground( Color.BLUE );
                    altern = true;
                }

                messLocations();

                if( counter == 90 ) {
                    Puzzle.status.setText( "  Armalo!" );
                    Timing.flag = true;
                    Thread.sleep( 700 );
                    Puzzle.completion.setVisible( true );
                    break;
                }

                Thread.sleep( 70 );
            } catch( Exception e ) {
                e.printStackTrace();
            }
        }
    }
}
//----------------------------------------------------------------------------------------------------------------------------------------------------