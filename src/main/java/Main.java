public class Main {

    public static void main(String[] args) {
       try{
           Class.forName("com.mysql.jdbc.Driver");
            new LoginForm();
       }catch(Exception e){
           e.printStackTrace();
       }
    }
}
