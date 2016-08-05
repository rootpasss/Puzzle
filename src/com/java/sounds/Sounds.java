//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.sounds;

import com.java.puzzle.Puzzle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnologia: Java.
 * Version: Java Development Kit 1.7.0_21, Standard Edition.
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 7/03/14,  Hora: 22:42 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Informacion Adicional.
 *
 * Objetivo Del Codigo:
 * Reproducir sonidos al encajar una ficha en el lugar correcto o al armar una linea horizontal completa.
 *
 * Licencia: Personal, no comercial.
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Sounds extends Thread {
    File F;
    int type;
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public Sounds( int type ) {
        this.type = type;
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void play( String media ) {
        try {
            Clip tune = AudioSystem.getClip();
            tune.open( AudioSystem.getAudioInputStream( new File( media ) ) );
            tune.start();
        } catch( Exception e ) {
            e.printStackTrace();
            Puzzle.toggleSound.setSelected( false );    //  WHEN NO SOUNDS ARE AVAILABLE, DESELECT toggleSound CHECKBOX
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    //  THIS METHOD HELPS TO SET SELECTED OR UNSELECTED THE toggleSound CHECKBOX AUTOMATICALLY
    private void checkStorage() {
        try {
            F = new File( "/Applications/Microsoft Office 2011/Office/OutlookCore.framework/Versions/14/Resources/mailsent.wav" );

            if( F.exists() ) {
                Puzzle.toggleSound.setSelected( true );
            } else {
                Puzzle.toggleSound.setSelected( false );
                System.err.println( "Los sonidos del puzzle no se pudieron cargar.  Revise la ruta \"" + F.getParent() + "/\"." );
            }
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void run() {
        switch( type ) {
            case 1:
                play( "/Applications/Microsoft Office 2011/Office/OutlookCore.framework/Versions/14/Resources/reminder.wav" );
                break;

            case 2:
                play( "/Applications/Microsoft Office 2011/Office/OutlookCore.framework/Versions/14/Resources/mailerror.wav" );
                break;

            case 3:
                play( "/Applications/Microsoft Office 2011/Office/OutlookCore.framework/Versions/14/Resources/mailsent.wav" );
                break;

            default:
                checkStorage();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------------------------------------------------------------------