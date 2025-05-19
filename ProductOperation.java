
public class ProductOperation {
    // Only core methods stubbed to save space
    private static ProductOperation instance;
    private ProductOperation() {}
    public static synchronized ProductOperation getInstance() { if (instance == null) instance = new ProductOperation(); return instance; }
    // Implement extractProductsFromFiles, pagination, figures, etc.
}