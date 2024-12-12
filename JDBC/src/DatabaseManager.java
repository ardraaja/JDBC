import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/callamart";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Ardra16!";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
