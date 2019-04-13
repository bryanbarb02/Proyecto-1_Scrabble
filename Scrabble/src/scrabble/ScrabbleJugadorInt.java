/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Bryan
 */
public interface ScrabbleJugadorInt extends Remote {
    public void actualizar (String name)throws RemoteException ;
    public String getNombre()throws RemoteException ;
    public void addJugador(ArrayList<String> s) throws RemoteException;
    public int getPuntuacion()throws RemoteException;
    public void actualizarPuntuacion(int points)throws RemoteException;
    public void iniciarJuego()throws RemoteException;
    public void setTurno(boolean turn)throws RemoteException;
    boolean getTurno()throws RemoteException;
    public int getNumFichas()throws RemoteException;
    
    public void addWord(ArrayList<String> wordList)throws RemoteException;
    
    public void rechazado()throws RemoteException;
    public void newTurno(Character[][] board)throws RemoteException;
    public void addPuntuacion(ArrayList<String> a) throws RemoteException;
    public void finJuego(Map<String,Integer> a) throws RemoteException;

	
}
