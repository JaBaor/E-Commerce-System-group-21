import java.util.*;

class IOInterface {
    private static IOInterface instance;
    private final Scanner scanner = new Scanner(System.in);
    private IOInterface() {}
    public static synchronized IOInterface getInstance() { if (instance == null) instance = new IOInterface(); return instance; }
    public String[] getUserInput(String msg, int n) {
        System.out.print(msg + " > ");
        String[] parts = scanner.nextLine().trim().split(" ");
        return Arrays.copyOf(parts, n);
    }
    /* Menus and display helpers stubbed */
}
