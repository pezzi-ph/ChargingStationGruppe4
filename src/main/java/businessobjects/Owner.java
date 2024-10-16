package businessobjects;

public class Owner {

    private String username;
    private String password;
    private boolean loggedIn;

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login() {
        this.loggedIn = true;
        return loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
