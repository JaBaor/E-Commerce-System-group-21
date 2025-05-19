import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class CustomerOperation {
    private static final String USER_DB = "data/users.txt";
    private static final DateTimeFormatter TS_FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
    private static final Pattern EMAIL_RE = Pattern.compile("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern MOBILE_RE = Pattern.compile("^(04|03)\\d{8}$");

    private static volatile CustomerOperation instance;
    private final UserOperation userOp = UserOperation.getInstance();

    private CustomerOperation() {}

    public static CustomerOperation getInstance() {
        if (instance == null) {
            synchronized (CustomerOperation.class) {
                if (instance == null) instance = new CustomerOperation();
            }
        }
        return instance;
    }

    public boolean validateEmail(String email) {
        return EMAIL_RE.matcher(email).matches();
    }

    public boolean validateMobile(String mobile) {
        return MOBILE_RE.matcher(mobile).matches();
    }

    public boolean registerCustomer(String name, String pw, String email, String mobile) {
    if (!userOp.validateUsername(name) || !userOp.validatePassword(pw) ||
        !validateEmail(email) || !validateMobile(mobile) || userOp.checkUsernameExist(name)) {
        System.out.println("Registration failed: Invalid input or username already exists.");
        return false;
    }

        String id = userOp.generateUniqueUserId();
        String time = LocalDateTime.now().format(TS_FMT);
        String pwEnc = userOp.encryptPassword(pw);

        String json = String.format(
            "{\"user_id\":\"%s\",\"user_name\":\"%s\",\"user_password\":\"%s\"," +
            "\"user_register_time\":\"%s\",\"user_role\":\"customer\"," +
            "\"user_email\":\"%s\",\"user_mobile\":\"%s\"}",
            id, name, pwEnc, time, email, mobile);

        try {
            Files.createDirectories(Path.of("data")); // Ensure the directory exists
            Files.writeString(Path.of(USER_DB), json + System.lineSeparator(),
                              StandardCharsets.UTF_8,
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception for debugging
            return false;
        }
    }
}
