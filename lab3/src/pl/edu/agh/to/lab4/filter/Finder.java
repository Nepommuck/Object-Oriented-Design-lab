package pl.edu.agh.to.lab4.filter;

import pl.edu.agh.to.lab4.data.CompositeAggregate;
import pl.edu.agh.to.lab4.data.SuspectAggregate;
import pl.edu.agh.to.lab4.types.Suspect;

import java.util.ArrayList;
import java.util.Collection;

public class Finder {
    private final CompositeAggregate dataAggregate;

    public Finder(Collection<SuspectAggregate> data) {
        this.dataAggregate = new CompositeAggregate(data);
    }
    
    public void display() {
        display(null);
    }
    public void display(int maxLength) {
        display(maxLength, null);
    }
    public void display(SearchStrategy searchStrategy) {
        display(10, searchStrategy);
    }
    
    public void display(int maxLength, SearchStrategy searchStrategy) {
        if (searchStrategy == null)
            searchStrategy = new CompositeSearchStrategy(new ArrayList<>());

        final Collection<Suspect> suspects = new ArrayList<>();

        for (Suspect sus : dataAggregate.getAllSuspects()) {
            if (searchStrategy.filter(sus))
                suspects.add(sus);
            if (suspects.size() >= maxLength)
                break;
        }
        System.out.println("Znalazlem " + suspects.size() + " pasujacych podejrzanych!");

        for (Suspect sus : suspects) {
            System.out.println(sus);
        }
    }
}
