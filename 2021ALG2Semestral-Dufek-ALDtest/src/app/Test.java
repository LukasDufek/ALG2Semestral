package app;

import utils.ComparatorPodleRocniku;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lukáš
 */
public class Test{

    
    private static final int POCET_OTAZEK_TESTU=3;

    private ArrayList<Otazka> otazky;

    public Test() throws IOException {

        otazky = new ArrayList<>();
        nacteniTestu();
    }

    

    /**
     * Metoda pouze cte Test ze souboru a uklada ho do ArrayListu
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void nacteniTestu() throws FileNotFoundException, IOException {

        File Otazky = new File("OtazkyALG2Semestral.txt"); //nazev jako konstanta
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

    }

    public ArrayList<Otazka> getOtazky() {
        ArrayList<Otazka> tmp=new ArrayList<>();

        Collections.copy(tmp, otazky);
        return tmp;//.copyOf(otazky,otazky.size());
    }
    private void zamichej(){
        Collections.shuffle(otazky);
    }
    
    
    
    public ArrayList<Otazka>novyTest(){
        ArrayList<Otazka>otazkyNovehoTestu=new ArrayList<>();
        zamichej();
        for (int i = 0; i < POCET_OTAZEK_TESTU; i++) {
            otazkyNovehoTestu.add(otazky.get(i));
        }
        return otazkyNovehoTestu;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Otazka o : otazky) {
            sb.append(o + "\n");
            //
        }
        return sb.toString();
    }

    public String toStringTest() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Otazka>novyTest=novyTest();
        for (Otazka o : novyTest) {
            sb.append(o + "\n");
            //
        }
        return sb.toString();
    }
    /*
    public void vytvorPdf() {
        final String FONT = "resources/fonts/FreeSans.ttf";
        String input = toString();
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
    */
    
    public void hledejVyraz() {
        Scanner sc = new Scanner(System.in);
        String input = toString();
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
    
    
    public static void main(String[] args) throws IOException {
        Test t= new Test();
        t.nacteniTestu();
        //System.out.println(t); //metoda automaticky vola toString
        System.out.println(t.toStringTest());
        
    }
    
    
    
    
//    
//    /**
//     * metoda nacte studenty ze souboru do ArrayListu
//     *
//     * @return
//     * @throws FileNotFoundException
//     * @throws IOException
//     */
//    public ArrayList<Student> nactiStudenty() throws FileNotFoundException, IOException { //vezme soubor se studenty
//
//        try (BufferedReader br = new BufferedReader(new FileReader(new File("OutputData" + File.separator + "StudentiALGSemestral.csv")))) {
//            String radek = null;
//            String[] parts;
//            String jmeno, prijmeni;
//            int ID, rocnik;
//            Student s;
//            int hodnoceni;
//            LocalDate denTestu;
//            while ((radek = br.readLine()) != null) { //vraci string
//                parts = radek.split(" ");
//                ID = Integer.parseInt(parts[0]);
//                //String[] firstLast = parts[1].split(" ");
//                jmeno = parts[1];
//                prijmeni = parts[2];
//                rocnik = Integer.parseInt(parts[3]);
//                hodnoceni = Integer.parseInt(parts[4]);
//                denTestu = LocalDate.parse(parts[5], dtf);
//
//                s = new Student(ID, jmeno, prijmeni, rocnik, hodnoceni, denTestu);
//                studenti.add(s);
//
//            }
//            br.close();
//        }
//        return studenti;
//
//    }
//
//    /**
//     * Metoda ulozi vysledky se studenty do souboru
//     *
//     * @param studenti
//     * @throws IOException
//     */
//    public void ulozVysledky(Student s) throws IOException {
//
//        File slozkaHodnoceni = new File("OutputData");
//        if (!slozkaHodnoceni.exists()) {
//            slozkaHodnoceni.mkdir();
//        }
//        File souborHodnoceni = new File("OutputData" + File.separator + "StudentiALGSemestral.csv");
//
////        if (!souborHodnoceni.exists()) {
////            souborHodnoceni.createNewFile();
////
////        }
//        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(souborHodnoceni, true)))) { //zapis na konec dat
//            String v;
//            //for (Student s : studenti) {
//            //v = String.format("%5d %10s %15s %d %d %10s", s.getID(), s.getJmeno(), s.getPrijmeni(), s.getRocnik(), s.getHodnoceni(), s.getDenTestu().format(dtf));
//            v = "" + s.getID() + " " + s.getJmeno() + " " + s.getPrijmeni() + " " + s.getRocnik() + " " + s.getHodnoceni() + " " + s.getDenTestu().format(dtf);
//            pw.println(v);
//
//            pw.close();
//        }
//
//    }
//
//    /**
//     * Metoda uklada vysledky do binarniho souboru, ale pouze ID a hodnoceni
//     * studenta
//     *
//     * @param sBin
//     * @throws IOException
//     */
//    public void ulozVysledkyB(int[][] sBin) throws IOException { //pro binarni soubor
//
//        File slozkaHodnoceni = new File("OutputData");
//        if (!slozkaHodnoceni.exists()) {
//            slozkaHodnoceni.mkdir();
//        }
//        File souborHodnoceni = new File("OutputData" + File.separator + "Studenti2021.dat");
//        //neni potreba pri zapise souboru
//        if (!souborHodnoceni.exists()) {
//            souborHodnoceni.createNewFile();
//
//        }
//
//        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(souborHodnoceni, true))) {
//            for (int i = 0; i < sBin.length; i++) {
//                int ID = sBin[i][0];
//                int hodnoceni = sBin[i][1];
//                out.writeInt(ID);
//                out.writeInt(hodnoceni);
//            }
//        }
//    }
//
//    public Student findByID(int ID) throws IOException {
//        studenti = nactiStudenty();
//        for (Student st : studenti) {
//            if (st.getID() == ID) {
//                return st;
//            }
//        }
//        return null;
//    }
//
//    public String getStudentTests(int ID) throws IOException {
//        Student s;
//        s = findByID(ID);
//        return s.toString();
//    }
//    */
//
//    /*
//    public void ulozVysledkyTry(ArrayList<Student> studenti) throws IOException {
//
//        File slozkaHodnoceni = new File("OutputData");
//        if (!slozkaHodnoceni.exists()) {
//            slozkaHodnoceni.mkdir();
//        }
//        File souborHodnoceni = new File("Studenti2021.txt");
//        
////        if (!souborHodnoceni.exists()) {
////            souborHodnoceni.createNewFile();
////
////        }
//
//
//        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(souborHodnoceni, true)))) { //zapis na konec dat
//            String radek = null;
//            String[] parts;
//            String jmeno, prijmeni;
//            int ID, rocnik;
//            Student s;
//            int hodnoceni;
//            LocalDate denTestu;
//            while ( (radek = pw.read()) != 0) 
//            { //vraci string
//                parts = radek.split(",");
//                ID = Integer.parseInt(parts[0]);
//                String[] firstLast = parts[1].split(" ");
//                jmeno = firstLast[0];
//                prijmeni = firstLast[1];
//                rocnik = Integer.parseInt(parts[2]);
//                hodnoceni = Integer.parseInt(parts[3]);
//                denTestu = LocalDate.parse(parts[4], dtf);
//
//                s = new Student(ID, jmeno, prijmeni, rocnik, hodnoceni, denTestu);
//                studenti.add(s);
//
//            }
//    }
//
//
//}
//     
}
