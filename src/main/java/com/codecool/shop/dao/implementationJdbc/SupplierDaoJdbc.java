package com.codecool.shop.dao.implementationJdbc;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SupplierDaoJdbc implements SupplierDao {
    private DataSource dataSource;
    private static SupplierDaoJdbc instance = null;

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    private SupplierDaoJdbc(){}

    public void establishConnection(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        System.out.println(supplier.getName());

        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO suppliers (uuid, name, description) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, supplier.getId().toString());
            st.setString(2, supplier.getName());
            st.setString(3, supplier.getDescription());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new supplier.", throwables);
        }
    }

    @Override
    public Supplier find(UUID id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM suppliers WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id.toString());
            ResultSet rs = st.executeQuery();
            // TODO extract Author from result set and return
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }

            Supplier supplier = new Supplier(rs.getString(2));
            supplier.setId(UUID.fromString(rs.getString(1)));
            supplier.setDescription(rs.getString(3));
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(UUID id) {
        System.out.println(id);
    }

    @Override
    public boolean isSupplierMissing(String name) {
        System.out.println("is it missing?");
        return false;
    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM suppliers;";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            // TODO extract Author from result set and return
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }
            List<Supplier> result = new ArrayList<>();

            while(rs.next()){
                Supplier s = new Supplier(rs.getString(2));
                s.setId(UUID.fromString(rs.getString(1)));
                s.setDescription(rs.getString(3));
                result.add(s);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
