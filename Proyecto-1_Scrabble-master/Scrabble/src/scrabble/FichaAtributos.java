/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

/**
 *
 * @author Bryan
 */
public enum FichaAtributos {
    // Utiliza frecuencias de SuperScrabble
    A(1, 16), B(3, 4), C(3, 6), D(2, 8), E(1, 24), F(4, 4), 
    G(2, 5), H(4, 5), I(1, 13), J(8, 2), K(5, 2), L(1, 7), 
    M(3, 6), N(1, 13), O(1, 15), P(3, 4), Q(10, 2), R(1, 13), 
    S(1, 10), T(1, 15), U(1, 7), V(4, 3), W(4, 4), X(8, 2), 
    Y(4, 4), Z(10, 2), BLANK(0, 4);

    // Representación de caracteres de azulejos vacíos y en blanco
    public static final char SIMBOLO_VACIO = '*';
    public static final char SIMBOLO_BLANCO = '#';	
	
    private final int puntuacion;
    private final int frecuencia;
	
    FichaAtributos(int puntuacion, int frecuencia) {
    	this.puntuacion = puntuacion;
	this.frecuencia = frecuencia;
    }
	
    public int getFrecuencia() {
	return this.frecuencia;
    }
	
    public int getPuntuacion() {
	return this.puntuacion;
    }
	
    // Encuentra el número total de fichas sumando todas las frecuencias
    public static int totalNumFichas() {
	int total = 0;
		
	for (FichaAtributos t : FichaAtributos.values()) {
            total += t.getFrecuencia();
	}
	return total;
    }
}

