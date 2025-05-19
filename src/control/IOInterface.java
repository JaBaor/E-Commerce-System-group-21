package control;

import model.User;
import model.Product;
import model.Customer;
import model.Order;
import operation.UserOperation;
import operation.ProductOperation;
import operation.CustomerOperation;
import operation.OrderOperation;
import java.util.List;
import java.util.Scanner;

public class IOInterface {
    private static IOInterface instance = null;
    private final Scanner scanner;

    private IOInterface() {
        scanner = new Scanner(System.in);
    }

    public static IOInterface getInstance() {
        if (instance == null) {
            instance = new IOInterface();
        }
        return instance;
    }

    public void mainMenu() {
        while (true) {
            System.out.println("====== E-Commerce System ======");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Quit");
            System.out.println("===============================" );
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    loginFlow();
                    break;
                case "2":
                    System.out.println("Registration not implemented yet.");
                    break;
                case "3":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void loginFlow() {
        System.out.print("Enter username and password: ");
        String[] input = scanner.nextLine().split(" ");
        String username = input.length > 0 ? input[0] : "";
        String password = input.length > 1 ? input[1] : "";

        User user = UserOperation.getInstance().login(username, password);
        if (user != null) {
            System.out.println("\nLogin successful. Welcome, " + user.getUserName() + "!");
            if (user.getUserRole().equals("admin")) {
                adminMenu();
            } else {
                System.out.println("Customer menu not yet implemented.");
            }
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    public void adminMenu() {
        while (true) {
            System.out.println("\n====== Admin Menu ======");
            System.out.println("1. Show products");
            System.out.println("2. Add customers");
            System.out.println("3. Show customers");
            System.out.println("4. Show orders");
            System.out.println("5. Generate test data");
            System.out.println("6. Generate all statistical figures");
            System.out.println("7. Delete all data");
            System.out.println("8. Logout");
            System.out.println("=========================");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayProducts();
                    break;
                case "2":
                    System.out.println("Add customer feature not implemented.");
                    break;
                case "3":
                    displayCustomers();
                    break;
                case "4":
                    displayOrders();
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Feature not implemented yet.");
            }
        }
    }

    private void displayProducts() {
        int page = 1, pageSize = 10;
        ProductOperation po = ProductOperation.getInstance();
        int totalPages = po.getTotalPages(pageSize);
        while (true) {
            List<Product> products = po.getProductsByPage(page, pageSize);
            System.out.printf("\n--- Products (Page %d/%d) ---\n", page, totalPages);
            for (Product p : products) {
                System.out.println(p);
            }
            System.out.print("(N)ext, (P)revious, (B)ack to menu: ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("n") && page < totalPages) page++;
            else if (input.equals("p") && page > 1) page--;
            else if (input.equals("b")) break;
        }
    }

    private void displayCustomers() {
        int page = 1, pageSize = 10;
        CustomerOperation co = CustomerOperation.getInstance();
        int totalPages = co.getTotalPages(pageSize);
        while (true) {
            List<Customer> customers = co.getCustomersByPage(page, pageSize);
            System.out.printf("\n--- Customers (Page %d/%d) ---\n", page, totalPages);
            for (Customer c : customers) {
                System.out.println(c);
            }
            System.out.print("(N)ext, (P)revious, (B)ack to menu: ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("n") && page < totalPages) page++;
            else if (input.equals("p") && page > 1) page--;
            else if (input.equals("b")) break;
        }
    }

    private void displayOrders() {
        int page = 1, pageSize = 10;
        OrderOperation oo = OrderOperation.getInstance();
        int totalPages = oo.getTotalPages(pageSize);
        while (true) {
            List<Order> orders = oo.getOrdersByPage(page, pageSize);
            System.out.printf("\n--- Orders (Page %d/%d) ---\n", page, totalPages);
            for (Order o : orders) {
                System.out.println(o);
            }
            System.out.print("(N)ext, (P)revious, (B)ack to menu: ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("n") && page < totalPages) page++;
            else if (input.equals("p") && page > 1) page--;
            else if (input.equals("b")) break;
        }
    }
}
