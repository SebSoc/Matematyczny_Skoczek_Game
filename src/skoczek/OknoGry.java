/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skoczek;
import javax.swing.*;
/**
 * Okno gry
 * @author Sebastian Socik
 */
public class OknoGry extends JFrame{
           
    /** obiekt reprezentujący panel gry*/
    public PanelGry pg;
    
    /**
     * Konstruktor - ustawienie parametrów okna gry
     * @param width szerokość okna
     * @param height wysokość okna
     * @param x współrzędna x narożnika okna
     * @param y wpółrzędna y narożnika okna
     */
    public OknoGry(int width, int height, int x, int y){
        super(); //wywołaj konstruktor klasy nadrzędnej - utwórz okno
        setSize(width, height); //ustaw wymiary okna
        setLocation(x,y); //ustaw pozycję okna
        setResizable(false); //zablokuj możliwość zmian rozmiaru okna
        setUndecorated(true); //ukryj ramkę okna i przyciski kontrolne
        initGUI(width,height); //wywołaj metodę budowy interfejsu
        setVisible(true); //pokaż okno
        animationLoop(); //uruchom pętlę animacji gry
    }
    
     private void initGUI(int width, int height){
        Param.loadInitialImages();
        Param.TworzZadania();
        pg = new PanelGry(width,height);
        add(pg); //dodaj panel gry zawierający grafikę i akcję
     }     
      
     
     private void animationLoop() {
       
        while (true) 
        {
          pg.zderzenia.checkJump();
          //odśwież ekran gry
          repaint();
          // przerwa pomiędzy odświeżaniem ekranu
          try 
          {
            Thread.sleep(80);
          } 
          catch (InterruptedException ex) 
          {
              System.out.println("Wyjątek: "+ex);      
          }
        }
    }
}
