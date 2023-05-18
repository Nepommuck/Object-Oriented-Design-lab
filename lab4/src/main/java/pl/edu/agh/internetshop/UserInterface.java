package pl.edu.agh.internetshop;

public interface UserInterface {
    public int getUserId();
    public String getEmailAddress();
    public String getFirstname();
    public void setFirstname(String firstname);
    public String getSurname();
    public void setSurname(String surname);
    public Gender getGender();
    public void setGender(Gender gender);
    public Address getAddress();
    public void setAddress(Address address);
}
