package app.network;

import java.io.IOException;
import java.net.Socket;

import app.HelperClass;
import app.objects.submissions.SubmissionDocument;

public class ClientHandler extends UserHandler {

    public ClientHandler(Socket clientSocket) {
        super(clientSocket);
    }

    // will facilitate communication with server
    @Override
    public void run() {
        boolean running = true;
        // requesting all admin responses
        pw.println("REQUEST_ADMIN_RESPONSES");
        while (running) {
            String message;
            try {
                message = br.readLine();
                String[] arr = message.split("\\.");
                switch (arr[0]) {
                    // server is sending admin response
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
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        closeAllConnections();
    }

    /**
     * 
     * @param doc
     * @return
     * @throws IOException
     */
    public boolean sendUserRequest(SubmissionDocument doc) throws IOException {
        if (!alreadyRequested(doc)) {
            System.out.println("REQUEST_TO_ADMIN." + doc + ".FROM_USER");
            pw.println("REQUEST_TO_ADMIN." + doc + ".FROM_USER");
            objOut.writeObject(doc);
            objOut.flush();
            userRequests.add((SubmissionDocument) doc);
            HelperClass.writeUserRequests(userRequests);
            return true;
        }
        return false;
    }

    /**
     * read in objects sent over the network
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void recieveAdminResponses() throws ClassNotFoundException, IOException {
        adminResponses.add((AdminResponse) objIn.readObject());
    }

    /**
     * check if the document has already been requested
     * 
     * @param doc
     * @return
     */
    private boolean alreadyRequested(SubmissionDocument doc) {
        if (userRequests.size() == 0) { // there are no requests
            return false;
        }
        for (SubmissionDocument sDocument : userRequests) {
            if (sDocument.getDocID().equals(doc.getDocID())) {
                return true;
            }
        }
        return false;
    }
}
