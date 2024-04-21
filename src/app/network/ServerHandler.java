package app.network;

import java.io.IOException;
import java.net.Socket;

import app.objects.submissions.SubmissionDocument;

public class ServerHandler extends UserHandler {

    public ServerHandler(Socket clientSocket) {
        super(clientSocket);
    }

    // all server functions here
    @Override
    public void run() {
        boolean running = true;
        while (running) {

        }
    }

    public void sendRequestToAdmin(SubmissionDocument request) {

    }

    public void sendResponseToUser(AdminResponse response) {

    }

    public void recieveData() throws ClassNotFoundException, IOException {
        Object o = objIn.readObject();
        if (o instanceof SubmissionDocument) {
            userRequests.add((SubmissionDocument) o);
        } else if (o instanceof AdminResponse) {
            adminResponses.add((AdminResponse) o);
        }
    }
}
