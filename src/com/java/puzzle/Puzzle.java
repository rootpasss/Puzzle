//----------------------------------------------------------------------------------------------------------------------------------------------------
package com.java.puzzle;

import com.java.cheat.Cheat;
import com.java.mess.Mess;
import com.java.saveStats.Stats;
import com.java.sounds.Sounds;
import com.java.theme.Theme;
import com.java.timing.Timing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.Date;

//----------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Desarrollado por Jhonny Trejos Barrios.
 * Tecnología: Java JDK 1.7.0_21
 * Entorno De Desarrollo: IntelliJ IDEA 12.1.6
 * Fecha: 1/01/14,  Hora: 20:20 Hrs.
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Información Adicional.
 *
 * Objetivo Del Código:
 * Interfaz grafica del tablero del juego 'Numeric Puzzle'.
 *
 * Licencia: Personal, no comercial.
 * Developer Contact: jtrejosb@live.com || jtrejosb@gmail.com
 */

//----------------------------------------------------------------------------------------------------------------------------------------------------

public class Puzzle extends JFrame {
    JPanel contentPane;
    static JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    JMenuBar menuBar;
    public static JLabel status, time, alerts, reference, completion;
    public static String[][] matrix = new String[ 4 ][ 4 ];
    boolean firstUse = true;
    public static int cheatPermissions = 0, cheatUses = 0, movements = 0;
    DateFormat DF = DateFormat.getDateInstance( DateFormat.MEDIUM );
    static boolean row1 = false, row2 = false, row3 = false;
    static LineBorder good = new LineBorder( Color.GREEN, 4, true ), wrong = new LineBorder( Color.RED, 4, true ), empty = new LineBorder( Color.BLUE, 4, true );
    static Font big = new Font( "lucida grande", Font.BOLD + Font.ITALIC, 19 ), small = new Font( "lucida grande", Font.PLAIN, 12 );
    static int goodButtons = 0;
    public static JCheckBox toggleSound;

