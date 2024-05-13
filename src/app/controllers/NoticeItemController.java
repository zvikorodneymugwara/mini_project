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

/**
 * Controller for each displayed notice item
 */
public class NoticeItemController extends MainScreenController {

    @FXML
    private Label noticeDateLbl;

    @FXML
    private Label noticeDetailsLbl;

    @FXML
    private Label noticeTitleLbl;

    // the notice data can either be a response or a request
    private AdminResponse response;
    private SubmissionDocument request;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing Notice");
    }

    /**
     * This method initializes the notice item with the specified data of the
     * response from the admin
     */
    public void init() {
        if (response != null) {
            // get date of response
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -2);

            System.out.println("Initializing Notice");

            String s = "";
            if (response.getResponseStatus()) {
                s = "Submission Approved";
            } else {
                s = "Submission Declined";
            }

            // set the text of the notice item
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

    /**
     * Sets the response of the notice item
     * 
     * @param response
     */
    public void setResponse(AdminResponse response) {
        this.response = response;
    }

    /**
     * Sets the request document of the notice item
     * 
     * @param reqDocument
     */
    public void setRequest(SubmissionDocument reqDocument) {
        this.request = reqDocument;
    }

}
