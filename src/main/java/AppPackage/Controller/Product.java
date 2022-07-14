package AppPackage.Controller;

public class Product {
    private String id;
    private String supId;
    private String catId;
    private String name;
    private int unit;
    private int price;

    public Product(String id, String supId, String catId, String name, int unit, int price) {
        this.id = id;
        this.supId = supId;
        this.catId = catId;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getSupId() {
        return supId;
    }

    public String getCatId() {
        return catId;
    }

    public String getName() {
        return name;
    }

    public int getUnit() {
        return unit;
    }

    public int getPrice() {
        return price;
    }
}
