package dvano.mysql.viewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dvano
 */
public final class MySQLConnector {

    private static Connection connection = null;
    private static Statement statement = null;

    private MySQLConnector() {
    }

    public static void registerDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public static void execute(String sql) throws SQLException {
        MySQLConnector.statement.execute(sql);
    }

    public static void connect(String url, String user, String password) throws SQLException {
        MySQLConnector.connection = DriverManager.getConnection(url, user, password);
        MySQLConnector.statement = MySQLConnector.connection.createStatement();
    }

    public static void disconnect() throws SQLException {
        if (MySQLConnector.statement != null) {
            if (!MySQLConnector.statement.isClosed()) {
                MySQLConnector.statement.close();
            }
        }
        if (MySQLConnector.connection != null) {
            if (!MySQLConnector.connection.isClosed()) {
                MySQLConnector.connection.close();
            }
        }
    }

    public static ResultSet getResultSet(String sql) throws SQLException {
        return MySQLConnector.statement.executeQuery(sql);
    }
}
