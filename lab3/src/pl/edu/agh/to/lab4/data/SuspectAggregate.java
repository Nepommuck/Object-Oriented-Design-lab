package pl.edu.agh.to.lab4.data;

import pl.edu.agh.to.lab4.types.Suspect;

import java.util.Iterator;

public interface SuspectAggregate {
    Iterator<Suspect> iterator();
}
