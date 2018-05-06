/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skoczek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Panel gry
 * @author Sebastian Socik
 */
public class PanelGry extends JPanel{
    
    /** Szerokość pola graficznego gry*/
    public int sWidth;
    /** Wysokość pola graficznego gry*/
    public int sHeight;
    /** Wysokość przycisków */
    public int przyciskWysokosc;
    /** Szerokość przycisku menu*/
    public int menuSzerokosc;
    /** Szerokość przycisku przerwij*/
    public int przerwijSzerokosc;
    /** Szerokość przycisku nowa gra*/
    public int nowagraSzerokosc;
    /** Szerokość przycisku zasady gry*/
    public int zasadySzerokosc;
    /** Szerokość przycisku wyjdź z gry*/
    public int wyjscieSzerokosc;
    /** Obiekt reprezentujący status gry*/
    public StatusGry gStatus;
    /** Obiekt rezprezentujący przeszkodę*/
    public Ruch swinka;
    /** Obiekt reprezentujący postać*/
    public Ruch pszczola;
    /** Obiekt reprezentujący tło gry*/
    public Ruch tlo;
    /** Obiekt reprezentujący zjawisko zderzenia się pszczoły ze świnka*/
    public Kolizja zderzenia;
    /** Obiekt przechowujący aktualnie wpisany przez gracza wynik działania*/
    public static String wynik;
    /** Obiekt pola do wpisywania swojego imienia*/
    public JTextField imiePole;
    
