import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class AdminServer {
    private static final int PORT = 12345;

    public static void startServer() {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Admin Server started. Listening on port " + PORT);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    pool.execute(new ClientHandler(clientSocket));
                } catch (IOException e) {
                    System.out.println("Exception when trying to listen on port " + PORT + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + PORT);
            System.out.println(e.getMessage());
        }
    }

    // ClientHandler and other inner classes/logic remains the same
}
