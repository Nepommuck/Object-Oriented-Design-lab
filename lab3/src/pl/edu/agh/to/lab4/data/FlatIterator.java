package pl.edu.agh.to.lab4.data;

import pl.edu.agh.to.lab4.types.Prisoner;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class FlatIterator<Suspect> implements Iterator<Suspect> {
    private final Map<String, Collection<Prisoner>> collection;
    private final Iterator<String> outerIterator;
    private Iterator<Prisoner> innerIterator;

    public FlatIterator(Map<String, Collection<Prisoner>> collection) {
        this.collection = collection;
        outerIterator = collection.keySet().iterator();
        innerIterator = collection.get(outerIterator.next()).iterator();
    }

    @Override
    public boolean hasNext() {
        if (innerIterator.hasNext())
            return true;
        if (!outerIterator.hasNext())
            return false;
        innerIterator = collection.get(outerIterator.next()).iterator();
        return hasNext();
    }

    @Override
    public Suspect next() {
        if (innerIterator.hasNext())
            return (Suspect) innerIterator.next();
        innerIterator = collection.get(outerIterator.next()).iterator();
        return next();
    }
}
