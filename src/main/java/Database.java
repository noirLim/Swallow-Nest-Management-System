
public class Database {
    private String con;
    private String user;
    private String password;

    public Database(){
       this.con ="jdbc:mysql://localhost:3306/walettdb";
       this.user = "root";
       this.password = "";
    }

    public String getCon() {
        return con;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
