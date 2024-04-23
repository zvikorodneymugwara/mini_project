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
                switch (arr[0]) {
                    case "REQUEST_ADMIN_RESPONSES":
                        sendRespones();
                        break;
                    case "REQUEST_USER_SUBMISSIONS":
                        sendRequests();
                        break;
                    case "RESPONSE_TO_USER":
                        AdminResponse response = (AdminResponse) objIn.readObject();
                        adminResponses.add(response);
                        // sendResponseToUser(response);
                        break;
                    case "REQUEST_TO_ADMIN":
                        SubmissionDocument reqDocument = (SubmissionDocument) objIn.readObject();
                        userRequests.add(reqDocument);
                        // sendRequestToAdmin(reqDocument);
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

    private void sendRespones() throws IOException {
        for (AdminResponse res : adminResponses) {
            pw.println("SENDING_RESPONSE." + res + ".TO_USER");
            System.out.println("SENDING_RESPONSE." + res + ".TO_USER");
            objOut.writeObject(res);
        }
    }

    private void sendRequests() throws IOException {
        for (SubmissionDocument req : userRequests) {
            pw.println("SENDING_USER_REQUEST." + req + ".TO_ADMIN");
            System.out.println("SENDING_USER_REQUEST." + req + ".TO_ADMIN");
            objOut.writeObject(req);
        }
    }

    // public void sendRequestToAdmin(SubmissionDocument request) throws IOException
    // {
    // pw.println("SENDING_USER_REQUEST." + request + ".TO_ADMIN");
    // System.out.println("SENDING_USER_REQUEST." + request + ".TO_ADMIN");
    // objOut.writeObject(request);
    // }

    // public void sendResponseToUser(AdminResponse response) throws IOException {
    // pw.println("SENDING_ADMIN_RESPONSE." + response + ".TO_USER");
    // System.out.println("SENDING_ADMIN_RESPONSE." + response + ".TO_USER");
    // objOut.writeObject(response);
    // }

    // public void recieveData() throws ClassNotFoundException, IOException {
    // Object o = objIn.readObject();
    // if (o instanceof SubmissionDocument) {
    // userRequests.add((SubmissionDocument) o);
    // } else if (o instanceof AdminResponse) {
    // adminResponses.add((AdminResponse) o);
    // }
    // }
}
