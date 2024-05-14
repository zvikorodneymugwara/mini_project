package app.network;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import app.objects.submissions.SubmissionDocument;

/**
 * handler for the admin user
 */
public class AdminHandler extends UserHandler {

    public AdminHandler(Socket clientSocket) {
        super(clientSocket);
    }

    // will facilitate communication with server
    @Override
    public void run() {
        running = true;
        // send request to server for user requests
        pw.println("REQUEST_USER_SUBMISSIONS");
        while (running) {
            String message;
            try {
                message = br.readLine();
                String[] arr = message.split("\\.");
                switch (arr[0]) {
                    // server is sending user requests to the admin
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

    /**
     * Sends the repsonse over the network to the user
     * 
     * @param response
     * @throws IOException
     */
    public void sendResponse(AdminResponse response) throws IOException {
        pw.println("RESPONSE_TO_USER." + response + ".FROM_ADMIN");
        System.out.println("RESPONSE_TO_USER." + response + ".FROM_ADMIN");
        objOut.writeObject(response);
        objOut.flush();
    }

    /**
     * recieves the requests send over the network from user
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private void recieveRequests() throws ClassNotFoundException, IOException {
        System.out.println("Recieving requests");
        Object o = objIn.readObject();
        this.userRequests = (ArrayList<SubmissionDocument>) o;
        System.out.println("The Requests: " + userRequests);
    }
}
