public class Customer extends User {
    private String userEmail;
    private String userMobile;

    /**
     * Constructs a customer object.
     * @param userId Must be unique, format: u_10 digits, such as u_1234567890
     * @param userName The user's name
     * @param userPassword The user's password
     * @param userRegisterTime Format: "DD-MM-YYYY_HH:MM:SS"
     * @param userRole Default value: "customer"
     * @param userEmail The customer's email address
     * @param userMobile The customer's mobile number
     */
    public Customer(String userId, String userName, String userPassword,
                    String userRegisterTime, String userRole,
                    String userEmail, String userMobile) {
        super(userId, userName, userPassword, userRegisterTime, userRole);
        this.userEmail = userEmail;
        this.userMobile = userMobile;
    }

    /**
     * Default constructor
     */
    public Customer() {
        super();
        this.userEmail = "";
        this.userMobile = "";
    }

    /**
     * Returns the customer information as a formatted string.
     * @return String in JSON-like format
     */
    @Override
    public String toString() {
        // Lấy chuỗi JSON từ User.toString()
        String userString = super.toString();
        // Cắt dấu đóng }
        String trimmedUser = userString.substring(0, userString.length() - 1);
        // Nối thêm email và mobile
        return trimmedUser +
               ", \"user_email\":\"" + userEmail + "\"" +
               ", \"user_mobile\":\"" + userMobile + "\"}";
    }
}


