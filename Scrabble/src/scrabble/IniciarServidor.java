/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IniciarServidor {

    public static void main(String[] args) {
        Registry registro;
	try {
            ScrabbleJugadorInt b = new ScrabbleServidor();	
            registro = LocateRegistry.createRegistry(1099);
            registro.rebind("rmi://127.0.0.1/myabc",b);
            System.out.println("[System] El servidor está listo.");
	}catch (Exception e) {
            System.out.println("El servidor falló: " + e);
	}
    }
}
