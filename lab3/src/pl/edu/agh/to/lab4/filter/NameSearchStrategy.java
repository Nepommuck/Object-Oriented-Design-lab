package pl.edu.agh.to.lab4.filter;

import pl.edu.agh.to.lab4.types.Suspect;

public class NameSearchStrategy implements SearchStrategy {
    private final String firstname;
    private final String lastname;

    public NameSearchStrategy(String firstname) {
        this(firstname, null);
    }
    public NameSearchStrategy(String firstname, String lastname) {
        firstname = (firstname != null && firstname.equals("")) ? null : firstname;
        lastname = (lastname != null && lastname.equals("")) ? null : lastname;
        if (firstname == null && lastname == null)
            throw new IllegalArgumentException("Either firstname or lastname must be not-null strings.");

        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public boolean filter(Suspect suspect) {
        return ((firstname == null || suspect.getFirstname().equals(firstname))
                && (lastname == null || suspect.getLastname().equals(lastname))) ;
    }
}
