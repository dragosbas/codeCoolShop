package com.codecool.shop.dao.implementationJdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDaoJdbc implements ProductDao{

    private DataSource dataSource;
    private static ProductDaoJdbc instance;

    public static ProductDaoJdbc getInstance() {
        if(instance == null){
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    private ProductDaoJdbc(){}
    public void establishConnection(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(UUID id) {
        try(Connection conn = dataSource.getConnection()){
//            String sql = "SELECT id, name, description, price, supplier_id, category_id FROM products WHERE id = ?";
            String sql = "SELECT products.id, products.name, " +
                    "   products.description, " +
                    "   price, products.supplier_id, " +
                    "   products.category_id, " +
                    "   c.name, " +
                    "   c.description, " +
                    "   s.name, " +
                    "   s.description, " +
                    "   products.img " +
                    "FROM products " +
                    "JOIN categories c ON c.id = products.category_id " +
                    "JOIN suppliers s ON products.supplier_id = s.id " +
                    "WHERE products.id = ?; ";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setObject(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()){
                return null;
            }

            ProductCategory pc = new ProductCategory(rs.getString(7),"", rs.getString(8));
            pc.setId(UUID.fromString(rs.getString(6)));
            Supplier s = new Supplier(rs.getString(9));
            s.setId(UUID.fromString(rs.getString(5)));
            Product p = new Product(rs.getString(2),rs.getBigDecimal(4),"USD", rs.getString(3), pc, s, "img");
            p.setId(UUID.fromString(rs.getString(1)));
            p.setDescription(rs.getString(3));

            return p;

        }catch (SQLException e ){
            throw new RuntimeException();
        }
    }

    @Override
    public void remove(UUID id) {

    }

    @Override
    public Product find(String productName) {
        return null;
    }

    @Override
    public List<Product> getAll() {

        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT products.id, products.name, " +
                    "   products.description, " +
                    "   price, products.supplier_id, " +
                    "   products.category_id, " +
                    "   c.name, " +
                    "   c.description, " +
                    "   s.name, " +
                    "   s.description, " +
                    "   products.img " +
                    "FROM products " +
                    "JOIN categories c ON c.id = products.category_id " +
                    "JOIN suppliers s ON products.supplier_id = s.id; ";
            ResultSet rs = conn.createStatement().executeQuery(sql);

//            if (!rs.next()) {
//                return null;
//            }
            List<Product> result = new ArrayList<>();

            while(rs.next()){
                ProductCategory pc = new ProductCategory(rs.getString(7),"", rs.getString(8));
                pc.setId(UUID.fromString(rs.getString(6)));
                Supplier s = new Supplier(rs.getString(9));
                s.setId(UUID.fromString(rs.getString(5)));
                Product p = new Product(rs.getString(2),rs.getBigDecimal(4),"USD", rs.getString(3), pc, s, rs.getString(11));
                p.setId(UUID.fromString(rs.getString(1)));
                p.setDescription(rs.getString(3));
                result.add(p);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    @Override
    public boolean isProductMissing(SupplierDao supplierDao, ProductCategoryDao productCategoryDao, String productNameInput, String defaultpriceInput, String defaultcurrencyInput, String descriptionInput, String productcategoryInput, String supplierInput, String imgInput) {
        return false;
    }
}
