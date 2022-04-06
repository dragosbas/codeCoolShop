package com.codecool.shop.dao.implementationJdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductCategoryDaoJdbc implements ProductCategoryDao{
    private DataSource dataSource;
    private static ProductCategoryDaoJdbc instance = null;

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    private ProductCategoryDaoJdbc(){}

    public void establishConnection(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(UUID id) {
        return null;
    }

    @Override
    public void remove(UUID id) {

    }

    @Override
    public boolean isCategoryMissing(String name, String department, String description) {
        return false;
    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM categories;";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            // TODO extract Author from result set and return
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }
            List<ProductCategory> result = new ArrayList<>();

            while(rs.next()){
                ProductCategory p = new ProductCategory(rs.getString(2),"", rs.getString(3));
                p.setId(UUID.fromString(rs.getString(1)));
                p.setDescription(rs.getString(3));
                result.add(p);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
