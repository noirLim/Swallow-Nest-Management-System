package AppPackage.Controller;

public class Employee {
    private String id;
    private String fname;
    private String lname;
    private String address;
    private String email;
    private int telpno;
    private int salary;

    public Employee(String id, String fname, String lname, String address, String email, int telpno, int salary) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.email = email;
        this.telpno = telpno;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getTelpno() {
        return telpno;
    }

    public int getSalary() {
        return salary;
    }
}
