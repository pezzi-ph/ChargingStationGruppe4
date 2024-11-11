package businessobjects;

public class Owner {

    private String username = "admin";
    private String password = "password123";
    private boolean loggedIn;

    public Owner(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Owner() {}


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


    @Override
    public String toString() {
        return "\nOwner{\n" +
                "username='" + username + '\'' +
                "\n}";
    }
}
