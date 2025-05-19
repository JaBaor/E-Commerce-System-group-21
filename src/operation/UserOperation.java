package operation;

import model.Admin;
import model.User;

public class UserOperation {
    private static UserOperation instance;

    private UserOperation() {}

    public static UserOperation getInstance() {
        if (instance == null) {
            instance = new UserOperation();
        }
        return instance;
    }

    public User login(String username, String password) {
        if (username.equals("admin") && password.equals("admin123")) {
            return new Admin("u_0000000001", "admin", "admin123",
                    "13-05-2025_21:00:00", "admin");
        }
        return null;
    }
}
