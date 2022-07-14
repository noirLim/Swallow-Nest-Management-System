import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainHome extends JFrame {
    public MainHome(){
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Home");

        JPanel pnlBtn = new JPanel();
        pnlBtn.setBackground(new Color(135, 206, 250));
        pnlBtn.setPreferredSize(new Dimension(400, 0));
        getContentPane().add(pnlBtn, BorderLayout.WEST);
        pnlBtn.setLayout(null);

        JButton btnCustomers = new JButton("Customers");
        btnCustomers.setBounds(110, 55, 163, 33);
        btnCustomers.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnCustomers.setForeground(Color.white);
        btnCustomers.setBackground(Color.black);
        pnlBtn.add(btnCustomers);

        JButton btnEmployee = new JButton("Employee");
        btnEmployee.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnEmployee.setForeground(Color.white);
        btnEmployee.setBackground(Color.BLACK);
        btnEmployee.setBounds(110, 130, 163, 33);
        pnlBtn.add(btnEmployee);

        JButton btnInventory = new JButton("Inventory");
        btnInventory.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnInventory.setForeground(Color.white);
        btnInventory.setBackground(Color.BLACK);
        btnInventory.setBounds(110, 205, 163, 33);
        pnlBtn.add(btnInventory);

        JButton btnSupplier = new JButton("Supplier");
        btnSupplier.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnSupplier.setForeground(Color.WHITE);
        btnSupplier.setBackground(Color.BLACK);
        btnSupplier.setBounds(110, 280, 163, 33);
        pnlBtn.add(btnSupplier);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnLogout.setForeground(Color.white);
        btnLogout.setBackground(Color.BLACK);
        btnLogout.setBounds(110, 486, 163, 33);
        pnlBtn.add(btnLogout);

        JButton btnOrders = new JButton("Orders");
        btnOrders.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnOrders.setForeground(Color.white);
        btnOrders.setBackground(Color.BLACK);
        btnOrders.setBounds(110, 353, 163, 33);
        pnlBtn.add(btnOrders);

        Color bgColor = Color.decode("#FFD0BF");
        JPanel pnlLogo = new JPanel();
        pnlLogo.setBackground(bgColor);
        pnlLogo.setPreferredSize(new Dimension(587, 0));
        getContentPane().add(pnlLogo, BorderLayout.CENTER);
        pnlLogo.setLayout(new BorderLayout(0, 0));

        JLabel logoLbl = new JLabel();
        logoLbl.setHorizontalAlignment(SwingConstants.CENTER);
        logoLbl.setBounds(50,30,500,500);
        pnlLogo.add(logoLbl, BorderLayout.CENTER);

//        Lokasi gambar berdasarkan directory
        ImageIcon icon = new ImageIcon(getClass().getResource("laskin.jpg"));
        Image img = icon.getImage();
        Image imageScale = img.getScaledInstance(logoLbl.getWidth(),logoLbl.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon imageScaled = new ImageIcon(imageScale);
        logoLbl.setIcon(imageScaled);

//        Action button
        btnCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CustomerForm();
            }
        });

        btnEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EmployeeForm();
            }
        });

        btnInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Inventory();
            }
        });

        btnSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SupplierForm();
            }
        });

        btnOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OrderForm();
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm();
            }
        });

        setVisible(true);
    }
}
