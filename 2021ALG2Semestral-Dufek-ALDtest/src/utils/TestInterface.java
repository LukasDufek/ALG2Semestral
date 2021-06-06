package utils;

import app.Otazka;
import app.Student;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Lukáš
 */
public interface TestInterface {
    
    
    /**
     * 
     * @param array
     * prevede arraylist do String
     * @return 
     */
    public String zobrazeni(ArrayList array);
    
    /**
     * 
     * @param otazky
     * @param studenti
     * nacte otazky ze souboru
     * nacte arraylist se studenaty, kteri uz test psali
     * nacte noveho studenta
     * da mu psat test
     * prida studenta i s hodnocenim k ostatnim
     * @return 
     */
    public void studentPiseTest(ArrayList<Otazka> otazky, ArrayList<Student> studenti);
    
    /**
     * 
     * @param studenti 
     * seradi podle rocniku
     */
    public void seradPodleRocniku(ArrayList<Student> studenti);
    
    /**
     * 
     * @param studenti 
     * seradi podle prijmeni
     */
    public void seradPodlePrijmeni(ArrayList<Student> studenti);
    
    /**
     * 
     * @param studenti 
     * seradi podle hodnoceni
     */
    public void seradPodleHodnoceni(ArrayList<Student> studenti);
    
    /**
     * 
     * @param otazky 
     * vytvori pdf soubor se vsemi otazkami
     */
    public void vytvorPdf(ArrayList<Otazka> otazky);
    
    /**
     * 
     * @param otazky 
     * vyhleda v otazkach zadany vyraz
     */
    public void hledejVyraz(ArrayList<Otazka> otazky);
}
