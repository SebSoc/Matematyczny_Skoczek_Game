/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skoczek;

/**
 * Klasa reprezentująca poruszające się obiekty w grze
 * @author Sebastian Socik
 */
public class Ruch {
    /** Początkowa współrzędna x obiektu */
    public int x;
    /** Początkowa współrzędna y obiektu */
    public int y;
    /** Aktualna współrzędna x obiektu */
    public int currX;
    /** Aktualna współrzędna y obiektu */
    public int currY;
    /** Krok przesunięcia obiektu w poziomie */
    public int dx;
    /** Krok przesunięcia obiektu w pionie */
    public int dy;
    /** Flaga określajaca kierunek poruszania się w pionie*/
    public boolean flyUp;
    /** Zezwolenie na skok*/
    public boolean jumpOn;
    /** Zmienna pomocnicza do uzyskania efektu chwilowego zawiśniecia w powietrzu postaci podczas skoku*/
    public int i = 0;
    /** Bufor pamietający aktualną predkość poruszania się w pionie*/
    public int dyBuf;
    
    /**
     * Konstruktor - ustawienie parametrow początkowych obiektu
     * @param x początkowe położenie w poziomie
     * @param y poczżtkowe położenie w pionie
     */
    public Ruch(int x, int y){
        this.x=x;
        this.y=y;
        currX=x;
        currY=y;
        this.dx=8;
        this.dy=dx;
        flyUp = true;
        jumpOn = false;
    }
    
    /** Metoda obliczająca położenie tła*/
    public void calculateBackgroundPos(){   
        currX=currX-dx;
        if(currX<-1050) { 
            currX=0;
        }    
    }
    
    /** Metoda obliczająca położenie przeszkody*/
    public void calculatePigPos(){   
        currX=currX-dx;
        if(currX<-200) { 
            currX=1024;
            Param.TworzZadania();
            PanelGry.wynik = " ";
            if(!Kolizja.kolizja)
            {
                StatusGry.points += 1;
            }
            Kolizja.kolizja = false;
            calculatePigSpeed();
        }    
    }
    
    /** Metoda obliczająca predkość poruszania się przeszkody*/
    public void calculatePigSpeed()
    {
        if(dx  < 50)
        {
            dx += 1;       
        }
    }
    
    /** Metoda obliczająca położenie postaci*/
    public void calculateBeePos(){          
            if(flyUp)                       //jeżeli zostaną spełnione warunki na skok
            {
                if(currY > 10)              //ograniczenie wysokości skoku
                {
                    currY=currY-dy;         //ruch w góre
                    if(currY < 10)          //dodatkowe ograniczenie wysokości skoku ze względu na rosnące dy
                    {
                        currY = 10;
                    }
                    if(currY < 50)          //wprowadzenie efektu chwilowego zawiśniecia w powietrzu
                    {
                        if(i == 0)
                        {
                            dyBuf = dy;     //zapamiętanie wartości dy aby opadanie odbywało się z tą samą prędkoscią
                            i++;
                        }
                        if(dy > 1)          //stopniowe spowolnienie
                        {
                            dy -= 1;
                        }  
                        else
                        {
                            flyUp = false;  //zezwolenie na rozpoczęcie opadania
                        }
                    }
                }
                else
                {
                    flyUp = false;
                    i = 0;
                }  
            }
            else                            //opadanie
            {
                if(currY < 300)             //ograniczenie miejsca spoczynku
                {
                    currY=currY+dy;         //ruch w dół
                    if(currY > 300)         //dodatkowe ograniczenie miejsca spoczynku ze względu na rosnące dy
                    {
                        currY = 300;
                    }
                    if(dy != dyBuf)         //stopniowe przyspieszanie
                    {
                        dy += 1;        
                    }
                }
                else                        //przygotowanie do kolejnego skoku
                {
                    flyUp = true;
                    jumpOn = false;
                    PanelGry.wynik = " ";
                    calculateBeeSpeed();   //zwiększenie prędkosci poruszania się w kolejnym skoku
                    i = 0;
                }
            }       
    }
    
    /** Metoda obliczająca predkość poruszania się postaci*/
    public void calculateBeeSpeed()
    {
        if(dy < 50)
        {
            dy += 1;      
        }
    }
}
