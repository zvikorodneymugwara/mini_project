package app.controllers;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import app.network.AdminResponse;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing Notice");
    }

    public void init(){
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
        noticeDateLbl.setText(new Date().toString());
        noticeDetailsLbl.setText(response.getResponse());
    }

    public void setResponse(AdminResponse response) {
        this.response = response;
    }

}
