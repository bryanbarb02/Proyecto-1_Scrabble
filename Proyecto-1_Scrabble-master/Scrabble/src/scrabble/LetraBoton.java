/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Bryan
 */
public class LetraBoton extends JButton {
    ImageIcon A = new ImageIcon("src/image/letter-a.png");
    ImageIcon B = new ImageIcon("src/image/letter-b.png");
    ImageIcon C = new ImageIcon("src/image/letter-c.png");
    ImageIcon D = new ImageIcon("src/image/letter-d.png");
    ImageIcon E = new ImageIcon("src/image/letter-e.png");
    ImageIcon F = new ImageIcon("src/image/letter-f.png");
    ImageIcon G = new ImageIcon("src/image/letter-g.png");
    ImageIcon H = new ImageIcon("src/image/letter-h.png");
    ImageIcon I = new ImageIcon("src/image/letter-i.png");
    ImageIcon J = new ImageIcon("src/image/letter-j.png");
    ImageIcon K = new ImageIcon("src/image/letter-k.png");
    ImageIcon L = new ImageIcon("src/image/letter-l.png");
    ImageIcon M = new ImageIcon("src/image/letter-m.png");
    ImageIcon N = new ImageIcon("src/image/letter-n.png");
    ImageIcon O = new ImageIcon("src/image/letter-o.png");
    ImageIcon P = new ImageIcon("src/image/letter-p.png");
    ImageIcon Q = new ImageIcon("src/image/letter-q.png");
    ImageIcon R = new ImageIcon("src/image/letter-r.png");
    ImageIcon S = new ImageIcon("src/image/letter-s.png");
    ImageIcon T = new ImageIcon("src/image/letter-t.png");
    ImageIcon U = new ImageIcon("src/image/letter-u.png");
    ImageIcon V = new ImageIcon("src/image/letter-v.png");
    ImageIcon W = new ImageIcon("src/image/letter-w.png");
    ImageIcon X = new ImageIcon("src/image/letter-x.png");
    ImageIcon Y = new ImageIcon("src/image/letter-y.png");
    ImageIcon Z = new ImageIcon("src/image/letter-z.png");
	
    char letra = 0;
    boolean seleccionado = false;

    private static final long serialVersionUID = 1L;
	
    public LetraBoton() {
	Random r = new Random();
	char c = (char) (r.nextInt(26) + 'a');
	letra = c;
		
	switch(letra) {
            case (char)'a':
                setIcon(A);
                break;
            case (char)'b':
                setIcon(B);
                break;
            case (char)'c':
                setIcon(C);
                break;
            case (char)'d':
                setIcon(D);
                break;
                case (char)'e':
                setIcon(E);
                break;
            case (char)'f':
                setIcon(F);
                break;
            case (char)'g':
                setIcon(G);
                break;
            case (char)'h':
                setIcon(H);
                break;
            case (char)'i':
                setIcon(I);
                break;
            case (char)'j':
                setIcon(J);
                break;
            case (char)'k':
                setIcon(K);
                break;
            case (char)'l':
                setIcon(L);
                break;
            case (char)'m':
                setIcon(M);
                break;
            case (char)'n':
                setIcon(N);
                break;
            case (char)'o':
                setIcon(O);
                break;
            case (char)'p':
                setIcon(P);
                break;
            case (char)'q':
                setIcon(Q);
                break;
            case (char)'r':
                setIcon(R);
                break;
            case (char)'s':
                setIcon(S);
                break;
            case (char)'t':
                setIcon(T);
                break;
            case (char)'u':
                setIcon(U);
                break;
            case (char)'v':
                setIcon(V);
                break;
            case (char)'w':
                setIcon(W);
                break;
            case (char)'x':
                setIcon(X);
                break;
            case (char)'y':
                setIcon(Y);
                break;
            case (char)'z':
                setIcon(Z);
                break;
            default:
                setIcon(null);
                break;
	}
    }
	
    public char getLetter() {
	return letra;
    }
	
    public void actualizarLetra() {
	Random r = new Random();
	char c = (char) (r.nextInt(26) + 'a');
	letra = c;
		
        switch(letra) {
            case (char)'a':
                setIcon(A);
                break;
            case (char)'b':
                setIcon(B);
                break;
            case (char)'c':
                setIcon(C);
                break;
            case (char)'d':
                setIcon(D);
                break;
            case (char)'e':
                setIcon(E);
                break;
            case (char)'f':
                setIcon(F);
                break;
            case (char)'g':
                setIcon(G);
                break;
            case (char)'h':
                setIcon(H);
                break;
            case (char)'i':
                setIcon(I);
                break;
            case (char)'j':
                setIcon(J);
                break;
            case (char)'k':
                setIcon(K);
                break;
            case (char)'l':
                setIcon(L);
                break;
            case (char)'m':
                setIcon(M);
                break;
            case (char)'n':
                setIcon(N);
                break;
            case (char)'o':
                setIcon(O);
                break;
            case (char)'p':
                setIcon(P);
                break;
            case (char)'q':
                setIcon(Q);
                break;
            case (char)'r':
                setIcon(R);
                break;
            case (char)'s':
                setIcon(S);
                break;
            case (char)'t':
                setIcon(T);
                break;
            case (char)'u':
                setIcon(U);
                break;
            case (char)'v':
                setIcon(V);
                break;
            case (char)'w':
                setIcon(W);
                break;
            case (char)'x':
                setIcon(X);
                break;
            case (char)'y':
                setIcon(Y);
                break;
            case (char)'z':
                setIcon(Z);
                break;
            default:
                setIcon(null);
                break;
        }
    }
	
    public boolean isSelected() {
	return this.seleccionado;
    }
	
    public void setSelected(boolean selected) {
        this.seleccionado = selected;
    }
}
