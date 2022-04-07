package com.codecool.shop.dao.implementationJdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.*;
import jdk.jfr.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CartDaoJdbc implements CartDao {
    private DataSource dataSource;
    private static CartDaoJdbc instance = null;
    private Cart cart;
    private User owner;

    public static CartDao getInstance() {
        if (instance == null) {
            instance = new CartDaoJdbc();
        }
        return instance;
    }


    private CartDaoJdbc() {

    }

    public void establishConnection(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public void addToCart(Product product, UUID userId) {

        UUID cartId = getCartId(userId);



        try(Connection conn = dataSource.getConnection()){
            //todo create this cart when a user registers

//            Cart cartID =  new Cart();


//            String sqlCart = "INSERT INTO cart (id, ownerid) VALUES (?, ?)";
//            PreparedStatement st1 = conn.prepareStatement(sqlCart, Statement.RETURN_GENERATED_KEYS);
//            st1.setObject(1, cart.getId());
//            st1.setObject(2, cart.getOwnerId());
//            st1.executeUpdate();
//            ResultSet rs = st1.getGeneratedKeys();
//            rs.next();
//            book.setId(rs.getInt(1));


            if (getItemsNumberInCartItems(product, cartId) > 0) {
                updateCartItemsAdd(product, cartId);
            } else {
                String sqlCartItems = "INSERT INTO cart_items (cart_id, product_id, quantity) VALUES (?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(sqlCartItems, Statement.RETURN_GENERATED_KEYS);
                st.setObject(1, cartId);
                st.setObject(2, product.getId());
                st.setInt(3, 1);
                st.executeUpdate();
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    private UUID getCartId(UUID userId) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id FROM cart WHERE ownerId = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setObject(1, userId);

            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            else {
                return  (UUID)rs.getObject(1);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private int getItemsNumberInCartItems(Product product, UUID cartId) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT quantity FROM cart_items WHERE product_id = ? AND cart_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setObject(1, product.getId());
            st.setObject(2, cartId);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            else {
                return rs.getInt(1);
            }

        }catch (SQLException e ){
            throw new RuntimeException();
        }
    }

    private void updateCartItemsAdd(Product product, UUID cartId) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "UPDATE cart_items SET quantity = quantity + 1 WHERE product_id = ? AND cart_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setObject(1, product.getId());
            st.setObject(2, cartId);
            st.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void updateCartItemsRemove(Product product, UUID cartId) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "UPDATE cart_items SET quantity = quantity - 1 WHERE product_id = ? AND cart_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setObject(1, product.getId());
            st.setObject(2, cartId);
            st.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeFromCart(Product product, UUID userId) {

        UUID cartId = getCartId(userId);

        try(Connection conn = dataSource.getConnection()){

            if (getItemsNumberInCartItems(product, userId) > 1) {
                updateCartItemsRemove(product, userId);
            } else {
                String sql = "DELETE FROM cart_items WHERE product_id = ? AND cart_id = ?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setObject(1, product.getId());
                st.setObject(2, cartId);
                st.executeUpdate();
            }


        }catch (SQLException e ){
            throw new RuntimeException();
        }

    }

    @Override
    public Map<Product, Integer> getCart(UUID userId) {
        // aka getAll
        try(Connection conn = dataSource.getConnection()){
//            String sql = "SELECT cart_items.quantity, products.name, products.description, products.price, suppliers.name, suppliers.description, category.name, category.description FROM cart WHERE ownerid = ?";
            String sql = "SELECT\n" +
                    "       ci.quantity products_quantity,\n" +
                    "       p.id," +
                    "       p.name product_name,\n" +
                    "       p.description product_description,\n" +
                    "       p.price product_price,\n" +
                    "       s.name supplier_name,\n" +
                    "       s.description supplier_description,\n" +
                    "       c.name category_name,\n" +
                    "       c.description category_nescription\n, " +
                    "       p.img " +
                    "\n" +
                    "FROM cart\n" +
                    "INNER JOIN cart_items ci on cart.id = ci.cart_id\n" +
                    "INNER JOIN products p on ci.product_id = p.id\n" +
                    "INNER JOIN categories c on c.id = p.category_id\n" +
                    "INNER JOIN suppliers s on s.id = p.supplier_id\n" +
                    "WHERE cart.ownerid = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setObject(1, userId);
            ResultSet rs = st.executeQuery();

            Map<Product, Integer> result = new HashMap<>();
            while (rs.next()) {
                ProductCategory category = new ProductCategory(rs.getString(8), "Tech", rs.getString(9));
                Supplier supplier = new Supplier(rs.getString(7));
                Product product = new Product(rs.getString(3), rs.getBigDecimal(5), "USD", rs.getString(4), category, supplier, rs.getString(10));
                product.setId((UUID) rs.getObject(2));
                result.put(product, rs.getInt(1));
            }
            return result;


        }catch (SQLException e ){
            throw new RuntimeException();
        }

    }

    @Override
    public void emptyCart() {

    }
}
