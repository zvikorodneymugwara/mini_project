package app.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NoticeItemController extends MainScreenController  {

    @FXML
    private Label noticeDateLbl;

    @FXML
    private Label noticeDetailsLbl;

    @FXML
    private Label noticeTitleLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing Notice");
    }

}
