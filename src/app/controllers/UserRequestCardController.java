package app.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

//TODO Fix it to work like the Notices thingy, but with a button to approve or decline
public class UserRequestCardController extends MainScreenController {

    @FXML
    private Button approveBtn;

    @FXML
    private Label companyNameLbl;

    @FXML
    private Button declineBtn;

    @FXML
    private Label docIDLbl;

    @FXML
    private Label docTypeLbl;

    @FXML
    private Label studentNumberLbl;

    @FXML
    private Label student_NameLbl;

    @FXML
    private Button viewInfoBtn;

    @FXML
    void approveBtnClick(ActionEvent event) {

    }

    @FXML
    void declineBtnClick(ActionEvent event) {

    }

    @FXML
    void viewInfoBtnClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing Request Cards");
    }

}
