package pl.edu.agh.to.lab4.filter;

import pl.edu.agh.to.lab4.data.SuspectAggregate;
import pl.edu.agh.to.lab4.types.Suspect;

import java.util.Collection;

public class CompositeSearchStrategy implements SearchStrategy {
    private final Collection<SearchStrategy> searchStrategies;
    public CompositeSearchStrategy(Collection<SearchStrategy> strategies) {
        searchStrategies = strategies;
    }

    @Override
    public boolean filter(Suspect suspect) {
        for (SearchStrategy strategy : searchStrategies)
            if (!strategy.filter(suspect))
                return false;
        return true;
    }
}
