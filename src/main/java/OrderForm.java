import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.*;

public class OrderForm extends JFrame{
    private Connection conn;

    JComboBox custNocomboBox;
    JComboBox empIDcomboBox;
    JComboBox productIDcomboBox;

    JLabel lblnameproduct;
    JLabel lblhargaproduct;

    JTextField unitTextField;

    private String orderId;
    private String custNo;
    private String empId;
    private String dateVal;
    private String prodID;
    private String prodname;
    private int unitVal;
    private int priceVal;
    private int count;

    public OrderForm(){
        setSize(1300,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Order");
        setResizable(false);

        JPanel pnlorder = new JPanel();
        pnlorder.setLayout(null);
        pnlorder.setPreferredSize(new Dimension(800, 0));
        pnlorder.setForeground(Color.BLACK);
        pnlorder.setBackground(new Color(135, 206, 250));
        getContentPane().add(pnlorder, BorderLayout.WEST);

        JLabel lblorderID = new JLabel("OrderID");
        lblorderID.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblorderID.setBounds(51, 104, 108, 25);
        pnlorder.add(lblorderID);

        JLabel lblcustNo = new JLabel("CustNo");
        lblcustNo.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblcustNo.setBounds(51, 156, 74, 25);
        pnlorder.add(lblcustNo);

        JLabel lblempID = new JLabel("EmpID");
        lblempID.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblempID.setBounds(51, 204, 108, 25);
        pnlorder.add(lblempID);

        JLabel lblproductID = new JLabel("ProductID");
        lblproductID.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblproductID.setBounds(51, 256, 108, 25);
        pnlorder.add(lblproductID);

        JLabel lblpurchaseDate = new JLabel("PurchaseDate");
        lblpurchaseDate.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblpurchaseDate.setBounds(420, 262, 158, 25);
        pnlorder.add(lblpurchaseDate);

        JTextField orderIDTextField = new JTextField();
        orderIDTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        orderIDTextField.setColumns(10);
        orderIDTextField.setBounds(188, 101, 215, 31);
        pnlorder.add(orderIDTextField);

        JTextField purchaseDateTextField = new JTextField();
        purchaseDateTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        purchaseDateTextField.setColumns(10);
        purchaseDateTextField.setBounds(577, 256, 215, 31);
        pnlorder.add(purchaseDateTextField);

        JLabel lblorder = new JLabel("Orders");
        lblorder.setFont(new Font("Century Gothic", Font.BOLD, 23));
        lblorder.setBounds(176, 30, 141, 39);
        pnlorder.add(lblorder);

        JButton btncount = new JButton("Count");
        btncount.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btncount.setBackground(Color.BLACK);
        btncount.setForeground(Color.WHITE);
        btncount.setBounds(479, 370, 108, 42);
        pnlorder.add(btncount);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(629, 370, 108, 42);
        pnlorder.add(btnBack);

        custNocomboBox = new JComboBox();
        custNocomboBox.setBounds(188, 152, 215, 31);
        pnlorder.add(custNocomboBox);

        empIDcomboBox = new JComboBox();
        empIDcomboBox.setBounds(188, 204, 215, 31);
        pnlorder.add(empIDcomboBox);

        productIDcomboBox = new JComboBox();
        productIDcomboBox.setBounds(188, 256, 215, 31);
        pnlorder.add(productIDcomboBox);


        unitTextField = new JTextField();
        unitTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        unitTextField.setColumns(10);
        unitTextField.setBounds(577, 201, 215, 31);
        pnlorder.add(unitTextField);

        JLabel lblunit = new JLabel("Unit");
        lblunit.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblunit.setBounds(440, 204, 41, 25);
        pnlorder.add(lblunit);

        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblname.setBounds(442, 101, 58, 25);
        pnlorder.add(lblname);

        lblnameproduct = new JLabel(".....");
        lblnameproduct.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblnameproduct.setBounds(579, 101, 88, 25);
        pnlorder.add(lblnameproduct);

        JLabel lblTotal = new JLabel("Total");
        lblTotal.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblTotal.setBounds(51, 310, 50, 25);
        pnlorder.add(lblTotal);

        JLabel lbltotalhargaproduct = new JLabel(".....");
        lbltotalhargaproduct.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lbltotalhargaproduct.setBounds(188, 310, 58, 25);
        pnlorder.add(lbltotalhargaproduct);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblHarga.setBounds(442, 150, 58, 25);
        pnlorder.add(lblHarga);

        lblhargaproduct = new JLabel(".....");
        lblhargaproduct.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblhargaproduct.setBounds(579, 150, 58, 25);
        pnlorder.add(lblhargaproduct);

        JLabel lblkembalian = new JLabel("Kembalian");
        lblkembalian.setBounds(53, 432, 108, 25);
        pnlorder.add(lblkembalian);
        lblkembalian.setFont(new Font("Century Gothic", Font.BOLD, 19));

        JLabel lblkembalianhargaproduct = new JLabel(".....");
        lblkembalianhargaproduct.setBounds(190, 432, 58, 25);
        pnlorder.add(lblkembalianhargaproduct);
        lblkembalianhargaproduct.setFont(new Font("Century Gothic", Font.BOLD, 19));

        JTextField cashtextField = new JTextField();
        cashtextField.setBounds(188, 370, 215, 31);
        pnlorder.add(cashtextField);
        cashtextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        cashtextField.setColumns(10);

        JLabel lblcash = new JLabel("Cash");
        lblcash.setBounds(53, 373, 50, 25);
        pnlorder.add(lblcash);
        lblcash.setFont(new Font("Century Gothic", Font.BOLD, 19));

        JPanel pnltblorder = new JPanel();
        pnltblorder.setLayout(null);
        pnltblorder.setPreferredSize(new Dimension(587, 0));
        pnltblorder.setBackground(new Color(0, 0, 205));
        getContentPane().add(pnltblorder, BorderLayout.CENTER);

        JButton btngenerate = new JButton("Generate\r\n");
        btngenerate.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btngenerate.setBackground(Color.BLACK);
        btngenerate.setForeground(Color.white);
        btngenerate.setBounds(115, 473, 125, 33);
        pnltblorder.add(btngenerate);

        JButton btnprint = new JButton("Print");
        btnprint.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnprint.setBackground(Color.BLACK);
        btnprint.setForeground(Color.WHITE);
        btnprint.setBounds(265, 473, 108, 33);
        pnltblorder.add(btnprint);

        JTextArea receiptArea = new JTextArea();
        receiptArea.setBounds(42, 41, 405, 406);
        receiptArea.setFont(new Font("Century Gothic",Font.BOLD,18));
        pnltblorder.add(receiptArea);

        setVisible(true);

        display();
        productIDcomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cbValue = productIDcomboBox.getSelectedItem().toString();
                getValueProduct(cbValue);
            }
        });

