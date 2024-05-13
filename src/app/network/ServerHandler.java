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
            String message;
            try {
                message = br.readLine();
                System.out.println("MESSAGE RECIEVED: " + message);
                String[] arr = message.split("\\.");
                System.out.println("Request: " + arr[0]);
                switch (arr[0].strip()) {
                    // client requests admin responses
                    case "REQUEST_ADMIN_RESPONSES":
                        sendResponses();
                        break;
                    // admin requests user submissions
                    case "REQUEST_USER_SUBMISSIONS":
                        sendRequests();
                        break;
                    // admin sent response to user submission
                    case "RESPONSE_TO_USER":
                        receiveData();
                        System.out.println("Admin Responses: " + adminResponses);
                        break;
                    // user sent request to admin
                    case "REQUEST_TO_ADMIN":
                        receiveData();
                        System.out.println("User Requests: " + userRequests);
                        sendRequests();
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
     * Sends responses to the connected client.
     *
     * @throws IOException
     */
    private void sendResponses() throws IOException {
        for (AdminResponse res : adminResponses) {
            pw.println("SENDING_RESPONSE." + res + ".TO_USER");
            System.out.println("SENDING_RESPONSE." + res + ".TO_USER");
            objOut.writeObject(res);
            objOut.flush();
        }
    }

    /**
     * Sends user requests to the connected admin.
     *
     * @throws IOException
     */
    private void sendRequests() throws IOException {
        pw.println("SENDING_USER_REQUEST." + userRequests + ".TO_ADMIN");
        System.out.println("SENDING_USER_REQUEST." + userRequests + ".TO_ADMIN");
        objOut.writeObject(userRequests);
        objOut.flush();
    }

    /**
     * Receives data from the connected client.
     *
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void receiveData() throws ClassNotFoundException, IOException {
        Object o = objIn.readObject();
        if (o instanceof SubmissionDocument) {
            userRequests.add((SubmissionDocument) o);
        } else if (o instanceof AdminResponse) {
            adminResponses.add((AdminResponse) o);
        }
    }

}
