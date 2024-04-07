package app.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class MyCourseScreenController extends MainScreenController {

    @FXML
    private Label creditsLbl;

    @FXML
    private Label currentYearLbl;

    @FXML
    private Label degCodeLbl;

    @FXML
    private Label degreeLbl;

    @FXML
    private Label facultyLbl;

    @FXML
    private Hyperlink homeLink;

    @FXML
    private LineChart<?, ?> marksChart;

    @FXML
    private ComboBox<?> moduleSelectComboBox;

    @FXML
    private Label referenceIdLbl;

    @FXML
    private Label statusLbl;


    @FXML
    void moduleSelectComboBoxClick(ActionEvent event) {

    }

    @FXML
    void homeLinkClicked(ActionEvent event) throws IOException {
        super.homeLinkClicked(event);
    }

    @FXML
    void myCourseLinkClicked(ActionEvent event) throws IOException {
        super.myCourseLinkClicked(event);

    }

    @FXML
    void noticesLinkClicked(ActionEvent event) throws IOException {
        super.noticesLinkClicked(event);
    }

    @FXML
    void submissionsLinkClicked(ActionEvent event) throws IOException {
        super.submissionsLinkClicked(event);
    }

}
