public class Main {
    public static void main(String[] args) {
        AdminOperation.getInstance().registerAdmin();
        IOInterface io = IOInterface.getInstance();
        while (true) {
            String[] cmd = io.getUserInput("Enter option", 1);
            if (cmd[0].equals("3")) break;
            // Handle login/register flow
        }
    }
}
