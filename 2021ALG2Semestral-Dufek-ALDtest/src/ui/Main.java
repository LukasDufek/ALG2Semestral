package ui;

import app.Otazka;
import app.Student;
import app.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.ComparatorPodleRocniku;
import utils.TestInterface;

/**
 *
 * @author Lukáš
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Test t;
    public static ArrayList<Student> studenti;
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");

    public static void main(String[] args) {
        studenti = new ArrayList<>();
        try {
            t = new Test();
            try {
                studenti = nactiStudenty();
            } catch (FileNotFoundException e) {
                System.out.println("Nenacten soubor se studentama, nebudou se vypisovat studenti");
            }
            //Student s;
            boolean end = false;

            while (!end) {

                displayMenu();
                System.out.println("Zadej volbu");
                String choice = sc.next();
                switch (choice) {
                    case "0":
                        System.out.println("Aplikace ukončena");
                        end = true;
                        break;
                    case "1":
                        psatTest();
                        break;
                    case "2":
                        vypisStudentyKteriPsaliTest();

                        break;
                    case "3":
                        seradStudentyPodleHodnoceni();
                        break;
                    case "4":
                        seradStudentyPodlePrijmeni();
                        break;
                    case "5":
                        seradStudentyPodleRocniku();
                        break;
                    case "6":
                        zobrazVsechnyOtazky();
                        break;
                    case "7":
                       // t.vytvorPdf();
                        break;
                    case "8":
                        t.hledejVyraz();
                        break;

                    default:
                        System.out.println("Neplatná volba");
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Nepodarilo se najit soubor s otazkami");
        } catch (IOException e) {
            System.out.println("Problem se souborem");
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

    private static void psatTest() {

        ArrayList<Otazka> otazkyNovehoTestu = t.novyTest();
        String jmeno, prijmeni;
        int ID, rocnik;

        System.out.println("Zadej ID studenta");
        ID = sc.nextInt();
        System.out.println("Zadej jméno studenta");
        jmeno = sc.next();
        System.out.println("Zadej příjmení studenta");
        prijmeni = sc.next();
        System.out.println("Zadej ročník studia");
        rocnik = sc.nextInt();
        System.out.println("***Test začíná***");

        Student s = new Student(ID, jmeno, prijmeni, rocnik);
        studenti.add(s);
        zobrazeniTestu(ID);
        s.ohodnot(); //da studentovi znamku
        System.out.println(s.toString());

    }

    public static ArrayList<Student> nactiStudenty() throws FileNotFoundException, IOException { //vezme soubor se studenty

        try (BufferedReader br = new BufferedReader(new FileReader(new File("OutputData" + File.separator + "StudentiALGSemestral.csv")))) {
            String radek = null;
            String[] parts;
            String jmeno, prijmeni;
            int ID, rocnik;
            Student s;
            int hodnoceni;
            LocalDate denTestu;
            while ((radek = br.readLine()) != null) { //vraci string
                parts = radek.split(" ");
                ID = Integer.parseInt(parts[0]);
                //String[] firstLast = parts[1].split(" ");
                jmeno = parts[1];
                prijmeni = parts[2];
                rocnik = Integer.parseInt(parts[3]);
                hodnoceni = Integer.parseInt(parts[4]);
                denTestu = LocalDate.parse(parts[5], dtf);

                s = new Student(ID, jmeno, prijmeni, rocnik, hodnoceni, denTestu);

                studenti.add(s);

            }
            br.close();
        }
        return studenti;

    }

    public static void zobrazeniTestu(int ID) {

        ArrayList<Otazka> otazky = t.novyTest();
        String answer;
        Student s;
        for (int i = 0; i < otazky.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.print(otazky.get(i).toString());
            answer = sc.next();
            if (otazky.get(i).isCorrectAnswer(answer)) {
                studenti.get(ID).pridejBody();
            }
        }
    }

    private static void vypisStudentyKteriPsaliTest() {

        StringBuilder sb = new StringBuilder();
        for (Student s : studenti) {
            sb.append(s + "\n");
            //
        }
        String s = sb.toString();
        System.out.println(s);
    }

    private static void seradStudentyPodleHodnoceni() {

        ComparatorPodleRocniku CPR = new ComparatorPodleRocniku();
        Collections.sort(studenti, CPR);

        vypisStudentyKteriPsaliTest();
    }

    private static void seradStudentyPodlePrijmeni() {
        Collections.sort(studenti, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                //kvuli prijmenim jako napr: Šimon
                return col.compare(o1.getPrijmeni(), o2.getPrijmeni());
            }
        });
        vypisStudentyKteriPsaliTest();
    }

    private static void seradStudentyPodleRocniku() {
        ComparatorPodleRocniku CPR = new ComparatorPodleRocniku();
        Collections.sort(studenti, CPR);
        vypisStudentyKteriPsaliTest();
    }

    private static void zobrazVsechnyOtazky() {
        System.out.println(t.toString());
    }

}
