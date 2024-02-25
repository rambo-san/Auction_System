import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserAuthentication {

    private Connection connect() {
        // Replace with your database credentials
        String url = "jdbc:mysql://localhost:3306/your_database_name?useSSL=false";
        String user = "your_database_user";
        String password = "your_database_password";
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public boolean verifyLogin(String username, String password) {
        String SQL = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // A user was found with this username and password.
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean registerUser(String username, String password, String role) {
        String SQL = "INSERT INTO users(username, password, role) VALUES(?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
