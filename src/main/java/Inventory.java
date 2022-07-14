import AppPackage.Controller.Category;
import AppPackage.Controller.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class Inventory extends JFrame {
    private Connection conn;
    Category cat;
    Product prod;

    JTable categoryTb;
    DefaultTableModel modelCategory;
    JScrollPane scrollCategory;

    JTable productTb;
    DefaultTableModel modelProduct;
    JScrollPane scrollProduct;

    JComboBox categoryIDcombobox;
    JComboBox suppliercombobox;

    private String catId;
    private String catName;
    private String catQuality;
    private String productId;
    private String supId;
    private String catProdId;
    private String productname;
    private String productUnit;
    private String productPrice;

    public Inventory(){
        setBounds(180, 50, 1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Inventory");
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel category = new JPanel();
        tabbedPane.addTab("Category", null, category, null);
        category.setLayout(new BorderLayout(0, 0));

        JPanel pnlcategory = new JPanel();
        pnlcategory.setLayout(null);
        pnlcategory.setPreferredSize(new Dimension(400, 0));
        pnlcategory.setForeground(Color.BLACK);
        pnlcategory.setBackground(new Color(135, 206, 250));
        category.add(pnlcategory, BorderLayout.WEST);

        JLabel lblcategory = new JLabel("Category Product");
        lblcategory.setFont(new Font("Century Gothic", Font.BOLD, 23));
        lblcategory.setBounds(84, 49, 209, 39);
        pnlcategory.add(lblcategory);

        JLabel lblcategoryID = new JLabel("CategoryID");
        lblcategoryID.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblcategoryID.setBounds(20, 158, 108, 25);
        pnlcategory.add(lblcategoryID);

        JLabel lblcategoryName = new JLabel("CategoryName");
        lblcategoryName.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblcategoryName.setBounds(10, 207, 148, 25);
        pnlcategory.add(lblcategoryName);

        JLabel lblquality = new JLabel("Quality");
        lblquality.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblquality.setBounds(20, 264, 73, 25);
        pnlcategory.add(lblquality);

        JTextField categoryIDTextField = new JTextField();
        categoryIDTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        categoryIDTextField.setColumns(10);
        categoryIDTextField.setBounds(157, 155, 215, 31);
        pnlcategory.add(categoryIDTextField);

        JTextField categoryNameTextField = new JTextField();
        categoryNameTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        categoryNameTextField.setColumns(10);
        categoryNameTextField.setBounds(157, 207, 215, 31);
        pnlcategory.add(categoryNameTextField);

        JComboBox qualitycombobox = new JComboBox();
        qualitycombobox.setBounds(157, 264, 215, 31);
        pnlcategory.add(qualitycombobox);
        qualitycombobox.addItem("Medium");
        qualitycombobox.addItem("Premium");
        qualitycombobox.setSelectedItem(null);

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnClear.setBackground(Color.BLACK);
        btnClear.setForeground(Color.white);
        btnClear.setBounds(50, 500, 108, 42);
        pnlcategory.add(btnClear);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.white);
        btnBack.setBounds(200, 500, 108, 42);
        pnlcategory.add(btnBack);

        JPanel pnltblcategory = new JPanel();
        pnltblcategory.setLayout(null);
        pnltblcategory.setPreferredSize(new Dimension(587, 0));
        pnltblcategory.setBackground(new Color(0, 0, 205));
        category.add(pnltblcategory, BorderLayout.CENTER);

        JButton btnInsertCat = new JButton("Insert");
        btnInsertCat.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnInsertCat.setBackground(Color.BLACK);
        btnInsertCat.setForeground(Color.white);
        btnInsertCat.setBounds(87, 499, 108, 33);
        pnltblcategory.add(btnInsertCat);

        JButton btnUpdateCat = new JButton("Update");
        btnUpdateCat.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnUpdateCat.setBackground(Color.BLACK);
        btnUpdateCat.setForeground(Color.WHITE);
        btnUpdateCat.setBounds(237, 499, 108, 33);
        pnltblcategory.add(btnUpdateCat);

        JButton btnDeleteCat = new JButton("Delete");
        btnDeleteCat.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnDeleteCat.setBackground(Color.BLACK);
        btnDeleteCat.setForeground(Color.white);
        btnDeleteCat.setBounds(389, 499, 108, 33);
        pnltblcategory.add(btnDeleteCat);

        scrollCategory = new JScrollPane();
        scrollCategory.setBounds(21, 29, 537, 452);
        pnltblcategory.add(scrollCategory);

//        display category table
        displayCategory();

//        Panel product
        JPanel product = new JPanel();
        tabbedPane.addTab("Product", null, product, null);
        product.setLayout(new BorderLayout(0, 0));

        JPanel pnlproduct = new JPanel();
        pnlproduct.setLayout(null);
        pnlproduct.setPreferredSize(new Dimension(400, 0));
        pnlproduct.setForeground(Color.BLACK);
        pnlproduct.setBackground(new Color(135, 206, 250));
        product.add(pnlproduct, BorderLayout.WEST);

        JLabel lblproductID = new JLabel("ProductID");
        lblproductID.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblproductID.setBounds(20, 98, 108, 25);
        pnlproduct.add(lblproductID);

        JLabel lblsupplierID = new JLabel("SupplierID");
        lblsupplierID.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblsupplierID.setBounds(20, 150, 99, 25);
        pnlproduct.add(lblsupplierID);

        JTextField productIdTextField = new JTextField();
        productIdTextField.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        productIdTextField.setColumns(10);
        productIdTextField.setBounds(157, 95, 215, 31);
        pnlproduct.add(productIdTextField);

        JLabel lblproduct = new JLabel("Product");
        lblproduct.setFont(new Font("Century Gothic", Font.BOLD, 23));
        lblproduct.setBounds(139, 27, 91, 39);
        pnlproduct.add(lblproduct);

        JButton btnClearProduct = new JButton("Clear");
        btnClearProduct.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnClearProduct.setBackground(Color.BLACK);
        btnClearProduct.setForeground(Color.white);
        btnClearProduct.setBounds(50, 500, 108, 42);
        pnlproduct.add(btnClearProduct);

        JButton btnBackProduct = new JButton("Back");
        btnBackProduct.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnBackProduct.setBackground(Color.BLACK);
        btnBackProduct.setForeground(Color.white);
        btnBackProduct.setBounds(200, 500, 108, 42);
        pnlproduct.add(btnBackProduct);

        JLabel lblcategoryID_1_1 = new JLabel("CategoryID");
        lblcategoryID_1_1.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblcategoryID_1_1.setBounds(20, 203, 108, 25);
        pnlproduct.add(lblcategoryID_1_1);

        JTextField productNameTextfield = new JTextField();
        productNameTextfield.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        productNameTextfield.setColumns(10);
        productNameTextfield.setBounds(157, 258, 215, 31);
        pnlproduct.add(productNameTextfield);

        JLabel lblproductName = new JLabel("ProductName");
        lblproductName.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblproductName.setBounds(20, 261, 128, 25);
        pnlproduct.add(lblproductName);

        JLabel lblproductunit = new JLabel("ProductUnit");
        lblproductunit.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblproductunit.setBounds(20, 311, 108, 25);
        pnlproduct.add(lblproductunit);

        JTextField productUnitTextfield = new JTextField();
        productUnitTextfield.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        productUnitTextfield.setColumns(10);
        productUnitTextfield.setBounds(157, 308, 215, 31);
        pnlproduct.add(productUnitTextfield);

        JTextField productPriceTextfield = new JTextField();
        productPriceTextfield.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        productPriceTextfield.setColumns(10);
        productPriceTextfield.setBounds(157, 360, 215, 31);
        pnlproduct.add(productPriceTextfield);

        JLabel lblproductprice = new JLabel("ProductPrice");
        lblproductprice.setFont(new Font("Century Gothic", Font.BOLD, 19));
        lblproductprice.setBounds(20, 363, 116, 25);
        pnlproduct.add(lblproductprice);

        suppliercombobox = new JComboBox();
        suppliercombobox.setBounds(157, 150, 215, 31);
        pnlproduct.add(suppliercombobox);

        categoryIDcombobox = new JComboBox();
        categoryIDcombobox.setBounds(157, 204, 215, 31);
        pnlproduct.add(categoryIDcombobox);

        JPanel pnltblproduct = new JPanel();
        pnltblproduct.setLayout(null);
        pnltblproduct.setPreferredSize(new Dimension(587, 0));
        pnltblproduct.setBackground(new Color(0, 0, 205));
        product.add(pnltblproduct, BorderLayout.CENTER);

        JButton btnInsertProduct = new JButton("Insert");
        btnInsertProduct.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnInsertProduct.setBackground(Color.BLACK);
        btnInsertProduct.setForeground(Color.WHITE);
        btnInsertProduct.setBounds(87, 499, 108, 33);
        pnltblproduct.add(btnInsertProduct);

        JButton btnUpdateProduct = new JButton("Update");
        btnUpdateProduct.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnUpdateProduct.setBackground(Color.BLACK);
        btnUpdateProduct.setForeground(Color.WHITE);
        btnUpdateProduct.setBounds(237, 499, 108, 33);
        pnltblproduct.add(btnUpdateProduct);

        JButton btnDeleteProduct = new JButton("Delete");
        btnDeleteProduct.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        btnDeleteProduct.setBackground(Color.BLACK);
        btnDeleteProduct.setForeground(Color.WHITE);
        btnDeleteProduct.setBounds(389, 499, 108, 33);
        pnltblproduct.add(btnDeleteProduct);

        scrollProduct = new JScrollPane();
        scrollProduct.setBounds(21, 29, 537, 452);
        pnltblproduct.add(scrollProduct);

        productTb = new JTable();
        scrollProduct.setViewportView(productTb);

        //display category table
        displayCategory();

//        Menampilkan product
        displayProduct();
        populateCombo();

//        Action button category
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoryIDTextField.setText("");
                categoryNameTextField.setText("");
                qualitycombobox.setSelectedItem(null);
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainHome();
            }
        });

        btnInsertCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catId = categoryIDTextField.getText();
                catName = categoryNameTextField.getText();
                catQuality = qualitycombobox.getSelectedItem().toString();
                try{
                    if(catId.equals("") || catName.equals("") || catQuality.equals("")){
                        JOptionPane.showMessageDialog(null,"Data field cannot blank","Error",JOptionPane.ERROR_MESSAGE);
                    }if(catId.length() > 4){
                        JOptionPane.showMessageDialog(null,"empId must be 4 character","Information",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        cat = new Category(catId,catName,catQuality);
                        insertCategory(cat.getId(),cat.getName(),cat.getQuality());
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        btnUpdateCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catId = categoryIDTextField.getText();
                catName = categoryNameTextField.getText();
                catQuality = qualitycombobox.getSelectedItem().toString();

                try{
                    cat = new Category(catId,catName,catQuality);
                    updateCategory(cat.getId(),cat.getName(),cat.getQuality());

                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        btnDeleteCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catId = categoryIDTextField.getText();
                try {
                    deleteCategory(catId);
                    categoryIDTextField.setText("");
                    categoryNameTextField.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

//       Memilig tuple dari table
        categoryTb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = categoryTb.getSelectedRow();
                categoryIDTextField.setText(modelCategory.getValueAt(rowIndex,0).toString());
                categoryNameTextField.setText(modelCategory.getValueAt(rowIndex,1).toString());
                qualitycombobox.setSelectedItem(modelCategory.getValueAt(rowIndex,2).toString());
                JOptionPane.showMessageDialog(null,"row selected","Information",JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int rowIndex = categoryTb.getSelectedRow();
                categoryIDTextField.setText(modelCategory.getValueAt(rowIndex,0).toString());
                categoryNameTextField.setText(modelCategory.getValueAt(rowIndex,1).toString());
                qualitycombobox.setSelectedItem(modelCategory.getValueAt(rowIndex,2).toString());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                categoryIDTextField.setText("");
                categoryNameTextField.setText("");
                qualitycombobox.setSelectedItem(null);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                mouseEntered
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                mouseEntered
            }
        });

//        Action button Product
        btnClearProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productIdTextField.setText("");
                productNameTextfield.setText("");
                productUnitTextfield.setText("");
                productPriceTextfield.setText("");
            }
        });

        btnBackProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainHome();
            }
        });

        btnInsertProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productId = productIdTextField.getText();
                supId = suppliercombobox.getSelectedItem().toString();
                catProdId = categoryIDcombobox.getSelectedItem().toString();
                productname = productNameTextfield.getText();
                productUnit = productUnitTextfield.getText();
                productPrice = productPriceTextfield.getText();

                try{
                    if(productId.equals("") || productname.equals("") || productUnit.equals("") || productPrice.equals("")){
                        JOptionPane.showMessageDialog(null,"Data field cannot blank","Error",JOptionPane.ERROR_MESSAGE);
                    }if(productId.length() > 6){
                        JOptionPane.showMessageDialog(null,"id must be 6 character","Information",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        try{
                            int unit = Integer.parseInt(productUnit);
                            int price = Integer.parseInt(productPrice);
                            prod = new Product(productId,supId,catProdId,productname,unit,price);
                            insertProduct(
                                    prod.getId(),
                                    prod.getSupId(),
                                    prod.getCatId(),
                                    prod.getName(),
                                    prod.getUnit(),
                                    prod.getPrice()
                            );
                        }catch (Exception error){
                            JOptionPane.showMessageDialog(null,"unit and price must be numeric","information",JOptionPane.INFORMATION_MESSAGE);
                            error.printStackTrace();
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        btnUpdateProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productId = productIdTextField.getText();
                supId = suppliercombobox.getSelectedItem().toString();
                catProdId = categoryIDcombobox.getSelectedItem().toString();
                productname = productNameTextfield.getText();
                productUnit = productUnitTextfield.getText();
                productPrice = productPriceTextfield.getText();

                try{
                    int unit = Integer.parseInt(productUnit);
                    int price = Integer.parseInt(productPrice);
                    prod = new Product(productId,supId,catProdId,productname,unit,price);
                    updateProduct(
                            prod.getId(),
                            prod.getSupId(),
                            prod.getCatId(),
                            prod.getName(),
                            prod.getUnit(),
                            prod.getPrice()
                        );
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        btnDeleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productId = productIdTextField.getText();
                try {
                    deleteProduct(productId);
                    productIdTextField.setText("");
                    productNameTextfield.setText("");
                    productUnitTextfield.setText("");
                    productPriceTextfield.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

//        Memilig tuple dari jtable
        productTb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = productTb.getSelectedRow();
                productIdTextField.setText(modelProduct.getValueAt(rowIndex,0).toString());
                suppliercombobox.setSelectedItem(modelProduct.getValueAt(rowIndex,1).toString());
                categoryIDcombobox.setSelectedItem(modelProduct.getValueAt(rowIndex,2).toString());
                productNameTextfield.setText(modelProduct.getValueAt(rowIndex,3).toString());
                productUnitTextfield.setText(modelProduct.getValueAt(rowIndex,4).toString());
                productPriceTextfield.setText(modelProduct.getValueAt(rowIndex,5).toString());
                JOptionPane.showMessageDialog(null,"row selected","Information",JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int rowIndex = productTb.getSelectedRow();
                productIdTextField.setText(modelProduct.getValueAt(rowIndex,0).toString());
                suppliercombobox.setSelectedItem(modelProduct.getValueAt(rowIndex,1).toString());
                categoryIDcombobox.setSelectedItem(modelProduct.getValueAt(rowIndex,2).toString());
                productNameTextfield.setText(modelProduct.getValueAt(rowIndex,3).toString());
                productUnitTextfield.setText(modelProduct.getValueAt(rowIndex,4).toString());
                productPriceTextfield.setText(modelProduct.getValueAt(rowIndex,5).toString());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                productIdTextField.setText("");
                productNameTextfield.setText("");
                productUnitTextfield.setText("");
                productPriceTextfield.setText("");
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

    public void displayCategory(){
        String[] columnName = {"categoryId","categoryname","quality"};
        modelCategory = new DefaultTableModel(columnName,0);
        categoryTb = new JTable(modelCategory);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String sql = "select * from category";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                String id = String.valueOf(rs.getString("categoryID"));
                cat = new Category(id,rs.getString("categoryname"),rs.getString("quality"));
                String[] data = {cat.getId(),cat.getName(),cat.getQuality()};
                modelCategory.addRow(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        scrollCategory.setViewportView(categoryTb);
    }

    public void displayProduct(){
        String[] columnName = {"productID","supplierId","categoryID","productName","productUnit","productPrice"};
        modelProduct = new DefaultTableModel(columnName,0);
        productTb = new JTable(modelProduct);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String sql = "select * from product";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                String id = String.valueOf(rs.getString("productID"));
                String supId = String.valueOf(rs.getString("supplierID"));
                String catId = String.valueOf(rs.getString("categoryID"));
                prod = new Product(id,supId,catId,rs.getString("productName"),rs.getInt("product_unit"),rs.getInt("product_price"));
                Object[] data = {
                        prod.getId(),
                        prod.getSupId(),
                        prod.getCatId(),
                        prod.getName(),
                        prod.getUnit(),
                        prod.getPrice()
                };
                modelProduct.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        scrollProduct.setViewportView(productTb);
    }

    public void insertCategory(String id,String name,String quality) throws SQLException {
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "insert into category(categoryID,categoryname,quality)" +
                    "values('"+id+"','"+name+"','"+quality+"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            Object[] data = {id,name,quality};
            modelCategory.addRow(data);

            JOptionPane.showMessageDialog(null, "Success insert data", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Id must be different","information",JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }

    public void insertProduct(String id,String supID,String catID,String name,int unit,int price) throws SQLException{
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "insert into product(productID,supplierID,categoryID,productName,product_unit,product_price)" +
                    "VALUES('"+id+"','"+supID+"','"+catID+"','"+name+"','"+unit+"','"+price+"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            Object[] data = {id,supID,catID,name,unit,price};
            modelProduct.addRow(data);
            JOptionPane.showMessageDialog(null, "Success insert data", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Id must be different","information",JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }

    public void updateCategory(String id,String name,String quality){
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "UPDATE category set categoryname='"+name+"',quality='"+quality+"'" +
                    "where categoryID='"+id+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            modelCategory.setValueAt(name,categoryTb.getSelectedRow(),1);
            modelCategory.setValueAt(quality,categoryTb.getSelectedRow(),2);
            JOptionPane.showMessageDialog(null, "Success Update data", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateProduct(String id,String supID,String catID,String name,int unit,int price) throws SQLException{
        try{
            Database db = new Database();
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String query = "UPDATE product set supplierID='"+supID+"',categoryID='"+catID+"',productname='"+name+"'," +
                    "product_unit='"+unit+"',product_price='"+price+"' where productID='"+id+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            modelProduct.setValueAt(supID,productTb.getSelectedRow(),1);
            modelProduct.setValueAt(catID,productTb.getSelectedRow(),2);
            modelProduct.setValueAt(name,productTb.getSelectedRow(),3);
            modelProduct.setValueAt(unit,productTb.getSelectedRow(),4);
            modelProduct.setValueAt(price,productTb.getSelectedRow(),5);

            JOptionPane.showMessageDialog(null, "Success Update data", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteCategory(String id) throws SQLException{
        Database db = new Database();
        conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
        String query = "delete from category where categoryID='"+id+"'";
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

        if(categoryTb.getSelectedRow() != -1){
            modelCategory.removeRow(categoryTb.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Delete Data success", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void deleteProduct(String id) throws SQLException{
        Database db = new Database();
        conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
        String query = "delete from product where productID='"+id+"'";
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

        if(productTb.getSelectedRow() != -1){
            modelProduct.removeRow(productTb.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Delete Data success", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void populateCombo(){
        Database db = new Database();
        try{
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String sql = "select * from category";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                categoryIDcombobox.addItem(rs.getString("categoryID"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            conn = DriverManager.getConnection(db.getCon(),db.getUser(),db.getPassword());
            String sql = "select * from supplier";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                suppliercombobox.addItem(rs.getString("supplierID"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