//        Action button
        btncount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int unitVal = Integer.parseInt(unitTextField.getText());
                    int priceVal = Integer.parseInt(lblhargaproduct.getText());
                    count = unitVal * priceVal;
                    lbltotalhargaproduct.setText(String.valueOf(count));
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Unit must be numeric","information",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainHome();
            }
        });

        btngenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bayar = Integer.parseInt(cashtextField.getText());
                if(bayar >= count){
                    int change = bayar - count;
                    lblkembalianhargaproduct.setText(String.valueOf(change));
                    String displayChange = lblkembalianhargaproduct.getText();

                    orderId = orderIDTextField.getText();
                    custNo = custNocomboBox.getSelectedItem().toString();
                    empId = empIDcomboBox.getSelectedItem().toString();
                    dateVal = purchaseDateTextField.getText();
                    prodID = productIDcomboBox.getSelectedItem().toString();
                    unitVal = Integer.parseInt(unitTextField.getText());
                    priceVal = Integer.parseInt(lblhargaproduct.getText());
                    prodname = lblnameproduct.getText();

                    insertOrder(orderId,custNo,empId,dateVal);
                    insertDetail(orderId,prodID,unitVal,priceVal);


                    receiptArea.setText("=================Laskin=================\n"+"" +
                            "OrderID       :  "+ orderId +"\n"+
                            "custNo        :  "+custNo +"\n"+
                            "ProductID     :  "+ prodID +"\n"+
                            "ProductName   :  "+ prodname+"\n"+
                            "Price         :  "+ priceVal+"\n"+
                            "Unit          :  "+ unitVal+"\n"+
                            "PurchaseDate  :  "+dateVal+"\n"+
                            "Total         :  "+lbltotalhargaproduct.getText()+"\n"+
                            "Cash          :  "+cashtextField.getText()+"\n"+
                            "Change        :  "+displayChange+"\n"
                            );
                }else{
                    JOptionPane.showMessageDialog(null,"Uang tidak mencukupi","information",JOptionPane.INFORMATION_MESSAGE);
                    cashtextField.setText("");
                }
            }
        });

        btnprint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    receiptArea.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public void display(){
        Database db = new Database();
        try{
//            Menampilkan nomor customer
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String sql = "select * from customer";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                custNocomboBox.addItem(rs.getString("custno"));
            }

            //  Menampilkan nomor empId
            String sql1 = "select * from employees";
            Statement statement1 =  conn.createStatement();
            ResultSet rs1 = statement1.executeQuery(sql1);

            while (rs1.next()){
                empIDcomboBox.addItem(rs1.getString("empID"));
            }

            String sql2 = "select * from product";
            Statement statement2 = conn.createStatement();
            ResultSet rs2 = statement2.executeQuery(sql2);

            while(rs2.next()){
                productIDcomboBox.addItem(rs2.getString("productID"));
                lblnameproduct.setText(rs2.getString("productName"));
                lblhargaproduct.setText(String.valueOf(rs2.getInt("product_price")));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getValueProduct(String id){
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String sql = "select productName,product_price from product where productID='"+id+"'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                lblnameproduct.setText(rs.getString("productName"));
                lblhargaproduct.setText(String.valueOf(rs.getInt("product_price")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertOrder(String id,String custNo,String empID,String dateVal){
        Date strDate = Date.valueOf(dateVal);
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "insert into orderwalet(orderID,custno,empID,purchasedate)" +
                    "VALUES('"+id+"','"+custNo+"','"+empID+"','"+strDate+"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Id must be different","information",JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }

    public void insertDetail(String id,String prodID,int qty,int price){
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "insert into orderdetail(orderID,productID,quantity,price) " +
                    "VALUES('"+id+"','"+prodID+"','"+qty+"','"+price+"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            updateOrder(prodID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateOrder(String prodID) {
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "update product,orderdetail\n" +
                    "set product.product_unit = product.product_unit - orderdetail.quantity\n" +
                    "where product.productID = orderdetail.productID\n" +
                    "and product.productID ='"+prodID+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
