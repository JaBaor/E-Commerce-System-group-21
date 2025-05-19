public class AdminOperation {
    private static AdminOperation instance;
    private final UserOperation userOp = UserOperation.getInstance();
    private AdminOperation() {}
    public static synchronized AdminOperation getInstance() {
        if (instance == null) instance = new AdminOperation();
        return instance;
    }
    public void registerAdmin() {
        if (!userOp.checkUsernameExist("admin")) {
            String id = userOp.generateUniqueUserId();
            String time = "01-01-2024_09:00:00";
            String json = String.format("{\"user_id\":\"%s\",\"user_name\":\"admin\",\"user_password\":\"%s\",\"user_register_time\":\"%s\",\"user_role\":\"admin\"}", id, userOp.encryptPassword("admin123"), time);
            try { Files.writeString(Paths.get("data/users.txt"), json + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND); } catch (IOException ignored) {}
        }
    }
}

