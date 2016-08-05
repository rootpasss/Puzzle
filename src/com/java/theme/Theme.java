//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.theme;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.UIManager;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnología: Java JDK 1.7.0_21
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 20/01/14,  Hora: 19:50 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Información Adicional.
 * <p>
 * Objetivo Del Código:
 * Establecer tema visual.
 * <p>
 * Licencia: Personal, no comercial
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Theme {
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public static void setTheme( int theme ) {
        String themeURL;

        if( theme == 1 ) {
            themeURL = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
        } else {
            themeURL = "com.apple.laf.AquaLookAndFeel";
        }

        try {
            JFrame.setDefaultLookAndFeelDecorated( true );
            JDialog.setDefaultLookAndFeelDecorated( true );
            UIManager.setLookAndFeel( themeURL );
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------------------------------------------------------------------