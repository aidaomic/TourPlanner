package Models;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.Notification.Allerts;
import BusinessLayer.Pdf.PdfGenerator;
import BusinessLayer.Pdf.PdfReader;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class MenuBarViewModel {

    public final ListProperty tourList = new SimpleListProperty();
    private LoggingHandler log = new LoggingHandler();

    //Methoden
    public ObservableList searchForTour(String searchText){
        ArrayList searchedList = new Database_Tours().getSearchedTours(searchText);
        ObservableList obList = FXCollections.observableList(searchedList);
        log.logDebug("Searched Tours displayed -MenuBarViewModel-");
        return obList;
    }

    public ObservableList searchForLog(String text) {
        ArrayList searchedList = new Database_Logs().getSearchedTourLogs(text);
        ObservableList obList = FXCollections.observableList(searchedList);
        log.logDebug("Searched Tour Logs displayed -MenuBarViewModel-");
        return obList;
    }

    public void exportTours(){
        new PdfGenerator().toursToPdf();
        new Allerts().allertExportSuccess();
        log.logDebug("Exporting Tours to Pdf finished -MenuBarViewModel-");
    }

    public void exportToursTable(){
        new PdfGenerator().toursToPdfTable();
        new Allerts().allertExportSuccess();
        log.logDebug("Exporting Tours to Table View finished -MenuBarViewModel-");
    }

    public void importTours(){
        new PdfReader().pdfToTours();
        log.logDebug("Importing Tours from Pdf finished -MenuBarViewModel-");
    }

    public void getHelp() {
        log.logDebug("help -MenuBarViewModel-");
    }

    public void importTourLogs(){
        new PdfReader().pdfToTourLogs();
        log.logDebug("Importing Tour Logs from Pdf finished -MenuBarViewModel-");
    }

    public void exportTourLogsTable() {
        new PdfGenerator().tourLogsToPdfTable();
        new Allerts().allertExportSuccess();
        log.logDebug("Exporting Tour Logs to Table View finished -MenuBarViewModel-");
    }

    public void exportTourLogs(){
        new PdfGenerator().logsToPdf();
        new Allerts().allertExportSuccess();
        log.logDebug("Exporting Tour Logs to Pdf finished -MenuBarViewModel-");
    }

    //Properties
    public ListProperty getTourList(){
        return tourList;
    }
}
