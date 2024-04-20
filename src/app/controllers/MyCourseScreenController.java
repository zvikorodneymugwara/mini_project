package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.objects.course.DegreeModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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
    private LineChart<String, Number> marksChart;

    @FXML
    private ComboBox<String> moduleSelectComboBox;

    @FXML
    private Label referenceIdLbl;

    @FXML
    private Label statusLbl;
    private ObservableList<String> moduleNames = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing Course Screen");
    }

    public void initializeScreen() {
        // initialize modules
        if (user != null) {
            Block<DegreeModule> modulesBlockChain = user.getDegree().getDegreeModules();
            for (Transaction<DegreeModule> moduleTransaction : modulesBlockChain.getTransactions()) {
                // Add module names to the ObservableList
                moduleNames.add(moduleTransaction.getData().getModuleCode());
            }
        }
        moduleSelectComboBox.setItems(moduleNames);

        // initialize text
        creditsLbl.setText(creditsLbl.getText() + " " + user.getDegree().getCredits());
        currentYearLbl.setText(currentYearLbl.getText() + " " + user.getCurrentYear());
        degCodeLbl.setText(degCodeLbl.getText() + " " + user.getDegree().getDegCode());
        degreeLbl.setText(degreeLbl.getText() + " " + user.getDegree().getDegName());
        facultyLbl.setText(facultyLbl.getText() + " " + user.getDegree().getFaculty());
        referenceIdLbl.setText(referenceIdLbl.getText() + " " + user.getDegree().getDegreeVerifier().hashCode());
        statusLbl.setText(statusLbl.getText() + " " + user.getDegree().getDegreeVerifier().getData());
    }
    
    @FXML
    //TODO Make this look nice
    void moduleSelectComboBoxClick(ActionEvent event) {
        int counter = 0;
        String moduleCode = moduleSelectComboBox.getValue();
        XYChart.Series<String, Number> series = new XYChart.Series<>(); // Use String for X-axis labels
        series.setName(moduleCode);
        marksChart.getData().clear();

        for (Transaction<DegreeModule> module : user.getDegree().getDegreeModules().getTransactions()) {
            if (module.getData().getModuleCode().equals(moduleCode)) {
                for (Transaction<Number> marks : module.getData().getAssessments().getTransactions()) {
                    counter++;
                    series.getData().add(new XYChart.Data<>("" + counter, marks.getData()));
                }
                break;
            }
        }
        marksChart.setTitle(moduleCode + " Marks");
        marksChart.getXAxis().setLabel("Semester Test");
        marksChart.getYAxis().setLabel("Mark");
        marksChart.getData().add(series);
        marksChart.layout();
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
