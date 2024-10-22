package businessobjects;

public class Owner {

    private String username = "admin";
    private String password = "password123";
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

    public boolean login(String username, String password) {
        this.loggedIn = this.username.equals(username) && this.password.equals(password);
        return loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
