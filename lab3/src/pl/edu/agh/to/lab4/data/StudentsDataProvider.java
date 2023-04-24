package pl.edu.agh.to.lab4.data;

import pl.edu.agh.to.lab4.types.Person;
import pl.edu.agh.to.lab4.types.Student;
import pl.edu.agh.to.lab4.types.Suspect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class StudentsDataProvider implements SuspectAggregate {
    private final Collection<Student> students = new ArrayList<>();

    public StudentsDataProvider() {
        students.add(new Student("Janusz", "Piwosz", "798512", 2001));
        students.add(new Student("Mirosław", "Doktoryzujacysie", "987151", 1939));
        students.add(new Student("Leon", "Nadprogramowy", "123156", 2008));
    }
    @Override
    public Iterator<Suspect> iterator() {
        return ((Collection<Suspect>)(Collection<?>) students).iterator();
    }
}
