import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JFrame {
    public LoginForm(){
        getContentPane().setFont(new Font("Century Gothic", Font.PLAIN, 19));
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Login");

        JPanel pnlSignIn = new JPanel();
        pnlSignIn.setBackground(new Color(135, 206, 250));
        pnlSignIn.setPreferredSize(new Dimension(400, 0));
        getContentPane().add(pnlSignIn, BorderLayout.EAST);
        pnlSignIn.setLayout(null);

        JLabel lblSignIn = new JLabel("Sign In");
        lblSignIn.setForeground(new Color(0, 0, 0));
        lblSignIn.setBounds(159, 50, 102, 48);
        lblSignIn.setFont(new Font("Century Gothic", Font.BOLD, 32));
        pnlSignIn.add(lblSignIn);

        JLabel lblGarisptspts = new JLabel("-----------------");
        lblGarisptspts.setForeground(new Color(0, 0, 0));
        lblGarisptspts.setFont(new Font("Century Gothic", Font.BOLD, 32));
        lblGarisptspts.setBounds(94, 80, 236, 48);
        pnlSignIn.add(lblGarisptspts);

        JTextField UsernameTextField = new JTextField();
        UsernameTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        UsernameTextField.setBounds(42, 185, 326, 42);
        pnlSignIn.add(UsernameTextField);
        UsernameTextField.setColumns(10);

        JLabel lblUsername = new JLabel("UserName");
        lblUsername.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblUsername.setBounds(42, 159, 108, 25);
        pnlSignIn.add(lblUsername);

        JLabel lblPassword = new JLabel("PassWord\r\n");
        lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblPassword.setBounds(41, 237, 108, 25);
        pnlSignIn.add(lblPassword);

        JPasswordField PasswordTextField = new JPasswordField();
        PasswordTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        PasswordTextField.setBounds(42, 261, 326, 42);
        pnlSignIn.add(PasswordTextField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(0, 0, 0));
        btnLogin.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnLogin.setForeground(Color.white);
        btnLogin.setBounds(72, 348, 108, 42);
        pnlSignIn.add(btnLogin);

        JButton btnExit = new JButton("Exit");
        btnExit.setBackground(new Color(0, 0, 0));
        btnExit.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnExit.setForeground(Color.white);
        btnExit.setBounds(222, 348, 108, 42);
        pnlSignIn.add(btnExit);

        JPanel pnlLogo = new JPanel();
        pnlLogo.setPreferredSize(new Dimension(587, 0));
        getContentPane().add(pnlLogo, BorderLayout.CENTER);
        pnlLogo.setLayout(new BorderLayout(0, 0));

        Color bgColor = Color.decode("#FFD0BF");
        JLabel lblImg = new JLabel();
        lblImg.setBounds(50,30,500,500);
        lblImg.setBackground(bgColor);
        lblImg.setOpaque(true);
        lblImg.setHorizontalAlignment(SwingConstants.CENTER);
        pnlLogo.add(lblImg, BorderLayout.CENTER);


//       Lokasi gambar berdasarkan directory
//        ImageIcon icon = new ImageIcon("D:\\YL\\Kuliah\\Semester 3\\OOP\\Uas\\Walet_Proyek\\src\\main\\java\\laskin.jpg");
        ImageIcon icon = new ImageIcon(getClass().getResource("laskin.jpg"));
        Image img = icon.getImage();
        Image imageScale = img.getScaledInstance(lblImg.getWidth(),lblImg.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon imageScaled = new ImageIcon(imageScale);
        lblImg.setIcon(imageScaled);

//        Button Action
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = String.valueOf(PasswordTextField.getPassword());
                String user = UsernameTextField.getText();
                try{
                    if(user == null || pass == null)
                        JOptionPane.showMessageDialog(null,"Data field cannot blank","Error",JOptionPane.ERROR_MESSAGE);
                    else{

                        Database db = new Database();
                        Connection conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
                        Statement statement = conn.createStatement();

                        String query ="select * from admin where username='"+user+"' and password='"+pass+"'";
                        ResultSet rs = statement.executeQuery(query);

                        if(rs.next()){
                            JOptionPane.showMessageDialog(null,"Login berhasil");
                            dispose();
                            new MainHome();
                        }
                    }
                }catch(Exception ex){
                   JOptionPane.showMessageDialog(null,"Cannot login","eror",JOptionPane.ERROR_MESSAGE);
                   ex.printStackTrace();
                }
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

}
