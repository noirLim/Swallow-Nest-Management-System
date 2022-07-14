import AppPackage.Controller.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class CustomerForm extends JFrame {
    Customer customer;
    private Connection conn;
    DefaultTableModel tbmodel;
    JTable customerTb;
    JScrollPane scrollPane;
    JPanel pnltblcustomer;

    private String custNo;
    private String fname;
    private String lname;
    private String telpNo;
    private String address;

    public CustomerForm(){

        setBounds(180, 100, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Customer");
        setResizable(false);

        JPanel pnlcustomers = new JPanel();
        pnlcustomers.setBackground(new Color(135, 206, 250));
        pnlcustomers.setForeground(new Color(0, 0, 0));
        pnlcustomers.setPreferredSize(new Dimension(400, 0));
        getContentPane().add(pnlcustomers, BorderLayout.WEST);
        pnlcustomers.setLayout(null);

        JLabel lblCustomers = new JLabel("Customers");
        lblCustomers.setFont(new Font("Century Gothic", Font.BOLD, 23));
        lblCustomers.setBounds(128, 30, 141, 39);
        pnlcustomers.add(lblCustomers);

        JLabel lblCustno = new JLabel("CustNo");
        lblCustno.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblCustno.setBounds(20, 104, 108, 25);
        pnlcustomers.add(lblCustno);

        JLabel lblFirstname = new JLabel("FirstName");
        lblFirstname.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblFirstname.setBounds(20, 153, 108, 25);
        pnlcustomers.add(lblFirstname);

        JLabel lblLastname = new JLabel("LastName");
        lblLastname.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblLastname.setBounds(20, 210, 108, 25);
        pnlcustomers.add(lblLastname);

        JLabel lblTelpno = new JLabel("TelpNo");
        lblTelpno.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblTelpno.setBounds(20, 262, 108, 25);
        pnlcustomers.add(lblTelpno);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblAddress.setBounds(20, 318, 108, 25);
        pnlcustomers.add(lblAddress);

        JTextField custNoTextField = new JTextField();
        custNoTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        custNoTextField.setColumns(10);
        custNoTextField.setBounds(157, 101, 215, 31);
        pnlcustomers.add(custNoTextField);

        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        firstNameTextField.setColumns(10);
        firstNameTextField.setBounds(157, 153, 215, 31);
        pnlcustomers.add(firstNameTextField);

        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        lastNameTextField.setColumns(10);
        lastNameTextField.setBounds(157, 210, 215, 31);
        pnlcustomers.add(lastNameTextField);

        JTextField telpNoTextField = new JTextField();
        telpNoTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        telpNoTextField.setColumns(10);
        telpNoTextField.setBounds(157, 262, 215, 31);
        pnlcustomers.add(telpNoTextField);

        JTextPane addressTextPane = new JTextPane();
        addressTextPane.setBounds(157, 318, 215, 109);
        pnlcustomers.add(addressTextPane);

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnClear.setBackground(Color.BLACK);
        btnClear.setForeground(Color.WHITE);
        btnClear.setBounds(50, 500, 108, 42);
        pnlcustomers.add(btnClear);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(200, 500, 108, 42);
        pnlcustomers.add(btnBack);

        pnltblcustomer = new JPanel();
        pnltblcustomer.setBackground(new Color(0, 0, 205));
        pnltblcustomer.setPreferredSize(new Dimension(587, 0));
        getContentPane().add(pnltblcustomer, BorderLayout.CENTER);
        pnltblcustomer.setLayout(null);

        JButton btnInsert = new JButton("Insert");
        btnInsert.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnInsert.setBackground(Color.BLACK);
        btnInsert.setForeground(Color.WHITE);
        btnInsert.setBounds(87, 499, 108, 33);
        pnltblcustomer.add(btnInsert);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.white);
        btnUpdate.setBounds(237, 499, 108, 33);
        pnltblcustomer.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnDelete.setBackground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBounds(389, 499, 108, 33);
        pnltblcustomer.add(btnDelete);

//       Menampilkan data table
         displayTable();

//        Action button
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                custNoTextField.setText("");
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                telpNoTextField.setText("");
                addressTextPane.setText("");
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainHome();
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

//       Insert button
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 custNo = custNoTextField.getText();
                 fname = firstNameTextField.getText();
                 lname = lastNameTextField.getText();
                 telpNo = telpNoTextField.getText();
                 address = addressTextPane.getText();

                try{
//                    Class.forName("com.mysql.jdbc.Driver");
                    if(custNo.equals("") || fname.equals("") || lname.equals("") || telpNo.equals("") || address.equals("")){
                        JOptionPane.showMessageDialog(null,"Data field cannot blank","Error",JOptionPane.ERROR_MESSAGE);
                    }if(custNo.length() > 4){
                        JOptionPane.showMessageDialog(null,"custNo must be 4 character","Information",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
//                        Memeriksa telpNo bersifat integer
                        try{
                            Integer telpno = Integer.parseInt(telpNo);
                                customer = new Customer(custNo,fname,lname,telpno,address);
                                insert(customer.getCustNo(),
                                        customer.getFname(),
                                        customer.getLname(),
                                        customer.getTelpNo(),
                                        customer.getAddress());
                        }catch (Exception ex){
                            JOptionPane.showMessageDialog(null,"telpno must be numeric","information",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }catch(Exception error){
                   error.printStackTrace();
                    JOptionPane.showMessageDialog(null,"telpno must be numeric","information",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                custNo = custNoTextField.getText();
                fname = firstNameTextField.getText();
                lname = lastNameTextField.getText();
                telpNo = telpNoTextField.getText();
                address = addressTextPane.getText();

                try{
                    int telpno = Integer.parseInt(telpNo);
                    customer = new Customer(custNo,fname,lname,telpno,address);
                    update(customer.getCustNo(),
                            customer.getFname(),
                            customer.getLname(),
                            customer.getTelpNo(),
                            customer.getAddress());

                }catch (Exception error){
                    error.printStackTrace();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                custNo = custNoTextField.getText();
                try{
//                    Class.forName("com.mysql.jdbc.Driver");
                    delete(custNo);
                    custNoTextField.setText("");
                    firstNameTextField.setText("");
                    lastNameTextField.setText("");
                    telpNoTextField.setText("");
                    addressTextPane.setText("");
                }catch (Exception error){
                    error.printStackTrace();
                }
            }
        });

//      Memilih tuple dari jtable
        customerTb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = customerTb.getSelectedRow();
                custNoTextField.setText(tbmodel.getValueAt(rowIndex,0).toString());
                firstNameTextField.setText(tbmodel.getValueAt(rowIndex,1).toString());
                lastNameTextField.setText(tbmodel.getValueAt(rowIndex,2).toString());
                telpNoTextField.setText(tbmodel.getValueAt(rowIndex,3).toString());
                addressTextPane.setText(tbmodel.getValueAt(rowIndex,4).toString());
                JOptionPane.showMessageDialog(null,"row selected","Information",JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int rowIndex = customerTb.getSelectedRow();
                custNoTextField.setText(tbmodel.getValueAt(rowIndex,0).toString());
                firstNameTextField.setText(tbmodel.getValueAt(rowIndex,1).toString());
                lastNameTextField.setText(tbmodel.getValueAt(rowIndex,2).toString());
                telpNoTextField.setText(tbmodel.getValueAt(rowIndex,3).toString());
                addressTextPane.setText(tbmodel.getValueAt(rowIndex,4).toString());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                custNoTextField.setText("");
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                telpNoTextField.setText("");
                addressTextPane.setText("");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                mouse entered
            }

            @Override
            public void mouseExited(MouseEvent e) {
//            mouse exited
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerForm();
    }

    public void displayTable(){
        scrollPane = new JScrollPane();
        scrollPane.setBounds(56, 95, 478, 327);
        pnltblcustomer.add(scrollPane);

        String[] columnName = {"custNo", "firstName", "lastName","TelpNo","address"};
        tbmodel = new DefaultTableModel(columnName,0);

        try{
//            Class.forName("com.mysql.jdbc.Driver");
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String sql = "select * from customer";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            customerTb = new JTable();

            while(rs.next()){
                String custNo = String.valueOf(rs.getString("custno"));
                customer = new Customer(custNo,rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("telpNo"),
                        rs.getString("address"));

                String data[] = {customer.getCustNo(),
                        customer.getFname(),
                        customer.getLname(),
                        String.valueOf(customer.getTelpNo()),
                        customer.getAddress()};
                tbmodel.addRow(data);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        customerTb = new JTable(tbmodel);
        scrollPane.setViewportView(customerTb);
    }

    public void insert(String no,String fname, String lname,Integer telp,String address) throws SQLException {

        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "INSERT INTO customer(custno,firstname,lastname,address,telpno) " +
                    "VALUES('"+no+"','"+fname+"','"+lname+"','"+address+"','"+telp+"')";
            Statement statement = conn.createStatement();

            statement.executeUpdate(query);

            Object data[] = {no,fname,lname, String.valueOf(telp),address};
            tbmodel.addRow(data);
            JOptionPane.showMessageDialog(null, "Success insert data", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Id must be different","information",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void update(String no,String fname, String lname,int telp,String address) throws SQLException{
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "UPDATE customer set firstname= '"+fname+"', lastname='"+lname+"', address='"+address+"',telpno='"+telp+"'" +
                    "where custno='"+no+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            tbmodel.setValueAt(fname,customerTb.getSelectedRow(),1);
            tbmodel.setValueAt(lname,customerTb.getSelectedRow(),2);
            tbmodel.setValueAt(address,customerTb.getSelectedRow(),3);
            tbmodel.setValueAt(telpNo,customerTb.getSelectedRow(),4);

            JOptionPane.showMessageDialog(null, "Success Update data", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void delete(String no) throws SQLException{
        Database db = new Database();
        conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
        String query = "delete from customer where custno='"+no+"'";
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

        if(customerTb.getSelectedRow() != -1){
            tbmodel.removeRow(customerTb.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Delete Data success", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
