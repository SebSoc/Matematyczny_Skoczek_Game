/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skoczek;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Ogólne parametry potrzebne w grze
 * @author Sebastian Socik
 */
public class Param {
    
    /** Grafika tła*/
    public static Image tlo;  
    /** Grafika przycisku przerwij*/
    public static Image przerwij;
    /** Grafika przycisku wyjdź z gry*/
    public static Image wyjscie;
    /** Grafika przycisku nowa gra*/
    public static Image nowagra;
    /** Grafika przycisku zasady gry*/
    public static Image zasady;
    /** Grafika przycisku menu*/
    public static Image menu;
    /** Grafika zasad gry*/
    public static Image instrukcja;
    /** Grafika tytułu gry*/
    public static Image tytul;
    /** Grafika serca reprezentującego ilość posiadanych żyć*/
    public static Image serce;
    /** Grafika postaci*/
    public static Image postac;
    /** Grafika przeszkody*/
    public static Image przeszkoda;  
    /** Pierwszy czynnik działania matematycznego*/
    public static int czynnik1;
    /** Drugi czynnik działania matematycznego*/
    public static int czynnik2;
    /** Poprawna odpowiedź działania matematycznego*/
    public static String odpowiedz;
    /** Całe działanie matematyczne bez odpowiedzi*/
    public static String dzialanie;
    
    /** Generowanie losowego działania*/
    public static void TworzZadania()
   {
       czynnik1 = new Random().nextInt(10) + 1;
       czynnik2 = new Random().nextInt(10) + 1;
       dzialanie = czynnik1 + " x " + czynnik2 + " = ";
       odpowiedz = Integer.toString(czynnik1 * czynnik2);
       
   }
    
    /** Wczytywanie grafiki*/
    public static void loadInitialImages() {
        tlo = loadImage("images\\landscape3.png");
        przerwij = loadImage("images\\przerwij.png");
        nowagra = loadImage("images\\nowagra.png");
        tytul = loadImage("images\\skoczek4.png");
        zasady = loadImage("images\\zasadygry.png");
        wyjscie = loadImage("images\\wyjscie.png");
        menu = loadImage("images\\menu.png"); 
        instrukcja = loadImage("images\\instrukcja.png");
        serce = loadImage("images\\heart.png");
        postac = loadImage("images\\bee2.png");
        przeszkoda = loadImage("images\\pig2.png");
        
    }
    
    /**
     * Metoda ułatwiająca wczytywanie grafiki
     * @param fileName ścieżka dostępu do grafiki
     * @return Image 
     */
    public static Image loadImage(String fileName) {
        return new ImageIcon(fileName).getImage();
    }
    
}
