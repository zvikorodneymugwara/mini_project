package app.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import app.objects.submissions.SubmissionDocument;

public class AdminHandler extends UserHandler {
    public AdminHandler(Socket clientSocket) {
        super(clientSocket);
    }

    // will facilitate communication with server
    @Override
    public void run() {
        try {
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            boolean running = true;
            while (running) {

            }
        } catch (IOException e) {

        }
    }

    public void sendResponse(AdminResponse response) throws IOException {
        objOut.writeObject(response);
    }

    public void recieveRequests() throws ClassNotFoundException, IOException {
        this.userRequests.add((SubmissionDocument) objIn.readObject());
    }

    public AdminResponse processResponse() {
        return null;
    }
}
