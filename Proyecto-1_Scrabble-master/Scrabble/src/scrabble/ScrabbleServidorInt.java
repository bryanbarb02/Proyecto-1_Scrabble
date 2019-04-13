/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Bryan
 */
public interface ScrabbleServidorInt extends Remote  {
    
    public boolean iniciarSesion (ScrabbleJugadorInt a)throws RemoteException ;
    public void publicar (String s)throws RemoteException ;
    public Vector<ScrabbleJugadorInt> getConectado() throws RemoteException;
    public void iniciarJuego() throws RemoteException;
    public void enviarPalabra(Character[][] commitStore)throws RemoteException;
    public void votar(boolean b)throws RemoteException;
    public void pasar() throws RemoteException;
    public void salirDelJuego() throws RemoteException;
    public boolean comprobarNombre(String s) throws RemoteException;
    public void borrarNombre() throws RemoteException;
    public void addNombreALista() throws RemoteException;
}
