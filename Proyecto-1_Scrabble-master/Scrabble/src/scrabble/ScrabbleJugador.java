/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class ScrabbleJugador extends UnicastRemoteObject implements ScrabbleJugadorInt {
    private static final long serialVersionUID = 1L;
	private String nombreUsuario;
	private IniciarSesionUI iniciarSesion;
	public JuegoUI juego;
	public FinJuegoUI end;
	public static final int TAMANO_MANO = 7;
	
	private ScrabbleFicha[] hand = new ScrabbleFicha[TAMANO_MANO];
	private int numFichas = 0;
	private int puntuacion = 0;
	private boolean isTurn = false;
	
	protected ScrabbleJugador() throws RemoteException {
            super();
        }
	
	public ScrabbleJugador (String n) throws RemoteException {
            nombreUsuario = n;
	}
	
	public void addJugador(ArrayList<String> st){
            System.out.println(st);
            iniciarSesion.addJugadorPool(st);
	}

	public void setIniciarSesionUI(IniciarSesionUI t){ 
            iniciarSesion = t ; 
	} 	
	
	@Override
	public void actualizar(String name) throws RemoteException {
			
	}

	@Override
	public String getNombre() throws RemoteException {
            return nombreUsuario;
	}
	
	//start game UI
	public void iniciarJuego() {
            System.out.println("Empieza el juego...");
        try {
            iniciarSesion.empezarNuevoJuego();
        } catch (IOException ex) {
            Logger.getLogger(ScrabbleJugador.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	//set game UI for the player
	public void setNuevoJuego(JuegoUI juego) {
            this.juego = juego;
	}
	

	public void finJuego(Map<String,Integer> a) {
            System.out.println("Final del juego...");
            juego.finJuego(a);
	}
	
	//set game UI for the player
	public void setFinJuego(FinJuegoUI end) {
            this.end = end;
	}
		
	public void addPuntuacion(ArrayList<String> a) throws RemoteException {
            for(int i=0;i<a.size();i++) {
		puntuacion += a.get(i).length();
            }
	}
	
	//display new word to in the text area
	public void addPalabra(ArrayList<String> palabra) {
            juego.addListaPalabras(palabra);
	}
	
	public void setTurno(boolean turn) {
            isTurn = turn;
	}

	public boolean getTurno() {
            return isTurn;
	}
	
	public void rechazado() {
            juego.palabraNoAceptada();
	}
	
	public void newTurno(Character[][] board) {
            juego.setNuevoTurno(board);
	}
	
	// Getter and setter methods
	public int getNumFichas() {
            return this.numFichas;
	}

	public int getPuntuacion() {
            return this.puntuacion;
	}
	
	public void setPuntuacion(int score) {
            this.puntuacion = score;
	}
	
	public void actualizarPuntuacion(int points) {
            this.puntuacion += points;
	}
}
