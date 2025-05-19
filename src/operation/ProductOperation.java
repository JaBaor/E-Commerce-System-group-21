package operation;

import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductOperation {
    private static ProductOperation instance;
    private List<Product> productList;

    private ProductOperation() {
        productList = new ArrayList<>();
        loadMockProducts();
    }

    public static ProductOperation getInstance() {
        if (instance == null) {
            instance = new ProductOperation();
        }
        return instance;
    }

    private void loadMockProducts() {
        // Simulate loading from file
        for (int i = 1; i <= 15; i++) {
            productList.add(new Product(
                String.format("p_%05d", i),
                "Model" + i,
                i % 2 == 0 ? "Electronics" : "Books",
                "Product " + i,
                99.99 + i,
                149.99 + i,
                i * 5 % 100,
                i * 10
            ));
        }
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public List<Product> getProductsByPage(int page, int size) {
        int from = (page - 1) * size;
        int to = Math.min(from + size, productList.size());
        if (from >= productList.size()) return new ArrayList<>();
        return productList.subList(from, to);
    }

    public int getTotalPages(int size) {
        return (int) Math.ceil((double) productList.size() / size);
    }
}
