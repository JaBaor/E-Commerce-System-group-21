public class Admin extends User {
    public Admin(String json) {
        super(json);
    }
    public Admin(String userId, String userName, String userPassword,
                 String userRegisterTime, String userRole) {
        super(userId, userName, userPassword, userRegisterTime, "admin");
    }

    public Admin() {
        super();
    }

    @Override
    public String toString() {
        String userString = super.toString();
        return userString.replaceAll("\"user_role\":\"[^\"]+\"", "\"user_role\":\"admin\"");
    }
}