    /**
     * Konstruktor
     * - inicjalizacja początkowych ustawień gry
     * - wstawienie pola tekstowego do wpisania imienia
     * - obsługa zdarzeń myszki i klawiatury
     * @param width szerokość okna gry
     * @param height wysokość okna gry
     */
    public PanelGry(int width, int height)
    {
        gStatus = new StatusGry();
        gStatus.reset();
        this.sWidth = width;
        this.sHeight = height;
        przyciskWysokosc = 75;
        przerwijSzerokosc = 352;
        nowagraSzerokosc = 276;
        zasadySzerokosc = 312;
        menuSzerokosc = 157;
        wyjscieSzerokosc = 326;
        
        swinka = new Ruch(825, 345);
        pszczola = new Ruch(25, 305);
        tlo = new Ruch(0, 0);
        zderzenia = new Kolizja(swinka, pszczola);
        
        wynik = " ";
        
        setLayout(null);
        imiePole = new JTextField("Imię");
        imiePole.setBounds(250, 525, 500, 75);
        imiePole.setForeground(Color.GRAY);
        imiePole.setHorizontalAlignment(JTextField.CENTER);
        imiePole.setFont(new Font("SERIF",Font.BOLD,50));
        imiePole.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if("Imię".equals(imiePole.getText()))
                {
                    imiePole.setText("");   
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if("".equals(imiePole.getText()))
                {
                    imiePole.setText("Imię");   
                }
            }
        });
        add(imiePole);
        /* Dodaj obsługę zdarzeń - wciśnięcie przycisku myszki*/
        addMouseListener(new MouseAdapter(){
            @Override
          public void mouseClicked(MouseEvent me){     
              if(StatusGry.game)
              {
                if(me.getX() < przerwijSzerokosc && me.getY()>(sHeight-przyciskWysokosc)){                  
                      gStatus.reset();
                      swinka.currX = 825;
                }
              }
              else if(StatusGry.menu)
              {
                if(me.getX() > 350 && me.getX() < (350 + nowagraSzerokosc) && me.getY() > (sHeight - 2*przyciskWysokosc) && me.getY() < (sHeight - przyciskWysokosc))  {                  
                    StatusGry.game = true;
                    StatusGry.menu = false;
                }
                else if(me.getX() > 100 && me.getX() < (100 + zasadySzerokosc) && me.getY() > (sHeight - przyciskWysokosc) )  {                  
                    StatusGry.menu = false;
                    StatusGry.instruction = true;
                }
                else if(me.getX() > 500 && me.getX() < (500 + wyjscieSzerokosc) && me.getY() > (sHeight - przyciskWysokosc))  {                  
                    System.exit(0);
                }
                Kolizja.kolizja = false;
              }
              else if(StatusGry.end)
              {
                if(me.getX() < menuSzerokosc && me.getY()>(sHeight-przyciskWysokosc)){ 
                    gStatus.reset();
                }
              }
              else if(StatusGry.instruction)
              {
                if(me.getX() < menuSzerokosc && me.getY()>(sHeight-przyciskWysokosc)){ 
                    StatusGry.instruction = false;
                    StatusGry.menu = true;
                }
              }
          }
        });
        /* Dodaj obsługę zdarzeń - wciśnięcie przycisku klawiatury*/
         addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent ke)
            {
                checkResult(ke);
            }
        });
        setFocusable(true);     
    }
    
    /**
     * Metoda sprawdzająca poprawność wpowadzonego wyniku
     * @param ke zdarzenie klawiatury
     */
    public void checkResult(KeyEvent ke)
    {
        if(ke.getKeyChar() == 8)                        //obsługa backspace
        {
            wynik = " ";
        }
        else if(Character.isDigit(ke.getKeyChar()))     //sprawdzanie czy wpisywane sa cyfry
        {
            if(" ".equals(wynik))                       //jeżeli jest to pierwsza cyfra wpisywanego wyniku
            {
                wynik = Character.toString(ke.getKeyChar());
            }
            else if(wynik.length() < 3)                 //wynik maksymalnie 3 cyfrowy
            {
                wynik += ke.getKeyChar();
            }
        }
        else if(ke.getKeyChar() == '\n')                //oczekiwanie na zatwierdzenie odpowiedzi
        {
            if(Param.odpowiedz.equals(wynik))           //jeżeli odpowiedź poprawna podskocz
            {
                pszczola.jumpOn = true;
            }                     
            else                                        //jeżeli niepoprawna wyswietl komunikat
            {
                PanelGry.wynik = "źle";
            }
        }
    }
    
    /**
     * Rysowanie grafiki w grze
     * @param gs obiekt grafiki
     */
    @Override
     public void paintComponent(Graphics gs)
     {
        Graphics2D g=(Graphics2D)gs;
        //Ustaw tryb lepszej jakości grafiki (wygładzanie/antyaliasing)
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(Param.tlo, tlo.currX, 0, this);
        
        if(StatusGry.game)
        {
            tlo.calculateBackgroundPos();
            if(swinka.currX == 1024)            //w momencie pojawienia nowej przeszkody
            {
                tlo.calculatePigSpeed();
            }
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Dialog",Font.BOLD,50));
            g.drawImage(Param.serce, 800, 650, this);
            g.drawImage(Param.przeszkoda, swinka.currX, 345, this);
            swinka.calculatePigPos();
            g.drawImage(Param.postac, 25, pszczola.currY, this);
            if(pszczola.jumpOn)
            {
               pszczola.calculateBeePos();
            }
            g.drawString("Punkty: "+StatusGry.points, 700, 80);
            g.setColor(Color.LIGHT_GRAY);
            g.setFont(new Font("Dialog",Font.BOLD,70));
            g.drawString("= "+StatusGry.lifes, 895, 710);
            g.setFont(new Font("Dialog",Font.BOLD,100));
            g.drawString(Param.dzialanie + wynik, 250, 660);
            g.drawImage(Param.przerwij, 0, sHeight - przyciskWysokosc, this);
            imiePole.setVisible(false);
        }
        else if(StatusGry.menu)
        {
            /* Usunięcie warunków ewentualnej kolizji*/
            swinka.dx = 8;
            tlo.dx = 8;
            pszczola.dy = 8;
            swinka.currX = swinka.x;
            pszczola.currY = pszczola.y;
            tlo.currX = tlo.x;
            /* Rysowanie menu*/
            g.drawImage(Param.tytul, 100, przyciskWysokosc, this);
            g.drawImage(Param.nowagra, 350, sHeight - 2*przyciskWysokosc, this);
            g.drawImage(Param.zasady, 100, sHeight - przyciskWysokosc, this);
            g.drawImage(Param.wyjscie, 500, sHeight - przyciskWysokosc, this);
            imiePole.setVisible(true);                  
        }
        else if(StatusGry.end)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("Dialog",Font.BOLD,50));
            g.drawString("KONIEC GRY", 250, 250);
            g.drawString("BRAWO " + imiePole.getText(), 250, 350);
            g.drawString("TWOJ WYNIK TO: " + StatusGry.points, 250, 450);
            g.drawImage(Param.menu, 0, sHeight - przyciskWysokosc, this);
            imiePole.setVisible(false);
            /* Usunięcie warunków ewentualnej kolizji*/
            swinka.dx = 8;
            tlo.dx = 8;
            pszczola.dy = 8;
            swinka.currX = swinka.x;
            pszczola.currY = pszczola.y;
            tlo.currX = tlo.x;
        }
        else if(StatusGry.instruction)
        {
            g.drawImage(Param.instrukcja, 100, 100, this);
            g.drawImage(Param.menu, 0, sHeight - przyciskWysokosc, this);
            imiePole.setVisible(false);
        }
     }
}
