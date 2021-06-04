package Models;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.Notification.AlertConfirmation;
import BusinessLayer.Notification.AlertInfo;
import BusinessLayer.Pdf.*;
import BusinessLayer.Report.Report;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MenuBarViewModel {

    public final ListProperty tourList = new SimpleListProperty();
    private LoggingHandler log = new LoggingHandler();

    //Methoden

    public ObservableList searchForTour(String searchText){
        ArrayList searchedList = new Database_Tours().getSearched(searchText);
        ObservableList obList = FXCollections.observableList(searchedList);
        log.logDebug("Searched Tours displayed -MenuBarViewModel-");
        return obList;
    }

    public ObservableList searchForLog(String text) {
            ArrayList searchedList = new Database_Logs().getSearched(text);
            ObservableList obList = FXCollections.observableList(searchedList);
            log.logDebug("Searched/Selected Tour Logs displayed -MenuBarViewModel-");
            return obList;
    }

    public ObservableList logsForSpecificTour(String name){
        ObservableList obList = null;
        ArrayList logs = null;
        if (name.equals("null")) {
            log.logDebug("No Tour Logs shown, because no Tour was selected -MenuBarViewModel-");
            logs = new Database_Logs().getLogs();
            obList = FXCollections.observableList(logs);
            return obList;
        }
        logs = new Database_Logs().getSpecific(name);
        obList = FXCollections.observableList(logs);
        return obList;
    }
    public void exportTours(){
        new PdfExportTours().toPdf();
        new AlertInfo().alertExportSuccess();
        log.logDebug("Exporting Tours to Pdf finished -MenuBarViewModel-");
    }

    public void exportToursTable(){
        new PdfExportTours().toPdfTable();
        new AlertInfo().alertExportSuccess();
        log.logDebug("Exporting Tours to Table View finished -MenuBarViewModel-");
    }

    public void importTours(){
        new PdfImportTours().importFromPdf();
        log.logDebug("Importing Tours from Pdf finished -MenuBarViewModel-");
    }

    public void getHelp(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("help");
        log.logDebug("help -MenuBarViewModel-");
    }

    public void importTourLogs(){
        new PdfImportTourLogs().importFromPdf();
        log.logDebug("Importing Tour Logs from Pdf finished -MenuBarViewModel-");
    }

    public void exportTourLogsTable() {
        new PdfExportTourLogs().toPdfTable();
        new AlertInfo().alertExportSuccess();
        log.logDebug("Exporting Tour Logs to Table View finished -MenuBarViewModel-");
    }

    public void exportTourLogs(){
        new PdfExportTourLogs().toPdf();
        new AlertInfo().alertExportSuccess();
        log.logDebug("Exporting Tour Logs to Pdf finished -MenuBarViewModel-");
    }

    public void createReport(String tourName) {
        if(tourName.equals("null") == false)
            new Report().singleTourReport(tourName);
        else {
            int alert = new AlertConfirmation().reportTourNull();
            if (alert != 0)
                log.logDebug("Printing Report stopped from user interaction -MenuBarViewModel-");
            else{
                log.logDebug("Printing Report for all Tours starting... -MenuBarViewModel-");
                new Report().tourReport(new Database_Tours().getTourNames());
            }

        }
    }

    public void loadLogLevelSelector(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("logLevel");
    }

    //Properties
    public ListProperty getTourList(){
        return tourList;
    }
}
