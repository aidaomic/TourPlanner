package TourPlanner;

import Models.MainViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        InputTextField.textProperty().bindBidirectional(viewModel.inputProperty());
        //OutputLabel.textProperty().bindBidirectional(viewModel.outputProperty());
        Bindings.bindBidirectional(OutputLabel.textProperty(), viewModel.outputProperty());
    }

    public void searchForTour(ActionEvent actionEvent) {
        viewModel.searchForTour();
    }

    public void getHelp(ActionEvent actionEvent) {
        viewModel.getHelp();
    }

    public void doEdit(ActionEvent actionEvent) throws IOException {
        viewModel.doEdit();
    }

    public void addTour(ActionEvent actionEvent) throws IOException {
        viewModel.addTour();
    }

    public void deleteTour(ActionEvent actionEvent) throws IOException {
        viewModel.deleteTour();
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
