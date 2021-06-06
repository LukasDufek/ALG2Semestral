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

    public Student(int ID, String jmeno, String prijmeni, int rocnik, int hodnoceni, LocalDate denTestu) {
        this.ID = ID;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
        this.hodnoceni = hodnoceni;
        this.denTestu = denTestu;
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

    public void setHodnoceni(int hodnoceni) {
        this.hodnoceni = hodnoceni;
    }

    public void setDenTestu(LocalDate denTestu) {
        this.denTestu = denTestu;
    }

    @Override
    public String toString() {
        return String.format("Student: %5d%13s%15s %d.rocnik, hodnoceni:%d, psal test dne:%10s", ID, jmeno, prijmeni, rocnik, hodnoceni, denTestu);

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
