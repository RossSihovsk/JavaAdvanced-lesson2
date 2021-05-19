import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;


public class DaoConnection {
    private static DaoConnection instance;
    private Connection connection;

    private String url = "jdbc:mysql://localhost/magazine_shop?serverTimezone=" + TimeZone.getDefault().getID();
    private String user = "root";
    private String password = "qwerty";

    private DaoConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver class can't be found!" + e);
        } catch (SQLException e) {
            System.out.println("Database connection creation failed!" + e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DaoConnection getInstance() {
        if (instance == null) {
            instance = new DaoConnection();
        } else
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DaoConnection();
                }
            } catch (SQLException e) {
                System.out.println("Database access error!" + e);
            }

        return instance;
    }
}