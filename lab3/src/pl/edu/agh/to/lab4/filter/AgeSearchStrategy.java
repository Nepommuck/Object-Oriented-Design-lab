package pl.edu.agh.to.lab4.filter;

import pl.edu.agh.to.lab4.types.Suspect;

public class AgeSearchStrategy implements SearchStrategy {
    private final int minAge;
    private final int maxAge;

    public AgeSearchStrategy(int minAge) {
        this(minAge, Integer.MAX_VALUE);
    }
    public AgeSearchStrategy(int minAge, int maxAge) {
        if (minAge > maxAge)
            throw new IllegalArgumentException("minAge bigger than maxAge");
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    @Override
    public boolean filter(Suspect suspect) {
        return minAge <= suspect.getAge() && suspect.getAge() <= maxAge;
    }
}
