package ui;

import app.Funkcionalita;
import app.Otazka;
import app.Student;
import app.Testy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import utils.TestInterface;

/**
 *
 * @author Lukáš
 */
public class Main {


    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Testy t = new Testy();
        ArrayList<Otazka> otazky = new ArrayList<>();
        ArrayList<Student> studenti = new ArrayList<>();
        boolean end = false;
        try{
        studenti = t.nactiStudenty();
        otazky = t.nacteniTestu();
        }catch(FileNotFoundException e){
            System.out.println("Tato aplikace potřebuje vstupní soubory");
            System.out.println("Aplikace nespuštěna");
            end = true;
        }
        TestInterface f = new Funkcionalita();
        
        while (!end){

            displayMenu();
            System.out.println("Zadej volbu");
            String choice = sc.next(); 
            switch (choice) {
                case "0":
                    System.out.println("Aplikace ukončena");
                    end = true;
                    break;
                case "1":
                    f.studentPiseTest(otazky, studenti);
                    break;
                case "2":
                    System.out.println(f.zobrazeni(studenti));
                    break;
                case "3":
                    f.seradPodleHodnoceni(studenti);
                    System.out.println(f.zobrazeni(studenti));
                    break;
                case "4":
                    f.seradPodlePrijmeni(studenti);
                    System.out.println(f.zobrazeni(studenti));
                    break;
                case "5":
                    f.seradPodleRocniku(studenti);
                    System.out.println(f.zobrazeni(studenti));
                    break;
                case "6":
                    System.out.println(f.zobrazeni(otazky));
                    break;
                case "7":
                    f.vytvorPdf(otazky);
                    break;
                case "8":
                    f.hledejVyraz(otazky);
                    break;
                default:
                    System.out.println("Neplatná volba");
            }

        }


    }
/**
 * zobrazeni menu
 */
    public static void displayMenu() {
        System.out.println("*****************************************************************");
        System.out.println("*   Vítejte v aplikaci ALDtest                                  *");
        System.out.println("*        Prosím zadejte svoji volbu                             *");
        System.out.println("* 1.Psát test                                                   *");
        System.out.println("* 2.Vypiš studenty, kteří psali test                            *");
        System.out.println("* 3.Seřaď studenty podle hodnocení                              *");
        System.out.println("* 4.Seřaď studenty podle příjmení                               *");
        System.out.println("* 5.Seřaď studenty podle ročníku                                *");
        System.out.println("* 6.Ukaž všechny otázky                                         *");
        System.out.println("* 7.Vygeneruj pdf soubor s otázkami                             *");
        System.out.println("* 8.Najdi v otázkách výraz                                      *");
        System.out.println("* 0.Konec                                                       *");
        System.out.println("*****************************************************************");
    
    }
}
