package ec2_group9.idat.amorecaffeapp.model;

public class Auth {
    private String id;
    private String correo;
    private String password;
    private String token;

    public Auth() {
    }

    public Auth(String id, String correo, String password, String token) {
        this.id = id;
        this.correo = correo;
        this.password = password;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
