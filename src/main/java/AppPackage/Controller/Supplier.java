package AppPackage.Controller;

public class Supplier {
    private String id;
    private String contact;
    private int telpNo;
    private String company;
    private String address;

    public Supplier(String id, String contact, int telpNo, String company, String address) {
        this.id = id;
        this.contact = contact;
        this.telpNo = telpNo;
        this.company = company;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getContact() {
        return contact;
    }

    public int getTelpNo() {
        return telpNo;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }
}
