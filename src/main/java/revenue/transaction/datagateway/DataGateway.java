package revenue.transaction.datagateway;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class DataGateway {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/sys";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    private static final String SELECT_CONTRACT_SQL =
            "SELECT c.id AS id, c.product_id AS product_id, c.revenue AS revenue, c.dateSigned AS dateSigned, p.type AS type "
                    + "FROM contracts c, products p "
                    + "WHERE c.id = ? AND c.product_id = p.id";

    private static final String INSERT_CONTRACT_SQL =
            "INSERT INTO contracts (product_id, revenue, dateSigned) VALUES (?, ?, ?)";

    private static final String SELECT_ALL_PRODUCTS =
            "SELECT * FROM products";

    private static final String findAllProducts = "SELECT * FROM products";

    public ResultSet findAllProducts() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement ps = connection.prepareStatement(
                SELECT_ALL_PRODUCTS);
        try (ResultSet rs = ps.executeQuery()) {
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public ResultSet findOne(long contractId) {
        try (
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement ps = connection.prepareStatement(
                        SELECT_CONTRACT_SQL);
        ) {
            ps.setLong(1, contractId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long insert(
            long productId, BigDecimal contractPrice, LocalDate dateSigned) {
        try (
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement ps = connection.prepareStatement(
                        INSERT_CONTRACT_SQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setLong(1, productId);
            ps.setBigDecimal(2, contractPrice);
            ps.setDate(3, java.sql.Date.valueOf(dateSigned));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
