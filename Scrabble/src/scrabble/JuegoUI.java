/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Bryan
 */
public class JuegoUI extends JFrame {
public static final int TAMANO_TABLERO = 14;
	public static final int TAMANO_MANO = 7;
	private static final long serialVersionUID = 1L;
	
	JFrame marcoJuego = new JFrame("Scrabble");
	JButton botonLIMPIAR = new JButton("Limpiar");
	JButton botonCONFIRMAR = new JButton("Confirmar");
	JButton botonPASAR = new JButton("Pasar");
	JButton botonSALIR = new JButton("Exit");
	final JPanel Field = new JPanel();
	//JPanel votingField = new JPanel();
	botonCuadricula[][] botones = new botonCuadricula[TAMANO_TABLERO][TAMANO_TABLERO];
	LetraBoton[] letras = new LetraBoton[TAMANO_MANO];
	JButton[] votar = new JButton[10];
	JTextArea[] palabra = new JTextArea[10];
	JButton botonVotarSi = new JButton();
	JButton botonVotarNo = new JButton();
	JTextArea areaPalabra = new JTextArea();
        //===================================================================================
	private Character commitStore[][] = new Character[TAMANO_TABLERO][TAMANO_TABLERO];
        //===================================================================================
	private ScrabbleJugador jugador;
	private ScrabbleServidorInt servidor;
	
	char letraSeleccionada = 0;
        String Palabra;
        Diccionario dict = new Diccionario();
	
