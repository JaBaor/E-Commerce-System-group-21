import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserOperation {

    private static final String USER_DB_PATH = "data/users.txt";
    private static UserOperation instanceRef;

    private final Random rng = new Random();

    private UserOperation() {
        // Private constructor to enforce singleton pattern
    }

    public static synchronized UserOperation getInstance() {
        // Lazy-load the instance
        if (instanceRef == null) {
            instanceRef = new UserOperation();
        }
        return instanceRef;
    }

    // Generates a user ID that shouldn't clash with any existing one in the file
    public String generateUniqueUserId() {
        Set<String> existingIds = getUsedIds(); // Maybe cache later?

        String newId;
        do {
            // IDs like u_0000001234
            newId = String.format("u_%010d", rng.nextInt(1_000_000_000));
        } while (existingIds.contains(newId));

        return newId;
    }

    // Loads IDs already present in the user data
    private Set<String> getUsedIds() {
        Set<String> idSet = new HashSet<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(USER_DB_PATH))) {
            // Assuming each line has something like "user_id":"u_0000001234"
            reader.lines().forEach(line -> {
                int index = line.indexOf("\"u_");
                if (index != -1) {
                    // This feels a bit brittle... may improve later
                    String id = line.substring(index + 1, index + 13);
                    idSet.add(id);
                }
            });
        } catch (IOException e) {
            // Probably log this in real code
        }

        return idSet;
    }

    // Slightly silly password encryption; just obfuscation really
    public String encryptPassword(String password) {
        String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < password.length() * 2; i++) {
            salt.append(charset.charAt(rng.nextInt(charset.length())));
        }

        StringBuilder obfuscated = new StringBuilder("^^");  // prefix just to mark it
        for (int i = 0, j = 0; i < password.length(); i++, j += 2) {
            obfuscated.append(salt.substring(j, j + 2)); // 2-char random salt per char
            obfuscated.append(password.charAt(i));
        }
        obfuscated.append("$$");  // suffix

        return obfuscated.toString();
    }

    // Reverse-engineering the nonsense above
    public String decryptPassword(String encrypted) {
        String middle = encrypted.substring(2, encrypted.length() - 2); // skip ^^ and $$
        StringBuilder originalPw = new StringBuilder();

        // Every third char is real password character
        for (int i = 2; i < middle.length(); i += 3) {
            originalPw.append(middle.charAt(i));
        }

        return originalPw.toString();
    }

    // Crude check if a username exists anywhere in the file
    public boolean doesUsernameExist(String userName) {
        // Might be a good idea to use a regex or proper JSON lib later...
        return readUserLines().stream()
            .anyMatch(line -> line.contains("\"user_name\":\"" + userName + "\""));
    }

    // Let's require at least 5 alphabetic or underscore characters
    public boolean isValidUsername(String name) {
        return name != null && name.matches("[A-Za-z_]{5,}");
    }

    // Just a basic check: at least one letter and one number, 5+ chars
    public boolean isValidPassword(String password) {
        return password != null && password.matches("(?=.*[A-Za-z])(?=.*\\d).{5,}");
    }

    // This could definitely use a JSON parser...
    public User login(String userName, String plainPassword) {
        for (String entry : readUserLines()) {
            if (entry.contains("\"user_name\":\"" + userName + "\"")) {
                // Hacky regex to extract the password
                String storedEncrypted = entry.replaceAll(".*\\\"user_password\\\":\\\"(.*?)\\\".*", "$1");

                if (decryptPassword(storedEncrypted).equals(plainPassword)) {
                    // Check role to return correct subclass
                    if (entry.contains("\"user_role\":\"admin\"")) {
                        return new Admin(entry);
                    } else {
                        return new Customer(entry);
                    }
                }
            }
        }

        return null; // No match found
    }

    // Reads the raw user lines from the data file
    private List<String> readUserLines() {
        try {
            return Files.readAllLines(Paths.get(USER_DB_PATH));
        } catch (IOException e) {
            // Eh, file might not exist yet
            return new ArrayList<>();
        }
    }
}

