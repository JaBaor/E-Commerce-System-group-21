package model;

public abstract class User {
    protected String userId;
    protected String userName;
    protected String userPassword;
    protected String userRegisterTime;
    protected String userRole;

    public User(String userId, String userName, String userPassword,
                String userRegisterTime, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRegisterTime = userRegisterTime;
        this.userRole = userRole;
    }

    public User() {
        this("u_0000000000", "default", "default", "01-01-2025_00:00:00", "customer");
    }

    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getUserPassword() { return userPassword; }
    public String getUserRegisterTime() { return userRegisterTime; }
    public String getUserRole() { return userRole; }

    @Override
    public String toString() {
        return String.format("{\"user_id\":\"%s\", \"user_name\":\"%s\", " +
                "\"user_password\":\"%s\", \"user_register_time\":\"%s\", \"user_role\":\"%s\"}",
                userId, userName, userPassword, userRegisterTime, userRole);
    }
}
