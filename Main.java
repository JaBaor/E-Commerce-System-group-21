import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        AdminOperation.getInstance().registerAdmin();
        IOInterface io = IOInterface.getInstance();
        while (true) {
            io.mainMenu();
            String[] cmd = io.getUserInput("Enter option", 1);
            if (cmd[0].equals("3")) break;
            // Handle login/register flow
        }
    }
}
