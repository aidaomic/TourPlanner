package TourPlanner;

import Models.MainViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    // create custom viewmodel
    public MainViewModel viewModel = new MainViewModel();

    // add fx:id and use intelliJ to create field in controller
    public TextField InputTextField;
    public Label OutputLabel;

    public MainWindowController()
    {
        System.out.println("Controller created");
    }

    @FXML
    public void searchForTour(ActionEvent actionEvent) {
        System.out.println("Controller Search");
        viewModel.searchForTour();
    }

    @FXML
    public void getHelp(ActionEvent actionEvent) {
        System.out.println("Controller Help");
        viewModel.getHelp();
    }

    @FXML
    public void getOptions(ActionEvent actionEvent) {
        System.out.println("Controller Options");
        viewModel.getOptions();
    }
    @FXML
    public void getFile(ActionEvent actionEvent) {
        System.out.println("Controller File");
        viewModel.getFile();
    }
    @FXML
    public void doEdit(ActionEvent actionEvent) {
        System.out.println("Controller Edit");
        viewModel.doEdit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller init");

        InputTextField.textProperty().bindBidirectional(viewModel.inputProperty());

        //OutputLabel.textProperty().bindBidirectional(viewModel.outputProperty());
        Bindings.bindBidirectional(OutputLabel.textProperty(), viewModel.outputProperty());
    }

    public void addTour(ActionEvent actionEvent) {
    }

    public void deleteTour(ActionEvent actionEvent) {
    }

    public void something(ActionEvent actionEvent) {
    }

    public void displayRoute(ActionEvent actionEvent) {
    }

    public void displayDescription(ActionEvent actionEvent) {
    }

    public void addLog(ActionEvent actionEvent) {
    }

    public void deleteLog(ActionEvent actionEvent) {
    }

    public void somethingLog(ActionEvent actionEvent) {
    }


    public void logFile(ActionEvent actionEvent) {
    }

    public void report(ActionEvent actionEvent) {
    }

    public void importData(ActionEvent actionEvent) {
    }

    public void exportData(ActionEvent actionEvent) {
    }
}
