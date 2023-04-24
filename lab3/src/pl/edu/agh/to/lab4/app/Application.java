package pl.edu.agh.to.lab4.app;

import pl.edu.agh.to.lab4.data.StudentsDataProvider;
import pl.edu.agh.to.lab4.filter.*;
import pl.edu.agh.to.lab4.data.PersonDataProvider;
import pl.edu.agh.to.lab4.data.PrisonersDataProvider;

import java.util.*;

import java.lang.String;

import static java.lang.System.out;

public class Application {
    public static void main(String[] args) {
        Finder suspects = new Finder(Arrays.asList(
                new PersonDataProvider(), new PrisonersDataProvider(), new StudentsDataProvider()
        ));

        out.println("NO SEARCH STRATEGIES: ");
        suspects.display(3);

        out.println("\nMŁODOCIANI PRZESTĘPCY SEARCH: ");
        suspects.display(new AgeSearchStrategy(9, 16));

        out.println("\nJANUSZE KTÓRYM MOŻNA POSTAWIĆ ZARZUTY: ");
        suspects.display(new CompositeSearchStrategy(
                Arrays.asList(new NameSearchStrategy("Janusz"), new AccusableSearchStrategy())
        ));


        out.println("\nEMERYCI: ");
        suspects.display(new AgeSearchStrategy(65));

    }
}
