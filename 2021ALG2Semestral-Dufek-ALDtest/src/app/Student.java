package app;

import java.time.LocalDate;

/**
 * trida urcena k implementaci Studenta
 * @author Lukáš
 */

public class Student implements Comparable<Student> {

    private int ID;
    private String jmeno;
    private String prijmeni;
    private int rocnik;
    private int hodnoceni;
    private LocalDate denTestu;
    
    private int body=0;
    private static final int BODY_ZA_OTAZKU=10;

    public Student(int ID, String jmeno, String prijmeni, int rocnik, int hodnoceni, LocalDate denTestu) {
        this.ID = ID;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
        this.hodnoceni = hodnoceni;
        this.denTestu = denTestu;
    }

    public Student(int ID, String jmeno, String prijmeni, int rocnik) {
        this.ID = ID;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
        this.hodnoceni = 0;
        this.denTestu = LocalDate.now();
    }

    public int getID() {
        return ID;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public int getRocnik() {
        return rocnik;
    }

    public int getHodnoceni() {
        return hodnoceni;
    }

    public LocalDate getDenTestu() {
        return denTestu;
    }



    

    @Override
    public String toString() {
        return String.format("Student: %5d%13s%15s %d.rocnik, hodnoceni:%d, psal/a test dne:%10s", ID, jmeno, prijmeni, rocnik, hodnoceni, denTestu);

    }
    
    /**
     * za kazdou spravnou odpoved se prida 10 bodu
     *
     * @return suma bodu
     */
    public void pridejBody() {
        this.body+=BODY_ZA_OTAZKU;
    }

    /**
     * podle poctu bodu se udeli znamka
     *
     * @param body
     * @return
     */
    public void ohodnot() {
        int hodnoceni = 4;
        if (body >= 90) {
            hodnoceni = 1;
        } else if (body < 90 && body >= 80) {
            hodnoceni = 2;
        } else if (body < 80 && body >= 60) {
            hodnoceni = 3;
        }

    }
    

    /**
     * Serazeni studenta podle hodnocenir
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Student o) {
        return this.hodnoceni - o.hodnoceni;

    }
//public int compareTo2(Student o) {
//        return o.hodnoceni - this.hodnoceni;
//
//    }
}
