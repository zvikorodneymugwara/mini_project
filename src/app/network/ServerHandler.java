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
                String[] arr = message.split(".");
                switch (arr[0]) {
                    case "SENDING_DATA":
                        recieveData();
                        break;
                    case "REQUEST_ADMIN_RESPONSES":
                        sendRequests();
                        break;
                    case "REQUEST_USER_RESPONSES":
                        sendRespones();
                        break;
                    case "RESPONSE_TO_USER":
                        AdminResponse response = (AdminResponse) objIn.readObject();
                        sendResponseToUser(response);
                        break;
                    case "REQUEST_TO_ADMIN":
                        SubmissionDocument reqDocument = (SubmissionDocument) objIn.readObject();
                        sendRequestToAdmin(reqDocument);
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

    public void sendRequestToAdmin(SubmissionDocument request) throws IOException {
        pw.println("SENDING_USER_REQUEST." + request + ".TO_ADMIN");
        userRequests.add(request);
        objOut.writeObject(request);
    }

    public void sendResponseToUser(AdminResponse response) throws IOException {
        pw.println("SENDING_ADMIN_RESPONSE." + response + ".TO_USER");
        adminResponses.add(response);
        objOut.writeObject(response);
    }

    public void sendRespones() throws IOException {
        for (AdminResponse res : adminResponses) {
            pw.println("SENDING_RESPONSE." + res + ".TO_USER");
            objOut.writeObject(res);
        }
    }

    public void sendRequests() throws IOException {
        for (SubmissionDocument req : userRequests) {
            pw.println("SENDING_USER_REQUEST." + req + ".TO_ADMIN");
            objOut.writeObject(req);
        }
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
