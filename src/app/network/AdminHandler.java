package app.network;

import java.io.IOException;
import java.net.Socket;

import app.objects.submissions.SubmissionDocument;

public class AdminHandler extends UserHandler {

    public AdminHandler(Socket clientSocket) {
        super(clientSocket);
    }

    // will facilitate communication with server
    @Override
    public void run() {
        boolean running = true;
        while (running) {

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
