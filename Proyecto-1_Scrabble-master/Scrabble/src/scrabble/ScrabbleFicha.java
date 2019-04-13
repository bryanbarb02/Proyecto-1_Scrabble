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
public class ScrabbleFicha {
    private char letra;
    private int puntos;
	
    // El constructor vacío inicializa el mosaico vacío (al símbolo de carácter vacío)
    public ScrabbleFicha() {
        this.letra = FichaAtributos.SIMBOLO_VACIO;
	this.puntos = 0;
    }
	
    // Crear ficha con letra y por defecto
    public ScrabbleFicha(char letra) {
        this.letra = letra;
	// Convertir char a valor de puntuación predeterminado
	try {
            String ch = Character.toString(letra).toUpperCase();
            // Convertir símbolo de carácter de ficha en blanco
            if (letra == FichaAtributos.SIMBOLO_BLANCO) {
		ch = "BLANCO";
            }
            int puntuacion = FichaAtributos.valueOf(ch).getPuntuacion();
            this.puntos = puntuacion;
            }
            catch (IllegalArgumentException e) {
                this.puntos = 0;
            }
	}
	// Asigna diferentes puntos a la ficha.
	public ScrabbleFicha(char letra, int puntos) {
            this.letra = letra;
            this.puntos = puntos;
	}
	
	// Getter and Setter
	public char getLetra() {
            return this.letra;
	}
	
	public int getPuntos() {
            return this.puntos;
	}
	
	public void setLetra(char letra) {
            this.letra = letra;
	}
	
	public void setPuntos(int puntos) {
            this.puntos = puntos;
	}
	
	// String print values
	@Override
	public String toString() {
            return "(\"" + this.letra + "\", " + this.puntos + ")";
	}
	
	// Tiles are equal if both letter and point fields are the same
	@Override
	public boolean equals(Object obj) {
            if (obj == null || !ScrabbleFicha.class.isAssignableFrom(obj.getClass())) {
                return false;
            }
            final ScrabbleFicha ficha = (ScrabbleFicha) obj;
            return (this.letra == ficha.letra && this.puntos == ficha.puntos);
	}
}
