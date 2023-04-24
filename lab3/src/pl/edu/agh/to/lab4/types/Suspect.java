package pl.edu.agh.to.lab4.types;

public abstract class Suspect {
    protected final String firstname;

    protected final String lastname;

    public Suspect(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public abstract int getAge();
    public abstract boolean canBeAccused();
}
