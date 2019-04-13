/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Bryan
 */
public class IniciarSesionUI extends JFrame {

    private static final long serialVersionUID = 1L;
	JFrame loginFrame = new JFrame("Login");
	final JPanel contenidoPanel = new JPanel();;
	JTextField nombreUsuarioField = new JTextField();;
	JTextField direccionField = new JTextField();;
        
	JTextArea textArea = new JTextArea();
	JButton btnConectar = new JButton("Unirse al juego");
	JButton btnInicio = new JButton("Comenzar el juego");
	
	private ScrabbleJugador cliente;
	private ScrabbleServidorInt servidor;

	/**
	 * Iniciar la aplicacion.
         */
	public static void main(String[] args) {
            new IniciarSesionUI();
            System.out.println("Esperando la conexión...");
	}

	public void hacerConexion(){
            if (btnConectar.getText().equals("Unirse al juego")){
	  	try{
                    Registry registry = LocateRegistry.getRegistry(direccionField.getText());
			cliente = new ScrabbleJugador(nombreUsuarioField.getText());
			cliente.setIniciarSesionUI(this);
                        servidor =(ScrabbleServidorInt)registry.lookup("rmi://"+direccionField.getText()+"/myabc");
		}
                catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(contenidoPanel, "ERROR, no nos conectaríamos ....");}		  
		}
        } 
	 
	public void print(Vector<ScrabbleJugadorInt> v) throws RemoteException {
            for(int i = 0; i < v.size(); i++) {
                System.out.println(v.get(i).getNombre());
            }
	}
	 
	public void empezarNuevoJuego() {
            loginFrame.setVisible(false);
            cliente.setNuevoJuego(new JuegoUI(cliente,servidor));
	}
	 
	public void addPlayerPool(ArrayList<String> name) {
            textArea.setText(null);
            for(int i=0;i<name.size();i++) {
                textArea.append(name.get(i) + "\n");
            }
	}
	
	/**
	 * Crear el frame.
	 */
	public IniciarSesionUI() {
            loginFrame.setBounds(100,100,400, 300);
            loginFrame.setLayout(null);
	
            JLabel lblUsername = new JLabel("Username:");
            lblUsername.setBounds(46, 27, 66, 16);
            loginFrame.add(lblUsername);
            
            nombreUsuarioField.setBounds(124, 22, 130, 26);
            loginFrame.add(nombreUsuarioField);
            nombreUsuarioField.setColumns(10);
            
            JLabel lblServerAddress = new JLabel("Server Address:");
            lblServerAddress.setBounds(17, 74, 102, 16);
            loginFrame.add(lblServerAddress);
            
            direccionField.setBounds(124, 69, 130, 26);
            direccionField.setColumns(10);
            loginFrame.add(direccionField);

            btnConectar.setBounds(275, 69, 117, 29);
            loginFrame.add(btnConectar);
		
            textArea.setBounds(17, 148, 235, 112);
            loginFrame.add(textArea);
		
            JLabel lblAvailablePlayers = new JLabel("Available Players:");
            lblAvailablePlayers.setBounds(17, 116, 117, 16);
            loginFrame.add(lblAvailablePlayers);
		
            btnInicio.setBounds(275, 213, 117, 29);
            loginFrame.add(btnInicio);
		
            btnConectar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){ 
                    hacerConexion();
                    try {
                        if(servidor.comprobarNombre(nombreUsuarioField.getText())==true) {
                            servidor.iniciarSesion(cliente);
                            servidor.borrarNombre();
                            servidor.addNombreALista();
                            servidor.publicar(nombreUsuarioField.getText());
                            btnConectar.setEnabled(false);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe, por favor ingrese nuevamente.", 
							"Nombre de usuario no válido", JOptionPane.ERROR_MESSAGE);
                        }
                    } 
                    catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
		} 
            });
		
            btnInicio.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){ 
                    int confirm = JOptionPane.showOptionDialog(loginFrame,
		          	"¿Estás seguro de que quieres comenzar un nuevo juego?", 
		            	"¿Empezar juego?", JOptionPane.YES_NO_OPTION,
		                   JOptionPane.QUESTION_MESSAGE, null, null, null);
		    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            print(servidor.getConectado());
                        } 
                        catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
		        try {
                            servidor.iniciarJuego();
			} 
                        catch (RemoteException e1) {
                            e1.printStackTrace();
			}
		    }
		} 
            });
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
            loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	}	
}
