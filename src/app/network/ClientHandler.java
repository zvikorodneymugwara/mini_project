package app.network;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import app.objects.submissions.SubmissionDocument;

public class ClientHandler extends UserHandler {

    public ClientHandler(Socket clientSocket) {
        super(clientSocket);
    }

    // will facilitate communication with server
    @Override
    public void run() {
        boolean running = true;
        pw.println("REQUEST_ADMIN_RESPONSES");
        while (running) {
            String message;
            try {
                message = br.readLine();
                String[] arr = message.split("\\.");
                switch (arr[0]) {
                    case "SENDING_ADMIN_RESPONSE":
                        recieveAdminResponses();
                        break;
                    case "CLOSE":
                        running = false;
                        break;
                    default:
                        System.err.println("Invalid Message");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        closeAllConnections();
    }

    public boolean sendUserRequest(SubmissionDocument doc) throws IOException {
        if (alreadyRequested(doc) == false) {
            pw.println("REQUEST_TO_ADMIN." + doc + ".FROM_USER");
            System.out.println("REQUEST_TO_ADMIN." + doc + ".FROM_USER");
            userRequests.add((SubmissionDocument) doc);
            objOut.writeObject(doc);
            return true;
        }
        return false;
    }

    public void recieveAdminResponses() throws ClassNotFoundException, IOException {
        adminResponses.add((AdminResponse) objIn.readObject());
    }

    // TODO: make this work
    private boolean alreadyRequested(SubmissionDocument doc) {
        if (userRequests.size() == 0) {
            return false;
        }
        for (SubmissionDocument sDocument : userRequests) {
            if (sDocument.getDocID() == doc.getDocID()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<AdminResponse> getAdminResponses() {
        return adminResponses;
    }

    public ArrayList<SubmissionDocument> getUserRequests() {
        return userRequests;
    }
}
