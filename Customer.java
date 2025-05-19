public class Customer extends User {
    private String userEmail;
    private String userMobile;

    public Customer(String json) {
        super(json);
    }
    public Customer(String userId, String userName, String userPassword,
                    String userRegisterTime, String userRole,
                    String userEmail, String userMobile) {
        super(userId, userName, userPassword, userRegisterTime, userRole);
        this.userEmail = userEmail;
        this.userMobile = userMobile;
    }

    public Customer() {
        super();
        this.userEmail = "";
        this.userMobile = "";
    }

    @Override
    public String toString() {
        String userString = super.toString();
        String trimmedUser  = userString.substring(0, userString.length() - 1);
        return trimmedUser  +
               ", \"user_email\":\"" + userEmail + "\"" +
               ", \"user_mobile\":\"" + userMobile + "\"}";
    }
}
