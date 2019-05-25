package udb.gl.Payload;

public class LoginModel {

    private String usrenameOrEmail;

    private String password;

    public LoginModel(String usrenameOrEmail, String password) {
        this.usrenameOrEmail = usrenameOrEmail;
        this.password = password;
    }

    public String getUsrenameOrEmail() {
        return usrenameOrEmail;
    }

    public void setUsrenameOrEmail(String usrenameOrEmail) {
        this.usrenameOrEmail = usrenameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
