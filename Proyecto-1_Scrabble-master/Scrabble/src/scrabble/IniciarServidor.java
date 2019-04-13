/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import java.rmi.RMISecurityManager;

public class IniciarServidor {
    public static void main(String[] args) {
        Registry registro;
        try {
            ScrabbleServidorInt b =new ScrabbleServidor();	
            registro = LocateRegistry.createRegistry(1099);
            registro.rebind("rmi://127.0.0.1/myabc",b);
            System.out.println("[Sistema] El servidor está listo.");
	}
        catch (Exception e) {
            System.out.println("El servidor falló " + e);
        }	
    }
}
