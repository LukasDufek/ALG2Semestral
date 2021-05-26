package utils;

import app.Student;
import java.util.Comparator;

/**
 * Trida slouzici pro serazeni podle rocniku
 * @author Lukáš
 */
public class ComparatorPodleRocniku implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getRocnik()- o2.getRocnik();
    }


    
}
