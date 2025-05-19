package model;

public class Customer extends User {
    private String userEmail;
    private String userMobile;

    public Customer(String userId, String userName, String userPassword,
                    String userRegisterTime, String userRole,
                    String userEmail, String userMobile) {
        super(userId, userName, userPassword, userRegisterTime, userRole);
        this.userEmail = userEmail;
        this.userMobile = userMobile;
    }

    public Customer() {
        super();
        this.userEmail = "default@example.com";
        this.userMobile = "0400000000";
    }

    public String getUserEmail() { return userEmail; }
    public String getUserMobile() { return userMobile; }

    @Override
    public String toString() {
        return String.format("{\"user_id\":\"%s\", \"user_name\":\"%s\", " +
                "\"user_password\":\"%s\", \"user_register_time\":\"%s\", " +
                "\"user_role\":\"customer\", \"user_email\":\"%s\", \"user_mobile\":\"%s\"}",
                userId, userName, userPassword, userRegisterTime, userEmail, userMobile);
    }
}

