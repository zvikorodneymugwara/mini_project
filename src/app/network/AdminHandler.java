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
        pw.println("REQUEST_USER_RESPONSES");
        while (running) {
            String message;
            try {
                message = br.readLine();
                String[] arr = message.split(".");
                switch (arr[0]) {
                    case "SENDING_USER_REQUEST":
                        recieveRequests();
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

    public void sendResponse(AdminResponse response) throws IOException {
        pw.println("RESPONSE_TO_USER."+response+".FROM_ADMIN");
        objOut.writeObject(processResponse(response));
    }

    public void recieveRequests() throws ClassNotFoundException, IOException {
        this.userRequests.add((SubmissionDocument) objIn.readObject());
    }

    public AdminResponse processResponse(AdminResponse res) {
        return res;
    }
}
