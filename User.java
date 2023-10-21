public class User {
    private String userId;
    private String username;
    private String password;

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public String getUserId(){
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkCredentials(String enteredUserId, String enteredPassword) {
        return userId.equals(enteredUserId) && password.equals(enteredPassword);
    }
}
