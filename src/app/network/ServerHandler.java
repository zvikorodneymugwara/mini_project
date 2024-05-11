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
                    case "REQUEST_ADMIN_RESPONSES":
                        sendRespones();
                        break;
                    case "REQUEST_USER_SUBMISSIONS":
                        sendRequests();
                        break;
                    case "RESPONSE_TO_USER":
                        recieveData();
                        System.out.println("Admin Responses: " + adminResponses);
                        break;
                    case "REQUEST_TO_ADMIN":
                        recieveData();
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
            objOut.flush();
        }
    }

    private void sendRequests() throws IOException {
        pw.println("SENDING_USER_REQUEST." + userRequests + ".TO_ADMIN");
        System.out.println("SENDING_USER_REQUEST." + userRequests + ".TO_ADMIN");
        objOut.writeObject(userRequests);
        objOut.flush();
    }

    public void recieveData() throws ClassNotFoundException, IOException {
        Object o = objIn.readObject();
        if (o instanceof SubmissionDocument) {
            userRequests.add((SubmissionDocument) o);
        } else if (o instanceof AdminResponse) {
            adminResponses.add((AdminResponse) o);
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
}
