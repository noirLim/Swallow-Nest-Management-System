import AppPackage.Controller.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class SupplierForm extends JFrame{
    private Connection conn;
    Supplier sup;
    DefaultTableModel tbmodel;
    JScrollPane scrollPane;
    JTable tablesupplier;
    JPanel pnltblsupplier;

    private String id;
    private String contact;
    private String telpno;
    private String companyname;
    private String address;

    public SupplierForm(){

        setBounds(180, 100, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Supplier");
        setResizable(false);

        JPanel pnlsupplier = new JPanel();
        pnlsupplier.setLayout(null);
        pnlsupplier.setPreferredSize(new Dimension(450, 0));
        pnlsupplier.setForeground(Color.BLACK);
        pnlsupplier.setBackground(new Color(135, 206, 250));
        getContentPane().add(pnlsupplier, BorderLayout.WEST);

        pnltblsupplier = new JPanel();
        pnltblsupplier.setLayout(null);
        pnltblsupplier.setPreferredSize(new Dimension(587, 0));
        pnltblsupplier.setBackground(new Color(0, 0, 205));
        getContentPane().add(pnltblsupplier, BorderLayout.CENTER);

        JLabel lblSupplier = new JLabel("Supplier");
        lblSupplier.setFont(new Font("Century Gothic", Font.BOLD, 23));
        lblSupplier.setBounds(176, 30, 141, 39);
        pnlsupplier.add(lblSupplier);

        JLabel lblSupplierid = new JLabel("SupplierID");
        lblSupplierid.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblSupplierid.setBounds(51, 104, 108, 25);
        pnlsupplier.add(lblSupplierid);

        JLabel lblContactname = new JLabel("ContactName");
        lblContactname.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblContactname.setBounds(41, 153, 137, 25);
        pnlsupplier.add(lblContactname);

        JLabel lblTelpno_1 = new JLabel("TelpNo");
        lblTelpno_1.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblTelpno_1.setBounds(51, 210, 108, 25);
        pnlsupplier.add(lblTelpno_1);

        JLabel lblCompanyname = new JLabel("CompanyName");
        lblCompanyname.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblCompanyname.setBounds(31, 265, 158, 25);
        pnlsupplier.add(lblCompanyname);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblAddress.setBounds(51, 365, 108, 25);
        pnlsupplier.add(lblAddress);

        JTextField supplierIDTextField = new JTextField();
        supplierIDTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        supplierIDTextField.setColumns(10);
        supplierIDTextField.setBounds(188, 101, 215, 31);
        pnlsupplier.add(supplierIDTextField);

        JTextField contactNameTextField = new JTextField();
        contactNameTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        contactNameTextField.setColumns(10);
        contactNameTextField.setBounds(188, 153, 215, 31);
        pnlsupplier.add(contactNameTextField);

        JTextField telpNoTextField = new JTextField();
        telpNoTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        telpNoTextField.setColumns(10);
        telpNoTextField.setBounds(188, 210, 215, 31);
        pnlsupplier.add(telpNoTextField);

        JTextPane companyNameTextPane = new JTextPane();
        companyNameTextPane.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        companyNameTextPane.setBounds(188, 262, 215, 74);
        pnlsupplier.add(companyNameTextPane);

        JTextPane addressTextPane = new JTextPane();
        addressTextPane.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        addressTextPane.setBounds(188, 365, 215, 96);
        pnlsupplier.add(addressTextPane);

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnClear.setBackground(Color.BLACK);
        btnClear.setForeground(Color.WHITE);
        btnClear.setBounds(76, 500, 108, 42);
        pnlsupplier.add(btnClear);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.white);
        btnBack.setBounds(226, 500, 108, 42);
        pnlsupplier.add(btnBack);

        JButton btnInsert = new JButton("Insert");
        btnInsert.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnInsert.setBackground(Color.BLACK);
        btnInsert.setForeground(Color.white);
        btnInsert.setBounds(87, 499, 108, 33);
        pnltblsupplier.add(btnInsert);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.white);
        btnUpdate.setBounds(237, 499, 108, 33);
        pnltblsupplier.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnDelete.setBackground(Color.BLACK);
        btnDelete.setForeground(Color.white);
        btnDelete.setBounds(389, 499, 108, 33);
        pnltblsupplier.add(btnDelete);

        tablesupplier = new JTable();
        tablesupplier.setBackground(Color.WHITE);
        tablesupplier.setBounds(64, 441, 485, -389);
        pnltblsupplier.add(tablesupplier);

//        Menampilkan data table
        displayTable();

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supplierIDTextField.setText("");
                contactNameTextField.setText("");
                telpNoTextField.setText("");
                companyNameTextPane.setText("");
                addressTextPane.setText("");
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainHome();
            }
        });

        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = supplierIDTextField.getText();
                contact = contactNameTextField.getText();
                telpno = telpNoTextField.getText();
                companyname = companyNameTextPane.getText();
                address = addressTextPane.getText();

                try{
                    if(id.equals("") || contact.equals("")|| telpno.equals("")|| companyname.equals("")||address.equals("")){
                        JOptionPane.showMessageDialog(null,"Data field cannot blank","Error",JOptionPane.ERROR_MESSAGE);
                    }if(id.length() > 5){
                        JOptionPane.showMessageDialog(null,"custNo must be 5 character","Information",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        try{
                            int telNum = Integer.parseInt(telpno);
                            sup = new Supplier(id,contact,telNum,companyname,address);
                            insert(sup.getId(),
                                    sup.getContact(),
                                    sup.getTelpNo(),
                                    sup.getCompany(),
                                    sup.getAddress());
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null,"telpno must be numeric","information",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = supplierIDTextField.getText();
                contact = contactNameTextField.getText();
                telpno = telpNoTextField.getText();
                companyname = companyNameTextPane.getText();
                address = addressTextPane.getText();
                try{
                    int telNum = Integer.parseInt(telpno);
                    sup = new Supplier(id,contact,telNum,companyname,address);
                    update(sup.getId(),
                            sup.getContact(),
                            sup.getTelpNo(),
                            sup.getCompany(),
                            sup.getAddress());
                }catch(Exception error){
                    error.printStackTrace();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = supplierIDTextField.getText();
                try{
                    delete(id);
                    supplierIDTextField.setText("");
                    contactNameTextField.setText("");
                    telpNoTextField.setText("");
                    companyNameTextPane.setText("");
                    addressTextPane.setText("");
                }catch(Exception error){
                    error.printStackTrace();
                }
            }
        });
//        Memilih tuple dari table
        tablesupplier.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = tablesupplier.getSelectedRow();
                supplierIDTextField.setText(tbmodel.getValueAt(rowIndex,0).toString());
                contactNameTextField.setText(tbmodel.getValueAt(rowIndex,1).toString());
                telpNoTextField.setText(tbmodel.getValueAt(rowIndex,2).toString());
                companyNameTextPane.setText(tbmodel.getValueAt(rowIndex,3).toString());
                addressTextPane.setText(tbmodel.getValueAt(rowIndex,4).toString());
                JOptionPane.showMessageDialog(null,"row selected","Information",JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int rowIndex = tablesupplier.getSelectedRow();
                supplierIDTextField.setText(tbmodel.getValueAt(rowIndex,0).toString());
                contactNameTextField.setText(tbmodel.getValueAt(rowIndex,1).toString());
                telpNoTextField.setText(tbmodel.getValueAt(rowIndex,2).toString());
                companyNameTextPane.setText(tbmodel.getValueAt(rowIndex,3).toString());
                addressTextPane.setText(tbmodel.getValueAt(rowIndex,4).toString());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                supplierIDTextField.setText("");
                contactNameTextField.setText("");
                telpNoTextField.setText("");
                companyNameTextPane.setText("");
                addressTextPane.setText("");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                mouseEntered
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                mouseExited
            }
        });

        setVisible(true);
    }

    public void displayTable(){
        scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 95, 478, 327);
        pnltblsupplier.add(scrollPane);

        String[] columnName = {"supplierID","contactName","telpno","companyName","address"};
        tbmodel = new DefaultTableModel(columnName,0);

        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String sql = "select * from supplier";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                String id = rs.getString("supplierID");
                sup = new Supplier(id,
                        rs.getString("contactname"),
                        rs.getInt("telpno"),
                        rs.getString("companyname"),
                        rs.getString("address"));

                String[] data = {sup.getId(),
                        sup.getContact(),
                        String.valueOf(sup.getTelpNo()),
                        sup.getCompany(),
                        sup.getAddress()};
                tbmodel.addRow(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        tablesupplier = new JTable(tbmodel);
        scrollPane.setViewportView(tablesupplier);
    }

    public void insert(String id, String contact, int telpNo, String company, String address) throws SQLException {
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "INSERT into supplier(supplierID,contactname,telpno,companyname,address)" +
                    "VALUES('"+id+"','"+contact+"','"+telpNo+"','"+company+"','"+address+"')";

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            Object[] data = {id,contact,String.valueOf(telpNo),company,address};
            tbmodel.addRow(data);
            JOptionPane.showMessageDialog(null, "Success insert data", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Id must be different","information",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void update(String id, String contact, int telpNo, String company, String address) throws SQLException{
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "UPDATE supplier set contactname='"+contact+"',telpno='"+telpNo+"',companyname='"+company+"',address='"+address+"'" +
                    "where supplierID='"+id+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            tbmodel.setValueAt(contact,tablesupplier.getSelectedRow(),1);
            tbmodel.setValueAt(telpNo,tablesupplier.getSelectedRow(),2);
            tbmodel.setValueAt(company,tablesupplier.getSelectedRow(),3);
            tbmodel.setValueAt(address,tablesupplier.getSelectedRow(),4);

            JOptionPane.showMessageDialog(null, "Success Update data", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void delete(String id) throws SQLException{
        Database db = new Database();
        conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
        String query = "delete from supplier where supplierID='"+id+"'";
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

        if(tablesupplier.getSelectedRow() != -1){
            tbmodel.removeRow(tablesupplier.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Delete Data success", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
