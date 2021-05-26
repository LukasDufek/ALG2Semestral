package app;

import utils.ComparatorPodleRocniku;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lukáš
 */
public class Testy {

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
    private ArrayList<Student> studenti;
    private ArrayList<Otazka> otazky;


    public Testy() {
        studenti = new ArrayList<>();
        otazky = new ArrayList<>();
    }

    /**
     * Pridani studenta do ArrayListu
     * @param s
     * @param studenti
     * @return 
     */
    public ArrayList<Student> kOstanimPridejStudenta(Student s, ArrayList<Student> studenti) {
        studenti.add(s);

        return studenti;
    }

    /**
     * za kazdou spravnou odpoved se prida 10 bodu
     * @return suma bodu
     */
    public int ziskaneBody() {
        int sum = 0;
        sum += 10;
        return sum;
    }

    /**
     * podle poctu bodu se udeli znamka
     * @param body
     * @return 
     */
    public int dejZnamku(int body) {
        int znamka = 4;
        if (body >= 90) {
            znamka = 1;
        } else if (body < 90 && body >= 80) {
            znamka = 2;
        } else if (body < 80 && body >= 60) {
            znamka = 3;
        }

        return znamka;
    }

    /**
     * Metoda pouze cte Test ze souboru a uklada ho do ArrayListu
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ArrayList<Otazka> nacteniTestu() throws FileNotFoundException, IOException {

        File Otazky = new File("OtazkyALG2Semestral.txt");
        Scanner sc = new Scanner(Otazky);
        Otazka o;

        String question;
        String answerA, answerB, answerC;
        String correctAnswer;

        while (sc.hasNext()) {
            question = sc.nextLine();
            correctAnswer = sc.nextLine();
            answerA = sc.nextLine();
            answerB = sc.nextLine();
            answerC = sc.nextLine();

            o = new Otazka(question, correctAnswer, answerA, answerB, answerC);
            otazky.add(o);

        }

        return otazky;
    }

    /**
     * metoda nacte studenty ze souboru do ArrayListu
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public ArrayList<Student> nactiStudenty() throws FileNotFoundException, IOException { //vezme soubor se studenty

        try (BufferedReader br = new BufferedReader(new FileReader(new File("StudentiALGSemestral.csv")))) {
            String radek;
            String[] parts;
            String jmeno, prijmeni;
            int ID, rocnik;
            Student s;
            int hodnoceni;
            LocalDate denTestu;
            while ((radek = br.readLine()) != null) { //vraci string
                parts = radek.split(",");
                ID = Integer.parseInt(parts[0]);
                String[] firstLast = parts[1].split(" ");
                jmeno = firstLast[0];
                prijmeni = firstLast[1];
                rocnik = Integer.parseInt(parts[2]);
                hodnoceni = Integer.parseInt(parts[3]);
                denTestu = LocalDate.parse(parts[4], dtf);

                s = new Student(ID, jmeno, prijmeni, rocnik, hodnoceni, denTestu);
                studenti.add(s);

            }
        }
        return studenti;

    }

    /**
     * Metoda ulozi vysledky se studenty do souboru
     * @param studenti
     * @throws IOException 
     */
    public void ulozVysledky(ArrayList<Student> studenti) throws IOException {

        File slozkaHodnoceni = new File("OutputData\\hodnoceni");
        if (!slozkaHodnoceni.exists()) {
            slozkaHodnoceni.mkdir();
        }
        File souborHodnoceni = new File("OutputData\\hodnoceni" + File.separator + "Studenti2021.txt");
        if (!souborHodnoceni.exists()) {
            souborHodnoceni.createNewFile();

        }

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(souborHodnoceni)))) {
            String v;
            for (Student s : studenti) {
                v = String.format("%5d %10s %15s %d %2d %15s", s.getID(), s.getJmeno(), s.getPrijmeni(), s.getRocnik(), s.getHodnoceni(), s.getDenTestu().format(dtf));
                pw.println(v);
            }
        }
    }

    /**
     * Metoda uklada vysledky do binarniho souboru, ale pouze ID a hodnoceni studenta
     * @param sBin
     * @throws IOException 
     */
    public void ulozVysledkyB(int[][] sBin) throws IOException { //pro binarni soubor

        File slozkaHodnoceni = new File("OutputData\\hodnoceni");
        if (!slozkaHodnoceni.exists()) {
            slozkaHodnoceni.mkdir();
        }
        File souborHodnoceni = new File("OutputData\\hodnoceni" + File.separator + "Studenti2021.dat");
        if (!souborHodnoceni.exists()) {
            souborHodnoceni.createNewFile();

        }

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(souborHodnoceni, true))) {
            for (int i = 0; i < sBin.length; i++) {
                int ID = sBin[i][0];
                int hodnoceni = sBin[i][1];
                out.write(ID);
                out.write(hodnoceni);
            }
        }
    }


}
