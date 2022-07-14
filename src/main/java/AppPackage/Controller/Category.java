package AppPackage.Controller;

public class Category {
    private String id;
    private String name;
    private String quality;

    public Category(String id, String name, String quality) {
        this.id = id;
        this.name = name;
        this.quality = quality;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuality() {
        return quality;
    }
}
