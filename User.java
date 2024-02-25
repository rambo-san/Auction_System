import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public abstract class User {
    protected int id;
    protected String username;
    protected String role;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/auction_system?characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASS = ""; // Use your actual database password

    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public abstract void performAction(Scanner scanner);

    public static User login(String username, String password) {
         Connection con = null;
        PreparedStatement pstmt = null;
        User user = null;

        try {
            // Register JDBC driver and open a connection
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare SQL query
            String query = "SELECT id, role FROM users WHERE username = ? AND password = ?";
            pstmt = con.prepareStatement(query);

            // Set parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Execute query
            ResultSet rs = pstmt.executeQuery();

            // Process the result set
            if (rs.next()) {
                int id = rs.getInt("id");
                String role = rs.getString("role");

                // Instantiate user based on role
                if ("Admin".equals(role)) {
                    user = new Admin(id, username);
                } else if ("Seller".equals(role)) {
                    user = new Seller(id, username);
                } else if ("Buyer".equals(role)) {
                    user = new Buyer(id, username);
                } else {
                    System.out.println("Unknown role: " + role);
                }
            }

            // Clean-up environment
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Login error: " + e);
        }

        return user;
    }

    public static boolean signUp(String username, String password, String role) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);

            int result = pstmt.executeUpdate();

            return result > 0;
        } catch (Exception e) {
            System.out.println("SignUp error: " + e);
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
