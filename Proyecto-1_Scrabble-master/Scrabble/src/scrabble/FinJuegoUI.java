/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.util.Map;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Bryan
 */
public class FinJuegoUI extends JFrame {
    private JFrame frame;
    
    public FinJuegoUI(ScrabbleJugador jugador, ScrabbleServidorInt servidor, Map<String, Integer> a) {
        
        frame = new JFrame("Marcador");
	frame.setLayout(null);
	frame.setBounds(100, 100, 300, 450);
	
	JLabel lblFinJuego = new JLabel("Fin del Juego!");
	lblFinJuego.setBounds(100, 20, 91, 16);
	frame.add(lblFinJuego);
		
	JTextArea textArea = new JTextArea();
	String marcador = "" ;
	for(String nombre: a.keySet() ) {
            String printOut ="Usuario "+ nombre +  " tiene una puntuación de " +a.get(nombre);
            marcador = marcador + printOut + "\n";
            System.out.println(marcador);
	}
	textArea.setText(marcador);
	textArea.setSize(275,400);
	textArea.setBounds(20,50,250,400);
	textArea.setBackground(null);
		
	frame.add(textArea);
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}