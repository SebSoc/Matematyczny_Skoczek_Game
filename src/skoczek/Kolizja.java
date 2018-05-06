/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skoczek;

/**
 * Klasa reprezentująca zjawisko kolizji między postacią a przeszkodą
 * @author Sebastian Socik
 */
public class Kolizja {
    
    /** Obiekt przeszkody*/
    public Ruch swinka;

    /** Obiekt postaci*/
    public Ruch pszczola;

    /** Flaga wystąpienia kolizji*/
    public static boolean kolizja;
    
    /**
     * Konstruktor - pobranie parametrów i ustawienie początkowego stanu kolizji
     * @param swinka obiekt przeszkody
     * @param pszczola obiekt postaci
     */
    public Kolizja(Ruch swinka, Ruch pszczola)
    {
        this.swinka = swinka;
        this.pszczola = pszczola;
        kolizja = false;
    }
    
    /** Metoda sprawdzająca czy doszło do kolizji*/
    public void checkJump()
    {
        if(swinka.currX < pszczola.currX + 180 && swinka.currX > -100 && swinka.currY < pszczola.currY + 50 && !kolizja)
        {
            kolizja = true;
            PanelGry.wynik = ":(";
            StatusGry.lifes -= 1;
            if(StatusGry.lifes == 0)
            {
                StatusGry.end = true;
                StatusGry.game = false;
            }
        }
    }
    
}
