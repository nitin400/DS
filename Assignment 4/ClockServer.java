import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockServer {

    // Function to handle client connections and synchronize time
    static void synchronizeTime(ServerSocket serverSocket) {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Thread to handle each client
                Thread clientThread = new Thread(() -> {
                    try {
                        while (true) {
                            // Receive current time from client
                            InputStream input = clientSocket.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                            String clientTimeStr = reader.readLine();
                            System.out.println("Received time from client: " + clientTimeStr);

                            // Send synchronized time back to client
                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                            String synchronizedTimeStr = dateFormat.format(new Date());
                            OutputStream output = clientSocket.getOutputStream();
                            PrintWriter writer = new PrintWriter(output, true);
                            writer.println(synchronizedTimeStr);
                            System.out.println("Sent synchronized time to client: " + synchronizedTimeStr + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to initialize the server
    static void initializeServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started\n");

            // Synchronize time with clients
            synchronizeTime(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Driver function
    public static void main(String[] args) {
        // Initialize the server
        initializeServer(8080);
    }
}
