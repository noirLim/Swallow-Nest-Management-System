package AppPackage.Controller;

public class Customer {
    private String custNo;
    private String fname;
    private String lname;
    private int telpNo;
    private String address;

    public Customer(String custNo, String fname, String lname, int telpNo, String address) {
        this.custNo = custNo;
        this.fname = fname;
        this.lname = lname;
        this.telpNo = telpNo;
        this.address = address;
    }

    public String getCustNo() {
        return custNo;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getTelpNo() {
        return telpNo;
    }

    public String getAddress() {
        return address;
    }

}
