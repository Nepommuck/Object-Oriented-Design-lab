package pl.edu.agh.to.lab4.filter;

import pl.edu.agh.to.lab4.types.Suspect;

public interface SearchStrategy {
    public boolean filter(Suspect suspect);
}
