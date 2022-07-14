import AppPackage.Controller.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class EmployeeForm extends JFrame {
    private Connection conn;
    Employee employee;

    DefaultTableModel tbmodel;
    JScrollPane scrollPane;
    JPanel pnltblemployee;
    JTable tableEmployee;

    private String empId;
    private String fname;
    private String lname;
    private String address;
    private String email;
    private String telpno;
    private String salary;

    public EmployeeForm(){

        setBounds(100, 100, 1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Employee");
        setResizable(false);

        JPanel pnlemployee = new JPanel();
        pnlemployee.setLayout(null);
        pnlemployee.setPreferredSize(new Dimension(400, 0));
        pnlemployee.setForeground(Color.BLACK);
        pnlemployee.setBackground(new Color(135, 206, 250));
        getContentPane().add(pnlemployee, BorderLayout.WEST);

        JLabel lblEmployee = new JLabel("Employee");
        lblEmployee.setFont(new Font("Century Gothic", Font.BOLD, 23));
        lblEmployee.setBounds(128, 30, 141, 39);
        pnlemployee.add(lblEmployee);

        JLabel lblEmpid = new JLabel("EmpID");
        lblEmpid.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblEmpid.setBounds(20, 104, 108, 25);
        pnlemployee.add(lblEmpid);

        JLabel lblFirstname = new JLabel("FirstName");
        lblFirstname.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblFirstname.setBounds(20, 153, 108, 25);
        pnlemployee.add(lblFirstname);

        JLabel lblLastname = new JLabel("LastName");
        lblLastname.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblLastname.setBounds(20, 210, 108, 25);
        pnlemployee.add(lblLastname);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblAddress.setBounds(20, 262, 108, 25);
        pnlemployee.add(lblAddress);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblEmail.setBounds(20, 363, 108, 25);
        pnlemployee.add(lblEmail);

        JLabel lblTelpNo = new JLabel("TelpNo");
        lblTelpNo.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblTelpNo.setBounds(20, 415, 108, 25);
        pnlemployee.add(lblTelpNo);

        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblSalary.setBounds(20, 470, 108, 25);
        pnlemployee.add(lblSalary);

        JTextField empIDTextField = new JTextField();
        empIDTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        empIDTextField.setColumns(10);
        empIDTextField.setBounds(157, 101, 215, 31);
        pnlemployee.add(empIDTextField);

        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        firstNameTextField.setColumns(10);
        firstNameTextField.setBounds(157, 153, 215, 31);
        pnlemployee.add(firstNameTextField);

        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        lastNameTextField.setColumns(10);
        lastNameTextField.setBounds(157, 210, 215, 31);
        pnlemployee.add(lastNameTextField);

        JTextPane addressTextPane = new JTextPane();
        addressTextPane.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        addressTextPane.setBounds(157, 262, 215, 81);
        pnlemployee.add(addressTextPane);

        JTextField emailTextField = new JTextField();
        emailTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        emailTextField.setColumns(10);
        emailTextField.setBounds(157, 363, 215, 31);
        pnlemployee.add(emailTextField);

        JTextField telpNoTextField = new JTextField();
        telpNoTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        telpNoTextField.setColumns(10);
        telpNoTextField.setBounds(157, 415, 215, 31);
        pnlemployee.add(telpNoTextField);

        JTextField salaryTextField = new JTextField();
        salaryTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        salaryTextField.setColumns(10);
        salaryTextField.setBounds(157, 470, 215, 31);
        pnlemployee.add(salaryTextField);

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnClear.setBackground(Color.BLACK);
        btnClear.setForeground(Color.white);
        btnClear.setBounds(51, 593, 108, 42);
        pnlemployee.add(btnClear);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.white);
        btnBack.setBounds(201, 593, 108, 42);
        pnlemployee.add(btnBack);

        pnltblemployee = new JPanel();
        pnltblemployee.setLayout(null);
        pnltblemployee.setPreferredSize(new Dimension(587, 0));
        pnltblemployee.setBackground(new Color(0, 0, 205));
        getContentPane().add(pnltblemployee, BorderLayout.CENTER);

        JButton btnInsert = new JButton("Insert");
        btnInsert.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnInsert.setBackground(Color.BLACK);
        btnInsert.setForeground(Color.white);
        btnInsert.setBounds(85, 591, 108, 33);
        pnltblemployee.add(btnInsert);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.white);
        btnUpdate.setBounds(235, 591, 108, 33);
        pnltblemployee.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnDelete.setBackground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBounds(387, 591, 108, 33);
        pnltblemployee.add(btnDelete);

        tableEmployee = new JTable();
        tableEmployee.setBackground(Color.WHITE);
        tableEmployee.setBounds(64, 562, 485, -510);
        pnltblemployee.add(tableEmployee);

//        Menampilkan table employee
        displaytable();

//        Action button
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empIDTextField.setText("");
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                addressTextPane.setText("");
                emailTextField.setText("");
                telpNoTextField.setText("");
                salaryTextField.setText("");
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
                empId = empIDTextField.getText();
                fname = firstNameTextField.getText();
                lname = lastNameTextField.getText();
                address = addressTextPane.getText();
                email = emailTextField.getText();
                telpno = telpNoTextField.getText();
                salary = salaryTextField.getText();

                try{
                    if(empId.equals("") ||
                            fname.equals("") ||
                            lname.equals("") ||
                            address.equals("") ||
                            email.equals("") ||
                            telpno.equals("")||
                            salary.equals("")){
                        JOptionPane.showMessageDialog(null,"Data field cannot blank","Error",JOptionPane.ERROR_MESSAGE);
                    }if(empId.length() > 5){
                        JOptionPane.showMessageDialog(null,"empId must be 5 character","Information",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        try{
                            int num= Integer.parseInt(telpno);
                            int sal = Integer.parseInt(salary);
                            employee = new Employee(empId,fname,lname,address,email,num,sal);
                            insert(employee.getId(),
                                    employee.getFname(),
                                    employee.getLname(),
                                    employee.getAddress(),
                                    employee.getEmail(),
                                    employee.getTelpno(),
                                    employee.getSalary());
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null,"telpno and salary must be numeric","information",JOptionPane.INFORMATION_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empId = empIDTextField.getText();
                fname = firstNameTextField.getText();
                lname = lastNameTextField.getText();
                address = addressTextPane.getText();
                email = emailTextField.getText();
                telpno = telpNoTextField.getText();
                salary = salaryTextField.getText();

                try{
                    int num= Integer.parseInt(telpno);
                    int sal = Integer.parseInt(salary);
                    employee = new Employee(empId,fname,lname,address,email,num,sal);
                    update(employee.getId(),
                            employee.getFname(),
                            employee.getLname(),
                            employee.getAddress(),
                            employee.getEmail(),
                            employee.getTelpno(),
                            employee.getSalary());
                }catch(Exception error){
                    error.printStackTrace();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empId = empIDTextField.getText();
                try{
                    delete(empId);
                    empIDTextField.setText("");
                    firstNameTextField.setText("");
                    lastNameTextField.setText("");
                    addressTextPane.setText("");
                    emailTextField.setText("");
                    telpNoTextField.setText("");
                    salaryTextField.setText("");
                }catch(Exception error){
                    error.printStackTrace();
                }
            }
        });

//    Memilih tuple dari jTable
        tableEmployee.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = tableEmployee.getSelectedRow();
                empIDTextField.setText(tbmodel.getValueAt(rowIndex,0).toString());
                firstNameTextField.setText(tbmodel.getValueAt(rowIndex,1).toString());
                lastNameTextField.setText(tbmodel.getValueAt(rowIndex,2).toString());
                addressTextPane.setText(tbmodel.getValueAt(rowIndex,3).toString());
                emailTextField.setText(tbmodel.getValueAt(rowIndex,4).toString());
                telpNoTextField.setText(tbmodel.getValueAt(rowIndex,5).toString());
                salaryTextField.setText(tbmodel.getValueAt(rowIndex,6).toString());
                JOptionPane.showMessageDialog(null,"row selected","Information",JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int rowIndex = tableEmployee.getSelectedRow();
                empIDTextField.setText(tbmodel.getValueAt(rowIndex,0).toString());
                firstNameTextField.setText(tbmodel.getValueAt(rowIndex,1).toString());
                lastNameTextField.setText(tbmodel.getValueAt(rowIndex,2).toString());
                addressTextPane.setText(tbmodel.getValueAt(rowIndex,3).toString());
                emailTextField.setText(tbmodel.getValueAt(rowIndex,4).toString());
                telpNoTextField.setText(tbmodel.getValueAt(rowIndex,5).toString());
                salaryTextField.setText(tbmodel.getValueAt(rowIndex,6).toString());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                empIDTextField.setText("");
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                addressTextPane.setText("");
                emailTextField.setText("");
                telpNoTextField.setText("");
                salaryTextField.setText("");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                mouse entered
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                mouse entered
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new EmployeeForm();
    }

    public void displaytable(){
        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 95, 720, 327);
        pnltblemployee.add(scrollPane);

        String[] columnName = {"empID","firstname","lastname","address","email","telpno","salary"};
        tbmodel = new DefaultTableModel(columnName,0);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String sql = "select * from employees";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            tableEmployee = new JTable();

            while(rs.next()){
                String empId = String.valueOf(rs.getString("empID"));
                employee = new Employee(empId,
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getInt("telpno"),
                        rs.getInt("salary"));

                Object[] data = {employee.getId(),
                        employee.getFname(),
                        employee.getLname(),
                        employee.getAddress(),
                        employee.getEmail(),
                        employee.getTelpno(),
                        employee.getSalary()
                    };
                tbmodel.addRow(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        tableEmployee = new JTable(tbmodel);
        scrollPane.setViewportView(tableEmployee);
    }

    public void insert(String id,String fname,String lname,String address,String email,int telp,int sal) throws SQLException {
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "INSERT into employees(empID,firstname,lastname,address,email,telpno,salary)" +
                    "VALUES('"+id+"','"+fname+"','"+lname+"','"+address+"','"+email+"','"+telp+"','"+sal+"')";

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            Object[] data = {id,fname,lname,address,email,String.valueOf(telp),String.valueOf(sal)};
            tbmodel.addRow(data);

            JOptionPane.showMessageDialog(null, "Success insert data", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Id must be different","information",JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }

    public void update(String id,String fname,String lname,String address,String email,int telpno,int sal) throws SQLException {
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "UPDATE employees set firstname='"+fname+"',lastname='"+lname+"',address='"+address+"'," +
                    "email='"+email+"',telpno='"+telpno+"',salary='"+sal+"' where empID='"+id+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            tbmodel.setValueAt(fname,tableEmployee.getSelectedRow(),1);
            tbmodel.setValueAt(lname,tableEmployee.getSelectedRow(),2);
            tbmodel.setValueAt(address,tableEmployee.getSelectedRow(),3);
            tbmodel.setValueAt(email,tableEmployee.getSelectedRow(),4);
            tbmodel.setValueAt(telpno,tableEmployee.getSelectedRow(),5);
            tbmodel.setValueAt(sal,tableEmployee.getSelectedRow(),6);

            JOptionPane.showMessageDialog(null, "Success Update data", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
           e.printStackTrace();
        }
    }

    public void delete(String id) throws SQLException{
        Database db = new Database();
        conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
        String query = "delete from employees where empID='"+id+"'";
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

        if(tableEmployee.getSelectedRow() != -1){
            tbmodel.removeRow(tableEmployee.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Delete Data success", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
