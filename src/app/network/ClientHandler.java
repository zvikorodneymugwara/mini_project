package app.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import app.objects.submissions.SubmissionDocument;

public class ClientHandler extends UserHandler {

    public ClientHandler(Socket clientSocket) {
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

    public void sendUserRequest(SubmissionDocument doc) throws IOException {
        if (!alreadyRequested(doc)) {
            objOut.writeObject(doc);
        }
    }

    public void recieveAdminResponses() throws ClassNotFoundException, IOException {
        adminResponses.add((AdminResponse) objIn.readObject());
    }

    private boolean alreadyRequested(SubmissionDocument doc) {
        if (userRequests.size() == 0) {
            return false;
        } else {
            for (SubmissionDocument sDocument : userRequests) {
                if (sDocument.equals(doc)) {
                    return true;
                }
            }
        }
        return false;
    }
}
