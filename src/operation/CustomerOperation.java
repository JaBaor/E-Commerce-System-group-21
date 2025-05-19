package operation;

import model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerOperation {
    private static CustomerOperation instance;
    private List<Customer> customerList;

    private CustomerOperation() {
        customerList = new ArrayList<>();
        loadMockCustomers();
    }

    public static CustomerOperation getInstance() {
        if (instance == null) {
            instance = new CustomerOperation();
        }
        return instance;
    }

    private void loadMockCustomers() {
        for (int i = 1; i <= 12; i++) {
            customerList.add(new Customer(
                String.format("u_%010d", i),
                "customer" + i,
                "pass123",
                "01-01-2025_09:00:00",
                "customer",
                "customer" + i + "@email.com",
                "04" + String.format("%08d", i)
            ));
        }
    }

    public List<Customer> getCustomersByPage(int page, int size) {
        int from = (page - 1) * size;
        int to = Math.min(from + size, customerList.size());
        if (from >= customerList.size()) return new ArrayList<>();
        return customerList.subList(from, to);
    }

    public int getTotalPages(int size) {
        return (int) Math.ceil((double) customerList.size() / size);
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }
}
