package app.network;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private ServerSocket ss; // the server socket
    private boolean running = false;

    // initialize the server socket
    public Server() {
        try {
            ss = new ServerSocket(2021);
            System.out.println("Waiting for Connections...");
            running = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // run the threaded server
    public void runServer() {
        while (running) {
            try {
                Thread serverThread = new Thread(new ServerHandler(ss.accept()));
                serverThread.run();
                System.out.println("Server Handler Connected");
                Thread userThread = new Thread(new ClientHandler(ss.accept()));
                userThread.start();
                System.out.println("Client Connected");
                Thread adminThread = new Thread(new AdminHandler(ss.accept()));
                adminThread.start();
                System.out.println("Admin Connected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.runServer();
    }
}