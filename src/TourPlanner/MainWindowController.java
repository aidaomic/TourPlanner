package TourPlanner;

import BusinessLayer.Logging.LoggingHandler;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import Models.*;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    // create custom viewmodel
    private MainViewModel viewModel = new MainViewModel();
    private AddTourViewModel addTourModel = new AddTourViewModel();
    private EditTourViewModel editTourModel = new EditTourViewModel();
    private MenuBarViewModel menuModel = new MenuBarViewModel();
    private AddLogViewModel addLogModel = new AddLogViewModel();
    private EditLogViewModel editLogModel = new EditLogViewModel();

    // add fx:id and use intelliJ to create field in controller
    public TextField textForSearch;
    public ListView tourList;
    public Label tourInformationDisplay, titleOutput;
    public ImageView tourImageDisplay;
    public TableView logTable;
    public TableColumn<LogTable, String> logId, tourName, dateAndTime, distance, totalTime, rating, weather, seasClos, transportation, traffic, fuelUsed, speed;

    private LoggingHandler log = new LoggingHandler();

    public MainWindowController(){
        log.logDebug("Controller -MainWindowController- created");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindProperties();
        //Tours
        Database_Tours data_t = new Database_Tours();
        ArrayList list = data_t.getTourNames();
        ObservableList obList = FXCollections.observableList(list);
        tourList.setItems(obList);
        //Logs
        Database_Logs data_l = new Database_Logs();
        ArrayList logs = data_l.getLogs();
        ObservableList obList_l = FXCollections.observableList(logs);
        logTable.setItems(obList_l);
        //log
        log.logInfo("MainWindowController initialized");
    }

    private void bindProperties(){
        textForSearch.textProperty().bindBidirectional(viewModel.inputProperty());
        tourInformationDisplay.textProperty().bindBidirectional(viewModel.informationProperty());
        tourImageDisplay.imageProperty().bindBidirectional(viewModel.tourImageProperty());
        titleOutput.textProperty().bindBidirectional(viewModel.outputPropertyTitle());
        tourList.itemsProperty().bindBidirectional(viewModel.tourListProperty());

        tourName.setCellValueFactory(new PropertyValueFactory<>("tourName"));
        dateAndTime.setCellValueFactory(new PropertyValueFactory<>("dateAndTime"));
        distance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        totalTime.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        weather.setCellValueFactory(new PropertyValueFactory<>("weather"));
        seasClos.setCellValueFactory(new PropertyValueFactory<>("seasClos"));
        transportation.setCellValueFactory(new PropertyValueFactory<>("transportation"));
        traffic.setCellValueFactory(new PropertyValueFactory<>("traffic"));
        fuelUsed.setCellValueFactory(new PropertyValueFactory<>("fuelUsed"));
        speed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        logId.setCellValueFactory(new PropertyValueFactory<>("logId"));

        log.logDebug("MainWindowController Property binding finished");
    }

    public void searchForTour(ActionEvent actionEvent) throws SQLException {
        tourList.setItems(menuModel.searchForTour(textForSearch.getText()));
        logTable.setItems(menuModel.searchForLog(textForSearch.getText()));
        log.logDebug("Search finished -MainWindowController-");
    }

    public void getHelp(ActionEvent actionEvent) {
        menuModel.getHelp();
        log.logDebug("Help finished -MainWindowController-");
    }

    public void doEdit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        editTourModel.editTourStage(stage, String.valueOf(tourList.getSelectionModel().getSelectedItem()));
        log.logDebug("Tour '"+tourList.getSelectionModel().getSelectedItem()+"' edited -MainWindowController-");
    }

    public void addTour(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        addTourModel.addTourStage(stage);
        log.logDebug("New tour added -MainWindowController-");
    }

    public void deleteTour(ActionEvent actionEvent) throws IOException, SQLException {
        //Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        viewModel.deleteTour(String.valueOf(tourList.getSelectionModel().getSelectedItem()));
        log.logDebug("Tour '"+tourList.getSelectionModel().getSelectedItem()+"' deleted -MainWindowController-");
    }

    public void addLog(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        addLogModel.addTourLogStage(stage, String.valueOf(tourList.getSelectionModel().getSelectedItem()));
        log.logDebug("new Log created for Tour '"+tourList.getSelectionModel().getSelectedItem()+"' -MainWindowController-");
    }

    public void deleteLog(ActionEvent actionEvent) throws IOException {
        LogTable l = (LogTable) logTable.getSelectionModel().getSelectedItem();
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        viewModel.deleteTourLog(stage, l.logId);
        log.logDebug("Log deleted -MainWindowController-");
    }

    public void editLog(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        editLogModel.editTourLogStage(stage, (LogTable) logTable.getSelectionModel().getSelectedItem());
        log.logDebug("Log edited -MainWindowController-");
    }

    public void importDataTours(ActionEvent actionEvent) {
        menuModel.importTours();
        Database_Tours data = new Database_Tours();
        ArrayList list = data.getTourNames();
        ObservableList obList = FXCollections.observableList(list);
        tourList.setItems(obList);
        log.logDebug("Tour Data imported successfully -MainWindowController-");
    }

    public void exportDataTours(ActionEvent actionEvent) {
        menuModel.exportTours();
        log.logDebug("Tour Data exported to Pdf successfully -MainWindowController-");
    }

    public void showTour(MouseEvent mouseEvent) {
        viewModel.showTour(String.valueOf(tourList.getSelectionModel().getSelectedItem()));
        log.logDebug("Tour -"+tourList.getSelectionModel().getSelectedItem()+"- displayed successfully -MainWindowController-");
    }

    public void zoom(MouseEvent mouseEvent) throws IOException {
        viewModel.zoomPicture(titleOutput.getText());
        log.logDebug("Zoomed into tour map -MainWindowController-");
    }

    public void copyTour(ActionEvent actionEvent) {
        viewModel.copyTour(String.valueOf(tourList.getSelectionModel().getSelectedItem()));
        Database_Tours data = new Database_Tours();
        ArrayList list = data.getTourNames();
        ObservableList obList = FXCollections.observableList(list);
        tourList.setItems(obList);
        log.logDebug("Tour '"+tourList.getSelectionModel().getSelectedItem()+"' copied -MainWindowController-");
    }

    public void exportDataToursTable(ActionEvent actionEvent) {
        menuModel.exportToursTable();
        log.logDebug("Tour Data exported as Table View successfully -MainWindowController-");
    }

    public void importDataTourLogs(ActionEvent actionEvent) {
        menuModel.importTourLogs();
        Database_Logs data = new Database_Logs();
        ArrayList list = data.getLogs();
        ObservableList obList = FXCollections.observableList(list);
        logTable.setItems(obList);
        log.logDebug("Tour Log Data imported successfully -MainWindowController-");
    }

    public void exportDataTourLogsTable(ActionEvent actionEvent) {
        menuModel.exportTourLogsTable();
        log.logDebug("Tour Log Data exported as Table View successfully -MainWindowController-");
    }

    public void exportDataTourLogs(ActionEvent actionEvent) {
        menuModel.exportTourLogs();
        log.logDebug("Tour Log Data exported to Pdf successfully -MainWindowController-");
    }

    public void report(ActionEvent actionEvent) {
        log.logDebug("Tour report finished -MainWindowController-");
    }
}
