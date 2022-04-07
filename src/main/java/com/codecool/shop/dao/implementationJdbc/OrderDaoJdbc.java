package com.codecool.shop.dao.implementationJdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderDaoJdbc implements OrderDao {

    private DataSource dataSource;
    public static OrderDao instance = null;


    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoJdbc();
        }
        return instance;

    }

    public void establishConnection(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    //TODO Il trimit din sesiune
    public Order createOrder(Map<String, String> clientDetails, CartDao cartDao, UUID userId) {
//        return null;
        UUID cartId = getCartId(userId);

        try(Connection conn = dataSource.getConnection()){

                String sqlCartItems = "INSERT INTO client_order (id, cart_id, user_id, address, phone_number, first_name, last_name, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(sqlCartItems, Statement.RETURN_GENERATED_KEYS);
                UUID idToBeInserted = UUID.randomUUID();
                st.setObject(1, idToBeInserted);
                st.setObject(2, cartId);
                st.setObject(3, userId);
                st.setString(4, clientDetails.get("Address"));
                st.setString(5, clientDetails.get("Phone"));
                st.setString(6, clientDetails.get("First Name"));
                st.setString(7, clientDetails.get("Last Name"));
                st.setString(8, clientDetails.get("Email"));
                st.executeUpdate();


            return getOrder(idToBeInserted);
            } catch (SQLException e){
                throw new RuntimeException(e);
            }

    }

    @Override
    public Order getOrder(UUID orderID) {
        try(Connection conn = dataSource.getConnection()){

            //todo trb sa returnez un order, care isi face singur id random, ii fac set de id cu id-ul din
            // parametru

            Map<String, String> clientDetails = new HashMap<>();

//            String sql = "SELECT author_id, title FROM book WHERE id = ?";
            String sql =  "SELECT cart_id, user_id, address, phone_number, first_name, last_name, email FROM client_order WHERE id = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setObject(1, orderID);
            ResultSet rs = st.executeQuery();
            if (!rs.next()){
                return null;
            }
            UUID cartId = (UUID) rs.getObject(1);

            //todo sa bag adresa in clientDetails
            //todo sa fac un new cart folosind user_id aka rs.getObject(2);
            //todo ii dau un new cartDao la misto, oricum e gol
            // ii dau user id
            // cu toate astea fac un nou order pe care il returnez
            clientDetails.put("First Name", rs.getString(5));
            clientDetails.put("Last Name", rs.getString(6));
            clientDetails.put("Email", rs.getString(7));
            clientDetails.put("Phone", rs.getString(4));
            clientDetails.put("Address", rs.getString(3));

            Cart cart = new Cart();
            cart.setOwnerId((UUID) rs.getObject(2));
            cart.setId((UUID) rs.getObject(1));

            CartDao cartDao = CartDaoJdbc.getInstance();

            Order order = new Order(clientDetails, cartDao, cart.getOwnerId());
            order.setOwnerId((UUID) rs.getObject(2));
            order.setOrderId(orderID);
//            var order = new Order(clientDetails, cartDao, cart.getOwnerId();

//            return new Order(clientDetails, cartDao, cart.getOwnerId());
            return order;

        }catch (SQLException e ){
            throw new RuntimeException();
        }
    }

    @Override
    public void confirmOrder() {

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
}
