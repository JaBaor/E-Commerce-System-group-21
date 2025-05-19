public class Admin extends User {

    /**
     * Constructs an admin object.
     * @param userId Must be unique, format: u_10 digits, such as u_1234567890
     * @param userName The user's name
     * @param userPassword The user's password
     * @param userRegisterTime Format: "DD-MM-YYYY_HH:MM:SS"
     * @param userRole Default value: "admin"
     */
    public Admin(String userId, String userName, String userPassword,
                 String userRegisterTime, String userRole) {
        super(userId, userName, userPassword, userRegisterTime, userRole);
    }

    /**
     * Default constructor
     */
    public Admin() {
        super();
    }

    /**
     * Returns the admin's attributes as a formatted string.
     * @return String in JSON-like format
     */
    @Override
    public String toString() {
        // Lấy chuỗi JSON từ User.toString()
        String userString = super.toString();

        // Ghi đè giá trị "user_role":"..." thành "user_role":"admin"
        String updated = userString.replaceAll("\"user_role\":\"[^\"]+\"", "\"user_role\":\"admin\"");

        return updated;
    }
}
