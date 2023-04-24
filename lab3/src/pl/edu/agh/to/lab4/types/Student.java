package pl.edu.agh.to.lab4.types;

import java.util.Calendar;

public class Student extends Suspect {
    private final String index;
    private final int birthyear;

    public Student(String firstname, String lastname, String index, int birthyear) {
        super(firstname, lastname);
        if (index == null || index.length() != 6)
            throw new IllegalArgumentException("Index must be a 6 digit-long string");

        this.index = index;
        this.birthyear = birthyear;
    }
    @Override
    public int getAge() {
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - birthyear;
    }
    @Override
    public boolean canBeAccused() {
        return true;
    }
    @Override
    public String toString() {
        return "(" + index.substring(0, 3) + " " + index.substring(3)+ ") " + super.toString();
    }
}
