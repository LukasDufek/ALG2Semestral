package app;

import utils.ComparatorPodleRocniku;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.text.Collator;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ui.Main;
import utils.TestInterface;

/**
 * Trida tvori hlavni logiku aplikace
 *
 * @author Lukáš
 */
public class Funkcionalita implements TestInterface {

    public Funkcionalita() {
    }
    public static Scanner sc = new Scanner(System.in);

    /**
     * metoda prevadi ArrayList na String pomoci StringBuilder
     *
     * @param array
     * @return String
     */
    @Override
    public String zobrazeni(ArrayList array) {
        StringBuilder sb = new StringBuilder();
        for (Object o : array) {
            sb.append(o + "\n");
            //
        }
        return sb.toString();
    }

    /**
     * metoda nejdrive inicializuje noveho studenta pote danemu studentovi zada
     * test vyhodnoti jej prida do pole k ostatnim studentum nakonec vytvori
     * prislusne soubory
     *
     * @param otazky
     * @param studenti
     * @return
     */
    @Override
    public void studentPiseTest(ArrayList<Otazka> otazky, ArrayList<Student> studenti) {
        Testy t = new Testy();
        int ID = 0, rocnik = 0;
        String jmeno = null, prijmeni = null;
        Student s = null;
        //zamichani otazek
        Collections.shuffle(otazky);

        LocalDate denTestu = null;

        System.out.println("Zadej ID studenta (5ti místné číslo)");
        ID = sc.nextInt();
        System.out.println("Zadej jméno studenta");
        jmeno = sc.next();
        System.out.println("Zadej příjmení studenta");
        prijmeni = sc.next();
        System.out.println("Zadej ročník studia");
        rocnik = sc.nextInt();
        System.out.println("***Test začíná***");

        s = new Student(ID, jmeno, prijmeni, rocnik, 0, denTestu);
        s.setDenTestu(LocalDate.now());
        s.toString();
        int znamka = 0;
        int body = 0;
        String answer;

        for (int i = 0; i < 10; i++) {
            System.out.print(i + 1 + ".");
            System.out.print(otazky.get(i).toString());
            answer = sc.next();
            if (answer.equalsIgnoreCase(otazky.get(i).getCorrectAnswer())) {
                System.out.println("Správná odpověď \n");
                body += t.ziskaneBody();

            } else {
                System.out.println("Špatná odpověď \n");
            }

        }

        znamka = t.dejZnamku(body);
        s.setHodnoceni(znamka);
        System.out.println("Tvoje hodnocení je: " + znamka);
        studenti = t.kOstanimPridejStudenta(s, studenti);

        int m = studenti.size();
        int[][] studentiB = new int[m][2];

        int i = 0;

        for (Student sB : studenti) {
            studentiB[i][0] = sB.getID();
            studentiB[i][1] = sB.getHodnoceni();
            i++;
        }
        try {
            t.ulozVysledky(studenti);
        } catch (IOException ex) {
            System.out.println("Error");
        }
        try {
            t.ulozVysledkyB(studentiB);
        } catch (IOException ex) {
            System.out.println("Error");
        }

    }

    /**
     * serazeni studentu podle rocniku
     *
     * @param studenti
     */
    @Override
    public void seradPodleRocniku(ArrayList<Student> studenti) {
        ComparatorPodleRocniku CPR = new ComparatorPodleRocniku();
        Collections.sort(studenti, CPR);

    }

    /**
     * serazeni studentu podle prijmeni
     *
     * @param studenti
     */
    @Override
    public void seradPodlePrijmeni(ArrayList<Student> studenti) {
        Collections.sort(studenti, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                //kvuli prijmenim jako napr: Šimon
                return col.compare(o1.getPrijmeni(), o2.getPrijmeni());
            }
        });
    }

    /**
     * serazeni studentu podle hodnoceni
     *
     * @param studenti
     */
    @Override
    public void seradPodleHodnoceni(ArrayList<Student> studenti) {
        Collections.sort(studenti);

    }

    /**
     * vytvoreni pdf souboru s otazkami
     *
     * @param otazky
     */
    @Override
    public void vytvorPdf(ArrayList<Otazka> otazky) {
        final String FONT = "resources/fonts/FreeSans.ttf";
        String input = zobrazeni(otazky);
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Otazky.pdf"));
            document.open();
            Font f1 = FontFactory.getFont(FONT, BaseFont.HELVETICA, true);
            document.add(new Paragraph(input, f1));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * hledani zadaneho vyrazu v souboru otazky
     *
     * @param otazky
     */
    @Override
    public void hledejVyraz(ArrayList<Otazka> otazky) {
        String input = zobrazeni(otazky);
        int i = 0;
        System.out.println("Zadej hledany vyraz:");
        String regexPattern = sc.nextLine();
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(input);
        boolean found = false;
        while (matcher.find()) {
            i++;
            found = true;
        }
        if (!found) {
            System.out.println("Vyraz nenalezen.");
        } else {
            System.out.println("Vyraz " + regexPattern + " v textu nalezen " + i + "krát");
        }
    }

}