	public JuegoUI(ScrabbleJugador jugador, ScrabbleServidorInt servidor) throws IOException {
		this.jugador = jugador;
		this.servidor = servidor;
                this.Palabra = Palabra;
    
		try {
			marcoJuego.setTitle("Scrabble: " + jugador.getNombre());
		} catch (RemoteException e) {
			// TODO Bloque de captura generado automáticamente
			e.printStackTrace();
		}
		Field.setLayout(new GridLayout(20, 20, 0, 0));
		Field.setBounds(20, 20, 20 * 30, 20 * 30);
		Field.setOpaque(false);
		marcoJuego.setSize(20 + 20 * 30 + 320, 20 + 20 * 30 + 40);
		marcoJuego.setLayout(null);
		
		establecerBotonEnCuadrícula(Field);
		establecerBarraLetras(marcoJuego);
		setBotonBorrar(marcoJuego);
		setBotonConfirmacion(marcoJuego);
		setBotonPaso(marcoJuego);
		setBotonSalir(marcoJuego);
		addIntroduccion(marcoJuego);
		addAreaVotacionLabel(marcoJuego);
		addAreaVotacionLabel(marcoJuego);
		addAreaVotacion(marcoJuego);
		
		marcoJuego.add(Field);
		marcoJuego.setLocationRelativeTo(null);
		marcoJuego.setVisible(true);
		marcoJuego.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		marcoJuego.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(marcoJuego, "¿Estás seguro de que quieres cerrar esta ventana?", "¿Cerrar ventana?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            	System.exit(0);
		        }
		    }
		});
	}
	
	public void addIntroduccion(JFrame frame) {
		JTextArea introduccion = new JTextArea(5,30);
		introduccion.setText( "Cómo jugar:" + "\n" + "Por favor seleccione una ficha" +  "\n" + "y luego selecciona donde quieres colocarlo.");
		introduccion.setBounds(640, 20, 300, 100);
		introduccion.setEditable(false);
		introduccion.setBackground(null);
		frame.add(introduccion);
	}
	
	public void addAreaVotacionLabel(JFrame frame) {
		JTextArea introduction = new JTextArea(5,30);
		introduction.setText("¿Crees que son todas las palabras adecuadas?");
		introduction.setBounds(640, 250, 300, 20);
		introduction.setEditable(false);
		introduction.setBackground(null);
		frame.add(introduction);
	}
	
	
	public void establecerBarraLetras(JFrame frame) {
            ActionListener click = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    LetraBoton tmpbutton = (LetraBoton) e.getSource();
                    if (tmpbutton.isFocusPainted()) {
                        actualizarPaint(letras);
                    } 
                    else {
                        letraSeleccionada = tmpbutton.getLetter();
                        actualizarPaint(letras);
                        tmpbutton.setFocusPainted(true);
                    }
		}
            };	
			
		for (int i = 0; i < TAMANO_MANO; i++) {
                    // Solicitar letra y enviar
                    letras[i]= new LetraBoton();
                    letras[i].setBounds(640 + i*40, 200, 40, 40);
                    letras[i].setFocusPainted(false);
                    if(!jugador.getTurno()) {
                        letras[i].setEnabled(false);
                    }
                    frame.add(letras[i]);
                    letras[i].addActionListener(click);		
		}
	}
	
	public void actualizarPaint(LetraBoton[] letters) {
            for(int j = 0; j < TAMANO_MANO; j++) {
                letters[j].setFocusPainted(false);
            }
	}
		
	public void establecerBotonEnCuadrícula(JPanel field) {
            ActionListener click = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    botonCuadricula tmpbutton = (botonCuadricula) e.getSource();
			if (tmpbutton.isEnabled() && (letraSeleccionada != 0)) {
                            int indice = tmpbutton.getIndiceLetra();
                            if (indice != -1) {
                                letras[indice].setEnabled(true);
				letras[indice].setSelected(false);
                            }
                            tmpbutton.setLetra(letraSeleccionada);
                            			
                            for (int i = 0; i < TAMANO_MANO; i++) {
                                if (letras[i].isFocusPainted()) {
                                    tmpbutton.setIndiceLetra(i);
                                    letras[i].setEnabled(false);
                                    letras[i].setSelected(true);
                                    letras[i].setBackground(new Color(3, 59, 90));
				}
                            }
			}
                        if (tmpbutton.isEnabled() && letraSeleccionada == 0) {
                            int indice = tmpbutton.getIndiceLetra();
                            if (indice != -1) {
                                letras[indice].setEnabled(true);
				letras[indice].setSelected(false);
				tmpbutton.setLetra((char) 0);
                            }
			}
			letraSeleccionada = 0;
		}
            };
            for (int i = 0; i < TAMANO_TABLERO; i++) {
                for (int j = 0; j < TAMANO_TABLERO; j++) {
                    botones[i][j] = new botonCuadricula();
                    botones[i][j].setFocusable(false);
		    field.add(botones[i][j]);
                    botones[i][j].addActionListener(click);
		}
            }
	}
	
	public void addAreaVotacion(JFrame frame) {			
		areaPalabra = new JTextArea(10,10);
		areaPalabra.setBounds(640, 300, 150,100);
		botonVotarSi = new JButton("Yes");
		botonVotarSi.setFocusable(false);
		botonVotarSi.setBounds(810, 300, 100, 40);
		botonVotarNo = new JButton("No");
		botonVotarNo.setFocusable(false);
		botonVotarNo.setBounds(810, 360, 100, 40);
		
		ActionListener clickYes = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
			try {
                        	servidor.votar(true);
			} 
                        catch (RemoteException e1) {
                            e1.printStackTrace();
			}
                        			
                    }
		};
		botonVotarSi.addActionListener(clickYes);
                
		ActionListener clickNo = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
			try {
                            servidor.votar(false);
                        } 
                        catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
				
                    }
		};
		botonVotarNo.addActionListener(clickNo);
		
		frame.add(areaPalabra);
		frame.add(botonVotarSi);
		frame.add(botonVotarNo);
	}
	
	public void setBotonBorrar(JFrame frame) {
            botonLIMPIAR.setBounds(640,150,90,40);
            botonLIMPIAR.setFocusable(false);
            frame.add(botonLIMPIAR);
            ActionListener click = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for(int i = 0; i < TAMANO_MANO; i++) {
                        letras[i].setEnabled(true);
                        letras[i].setFocusPainted(false);
                    }
                    for (int i = 0; i < TAMANO_TABLERO; i++) {
                        for (int j = 0; j < TAMANO_TABLERO; j++) {
                            if(botones[i][j].isEnabled()) {
                                botones[i][j].setLetra((char) 0);
                            }
                        }
                    }			
                }
            };
            botonLIMPIAR.addActionListener(click);
	}
	
	public void setBotonConfirmacion(JFrame frame) {
            botonCONFIRMAR.setBounds(735,150,90,40);
            botonCONFIRMAR.setFocusable(false);
            frame.add(botonCONFIRMAR);
            ActionListener click = new ActionListener() {
                @Override
		public void actionPerformed(ActionEvent e) {
                    //Almacenar nueva letra añadida y su posición
                    int P;
                    Palabra ="";
                    for (int i = 0; i < TAMANO_TABLERO; i++) {
                        for (int j = 0; j < TAMANO_TABLERO; j++) {
                            if (botones[i][j].getIcon() != null) {
                                commitStore[i][j] = botones[i][j].getLetra();
                                
                                P = commitStore[i][j];
                                Palabra = Palabra + P;
                                
                                
            //====================================================================================
                                //if(palabraCorrecta()){
                                System.out.print(commitStore[i][j]);
            //====================================================================================
                            }
                            else {
				commitStore[i][j] = 0;
                            }
			}
                    }
                    if(VerificarPalabra(Palabra)){
                        try {
                            servidor.enviarPalabra(commitStore);
                    } 
                        catch (RemoteException e1) {
                            e1.printStackTrace();
                    }
                                }
                    else{
                        for(int i = 0; i < TAMANO_MANO; i++) {
                            letras[i].setEnabled(true);
                            letras[i].setFocusPainted(false);
                            JTextArea palabraIncorrecta = new JTextArea(5,30);
                            palabraIncorrecta.setText( "Palabra incorrecta, intente de nuevo");
                            palabraIncorrecta.setBounds(640, 50, 300, 100);
                            palabraIncorrecta.setEditable(false);
                            palabraIncorrecta.setBackground(null);
                            frame.add(palabraIncorrecta);
                        }
                        for (int i = 0; i < TAMANO_TABLERO; i++) {
                            for (int j = 0; j < TAMANO_TABLERO; j++) {
                            if(botones[i][j].isEnabled()) {
                                botones[i][j].setLetra((char) 0);
                                }
                            }
                        }
                                    /*try {
                                        //servidor.enviarPalabra();
                                        servidor.enviarPalabra(commitStore);
                                        servidor.pasar();
                                        System.out.println("palabra no existe");
                                    } 
                                    catch (RemoteException e1) {
                                         e1.printStackTrace();
                                    System.out.println("palabra no existe");
                                    }*/
                    
                        }
                }
            };
            botonCONFIRMAR.addActionListener(click);
	}
        
        public boolean VerificarPalabra(String Palabra){
            if(dict.BuscarDiccionario(Palabra)){
                return true;     
            }
            else{
                return false;
            }
        }
	
	public void setBotonPaso(JFrame frame) {
            botonPASAR.setBounds(830,150,90,40);
            botonPASAR.setFocusable(false);
            frame.add(botonPASAR);
            ActionListener click = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        servidor.pasar();
                    } 
                    catch (RemoteException e1) {
                       e1.printStackTrace();
                    }		
		}
            };
            botonPASAR.addActionListener(click);
	}
	
	public void setBotonSalir(JFrame frame) {
            botonSALIR.setBounds(830,585,90,40);
            botonSALIR.setFocusable(false);
            frame.add(botonSALIR);
            ActionListener click = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
	            int confirm = JOptionPane.showOptionDialog(frame, "¿Estás seguro de que quieres cerrar esta ventana?", "¿Cerrar ventana?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            servidor.salirDelJuego();
                        } 
                        catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                        //salirJuego();
                    }
                }
            };
		botonSALIR.addActionListener(click);
        }
	
	public void salirJuego() {
            System.exit(0);
	}

	public void addListaPalabras(ArrayList<String> st) {
            areaPalabra.setText(null);
            for(int i=0;i<st.size();i++) {
		areaPalabra.append(st.get(i) + "\n");
            }
	}
	
	public void palabraNoAceptada() {
            JOptionPane.showMessageDialog(marcoJuego, "Sus palabras no son aceptadas por todos los jugadores. Inténtelo de nuevo o presione el botón \\ \"pasar \\\".");
            for(int i = 0; i < TAMANO_MANO; i++) {
                letras[i].setEnabled(true);
                letras[i].setFocusPainted(false);
            }
            for (int i = 0; i < TAMANO_TABLERO; i++) {
                for (int j = 0; j < TAMANO_TABLERO; j++) {
                    if(botones[i][j].isEnabled()) {
                        botones[i][j].setLetra((char) 0);
                    }
                }
            }
	}
	
	public void finJuego(Map<String,Integer> a) {
            marcoJuego.setVisible(false);
            jugador.setFinJuego(new FinJuegoUI(jugador,servidor,a));	
	}
	
	public void setNuevoTurno(Character[][] board) {
            for (int i = 0; i < TAMANO_TABLERO; i++) {
                for (int j = 0; j < TAMANO_TABLERO; j++) {
                    botones[i][j].setLetra(board[i][j]);
                    if (botones[i][j].getIcon() != null) {
                        botones[i][j].setEnabled(false);
                    }
		}
            }
            
            for (int i = 0; i < TAMANO_MANO; i++) {
                // resetear barra de letras
		if (letras[i].isSelected()) {
                    letras[i].setSelected(false);
                    letras[i].actualizarLetra();
		}
		System.out.println(jugador.getTurno());
                    if(!jugador.getTurno()) {
                        letras[i].setEnabled(false);
                    }
                    else {
                        letras[i].setEnabled(true);	
                    }
                    letras[i].setFocusPainted(false);
            }
	}
}