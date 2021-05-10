package TourPlanner;

import DataAccessLayer.Database_Tours;
import Models.MainViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class MainWindowController implements Initializable {

    // create custom viewmodel
    public MainViewModel viewModel = new MainViewModel();

    // add fx:id and use intelliJ to create field in controller
    public TextField textForSearch;
    public ListView tourList;
    public Label tourInformationDisplay, titleOutput;
    public ImageView tourImageDisplay;

    public MainWindowController(){
        System.out.println("Controller created");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        textForSearch.textProperty().bindBidirectional(viewModel.inputProperty());
        tourInformationDisplay.textProperty().bindBidirectional(viewModel.informationProperty());
        tourImageDisplay.imageProperty().bindBidirectional(viewModel.tourImageProperty());
        titleOutput.textProperty().bindBidirectional(viewModel.outputPropertyTitle());
        tourList.itemsProperty().bindBidirectional(viewModel.tourListProperty());

        Database_Tours data = new Database_Tours();
        ArrayList list = data.getTourNames();
        ObservableList obList = FXCollections.observableList(list);
        tourList.setItems(obList);
    }

    public void searchForTour(ActionEvent actionEvent) throws SQLException {
        viewModel.searchForTour(textForSearch.getText());
    }

    public void getHelp(ActionEvent actionEvent) {
        viewModel.getHelp();
    }

    public void doEdit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        viewModel.doEdit(stage, String.valueOf(tourList.getSelectionModel().getSelectedItem()));
    }

    public void addTour(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        viewModel.addTour(stage);
    }

    public void deleteTour(ActionEvent actionEvent) throws IOException, SQLException {
        //Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        viewModel.deleteTour(String.valueOf(tourList.getSelectionModel().getSelectedItem()));
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
        viewModel.importTours();
        Database_Tours data = new Database_Tours();
        ArrayList list = data.getTourNames();
        ObservableList obList = FXCollections.observableList(list);
        tourList.setItems(obList);
    }

    public void exportData(ActionEvent actionEvent) {
        viewModel.exportTours();
    }

    public void showTour(MouseEvent mouseEvent) {
        viewModel.showTour(String.valueOf(tourList.getSelectionModel().getSelectedItem()));
    }

    public void zoom(MouseEvent mouseEvent) throws IOException {
        viewModel.zoomPicture(titleOutput.getText());
    }
}
