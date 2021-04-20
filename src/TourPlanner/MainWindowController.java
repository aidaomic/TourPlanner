package TourPlanner;

import DataAccessLayer.Database_Tours;
import Models.MainViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    // create custom viewmodel
    public MainViewModel viewModel = new MainViewModel();

    // add fx:id and use intelliJ to create field in controller
    public TextField InputTextField;
    public ListView tourList;
    public Label OutputLabel, titleOutput;

    public MainWindowController(){
        System.out.println("Controller created");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database_Tours data = new Database_Tours();
        ArrayList list = data.getTourNames();
        ObservableList obList = FXCollections.observableList(list);
        tourList.setItems(obList);
        InputTextField.textProperty().bindBidirectional(viewModel.inputProperty());
        OutputLabel.textProperty().bindBidirectional(viewModel.outputProperty());
        titleOutput.textProperty().bindBidirectional(viewModel.outputPropertyTitle());
    }

    public void searchForTour(ActionEvent actionEvent) {
        viewModel.searchForTour();
    }

    public void getHelp(ActionEvent actionEvent) {
        viewModel.getHelp();
    }

    public void doEdit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        viewModel.doEdit(stage, String.valueOf(tourList.getSelectionModel().getSelectedItem()));
    }

    public void addTour(ActionEvent actionEvent) throws IOException {
        //tourList.itemsProperty().bindBidirectional(viewModel.outputList());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        viewModel.addTour(stage);
    }

    public void deleteTour(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        viewModel.deleteTour(stage);
    }

    public void displayRoute(ActionEvent actionEvent) {
    }

    public void displayDescription(ActionEvent actionEvent) {
    }

    public void addLog(ActionEvent actionEvent) {
    }

    public void deleteLog(ActionEvent actionEvent) {
    }

    public void logFile(ActionEvent actionEvent) {
    }

    public void report(ActionEvent actionEvent) {
    }

    public void importData(ActionEvent actionEvent) {
    }

    public void exportData(ActionEvent actionEvent) {
    }

    public void showTour(MouseEvent mouseEvent) {
        viewModel.showTour(String.valueOf(tourList.getSelectionModel().getSelectedItem()));
    }

}
