package com.codecool.shop.dao.implementationJdbc;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.*;
import java.util.UUID;

public class UserDaoJdbc implements UserDao {
    private static UserDaoJdbc instance = null;
    private DataSource dataSource;

    public static UserDaoJdbc getInstance() {
        if (instance == null) {
            instance = new UserDaoJdbc();
        }
        return instance;
    }

    private UserDaoJdbc() {
    }

    public void establishConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private boolean isUsernameTaken(String name, String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sqlCartItems = "SELECT * FROM users WHERE user_name = ? OR email = ?";
            PreparedStatement st = conn.prepareStatement(sqlCartItems, Statement.RETURN_GENERATED_KEYS);
            st.setObject(1, name);
            st.setObject(2, email);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public User addUser(String name, String password, String email, Role role, UUID userId) {
        if (userId == null) {
            userId = UUID.randomUUID();
        }
        try (Connection conn = dataSource.getConnection()) {
            //CHECK IF ID EXISTS, IF DOES, UPDATE DETAILS


            UUID tempId = isIdAlreadyInDb(userId);
            //todo check if works
            if (tempId != null) {
                User createdUser = updateUser(userId, name, password, email, role);
                if (createdUser != null && createdUser.getName() != null) {
                    return createdUser;
                }
                return null;
            }

            if (!isUsernameTaken(name, email)) {
                String sqlCartItems = "INSERT INTO users (id, user_name, password,email,role) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(sqlCartItems, Statement.RETURN_GENERATED_KEYS);
                st.setObject(1, userId);
                st.setString(2, name);
                st.setString(3, password);
                st.setString(4, email);
                if (role != null) {
                    st.setObject(5, role.toString());
                } else {
                    st.setObject(5, null);
                }
                st.executeUpdate();
                User user = new User();
                user.setId(userId);
                user.setName(name);
                user.setEmail(email);
                user.setRole(role);
                return user;
            }
            else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private User updateUser(UUID userId, String name, String password, String email, Role role) {
        if (name != null) {
            try (Connection conn = dataSource.getConnection()) {
                String sql = "UPDATE users SET email = ?, user_name = ?, password = ?, role =  ?  WHERE id = ?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, email);
                st.setString(2, name);
                st.setString(3, password);
                st.setObject(4, role.toString());
                st.setObject(5 , userId);
                st.executeUpdate();
                User user =  new User();
                user.setEmail(email);
                user.setName(name);
                user.setId(userId);
                user.setRole(role);
                return user;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }

    }


    private UUID isIdAlreadyInDb(UUID userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setObject(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return (UUID) rs.getObject(1);
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUser(UUID id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setObject(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changePassword(UUID userid, String oldPassword, String newPassword) {

    }

    @Override
    public Cart getCart(UUID userid) {
        return null;
    }

    private String getHashedPass(String user) {
        try (Connection conn = dataSource.getConnection()) {
            String sqlUser = "SELECT password FROM users WHERE user_name = ?";
            PreparedStatement st = conn.prepareStatement(sqlUser);
            st.setObject(1, user);
            ResultSet rs = st.executeQuery();
            String password = "";
            while (rs.next()) {
                password = rs.getString("password");
            }
            return password;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(UUID id) {
        try (Connection conn = dataSource.getConnection()) {
            String sqlUser = "SELECT * FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sqlUser);
            st.setObject(1, id);
            ResultSet rs = st.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId((UUID) rs.getObject("id"));
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                if (rs.getString("role") != null && rs.getString("role").equalsIgnoreCase("admin")) {
                    user.setRole(Role.ADMIN);
                } else {
                    user.setRole(Role.USER);
                }

            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User getUserByName(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sqlUser = "SELECT * FROM users WHERE LOWER(user_name) = LOWER(?)";
            PreparedStatement st = conn.prepareStatement(sqlUser);
            st.setObject(1, name);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId((UUID) rs.getObject("id"));
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
