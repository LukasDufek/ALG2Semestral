/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import app.Main;
import app.Otazka;
import app.Student;
import app.Testy;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lukáš
 */
public class UI {

    public UI() {
    }

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//id != int InputMismatchException
        Testy t = new Testy();
        ArrayList<Otazka> otazky = new ArrayList<>();
        ArrayList<Student> studenti = new ArrayList<>();
        studenti = t.nactiStudenty();
        otazky = t.nacteniTestu();
        Main m = new Main();
        boolean end = false;
        
        do {

            displayMenu();
            System.out.println("Zadej volbu");
            //answer = sc.next().charAt(0);
            String choice = sc.next(); //lokalni promenna
            switch (choice) {
                case "0":
                    System.out.println("Aplikace ukončena");
                    end = true;
                    break;
                case "1":
                    m.studentPiseTest(otazky, studenti);
                    break;
                case "2":
                    System.out.println(m.zobrazeni(studenti));

                    break;
                case "3":
                    //Seřaď studenti podle hodnoceni  
                    m.seradPodleHodnoceni(studenti);
                    System.out.println(m.zobrazeni(studenti));
                    break;
                case "4":
                    //Seřaď studenti podle prijmeni
                    m.seradPodlePrijmeni(studenti);
                    System.out.println(m.zobrazeni(studenti));
                    break;
                case "5":
                    //Seřaď studenti podle rocniku
                    m.seradPodleRocniku(studenti);
                    System.out.println(m.zobrazeni(studenti));
                    break;
                case "6":
                    //zobrazit vsechny otazky 
                    System.out.println(m.zobrazeni(otazky));
                    break;
                case "7":
                    //vygeneruje pdf, zatim bez uplne diakritiky
                    m.vytvorPdf(otazky);
                    break;
                case "8":
                    //vyhledavani vyrazu
                    m.hledejVyraz(otazky);
                    break;
                default:
                    System.out.println("Neplatná volba");
            }

        } while (!end);

       // menu();

    }

    public static void displayMenu() {
        System.out.println("*****************************************************************");
        System.out.println("*   Vítejte v aplikaci ALDtest                                  *");
        System.out.println("*        Prosím zadejte svoji volbu                             *");
        System.out.println("* 1.Psát test                                                   *");
        System.out.println("* 2.Vypiš studenty, kteří psali test                            *");
        System.out.println("* 3.Seřaď studenti podle hodnocení                              *");
        System.out.println("* 4.Seřaď studenti podle příjmení                               *");
        System.out.println("* 5.Seřaď studenti podle ročníku                                *");
        System.out.println("* 6.Ukaž všechny otázky                                         *"); //bez správných odpovědí
        System.out.println("* 7.Vygeneruj pdf soubor s otázkami                             *");
        System.out.println("* 8.Hledej výraz                                                *");
        //System.out.println("* 9.informace o aplikaci                                        *");
        System.out.println("* 0.Konec                                                       *");
        System.out.println("*****************************************************************");
    
    }
}
