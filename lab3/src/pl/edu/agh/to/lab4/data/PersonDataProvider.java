package pl.edu.agh.to.lab4.data;

import pl.edu.agh.to.lab4.types.Person;
import pl.edu.agh.to.lab4.types.Suspect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PersonDataProvider implements SuspectAggregate {

    private final Collection<Person> cracowCitizens = new ArrayList<>();

    public PersonDataProvider() {
        cracowCitizens.add(new Person("Jan", "Kowalski", 30));
        cracowCitizens.add(new Person("Janusz", "Krakowski", 30));
        cracowCitizens.add(new Person("Janusz", "Mlodociany", 10));
        cracowCitizens.add(new Person("Kasia", "Kosinska", 19));
        cracowCitizens.add(new Person("Piotr", "Zgredek", 29));
        cracowCitizens.add(new Person("Tomek", "Gimbus", 14));
        cracowCitizens.add(new Person("Janusz", "Gimbus", 15));
        cracowCitizens.add(new Person("Alicja", "Zaczarowana", 22));
        cracowCitizens.add(new Person("Janusz", "Programista", 77));
        cracowCitizens.add(new Person("Pawel", "Pawlowicz", 32));
        cracowCitizens.add(new Person("Krzysztof", "Mendel", 30));
    }

    @Override
    public Iterator<Suspect> iterator() {
        return ((Collection<Suspect>)(Collection<?>) cracowCitizens).iterator();
    }
}
