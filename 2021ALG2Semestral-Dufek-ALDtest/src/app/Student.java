package app;

import java.time.LocalDate;

/**
 *
 * @author Lukáš
 */
public class Student implements Comparable<Student>{
    private int ID;
    private String jmeno;
    private String prijmeni;
    private int rocnik;
    private int hodnoceni;
    private LocalDate denTestu;
     //private int body;

    public Student(int ID, int hodnoceni) {
        this.ID = ID;
        this.hodnoceni = hodnoceni;
    }
    

  
    

    public Student(int ID, String jmeno, String prijmeni, int rocnik) {
        this.ID = ID;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
        //this.body=0;
        this.hodnoceni = 0;
        this.denTestu = null;
    }

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

    public LocalDate setDenTestu(LocalDate denTestu) {
         return this.denTestu = denTestu;
    }

    @Override
    public String toString() {
        return "Student: "+ ID + " " + jmeno + " " + prijmeni + " " + rocnik + " " + hodnoceni + " " + denTestu;
    }


    @Override
    public int compareTo(Student o) {
      return this.hodnoceni-o.hodnoceni;
      
    }


    
    

    
    
    
    
    
}

