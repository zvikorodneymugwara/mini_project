package app.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import app.network.AdminResponse;
import app.objects.submissions.SubmissionDocument;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NoticeItemController extends MainScreenController {

    @FXML
    private Label noticeDateLbl;

    @FXML
    private Label noticeDetailsLbl;

    @FXML
    private Label noticeTitleLbl;

    private AdminResponse response;
    private SubmissionDocument request;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing Notice");
    }

    public void init() {
        if (response != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -2);

            System.out.println("Initializing Notice");
            // TODO add more notice details
            String s = "";
            if (response.getResponseStatus()) {
                s = "Submission Approved";
            } else {
                s = "Submission Declined";
            }
            noticeTitleLbl.setText(s);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            noticeDateLbl.setText(dateFormat.format(new Date()));
            noticeDetailsLbl.setText(response.getResponse());
        } else if (request != null) {
            noticeTitleLbl.setText("Processing...");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            noticeDateLbl.setText(dateFormat.format(new Date()));
            noticeDetailsLbl.setText("Processing " + request);
        }
    }

    public void setResponse(AdminResponse response) {
        this.response = response;
    }

    public void setRequest(SubmissionDocument reqDocument) {
        this.request = reqDocument;
    }

}
