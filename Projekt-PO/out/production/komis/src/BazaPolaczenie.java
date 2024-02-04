import java.sql.*;
public class BazaPolaczenie {
    private Connection connection;

    public BazaPolaczenie() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/komis_samochodowy", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
