import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CustomerOperation {
    private static final String USER_DB = "data/users.txt";
    private static CustomerOperation instance;
    private final UserOperation userOp = UserOperation.getInstance();
    private CustomerOperation() {}
    public static synchronized CustomerOperation getInstance() {
        if (instance == null) instance = new CustomerOperation();
        return instance;
    }
    public boolean validateEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    }
    public boolean validateMobile(String mobile) {
        return mobile.matches("0[34]\\d{8}");
    }
    public boolean registerCustomer(String name, String pw, String email, String mobile) {
        if (!userOp.validateUsername(name) || !userOp.validatePassword(pw) || !validateEmail(email) || !validateMobile(mobile) || userOp.checkUsernameExist(name)) return false;
        String id = userOp.generateUniqueUserId();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"));
        String json = String.format("{\"user_id\":\"%s\",\"user_name\":\"%s\",\"user_password\":\"%s\",\"user_register_time\":\"%s\",\"user_role\":\"customer\",\"user_email\":\"%s\",\"user_mobile\":\"%s\"}", id, name, userOp.encryptPassword(pw), time, email, mobile);
        try { Files.writeString(Paths.get(USER_DB), json + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND); return true; } catch (IOException e) { return false; }
    }
    public boolean updateProfile(String attr, String val, Customer cust) {
        switch (attr) {
            case "user_name" -> { if (!userOp.validateUsername(val) || userOp.checkUsernameExist(val)) return false; cust.setUserName(val); }
            case "user_password" -> { if (!userOp.validatePassword(val)) return false; cust.setUserPassword(userOp.encryptPassword(val)); }
            case "user_email" -> { if (!validateEmail(val)) return false; cust.setUserEmail(val); }
            case "user_mobile" -> { if (!validateMobile(val)) return false; cust.setUserMobile(val); }
            default -> { return false; }
        }
        return persistCustomerChanges(cust);
    }
    private boolean persistCustomerChanges(Customer cust) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(USER_DB));
            for (int i = 0; i < lines.size(); i++) if (lines.get(i).contains(cust.getUserId())) lines.set(i, cust.toString());
            Files.write(Paths.get(USER_DB), lines);
            return true;
        } catch (IOException e) { return false; }
    }
    public boolean deleteCustomer(String id) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(USER_DB));
            lines.removeIf(l -> l.contains("\"" + id + "\""));
            Files.write(Paths.get(USER_DB), lines);
            return true;
        } catch (IOException e) { return false; }
    }
    /* Pagination helper & deleteAll omitted for brevity */
}
