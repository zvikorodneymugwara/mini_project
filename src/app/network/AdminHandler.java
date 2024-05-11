package app.network;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import app.objects.submissions.SubmissionDocument;

public class AdminHandler extends UserHandler {

    public AdminHandler(Socket clientSocket) {
        super(clientSocket);
        // pw.println("REQUEST_USER_SUBMISSIONS");
    }

    // will facilitate communication with server
    @Override
    public void run() {
        boolean running = true;
        while (running) {
            String message;
            try {
                message = br.readLine();
                String[] arr = message.split("\\.");
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

    // TODO: Admin GUI will help process this request
    public void sendResponse(AdminResponse response) throws IOException {
        pw.println("RESPONSE_TO_USER." + response + ".FROM_ADMIN");
        System.out.println("RESPONSE_TO_USER." + response + ".FROM_ADMIN");
        objOut.writeObject(processResponse(response));
        objOut.flush();
    }

    @SuppressWarnings("unchecked")
    private void recieveRequests() throws ClassNotFoundException, IOException {
        System.out.println("Recieving requests");
        Object o = objIn.readObject();
        this.userRequests = (ArrayList<SubmissionDocument>) o;
        System.out.println("The Requests: "+userRequests);
    }

    // TODO: Admin GUI will help process this request
    public AdminResponse processResponse(AdminResponse res) {
        return res;
    }
}
