//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.saveStats;

import com.java.puzzle.Puzzle;
import com.java.theme.Theme;
import com.java.timing.Timing;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Vector;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnologia: Java.
 * Version: Java Development Kit 1.7.0_21, Standard Edition.
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 27/02/14,  Hora: 14:18 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Informacion Adicional.
 *
 * Objetivo Del Codigo:
 * Guardar datos por juego completado o desplegar una ventana con el timeline completo del puzzle.
 *
 * Licencia: Personal, no comercial.
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Stats extends JFrame {
    //static File F = new File( "/users/jhonnytrejos/desktop/idea projects/puzzle/src/com/java/savestats/timeline/Stats.sts" );
    static File F = new File( "/users/jhonny/documents/programming/idea projects/puzzle/src/com/java/savestats/timeline/Stats.sts" );
    static FileInputStream FIS;
    static FileOutputStream FOS;
    static ObjectInputStream OIS;
    static ObjectOutputStream OOS;
    static Vector< Game > stats = new Vector<>();
    JTable table;
    DefaultTableModel DTM;
    JScrollPane scroll;
    JTextArea info;
    JLabel gameCounter, bestTime, averageTime;
    JPanel details;
    Process p = new Process();
    //------------------------------------------------------------------------------------------------------------------------------------------------

    //public static void main( String[] args )
    //{
    //    new Stats().setVisible( true );
    //}
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public Stats() {
        Theme.setTheme( 2 );
        setTitle( "Puzzle Stats" );
        setSize( 636, 314 );
        setResizable( false );
        setType( Type.UTILITY );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        addWindowListener( new WindowAdapter() {
            public void windowClosed( WindowEvent e ) {
                if( ! Puzzle.status.getText().contains( "Desordenando..." ) ) {
                    Timing.flag = true;
                }

                Theme.setTheme( 1 );
            }
        } );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        getContentPane().setLayout( null );
        getContentPane().setBackground( new Color( 60, 63, 65 ) );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        gameCounter = new JLabel( "(aun no tiene juegos completados)" );
        gameCounter.setBounds( 295, 0, 220, 30 );
        gameCounter.setForeground( new Color( 255, 123, 104 ) );
        gameCounter.setFont( new Font( "ubuntu", Font.BOLD, 13 ) );
        getContentPane().add( gameCounter );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        String header = "Rompecocos Version 2.5.1 - Timeline.\nLa lista inferior muestra los resultados de sus partidas" +
                " finalizadas en el puzzle, a medida que vaya\nacumulando juegos ganados, se iran agregando" +
                " las estadisticas y se guardaran como su timeline.";
        info = new JTextArea( header );
        info.setEditable( false );
        info.setFocusable( false );
        info.setBounds( 14, 7, getWidth() - 10, 52 );
        info.setBackground( new Color( 60, 63, 65 ) );
        info.setForeground( new Color( 179, 187, 187 ) );
        info.setFont( new Font( "ubuntu", Font.BOLD, 13 ) );

        getContentPane().add( info );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        details = new JPanel();
        details.setBorder( new LineBorder( new Color( 44, 44, 44 ), 2, true ) );
        details.setLayout( null );
        details.setBounds( 5, 265, getWidth() - 10, 29 );
        details.setBackground( new Color( 53, 94, 147 ).darker() );
        getContentPane().add( details );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        bestTime = new JLabel( "Mejor Tiempo:              - -  :  - -  :  - -" );
        bestTime.setBounds( 15, 1, 235, 28 );
        bestTime.setForeground( new Color( 179, 187, 187 ) );
        bestTime.setFont( new Font( "ubuntu", Font.BOLD, 14 ) );
        details.add( bestTime );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        averageTime = new JLabel( "Promedio de Tiempos:            - -  :  - -  :  - -" );
        averageTime.setBounds( 290, 1, 290, 28 );
        averageTime.setForeground( new Color( 179, 187, 187 ) );
        averageTime.setFont( new Font( "ubuntu", Font.BOLD, 14 ) );
        details.add( averageTime );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        createTable();
        loadStats();
        //--------------------------------------------------------------------------------------------------------------------------------------------
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void createTable() {
        DTM = new Model();  //  PERSONALIZED TABLEMODEL TO GET NON-EDITABLE CELLS

        TableRowSorter< DefaultTableModel > sorter = new TableRowSorter<>( DTM );

        table = new JTable( DTM );
        table.setBackground( new Color( 60, 63, 65 ) );
        table.setGridColor( new Color( 44, 44, 44 ) );
        table.setForeground( new Color( 179, 187, 187 ) );
        table.setFont( new Font( "ubuntu", Font.BOLD, 13 ) );
        table.getTableHeader().setBackground( new Color( 44, 44, 44 ) );
        table.getTableHeader().setForeground( new Color( 179, 187, 187 ) );
        table.getTableHeader().setFont( new Font( "ubuntu", Font.BOLD, 11 ) );
        table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        table.setRowSorter( sorter );

        scroll = new JScrollPane( table );
        scroll.setFocusable( false );
        scroll.setBounds( 5, 60, getWidth() - 10, 194 );
        scroll.getViewport().setBackground( new Color( 69, 73, 74 ) );
        scroll.setBorder( new LineBorder( new Color( 44, 44, 44 ) ) );
        scroll.setBackground( new Color( 69, 73, 74 ) );
        getContentPane().add( scroll );
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    @SuppressWarnings( "unchecked" )
    public static void recordStats( String date, String time, int moves, int cheats ) {
        try {
            if( F.exists() ) {
                FIS = new FileInputStream( F );
                OIS = new ObjectInputStream( FIS );

                stats = ( Vector< Game > ) OIS.readObject();

                OIS.close();
                FIS.close();
            }

            FOS = new FileOutputStream( F );
            OOS = new ObjectOutputStream( FOS );

            stats.add( new Game( date, time, moves, cheats ) );

            OOS.writeObject( stats );

            OOS.close();
            FOS.close();
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    @SuppressWarnings( "unchecked" )
    public void loadStats() {
        try {
            FIS = new FileInputStream( F );
            OIS = new ObjectInputStream( FIS );

            stats = ( Vector< Game > ) OIS.readObject();

            if( stats != null ) {
                for( Game pointer : stats ) {
                    Object[] data = { pointer.getDate(), pointer.getTime(), pointer.getMovements(), pointer.getCheats() + "/3" };

                    DTM.addRow( data );

                    p.extractValues( pointer.getTime() );
                }

                bestTime.setText( "Mejor Tiempo:              " + p.getBest() );
                averageTime.setText( "Promedio de Tiempos:            " + p.getAverage( table.getRowCount() ) );
            }

            gameCounter.setForeground( new Color( 68, 169, 110 ) );

            if( table.getRowCount() == 1 ) {
                gameCounter.setText( "(" + table.getRowCount() + " juego completado)" );
            } else {
                gameCounter.setText( "(" + table.getRowCount() + " juegos completados)" );
            }

            OIS.close();
            FIS.close();
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------------------------------------------------------------------