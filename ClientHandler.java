import java.io.*;
import java.net.Socket;
import java.sql.*;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    private Connection connect() {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/auction_system?characterEncoding=utf8";
        String user = "root";
        String password = "";
        
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
            System.out.println("Verified login");
            
            if (rs.next()) {
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

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] commands = inputLine.split(" ");
                if ("login".equals(commands[0])) {
                    String username = commands[1];
                    String password = commands[2];
                    if (verifyLogin(username, password)) {
                        out.println("Login successful");
                    } else {
                        out.println("Login failed");
                    }
                } else if ("register".equals(commands[0])) {
                    String username = commands[1];
                    String password = commands[2];
                    String role = commands[3];
                    if (registerUser(username, password, role)) {
                        out.println("Registration successful");
                    } else {
                        out.println("Registration failed");
                    }
                }
                // Handle other commands or actions here
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
