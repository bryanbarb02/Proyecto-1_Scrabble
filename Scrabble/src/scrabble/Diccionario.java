package scrabble;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Diccionario {
    ArrayList<String> arrList = new ArrayList<String>();
    private Iterator stepper = arrList.iterator();
    BufferedReader bufReader = new BufferedReader(new FileReader("Dict.txt"));
    
    public Diccionario()throws IOException {
        while(bufReader.ready()){
            String strLine = bufReader.readLine();
            arrList.add(strLine);
        }
    }

    public boolean BuscarDiccionario(String palabra){
        if(arrList.contains(palabra)){
           return true;
        }
        else{
           return false;
        }
    }
}