    Timing timing;
    Puzzle frame = this;
    Mess mess = new Mess();
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public static void main( String[] args ) {
        new Puzzle().setVisible( true );
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public Puzzle() {
        Theme.setTheme( 1 );
        setTitle( "Rompecocos" );
        setSize( 266, 310 );
        setResizable( false );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        addKeyListener( new KeyAdapter() {
            public void keyPressed( KeyEvent e ) {
                if( ( e.getKeyCode() == KeyEvent.VK_C ) && ( ! firstUse ) && ( ! status.getText().contains( "Desordenando" ) ) ) {
                    new Cheat().setVisible( true );
                }

                if( e.getKeyCode() == KeyEvent.VK_H ) {
                    new Stats().setVisible( true );
                    Timing.flag = false;
                }
            }
        } );

        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
                if( ! firstUse ) {
                    Theme.setTheme( 1 );   //RESTORING THE ORIGINAL WINDOW THEME ( NIMBUS LOOK AND FEEL ) TO THE GAME BOARD.
                    Timing.flag = false;

                    String message = "Tu juego esta en curso, al cerrar se perdera cualquier progreso...\n¿Seguro deseas abandonarlo?", title = "Game Status";
                    int select = JOptionPane.showConfirmDialog( null, message, title, JOptionPane.YES_NO_OPTION );

                    if( select == 1 ) {
                        if( ! status.isVisible() || ! status.getText().contains( "Desordenando..." ) ) {
                            Timing.flag = true;
                        }

                        e.getOppositeWindow().setVisible( true );
                    }
                }
            }
        } );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        contentPane = new JPanel();
        contentPane.setBorder( new EmptyBorder( 2, 2, 2, 2 ) );
        contentPane.setLayout( null );
        contentPane.setBackground( Color.BLACK );
        setContentPane( contentPane );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b1 = new JButton( "1" );
        b1.setBounds( 5, 5, 60, 60 );
        b1.setFocusable( false );
        b1.setEnabled( false );
        b1.setBorder( good );
        b1.setName( "b1" );
        b1.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 0 ][ 1 ].equals( "" ) ) {
                    swap( 0, 0, 0, 1, "2" );
                    row1 = false;
                } else {
                    if( matrix[ 1 ][ 0 ].equals( "" ) ) {
                        swap( 0, 0, 1, 0, "5" );
                        row1 = false;
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b1 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b2 = new JButton( "2" );
        b2.setBounds( 70, 5, 60, 60 );
        b2.setFocusable( false );
        b2.setEnabled( false );
        b2.setBorder( good );
        b2.setName( "b2" );
        b2.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 0 ][ 0 ].equals( "" ) ) {
                    swap( 0, 1, 0, 0, "1" );
                    row1 = false;   //  SWITCHING row1 CONTENT TO AVOID THE COMPLETE-LINE-SOUND BUG AFTER SWAP POSITIONS
                } else {
                    if( matrix[ 0 ][ 2 ].equals( "" ) ) {
                        swap( 0, 1, 0, 2, "3" );
                        row1 = false;
                    } else {
                        if( matrix[ 1 ][ 1 ].equals( "" ) ) {
                            swap( 0, 1, 1, 1, "6" );
                            row1 = false;
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b2 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b3 = new JButton( "3" );
        b3.setBounds( 135, 5, 60, 60 );
        b3.setFocusable( false );
        b3.setEnabled( false );
        b3.setBorder( good );
        b3.setName( "b3" );
        b3.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 0 ][ 1 ].equals( "" ) ) {
                    swap( 0, 2, 0, 1, "2" );
                    row1 = false;
                } else {
                    if( matrix[ 0 ][ 3 ].equals( "" ) ) {
                        swap( 0, 2, 0, 3, "4" );
                        row1 = false;
                    } else {
                        if( matrix[ 1 ][ 2 ].equals( "" ) ) {
                            swap( 0, 2, 1, 2, "7" );
                            row1 = false;
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b3 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b4 = new JButton( "4" );
        b4.setBounds( 200, 5, 60, 60 );
        b4.setFocusable( false );
        b4.setEnabled( false );
        b4.setBorder( good );
        b4.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 0 ][ 2 ].equals( "" ) ) {
                    swap( 0, 3, 0, 2, "3" );
                    row1 = false;
                } else {
                    if( matrix[ 1 ][ 3 ].equals( "" ) ) {
                        swap( 0, 3, 1, 3, "8" );
                        row1 = false;
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b4 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b5 = new JButton( "5" );
        b5.setBounds( 5, 70, 60, 60 );
        b5.setFocusable( false );
        b5.setEnabled( false );
        b5.setBorder( good );
        b5.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 0 ][ 0 ].equals( "" ) ) {
                    swap( 1, 0, 0, 0, "1" );
                    row2 = false;
                } else {
                    if( matrix[ 1 ][ 1 ].equals( "" ) ) {
                        swap( 1, 0, 1, 1, "6" );
                        row2 = false;
                    } else {
                        if( matrix[ 2 ][ 0 ].equals( "" ) ) {
                            swap( 1, 0, 2, 0, "9" );
                            row2 = false;
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b5 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b6 = new JButton( "6" );
        b6.setBounds( 70, 70, 60, 60 );
        b6.setFocusable( false );
        b6.setEnabled( false );
        b6.setBorder( good );
        b6.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 0 ][ 1 ].equals( "" ) ) {
                    swap( 1, 1, 0, 1, "2" );
                    row2 = false;
                } else {
                    if( matrix[ 1 ][ 0 ].equals( "" ) ) {
                        swap( 1, 1, 1, 0, "5" );
                        row2 = false;
                    } else {
                        if( matrix[ 1 ][ 2 ].equals( "" ) ) {
                            swap( 1, 1, 1, 2, "7" );
                            row2 = false;
                        } else {
                            if( matrix[ 2 ][ 1 ].equals( "" ) ) {
                                swap( 1, 1, 2, 1, "10" );
                                row2 = false;
                            }
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b6 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b7 = new JButton( "7" );
        b7.setBounds( 135, 70, 60, 60 );
        b7.setFocusable( false );
        b7.setEnabled( false );
        b7.setBorder( good );
        b7.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 0 ][ 2 ].equals( "" ) ) {
                    swap( 1, 2, 0, 2, "3" );
                    row2 = false;
                } else {
                    if( matrix[ 1 ][ 1 ].equals( "" ) ) {
                        swap( 1, 2, 1, 1, "6" );
                        row2 = false;
                    } else {
                        if( matrix[ 1 ][ 3 ].equals( "" ) ) {
                            swap( 1, 2, 1, 3, "8" );
                            row2 = false;
                        } else {
                            if( matrix[ 2 ][ 2 ].equals( "" ) ) {
                                swap( 1, 2, 2, 2, "11" );
                                row2 = false;
                            }
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b7 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b8 = new JButton( "8" );
        b8.setBounds( 200, 70, 60, 60 );
        b8.setFocusable( false );
        b8.setEnabled( false );
        b8.setBorder( good );
        b8.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 0 ][ 3 ].equals( "" ) ) {
                    swap( 1, 3, 0, 3, "4" );
                    row2 = false;
                } else {
                    if( matrix[ 1 ][ 2 ].equals( "" ) ) {
                        swap( 1, 3, 1, 2, "7" );
                        row2 = false;
                    } else {
                        if( matrix[ 2 ][ 3 ].equals( "" ) ) {
                            swap( 1, 3, 2, 3, "12" );
                            row2 = false;
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b8 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b9 = new JButton( "9" );
        b9.setBounds( 5, 135, 60, 60 );
        b9.setFocusable( false );
        b9.setEnabled( false );
        b9.setBorder( good );
        b9.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 1 ][ 0 ].equals( "" ) ) {
                    swap( 2, 0, 1, 0, "5" );
                    row3 = false;
                } else {
                    if( matrix[ 2 ][ 1 ].equals( "" ) ) {
                        swap( 2, 0, 2, 1, "10" );
                        row3 = false;
                    } else {
                        if( matrix[ 3 ][ 0 ].equals( "" ) ) {
                            swap( 2, 0, 3, 0, "13" );
                            row3 = false;
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b9 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b10 = new JButton( "10" );
        b10.setBounds( 70, 135, 60, 60 );
        b10.setFocusable( false );
        b10.setEnabled( false );
        b10.setBorder( good );
        b10.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 1 ][ 1 ].equals( "" ) ) {
                    swap( 2, 1, 1, 1, "6" );
                    row3 = false;
                } else {
                    if( matrix[ 2 ][ 0 ].equals( "" ) ) {
                        swap( 2, 1, 2, 0, "9" );
                        row3 = false;
                    } else {
                        if( matrix[ 2 ][ 2 ].equals( "" ) ) {
                            swap( 2, 1, 2, 2, "11" );
                            row3 = false;
                        } else {
                            if( matrix[ 3 ][ 1 ].equals( "" ) ) {
                                swap( 2, 1, 3, 1, "14" );
                                row3 = false;
                            }
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b10 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b11 = new JButton( "11" );
        b11.setBounds( 135, 135, 60, 60 );
        b11.setFocusable( false );
        b11.setEnabled( false );
        b11.setBorder( good );
        b11.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 1 ][ 2 ].equals( "" ) ) {
                    swap( 2, 2, 1, 2, "7" );
                    row3 = false;
                } else {
                    if( matrix[ 2 ][ 1 ].equals( "" ) ) {
                        swap( 2, 2, 2, 1, "10" );
                        row3 = false;
                    } else {
                        if( matrix[ 2 ][ 3 ].equals( "" ) ) {
                            swap( 2, 2, 2, 3, "12" );
                            row3 = false;
                        } else {
                            if( matrix[ 3 ][ 2 ].equals( "" ) ) {
                                swap( 2, 2, 3, 2, "15" );
                                row3 = false;
                            }
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b11 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b12 = new JButton( "12" );
        b12.setBounds( 200, 135, 60, 60 );
        b12.setFocusable( false );
        b12.setEnabled( false );
        b12.setBorder( good );
        b12.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 1 ][ 3 ].equals( "" ) ) {
                    swap( 2, 3, 1, 3, "8" );
                    row3 = false;
                } else {
                    if( matrix[ 2 ][ 2 ].equals( "" ) ) {
                        swap( 2, 3, 2, 2, "11" );
                        row3 = false;
                    } else {
                        if( matrix[ 3 ][ 3 ].equals( "" ) ) {
                            swap( 2, 3, 3, 3, "" );
                            row3 = false;
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b12 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b13 = new JButton( "13" );
        b13.setBounds( 5, 200, 60, 60 );
        b13.setFocusable( false );
        b13.setEnabled( false );
        b13.setBorder( good );
        b13.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 2 ][ 0 ].equals( "" ) ) {
                    swap( 3, 0, 2, 0, "9" );
                } else {
                    if( matrix[ 3 ][ 1 ].equals( "" ) ) {
                        swap( 3, 0, 3, 1, "14" );
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b13 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b14 = new JButton( "14" );
        b14.setBounds( 70, 200, 60, 60 );
        b14.setFocusable( false );
        b14.setEnabled( false );
        b14.setBorder( good );
        b14.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 2 ][ 1 ].equals( "" ) ) {
                    swap( 3, 1, 2, 1, "10" );
                } else {
                    if( matrix[ 3 ][ 0 ].equals( "" ) ) {
                        swap( 3, 1, 3, 0, "13" );
                    } else {
                        if( matrix[ 3 ][ 2 ].equals( "" ) ) {
                            swap( 3, 1, 3, 2, "15" );
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b14 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b15 = new JButton( "15" );
        b15.setBounds( 135, 200, 60, 60 );
        b15.setFocusable( false );
        b15.setEnabled( false );
        b15.setBorder( good );
        b15.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( matrix[ 2 ][ 2 ].equals( "" ) ) {
                    swap( 3, 2, 2, 2, "11" );
                } else {
                    if( matrix[ 3 ][ 1 ].equals( "" ) ) {
                        swap( 3, 2, 3, 1, "14" );
                    } else {
                        if( matrix[ 3 ][ 3 ].equals( "" ) ) {
                            swap( 3, 2, 3, 3, "" );
                        }
                    }
                }

                setButtons();
                verify();
            }
        } );
        getContentPane().add( b15 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        b16 = new JButton( "Go!" );
        b16.setBounds( 200, 200, 60, 60 );
        b16.setFocusable( false );
        b16.setBorder( empty );
        b16.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( ! firstUse ) {
                    if( matrix[ 2 ][ 3 ].equals( "" ) ) {
                        swap( 3, 3, 2, 3, "12" );
                    } else {
                        if( matrix[ 3 ][ 2 ].equals( "" ) ) {
                            swap( 3, 3, 3, 2, "15" );
                        }
                    }

                    setButtons();
                    verify();
                } else {
                    matrix[ 3 ][ 3 ] = "";
                    mess.start();
                    timing = new Timing( frame );
                    timing.start();
                    Timing.flag = false;
                    firstUse = false;
                }
            }
        } );
        getContentPane().add( b16 );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        alerts = new JLabel();
        alerts.setBounds( 0, 280, getWidth(), 25 );
        alerts.setFont( new Font( "ubuntu", Font.BOLD, 24 ) );
        alerts.setHorizontalAlignment( JLabel.CENTER );
        getContentPane().add( alerts );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        reference = new JLabel();
        reference.setLocation( 340, 120 );
        getContentPane().add( reference );  // ADDING A LABEL OUTSIDE THE FRAME TO REFERENCE THE CHEAT WINDOW POSITION.
        //--------------------------------------------------------------------------------------------------------------------------------------------

        setMatrix();
        //--------------------------------------------------------------------------------------------------------------------------------------------

        menuBar = new JMenuBar();
        setJMenuBar( menuBar );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        toggleSound = new JCheckBox( "Sonidos" );
        toggleSound.setFont( new Font( "ubuntu", Font.BOLD + Font.ITALIC, 12 ) );
        toggleSound.setFocusable( false );
        toggleSound.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( toggleSound.isSelected() ) {
                    new Sounds( 4 ).start();    //  CHECK FOR SOUNDS AVAILABILITY
                }
            }
        } );
        menuBar.add( toggleSound );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        status = new JLabel( "  Hola" );
        menuBar.add( status );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        time = new JLabel( "    " );
        menuBar.add( time );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        completion = new JLabel();
        completion.setVisible( false );
        menuBar.add( completion );
        //--------------------------------------------------------------------------------------------------------------------------------------------

        new Sounds( 4 ).start();    //  LINE-CODE FOR CHECK SOUNDS AVAILABILITY
        //--------------------------------------------------------------------------------------------------------------------------------------------
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void setMatrix() {
        matrix[ 0 ][ 0 ] = b1.getText();
        matrix[ 0 ][ 1 ] = b2.getText();
        matrix[ 0 ][ 2 ] = b3.getText();
        matrix[ 0 ][ 3 ] = b4.getText();

        matrix[ 1 ][ 0 ] = b5.getText();
        matrix[ 1 ][ 1 ] = b6.getText();
        matrix[ 1 ][ 2 ] = b7.getText();
        matrix[ 1 ][ 3 ] = b8.getText();

        matrix[ 2 ][ 0 ] = b9.getText();
        matrix[ 2 ][ 1 ] = b10.getText();
        matrix[ 2 ][ 2 ] = b11.getText();
        matrix[ 2 ][ 3 ] = b12.getText();

        matrix[ 3 ][ 0 ] = b13.getText();
        matrix[ 3 ][ 1 ] = b14.getText();
        matrix[ 3 ][ 2 ] = b15.getText();
        matrix[ 3 ][ 3 ] = b16.getText();
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public static void setButtons() {
        b1.setText( matrix[ 0 ][ 0 ] );
        b1.setEnabled( ! b1.getText().equals( "" ) );
        coloring( b1, "1" );

        b2.setText( matrix[ 0 ][ 1 ] );
        b2.setEnabled( ! b2.getText().equals( "" ) );
        coloring( b2, "2" );

        b3.setText( matrix[ 0 ][ 2 ] );
        b3.setEnabled( ! b3.getText().equals( "" ) );
        coloring( b3, "3" );

        b4.setText( matrix[ 0 ][ 3 ] );
        b4.setEnabled( ! b4.getText().equals( "" ) );
        coloring( b4, "4" );


        b5.setText( matrix[ 1 ][ 0 ] );
        b5.setEnabled( ! b5.getText().equals( "" ) );
        coloring( b5, "5" );

        b6.setText( matrix[ 1 ][ 1 ] );
        b6.setEnabled( ! b6.getText().equals( "" ) );
        coloring( b6, "6" );

        b7.setText( matrix[ 1 ][ 2 ] );
        b7.setEnabled( ! b7.getText().equals( "" ) );
        coloring( b7, "7" );

        b8.setText( matrix[ 1 ][ 3 ] );
        b8.setEnabled( ! b8.getText().equals( "" ) );
        coloring( b8, "8" );


        b9.setText( matrix[ 2 ][ 0 ] );
        b9.setEnabled( ! b9.getText().equals( "" ) );
        coloring( b9, "9" );

        b10.setText( matrix[ 2 ][ 1 ] );
        b10.setEnabled( ! b10.getText().equals( "" ) );
        coloring( b10, "10" );

        b11.setText( matrix[ 2 ][ 2 ] );
        b11.setEnabled( ! b11.getText().equals( "" ) );
        coloring( b11, "11" );

        b12.setText( matrix[ 2 ][ 3 ] );
        b12.setEnabled( ! b12.getText().equals( "" ) );
        coloring( b12, "12" );


        b13.setText( matrix[ 3 ][ 0 ] );
        b13.setEnabled( ! b13.getText().equals( "" ) );
        coloring( b13, "13" );

        b14.setText( matrix[ 3 ][ 1 ] );
        b14.setEnabled( ! b14.getText().equals( "" ) );
        coloring( b14, "14" );

        b15.setText( matrix[ 3 ][ 2 ] );
        b15.setEnabled( ! b15.getText().equals( "" ) );
        coloring( b15, "15" );

        b16.setText( matrix[ 3 ][ 3 ] );
        b16.setEnabled( ! b16.getText().equals( "" ) );
        coloring( b16, "0" );

        goodButtons = 0;
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * __________________ SUCCESFULLY REFINED METHOD!  -  COMPARE IT WITH OLDER CODE VERSIONS  ________________________________________________
     */
    public static void coloring( JButton specialButton, String value ) {
        if( specialButton.getText().equals( value ) ) {
            specialButton.setBorder( good );
            specialButton.setFont( big );
            specialButton.setForeground( Color.MAGENTA );

            goodButtons++;
        } else {
            if( ! specialButton.getText().equals( "" ) ) {
                specialButton.setBorder( wrong );
                specialButton.setFont( small );
                specialButton.setForeground( Color.BLACK );
            } else {
                specialButton.setBorder( empty );

                if( b16.getText().equals( "" ) ) {
                    b16.setBorder( good );
                }
            }
        }

        completion.setText( "   Power: " + ( goodButtons * 100 ) / 15 + "%" );
    }
    /**  _________________________________________________________________________________________________________________________________________  */
    //------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * __________________  SUCCESFULLY REFINED METHOD!  -  COMPARE IT WITH OLDER CODE VERSIONS  ________________________________________________
     */
    public void restartGame() {
        firstUse = true;
        status.setText( "  Hola" );
        status.setVisible( true );
        time.setText( "    " );
        completion.setVisible( false );
        toggleSound.setVisible( true );
        cheatPermissions = 0;
        cheatUses = 0;
        movements = 0;

        getContentPane().setBackground( Color.BLACK );

        JButton[] buttons = { b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16 };

        for( JButton pointer : buttons ) {
            pointer.setForeground( Color.GRAY );
            pointer.setEnabled( false );
            pointer.setFont( small );

            if( pointer.getText().equals( "" ) ) {
                pointer.setForeground( Color.BLACK );
                pointer.setEnabled( true );
                pointer.setBorder( empty );
                pointer.setText( "Go!" );
            }
        }

        mess = new Mess();
    }
    /**  _________________________________________________________________________________________________________________________________________  */
    //------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * __________________  SUCCESFULLY REFINED METHOD!  -  COMPARE IT WITH OLDER CODE VERSIONS  ________________________________________________
     */
    public void verify() {
        boolean iWIN = true;
        String[][] solution = { { "1", "2", "3", "4" },
                { "5", "6", "7", "8" },
                { "9", "10", "11", "12" },
                { "13", "14", "15", "" } };

        for( int i = 0; i < matrix.length; i++ ) {
            for( int j = 0; j < matrix.length; j++ ) {
                if( ! matrix[ i ][ j ].equals( solution[ i ][ j ] ) ) {
                    iWIN = false;
                    break;
                }
            }
        }

        if( iWIN ) {
            Timing.flag = false;
            timing.killProcess();
            time.setText( "   " + timing.getTotalTime() );
            toggleSound.setVisible( false );
            getContentPane().setBackground( Color.YELLOW );
            JOptionPane.showMessageDialog( null, "Completado.\nGanaste el juego con " + movements + " movimientos." +
                    "\nTrucos sucios: " + cheatUses + " de 3.", "Rompecocos", JOptionPane.INFORMATION_MESSAGE );

            Stats.recordStats( DF.format( new Date() ), time.getText().substring( 10 ), movements, cheatUses );

            int option = JOptionPane.showConfirmDialog( null, "¿Quieres jugar de nuevo?", "Rompecocos", JOptionPane.YES_NO_OPTION );

            if( option == 0 ) {
                restartGame();
            } else {
                System.exit( 0 );
            }
        }
    }

    /**
     * _________________________________________________________________________________________________________________________________________
     */
    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void swap( int pointerI, int pointerJ, int x, int y, String value ) {
        String temp = matrix[ pointerI ][ pointerJ ];
        matrix[ pointerI ][ pointerJ ] = matrix[ x ][ y ];
        matrix[ x ][ y ] = temp;

        movements++;

        if( matrix[ x ][ y ].equals( value ) ) {
            if( toggleSound.isSelected() ) {
                new Sounds( 2 ).start();
            }
        }

        if( ( matrix[ 0 ][ 0 ] + matrix[ 0 ][ 1 ] + matrix[ 0 ][ 2 ] + matrix[ 0 ][ 3 ] ).equals( "1234" ) && ! row1 ) {
            if( toggleSound.isSelected() ) {
                new Sounds( 3 ).start();
            }

            row1 = true;
        }

        if( ( matrix[ 1 ][ 0 ] + matrix[ 1 ][ 1 ] + matrix[ 1 ][ 2 ] + matrix[ 1 ][ 3 ] ).equals( "5678" ) && ! row2 ) {
            if( toggleSound.isSelected() ) {
                new Sounds( 3 ).start();
            }

            row2 = true;
        }

        if( ( matrix[ 2 ][ 0 ] + matrix[ 2 ][ 1 ] + matrix[ 2 ][ 2 ] + matrix[ 2 ][ 3 ] ).equals( "9101112" ) && ! row3 ) {
            if( toggleSound.isSelected() ) {
                new Sounds( 3 ).start();
            }

            row3 = true;
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
//----------------------------------------------------------------------------------------------------------------------------------------------------