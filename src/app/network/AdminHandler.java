package app.network;

import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

import app.objects.submissions.Affidavit;
import app.objects.submissions.MedicalSubmission;
import app.objects.submissions.SubmissionDocument;

public class AdminHandler extends UserHandler {

    public AdminHandler(Socket clientSocket) {
        super(clientSocket);

        // for testing purposes
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -2);

        Affidavit affidavit = new Affidavit("1", new Date().toString(), "100", "Student Was at  a Funeral",
                "221100999", true);
        MedicalSubmission sickNote = new MedicalSubmission("2", calendar.getTime().toString(), "101",
                "Student Had Eye Surgery", "221100999", false);

        userRequests.add(affidavit);
        userRequests.add(sickNote);
    }

    // will facilitate communication with server
    @Override
    public void run() {
        boolean running = true;
        pw.println("REQUEST_USER_SUBMISSIONS");
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
    }

    private void recieveRequests() throws ClassNotFoundException, IOException {
        this.userRequests.add((SubmissionDocument) objIn.readObject());
    }

    // TODO: Admin GUI will help process this request
    public AdminResponse processResponse(AdminResponse res) {
        return res;
    }
}
