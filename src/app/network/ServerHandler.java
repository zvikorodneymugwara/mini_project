package app.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    // all server functions here
    @Override
    public void run() {
        // initialize streams
        try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            boolean running = true;
            
            // run the server
            while (running) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResponse() {

    }

    public void sendRequestToAdmin(UserRequest request) {

    }

    public void sendResponseToUser(AdminResponse response) {

    }

    public void recieveData() {

    }

    public ArrayList<String> getRequests() {
        return null;
    }
}
