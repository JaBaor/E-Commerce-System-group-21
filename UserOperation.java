import java.io.*;
import java.nio.file.*;
import java.util.*;

public class UserOperation {
    private static final String USER_DB = "data/users.txt";
    private static UserOperation instance;
    private final Random random = new Random();

    private UserOperation() {}

    public static synchronized UserOperation getInstance() {
        if (instance == null) instance = new UserOperation();
        return instance;
    }

    public String generateUniqueUserId() {
        String id;
        Set<String> used = loadExistingIds();
        do {
            id = String.format("u_%010d", random.nextInt(1_000_000_000));
        } while (used.contains(id));
        return id;
    }

    private Set<String> loadExistingIds() {
        Set<String> ids = new HashSet<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(USER_DB))) {
            String line;
            while ((line = br.readLine()) != null) {
                int start = line.indexOf("\"u_");
                if (start != -1) {
                    ids.add(line.substring(start + 1, start + 13));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return ids;
    }

    public String encryptPassword(String pw) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder rand = new StringBuilder();
        for (int i = 0; i < pw.length() * 2; i++) {
            rand.append(chars.charAt(random.nextInt(chars.length())));
        }
        StringBuilder enc = new StringBuilder("^^");
        for (int i = 0, j = 0; i < pw.length(); i++, j += 2) {
            enc.append(rand.substring(j, j + 2)).append(pw.charAt(i));
        }
        enc.append("$$");
        return enc.toString();
    }

    public String decryptPassword(String enc) {
        String body = enc.substring(2, enc.length() - 2); // Strip ^^ and $$
        StringBuilder pw = new StringBuilder();
        for (int i = 2; i < body.length(); i += 3) {
            pw.append(body.charAt(i));
        }
        return pw.toString();
    }

    public boolean checkUsernameExist(String userName) {
        return readAllUsers().stream().anyMatch(u -> u.contains("\"user_name\":\"" + userName + "\""));
    }

    public boolean validateUsername(String name) {
        return name.matches("[A-Za-z_]{5,}");
    }

    public boolean validatePassword(String pw) {
        return pw.matches("(?=.*[A-Za-z])(?=.*\\d).{5,}");
    }

    public User login(String userName, String pw) {
        for (String json : readAllUsers()) {
            if (json.contains("\"user_name\":\"" + userName + "\"")) {
                String enc = json.replaceAll(".*\\\"user_password\\\":\\\"(.*?)\\\".*", "$1");
                if (decryptPassword(enc).equals(pw)) {
                    return json.contains("\"user_role\":\"admin\"") ? new Admin(json) : new Customer(json);
                }
            }
        }
        return null; // Return null if login fails
    }

    private List<String> readAllUsers() {
        try {
            return Files.readAllLines(Paths.get(USER_DB));
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ArrayList<>(); // Return an empty list on error
        }
    }
    

}
