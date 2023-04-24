package pl.edu.agh.to.lab4.types;

import pl.edu.agh.to.lab4.types.Suspect;

import java.util.Calendar;

public class Prisoner extends Suspect {
    private final int judgementYear;
    private final int sentenceDuration;
    private final String pesel;

    public Prisoner(String firstname, String lastname, String pesel, int judgementYear, int sentenceDuration) {
        super(firstname, lastname);

        this.pesel = pesel;
        this.judgementYear = judgementYear;
        this.sentenceDuration = sentenceDuration;
    }
    public boolean isJailedNow() {
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return judgementYear + sentenceDuration < currentYear;
    }
    @Override
    public int getAge() {
        final int peselYear = Integer.parseInt(pesel.substring(0, 2));
        final int peselMonth = Integer.parseInt(pesel.substring(2, 4));

        final int birthYear = (peselMonth >= 20) ? 2000 + peselYear : 1900 + peselYear;
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - birthYear;
    }
    @Override
    public boolean canBeAccused() {
        return !isJailedNow() && getAge() >= 18;
    }
}
