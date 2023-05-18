package pl.edu.agh.internetshop;

public class User implements UserInterface {
    private static int FIRST_FREE_ID = 0;
    private final int userId;
    private final String emailAddress;
    private String firstname;
    private String surname;
    private Gender gender;
    private Address address;

    public User(String firstname, String surname, String emailAddress) {
        this(firstname, surname, emailAddress, (Gender) null);
    }
    public User(String firstname, String surname, String emailAddress, Gender gender) {
        this(firstname, surname, emailAddress, gender, null);
    }
    public User(String firstname, String surname, String emailAddress, Address address) {
        this(firstname, surname, emailAddress, null, address);
    }
    public User(String firstname, String surname, String emailAddress, Gender gender, Address address) {
        if (firstname == null || surname == null || emailAddress == null)
            throw new IllegalArgumentException("Firstname, surname and emailAddress cannot be null.");

        this.userId = getFirstFreeId();
        this.emailAddress = emailAddress;
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.address = address;
    }

    private int getFirstFreeId() {
        int result = FIRST_FREE_ID;
        FIRST_FREE_ID += 1;
        return result;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
