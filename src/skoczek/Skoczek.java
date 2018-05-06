/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skoczek;

import java.awt.*;

/**
 *  Gra pod tytulem Matematyczny Skoczek
 * @author Sebastian Socik
 */

public class Skoczek {

    /**
     * Pobranie rozmiaru ekranu a nastepnie obliczenie wspołrzędnych narożnika 
     * okna gry tak, aby pole gry bylo wyśrodkowane
     * @param args
     */
    public static void main(String[] args) {
        int gameWidth = 1024;
        int gameHeight = 768;
              
        //pobranie rozmiaru ekranu
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        //obliczenie współrzędnych narożnika tak, aby pole gry było wyśrodkowane
        int xCorner = (screenWidth-gameWidth)/2;
        int yCorner = (screenHeight-gameHeight)/2;  
                
        //tworzenie obiektu klasy OknoGry
        OknoGry o = new OknoGry(gameWidth,gameHeight,xCorner,yCorner);
    
    }
    
}
