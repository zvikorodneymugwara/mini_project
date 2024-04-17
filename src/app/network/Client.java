package app.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Client {
    private static String hostname = "localhost";
    private static int portNumber = 8080;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(hostname, portNumber);
        System.out.println("Connected to server on port " + portNumber);

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // Send messages and read responses
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = stdIn.readLine()) != null) {
            out.println(line);
            String response = in.readLine();
            System.out.println("Server: " + response);
        }

        // Close resources
        in.close();
        out.close();
        clientSocket.close();
    }
}
