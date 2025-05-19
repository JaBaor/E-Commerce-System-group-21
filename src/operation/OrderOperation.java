package operation;

import model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderOperation {
    private static OrderOperation instance;
    private List<Order> orderList;

    private OrderOperation() {
        orderList = new ArrayList<>();
        loadMockOrders();
    }

    public static OrderOperation getInstance() {
        if (instance == null) {
            instance = new OrderOperation();
        }
        return instance;
    }

    private void loadMockOrders() {
        for (int i = 1; i <= 20; i++) {
            orderList.add(new Order(
                String.format("o_%05d", i),
                "u_0000000001",
                String.format("p_%05d", i),
                "15-05-2025_10:00:00"
            ));
        }
    }

    public List<Order> getOrdersByPage(int page, int size) {
        int from = (page - 1) * size;
        int to = Math.min(from + size, orderList.size());
        if (from >= orderList.size()) return new ArrayList<>();
        return orderList.subList(from, to);
    }

    public int getTotalPages(int size) {
        return (int) Math.ceil((double) orderList.size() / size);
    }

    public List<Order> getAllOrders() {
        return orderList;
    }
}
