package model;

public class Product {
    private String proId;
    private String proModel;
    private String proCategory;
    private String proName;
    private double proCurrentPrice;
    private double proRawPrice;
    private double proDiscount;
    private int proLikesCount;

    public Product(String proId, String proModel, String proCategory,
                   String proName, double proCurrentPrice, double proRawPrice,
                   double proDiscount, int proLikesCount) {
        this.proId = proId;
        this.proModel = proModel;
        this.proCategory = proCategory;
        this.proName = proName;
        this.proCurrentPrice = proCurrentPrice;
        this.proRawPrice = proRawPrice;
        this.proDiscount = proDiscount;
        this.proLikesCount = proLikesCount;
    }

    public Product() {
        this("p_00001", "DefaultModel", "DefaultCategory", "DefaultName",
             0.0, 0.0, 0.0, 0);
    }

    public String getProId() { return proId; }
    public String getProName() { return proName; }
    public String getProCategory() { return proCategory; }
    public double getProCurrentPrice() { return proCurrentPrice; }
    public double getProRawPrice() { return proRawPrice; }
    public double getProDiscount() { return proDiscount; }
    public int getProLikesCount() { return proLikesCount; }

    @Override
    public String toString() {
        return String.format("{\"pro_id\":\"%s\", \"pro_model\":\"%s\", \"pro_category\":\"%s\", " +
                "\"pro_name\":\"%s\", \"pro_current_price\":\"%.2f\", \"pro_raw_price\":\"%.2f\", " +
                "\"pro_discount\":\"%.2f\", \"pro_likes_count\":\"%d\"}",
                proId, proModel, proCategory, proName, proCurrentPrice,
                proRawPrice, proDiscount, proLikesCount);
    }
}
