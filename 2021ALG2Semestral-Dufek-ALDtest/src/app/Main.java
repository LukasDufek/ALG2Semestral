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
import ui.UI;
import utils.TestInterface;

/**
 *
 * @author Lukáš
 */
public class Main implements TestInterface {

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/M/d");

    public Main() {
    }
    public static Scanner sc = new Scanner(System.in);

    @Override
    public String zobrazeni(ArrayList array) { //zobrazi se i correctAnswer //do hlavicky metody arraylist
        StringBuilder sb = new StringBuilder();
        //otazky.forEach(o -> sb.append(o.toString()));
        for (Object o : array) {
            sb.append(o + "\n");
            //
        }
        return sb.toString();
    }

    /**
     *
     * @param otazky
     * @param studenti
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<Student> studentPiseTest(ArrayList<Otazka> otazky, ArrayList<Student> studenti) {
        Testy t = new Testy();
        int ID = 0, rocnik = 0;
        String jmeno = null, prijmeni = null;
        Student s = null;
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
        t.novyStudent(s);
        int znamka = 0;
        int body = 0;
        String answer;

        for (int i = 0; i < 10; i++) {
            System.out.print(i + 1 + ".");
            System.out.print(otazky.get(i).toString());
            //System.out.println("Vase odpoved je ");
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
        System.out.println("Tvoje znamka je: " + znamka);
        System.out.println(s.toString());
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
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }
        try {
            t.ulozVysledkyB(studentiB);
        } catch (IOException ex) {
            // Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }

        return studenti;
    }

    @Override
    public void seradPodleRocniku(ArrayList<Student> studenti) {
        ComparatorPodleRocniku CPR = new ComparatorPodleRocniku();
        Collections.sort(studenti, CPR);

    }

    @Override
    public void seradPodlePrijmeni(ArrayList<Student> studenti) { //annonymni trida
        Collections.sort(studenti, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                Collator col = Collator.getInstance(new Locale("cs", "CZ")); //kvuli prijmenim jako napr: Šimon
                return col.compare(o1.getPrijmeni(), o2.getPrijmeni());
            }
        });
    }

    @Override
    public void seradPodleHodnoceni(ArrayList<Student> studenti) {
        Collections.sort(studenti); //compareTo
    }

    public final String FONT = "resources/fonts/FreeSans.ttf";

    @Override
    public void vytvorPdf(ArrayList<Otazka> otazky) {

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
