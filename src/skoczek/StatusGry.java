/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skoczek;

import javax.swing.*;


/**
 * Status gry
 * @author Sebastian Socik
 */
public class StatusGry extends JPanel {
    /** Liczba zgromadzonych punktów*/
    public static int points;
    /** Ilość życ */
    public static int lifes;
    /** Czy ekran końca gry*/
    public static boolean end;
    /** Czy ekran menu*/
    public static boolean menu;
    /** Czy ekran zasad gry*/
    public static boolean instruction;
    /** Czy ekran gry*/
    public static boolean game;
        
   /**
     * Zeruj parametry gry
     */
    public void reset(){
        points=0;
        lifes = 3;
        end = false;
        menu = true;
        instruction = false;
        game = false;
        Kolizja.kolizja = false;
        PanelGry.wynik = " ";
    }
}
