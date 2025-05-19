public class User {
    private String userId;
    private String userName;
    private String userPassword;
    private String userRegisterTime;
    private String userRole;

    /**
     * Constructs a user object.
     * @param userId Must be unique, format: u_10 digits, such as u_1234567890
     * @param userName The user's name
     * @param userPassword The user's password
     * @param userRegisterTime Format: "DD-MM-YYYY_HH:MM:SS"
     * @param userRole Default value: "customer"
     */
    public User(String json) {
        // Simple parsing using regular expressions (no external libraries)
        this.userId = extractJsonValue(json, "user_id");
        this.userName = extractJsonValue(json, "user_name");
        this.userPassword = extractJsonValue(json, "user_password");
        this.userRegisterTime = extractJsonValue(json, "user_register_time");
        this.userRole = extractJsonValue(json, "user_role");
    }

    // Helper method to extract value for a given key from a JSON-like string
    private String extractJsonValue(String json, String key) {
        String pattern = "\""+key+"\":\"([^\"]*)\"";
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(json);
        return matcher.find() ? matcher.group(1) : "";
    }

    public User(String userId, String userName, String userPassword,
                String userRegisterTime, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRegisterTime = userRegisterTime;
        this.userRole = userRole;
    }

    /**
     * Default constructor
     */
    public User() {
        this.userId = "";
        this.userName = "";
        this.userPassword = "";
        this.userRegisterTime = "";
        this.userRole = "customer";
    }

    /**
     * Returns the user Information as a formatted string.
     * @return String in JSON-like format
     */
    @Override
    public String toString() {
        return String.format("{\"user_id\":\"%s\", \"user_name\":\"%s\", \"user_password\":\"%s\", " +
                        "\"user_register_time\":\"%s\", \"user_role\":\"%s\"}",
                userId, userName, userPassword, userRegisterTime, userRole);
    }
}
