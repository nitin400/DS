import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    // Function to send current time to the server
    static void sendTimeToServer(Socket clientSocket) {
        try {
            while (true) {
                OutputStream output = clientSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(new Date());
                System.out.println("Sent current time to server\n");
                Thread.sleep(5000); // Wait for 5 seconds before sending again
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Function to receive synchronized time from the server
    static void receiveSynchronizedTime(Socket clientSocket) {
        try {
            while (true) {
                InputStream input = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String synchronizedTimeStr = reader.readLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date synchronizedTime = dateFormat.parse(synchronizedTimeStr);
                System.out.println("Synchronized time from server: " + synchronizedTime + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to initialize the client
    static void initializeClient(String serverAddress, int serverPort) {
        try {
            Socket clientSocket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server\n");

            // Start threads for sending time and receiving synchronized time
            Thread sendThread = new Thread(() -> sendTimeToServer(clientSocket));
            Thread receiveThread = new Thread(() -> receiveSynchronizedTime(clientSocket));

            sendThread.start();
            receiveThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Driver function
    public static void main(String[] args) {
        // Initialize the client
        initializeClient("127.0.0.1", 8080);
    }
}
