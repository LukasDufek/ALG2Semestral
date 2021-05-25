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

//import java.util.regex.Pattern;
/**
 *
 * @author Lukáš
 */
public class Testy {

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
    private ArrayList<Student> studenti;
    private ArrayList<Student> studentiB;
    private ArrayList<Otazka> otazky;
    private ArrayList<String> choices;

    private int vysledek;
    private LocalDate denTestu;

    public Testy() {
        studenti = new ArrayList<>(); //do arrayListu prijde soubor se studentama
        otazky = new ArrayList<>();
        choices = new ArrayList<>();
    }

    /**
     * deklaruj studenta dej mu test nastav jeho hodnoceni pridej ho do pole
     *
     * @param s
     * @return
     */
    public void novyStudent(Student s) {
        LocalDate denTestu = null;
        //denTestu.format(dtf);
        int hodnoceni = 0;
        s = new Student(s.getID(), s.getJmeno(), s.getPrijmeni(), s.getRocnik(), hodnoceni, denTestu);
        //s.setDenTestu(LocalDate.now());
        s.toString();
    }

    public ArrayList<Student> kOstanimPridejStudenta(Student s, ArrayList<Student> studenti) {
        studenti.add(s);

        return studenti;
    }

    public int ziskaneBody() {
        int sum = 0;
        sum += 10;
        return sum;
    }

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

//metody pro zobrazovani    
    public String zobrazeni(ArrayList array) { //zobrazi se i correctAnswer //do hlavicky metody arraylist
        StringBuilder sb = new StringBuilder();
        for (Object o : array) {
            sb.append(o + "\n");
            //
        }

        return sb.toString();

    }

    public String zobrazeni() { //zobrazeni studentu s vysledky
        StringBuilder sb = new StringBuilder();
        for (Student s : studenti) {
            sb.append(s + "\n");
        }
        return sb.toString();
    }

    public void seradPodleRocniku(ArrayList<Student> studenti) {
        ComparatorPodleRocniku CPR = new ComparatorPodleRocniku();
        Collections.sort(studenti, CPR);

    }

    public void seradPodlePrijmeni(ArrayList<Student> studenti) { //annonymni trida
        Collections.sort(studenti, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                Collator col = Collator.getInstance(new Locale("cs", "CZ")); //kvuli prijmenim jako napr: Šimon
                return col.compare(o1.getPrijmeni(), o2.getPrijmeni());
            }
        });
    }

    public void seradPodleHodnoceni(ArrayList<Student> studenti) {
        Collections.sort(studenti); //compareTo
    }

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

    public void toPDF(String input) {
        Document document = new Document();

        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Otazky.pdf"));

            document.open();
            document.add(new Paragraph(input));

            document.close();
            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public final String FONT = "resources/fonts/FreeSans.ttf";

    public void createPdf(String input) {
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


    /*

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Testy t = new Testy();
        ArrayList<Otazka> otazky = new ArrayList<>();
        ArrayList<Student> studenti = new ArrayList<>();
        studenti = t.nactiStudenty();
        otazky = t.nacteniTestu();
        LocalDate denTestu = null; //nejak vymyslet
        Student s = new Student(sc.nextInt(), sc.next(), sc.next(), sc.nextInt(), 0, denTestu);
        s.setDenTestu(LocalDate.now());
        t.novyStudent(s);
        //pro kontrolu      
        //System.out.println(s);
        Collections.shuffle(otazky);

        int znamka = 0;
        int body = 0;
        String answer;

        for (int i = 0; i < 3; i++) {
            //System.out.println("Otazka");
            System.out.print(otazky.get(i).toString());
            //System.out.println("Vase odpoved je ");
            answer = sc.next();
            if (answer.equalsIgnoreCase(otazky.get(i).getCorrectAnswer())) {
                System.out.println("Spravne \n");
                body += t.ziskaneBody();

            } else {
                System.out.println("Spatne \n");
            }

        }

        znamka = t.dejZnamku(body);
        s.setHodnoceni(znamka);
        //System.out.println("Tvoje hodnoceni je: " + znamka);
        studenti = t.kOstanimPridejStudenta(s, studenti);

        /*
        for (Student student : studenti) {
            int i=0;
            String radka= student.toString();
            String [] podretezce = radka.split("[-]");
            String MonthAndDay= ("|"+podretezce[i]+"|");
            System.out.println(MonthAndDay);
            i++;
        }
         
        int m = studenti.size();
        int[][] studentiB = new int[m][2];

        int i = 0;

        for (Student sB : studenti) {
            studentiB[i][0] = sB.getID();
            studentiB[i][1] = sB.getHodnoceni();
            i++;
        }

        System.out.println(t.zobrazeni(studenti));
        t.ulozVysledky(studenti);
        t.ulozVysledkyB(studentiB);

    }

}

     */
 /*
        try {
            while (true) {
                try {
                    t.vyhodnoceni();
                    System.out.println(t.zobrazeniOtazek(otazky));
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Zadali jste neexistujici soubor");
                }
            }
        } catch (IOException e) {
            System.out.println("Nastal problem: " + e.getMessage());
        }
    
    }
     */
 /*
    public static void main(String[] args) throws IOException {
        Testy t = new Testy();
        ArrayList<Student> studentiB = new ArrayList<>();
        ArrayList<Student> studenti = new ArrayList<>();
        ArrayList<Otazka> otazky = new ArrayList<>();
        studenti = t.nactiStudenty();
        int m= studenti.size();
        
        int [][] doBin= new int[m][2];

        int i=0;
        for (Student s : studenti) {
            doBin[i][0]=s.getID();
            doBin[i][1]=s.getHodnoceni();
            i++;
        }

        t.seradPodleRocniku(studenti);
        System.out.println(t.zobrazeni());
        
        //System.out.println(t.zobrazeniStudentu());
        //t.ulozVysledkyB(doBin);

    }
    
}
     */
    public static void main(String[] args) throws IOException {
        Testy t = new Testy();
        ArrayList<Otazka> otazky = new ArrayList<>();

        otazky = t.nacteniTestu();

        String input = t.zobrazeni(otazky);
        //String input ="Ahoj";
        t.toPDF(input);
        t.createPdf(input);

        
    }

}
