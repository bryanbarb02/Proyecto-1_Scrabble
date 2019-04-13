/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Bryan
 */
public class ScrabbleServidor extends UnicastRemoteObject implements ScrabbleServidorInt {
    public static final int TAMANO_TABLERO = 20;
    
	private Vector<ScrabbleJugadorInt> listaJugadores = new Vector<ScrabbleJugadorInt>();
	ArrayList<String> nombreLista = new ArrayList<String>();
	int jugadorNum, jugadorSeq,votarNum, votarSi,pasarNum;
	Character[][] Tablero = new Character[TAMANO_TABLERO][TAMANO_TABLERO];
	Character[][] tmpTablero = new Character[TAMANO_TABLERO][TAMANO_TABLERO];
	ArrayList<String> listaPalabras = new ArrayList<String>();
	ArrayList<String> nuevaListaPalabras = new ArrayList<String>();
	Map<String,Integer> puntuacionUsuario = new HashMap<String,Integer>();

    protected ScrabbleServidor() throws RemoteException {
    	super();
    }

    private static final long serialVersionUID = 1L;

    public boolean iniciarSesion(ScrabbleJugadorInt a) throws RemoteException {
	System.out.println(a.getNombre() + "  conectarse....");	
	listaJugadores.add(a);
        jugadorNum++;
	return true;	
    }
	
    public void salirDelJuego() throws RemoteException {
        System.out.println("Fin del juego...");
        for(int i = 0; i<jugadorNum;i++) {
            ScrabbleJugadorInt tmp5 = (ScrabbleJugadorInt) listaJugadores.get(i);
            puntuacionUsuario.put(tmp5.getNombre(), tmp5.getPuntuacion());
        }
        for(int i = 0; i<jugadorNum;i++) {
            ScrabbleJugadorInt tmp6 = (ScrabbleJugadorInt) listaJugadores.get(i);
            tmp6.finJuego(puntuacionUsuario);
            System.out.println(i);
        }
    }
    
    public Vector<ScrabbleJugadorInt> getConectado() throws RemoteException {
	return listaJugadores;
    }

	//publish player list
	public void borrarNombre() throws RemoteException {
		nombreLista.clear();
	}
	
	public void addNombreALista() throws RemoteException {
		for(int i=0;i<jugadorNum;i++){
			ScrabbleJugadorInt n=(ScrabbleJugadorInt)listaJugadores.get(i);
			nombreLista.add(n.getNombre());
		}
	
	}
	public void publicar(String s) throws RemoteException {
            for(int i=0;i<jugadorNum;i++){
                try{
                    ScrabbleJugadorInt tmp=(ScrabbleJugadorInt)listaJugadores.get(i);
			tmp.addJugador(nombreLista);
                }
                catch(Exception e){
                    //Problema con el cliente no conectado
                    //Mejor quitarlo
                }
            }	
	}
	
	public boolean comprobarNombre(String s) throws RemoteException {
            boolean comprobar = true;
            for (int i =0; i<jugadorNum;i++) {
                if (listaJugadores.get(i).getNombre().equals(s)) {
                    comprobar = false;
                    break;
		}
            }
            return comprobar;
	}
	

	public void iniciarJuego() throws RemoteException {
            jugadorSeq = 0;
            pasarNum =0;
            listaJugadores.get(jugadorSeq).setTurno(true);
            for(int i = 0; i < TAMANO_TABLERO; i++) {
		for(int j = 0; j < TAMANO_TABLERO; j++) {
                    Tablero[i][j] = 0;
		}
            }
            for(int i = 0; i < jugadorNum; i++) {
                listaJugadores.get(i).iniciarJuego();
            }
	}
	
	public void enviarPalabra(Character[][] board) {
            nuevaListaPalabras.clear();
            for (int i = 0; i < TAMANO_TABLERO; i++) {
		for (int j = 0; j < TAMANO_TABLERO; j++) {
                    tmpTablero[i][j]=board[i][j];
		}
            }
	
            for (int i = 0; i < TAMANO_TABLERO; i++) {
                String palabra ="";
		for (int j = 0; j < TAMANO_TABLERO; j++) {
                    if(tmpTablero[i][j]!=0) {
                        palabra = palabra + tmpTablero[i][j].toString();
                    }
                    else {
                        palabra = palabra + " ";
                    }
		}
                StringTokenizer st = new StringTokenizer(palabra);
		while (st.hasMoreTokens()) {
                    String w = st.nextToken();
                    if(!listaPalabras.contains(w) && w.length()>1) {
                        nuevaListaPalabras.add(w);
                        listaPalabras.add(w);
		    }
		}
            }
            for (int j = 0; j < TAMANO_TABLERO; j++) {
                String word = "";
                for (int i = 0; i < TAMANO_TABLERO; i++) {
                    if(tmpTablero[i][j]!=0) {
                        word = word + tmpTablero[i][j].toString();
                    }
                    else 
                        if(tmpTablero[i][j]==0) {
                            word = word + " ";
			}
		}	
			
		StringTokenizer st = new StringTokenizer(word);
		while (st.hasMoreTokens()) {
		    String w = st.nextToken();
		    if(!listaPalabras.contains(w) && w.length()>1) {
                        nuevaListaPalabras.add(w);
                        listaPalabras.add(w);
		    }
		}
            }
		
            for(int i=0; i < nuevaListaPalabras.size();i++) {
                System.out.println(nuevaListaPalabras.get(i));
            }
	
            for(int i=0;i<jugadorNum;i++){
                try{
                    ScrabbleJugadorInt tmp=(ScrabbleJugadorInt)listaJugadores.get(i);
		    tmp.addPalabra(nuevaListaPalabras);
		}
                catch(Exception e){
		    //Problema con el cliente no conectado
                    //Mejor quitarlo
		}
            }
            votarNum = 0;
            votarSi = 0;
	}
	
