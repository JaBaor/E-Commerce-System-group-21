import java.util.*;

public class OrderOperation {
    private static OrderOperation instance;
    private final Random random = new Random();
    private OrderOperation() {}
    public static synchronized OrderOperation getInstance() { if (instance == null) instance = new OrderOperation(); return instance; }
    public String generateUniqueOrderId() { return String.format("o_%05d", random.nextInt(100000)); }
    /* Other methods omitted for brevity */
}
