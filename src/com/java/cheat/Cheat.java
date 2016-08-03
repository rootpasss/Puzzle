//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.cheat;

import com.java.animatedCheatBoard.Animation;
import com.java.puzzle.Puzzle;
import com.java.theme.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnologia: Java JDK 1.7.0_21
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 26/01/14,  Hora: 13:23 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Informacion Adicional.
 *
 * Objetivo Del Codigo:
 * Programa para hacer 'trampa' en los juegos de soluciones dificiles; el 'cheat' consiste en intercambiar cualquier
 * posicion posible de los numeros en el tablero de juego.
 *
 * Licencia: Personal, no comercial.
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Cheat extends JFrame {
    public static JPanel contentPane;
    JToggleButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    JToggleButton[] buttons;
    public static JButton swapb;
    public static JLabel alert;
    int toggles = 0, A, B, X, Y;
    boolean position1 = false;  //  BOOL FOR AVOID A & B OVERWRITES

    Limit limit = new Limit();
    Animation colorAnimator = new Animation();
    //------------------------------------------------------------------------------------------------------------------------------------------------

//    public static void main( String[] args )
//    {
//        new Cheat().setVisible( true );
//    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public Cheat() {
        Theme.setTheme( 2 );
        setTitle( "Cheat" );
        setSize( 140, 130 );
        setResizable( false );
        setUndecorated( true );
        setOpacity( 0.5f );
        setLocationRelativeTo( Puzzle.reference );
        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        addFocusListener( new FocusAdapter() {
            public void focusLost( FocusEvent e ) {
                Theme.setTheme( 1 );   //RESTORING ORIGINAL THEME ( NIMBUS LOOK AND FEEL ) TO THE GAME BOARD.
                dispose();
                colorAnimator.interrupt();   //DESTROY THE COLOR ANIMATOR THREAD.
            }
        } );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        contentPane = new JPanel();
        contentPane.setBorder( new EmptyBorder( 2, 2, 2, 2 ) );
        contentPane.setLayout( null );
        contentPane.setBackground( new Color( 160, 255, 125 ) );
        setContentPane( contentPane );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b1 = new JToggleButton();
        b1.setBounds( 5, 5, 40, 28 );
        b1.setFocusable( false );
        b1.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b1.isSelected(), 0, 0 );
            }
        } );
        getContentPane().add( b1 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b2 = new JToggleButton();
        b2.setBounds( 35, 5, 40, 28 );
        b2.setFocusable( false );
        b2.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b2.isSelected(), 0, 1 );
            }
        } );
        getContentPane().add( b2 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b3 = new JToggleButton();
        b3.setBounds( 65, 5, 40, 28 );
        b3.setFocusable( false );
        b3.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b3.isSelected(), 0, 2 );
            }
        } );
        getContentPane().add( b3 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b4 = new JToggleButton();
        b4.setBounds( 95, 5, 40, 28 );
        b4.setFocusable( false );
        b4.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b4.isSelected(), 0, 3 );
            }
        } );
        getContentPane().add( b4 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b5 = new JToggleButton();
        b5.setBounds( 5, 28, 40, 28 );
        b5.setFocusable( false );
        b5.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b5.isSelected(), 1, 0 );
            }
        } );
        getContentPane().add( b5 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b6 = new JToggleButton();
        b6.setBounds( 35, 28, 40, 28 );
        b6.setFocusable( false );
        b6.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b6.isSelected(), 1, 1 );
            }
        } );
        getContentPane().add( b6 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b7 = new JToggleButton();
        b7.setBounds( 65, 28, 40, 28 );
        b7.setFocusable( false );

        b7.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b7.isSelected(), 1, 2 );
            }
        } );
        getContentPane().add( b7 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b8 = new JToggleButton();
        b8.setBounds( 95, 28, 40, 28 );
        b8.setFocusable( false );
        b8.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b8.isSelected(), 1, 3 );
            }
        } );
        getContentPane().add( b8 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b9 = new JToggleButton();
        b9.setBounds( 5, 51, 40, 28 );
        b9.setFocusable( false );
        b9.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b9.isSelected(), 2, 0 );
            }
        } );
        getContentPane().add( b9 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b10 = new JToggleButton();
        b10.setBounds( 35, 51, 40, 28 );
        b10.setFocusable( false );
        b10.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b10.isSelected(), 2, 1 );
            }
        } );
        getContentPane().add( b10 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b11 = new JToggleButton();
        b11.setBounds( 65, 51, 40, 28 );
        b11.setFocusable( false );
        b11.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b11.isSelected(), 2, 2 );
            }
        } );
        getContentPane().add( b11 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b12 = new JToggleButton();
        b12.setBounds( 95, 51, 40, 28 );
        b12.setFocusable( false );
        b12.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b12.isSelected(), 2, 3 );
            }
        } );
        getContentPane().add( b12 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b13 = new JToggleButton();
        b13.setBounds( 5, 74, 40, 28 );
        b13.setFocusable( false );
        b13.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b13.isSelected(), 3, 0 );
            }
        } );
        getContentPane().add( b13 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b14 = new JToggleButton();
        b14.setBounds( 35, 74, 40, 28 );
        b14.setFocusable( false );
        b14.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b14.isSelected(), 3, 1 );
            }
        } );
        getContentPane().add( b14 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b15 = new JToggleButton();
        b15.setBounds( 65, 74, 40, 28 );
        b15.setFocusable( false );
        b15.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b15.isSelected(), 3, 2 );
            }
        } );
        getContentPane().add( b15 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b16 = new JToggleButton();
        b16.setBounds( 95, 74, 40, 28 );
        b16.setFocusable( false );
        b16.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                analyze( b16.isSelected(), 3, 3 );
            }
        } );
        getContentPane().add( b16 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        swapb = new JButton( "Select 2 Cells" );
        swapb.setBounds( 5, 98, 130, 26 );
        swapb.setFocusable( false );
        swapb.setEnabled( false );
        swapb.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                Puzzle.swap( A, B, X, Y, "" );
                Puzzle.setButtons();
                swapb.setText( "Select 2 Cells" );
                swapb.setFont( new Font( swapb.getFont().getName(), Font.PLAIN, 13 ) );

                importValues();
                restartButtons();
                enableButtons();

                if( Puzzle.cheatPermissions == 2 ) {
                    limit.start();
                    colorAnimator.interrupt();  //  KILL colorAnimator THREAD
                    disableButtons();
                    swapb.setEnabled( false );
                    setSize( 140, 150 );
                    contentPane.setBackground( Color.BLACK );
                }

                Puzzle.cheatPermissions++;
                Puzzle.cheatUses++;
            }
        } );
        getContentPane().add( swapb );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        alert = new JLabel( "Cheat limit reached!" );
        alert.setBounds( 1, 122, getWidth(), 28 );
        alert.setForeground( Color.RED );
        alert.setFont( new Font( alert.getFont().getName(), Font.BOLD, 13 ) );
        getContentPane().add( alert );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        importValues();
        verifyPermission();
        //--------------------------------------------------------------------------------------------------------------------------------------------
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void verifyPermission() {
        if( Puzzle.cheatPermissions == 3 ) {
            limit.start();
            disableButtons();
            swapb.setEnabled( false );
            setSize( 140, 150 );
            contentPane.setBackground( Color.BLACK );
        } else {
            colorAnimator.start();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void analyze( boolean flag, int point1, int point2 ) {
        if( flag ) {
            if( ! position1 ) {
                A = point1;
                B = point2; //  ADDING FIRST VALUE POSITION
                position1 = true;   //  LINE CODE FOR AVOID A & B VARIABLE OVERWRITES
            }

            toggles++;
        } else {
            toggles--;
            position1 = false;
            restartButtons();
        }

        if( toggles == 2 ) {
            X = point1;
            Y = point2; //  ADDING SECOND VALUE POSITION
            swapb.setText( "Swap n' Cheat!" );
            swapb.setEnabled( true );
            swapb.setFont( new Font( swapb.getFont().getName(), Font.BOLD + Font.ITALIC, 13 ) );
            disableButtons();
        } else {
            swapb.setText( "Select 2 Cells" );
            swapb.setEnabled( false );
            swapb.setFont( new Font( swapb.getFont().getName(), Font.PLAIN, 13 ) );
            enableButtons();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * SUCCESFULLY REFINED METHOD!  -  COMPARE IT WITH OLDER CODE VERSIONS.
     */
    public void disableButtons() {
        for( JToggleButton pointer : buttons ) {
            pointer.setEnabled( pointer.isSelected() );
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * SUCCESFULLY REFINED METHOD!  -  COMPARE IT WITH OLDER CODE VERSIONS.
     */
    public void enableButtons() {
        for( JToggleButton pointer : buttons ) {
            pointer.setEnabled( true );
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * SUCCESFULLY REFINED METHOD!  -  COMPARE IT WITH OLDER CODE VERSIONS.
     */
    public void restartButtons() {
        for( JToggleButton pointer : buttons ) {
            pointer.setSelected( false );
        }

        swapb.setEnabled( false );
        toggles = 0;
        position1 = false;
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * SUCCESFULLY REFINED METHOD!  -  COMPARE IT WITH OLDER CODE VERSIONS.
     */
    public void importValues() {
        buttons = new JToggleButton[]{ b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16 };
        int point = 0;

        for( int i = 0; i < Puzzle.matrix.length; i++ ) {
            for( int j = 0; j < Puzzle.matrix.length; j++ ) {
                buttons[ point++ ].setText( Puzzle.matrix[ i ][ j ] );
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------------------------------------------------------------------