    public void pasar() throws RemoteException {
	pasarNum++;
	if(pasarNum == jugadorNum) {
            System.out.println("Fin del juego...");
            for(int i = 0; i<jugadorNum;i++) {
                ScrabbleJugadorInt tmp = (ScrabbleJugadorInt) listaJugadores.get(i);
		puntuacionUsuario.put(tmp.getNombre(), tmp.getPuntuacion());
            }
            for(int i = 0; i<jugadorNum;i++) {
                ScrabbleJugadorInt tmp2 = (ScrabbleJugadorInt) listaJugadores.get(i);
		tmp2.finJuego(puntuacionUsuario);
		System.out.println(i);
            }
	} 
        else {
            try{
                for(int i=0;i<jugadorNum;i++){
                    listaJugadores.get(jugadorSeq).setTurno(false);
                    System.out.println(listaJugadores.get(i).getTurno());
		}
		jugadorSeq++;
		if(jugadorSeq==jugadorNum) {
                    jugadorSeq=0;
                    pasarNum=0;
                }
		listaJugadores.get(jugadorSeq).setTurno(true);
		System.out.println(jugadorSeq);
		System.out.println(listaJugadores.get(jugadorSeq).getTurno());
		for(int i=0;i<jugadorNum;i++){
                    ScrabbleJugadorInt tmp1=(ScrabbleJugadorInt)listaJugadores.get(i);
                    tmp1.newTurno(Tablero);
		}
            } 
            catch (RemoteException e) {
		e.printStackTrace();
            }
            for(int i=0;i<jugadorNum;i++){
                try{
                    ScrabbleJugadorInt tmp1=(ScrabbleJugadorInt)listaJugadores.get(i);
                    tmp1.newTurno(Tablero);
		    System.out.println("YYYYY");
		}
                catch(Exception e){
                    //Problema con el cliente no conectado
                    //Mejor quitarlo			    	
                }
            }
        }		
    }
    
    synchronized public void votar(boolean votes) throws RemoteException {
        votarNum++;
	if(votes ==true) {
            votarSi++;
	}
	if(votarNum == jugadorNum) {
            if(votarSi != jugadorNum) {
		try {
                    System.out.println(listaJugadores.get(jugadorSeq).getNombre() + "No se aceptan la(s) palabra(s).");
                    listaJugadores.get(jugadorSeq).rechazado();
		} 
                catch (RemoteException e) {
                    e.printStackTrace();
		}
            }
            else {
                try {
                    listaJugadores.get(jugadorSeq).addPuntuacion(nuevaListaPalabras);
		} 
                catch (RemoteException e1) {
                    e1.printStackTrace();
		}
		for (int i = 0; i < TAMANO_TABLERO; i++) {
                    for (int j = 0; j < TAMANO_TABLERO; j++) {
                        Tablero[i][j]=tmpTablero[i][j];
                    }
		}
                try{
                    for(int i=0;i<jugadorNum;i++){
                        listaJugadores.get(jugadorSeq).setTurno(false);
			System.out.println(listaJugadores.get(i).getTurno());
                    }
                    jugadorSeq++;
                    if(jugadorSeq==jugadorNum) {
                        jugadorSeq=0;
			pasarNum =0;
                    }
                    listaJugadores.get(jugadorSeq).setTurno(true);
                    System.out.println(jugadorSeq);
                    System.out.println(listaJugadores.get(jugadorSeq).getTurno());
		} 
                catch (RemoteException e) {
                    e.printStackTrace();
		}
		for(int i=0; i<jugadorNum; i++){
                    try{
                        ScrabbleJugadorInt tmp = (ScrabbleJugadorInt)listaJugadores.get(i);
                        tmp.newTurno(Tablero);
                    }
                    catch(Exception e){
                        //Problema con el cliente no conectado
                        //Mejor quitarlo
                    }
		}
            }
	}
    }
}
