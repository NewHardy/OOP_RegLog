public class User
{
    private String userName;
    private String password;
    private boolean isAdmin;
    private boolean isModerator;
    private String email;
    private String birthDate;
    private String phoneNumber;
    public User(String userName,String password,boolean isAdmin, boolean isModerator,String email, String birthDate, String phoneNumber)
    {
        this.userName=userName;
        this.password=password;
        this.isAdmin=isAdmin;
        this.isModerator=isModerator;
        this.email=email;
        this.birthDate=birthDate;
        this.phoneNumber=phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isModerator() {
        return isModerator;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
