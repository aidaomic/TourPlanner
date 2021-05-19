package Models;

import BuissnessLayer.Notification.Allerts;
import BuissnessLayer.Pdf.PdfGenerator;
import BuissnessLayer.Pdf.PdfReader;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class MenuBarViewModel {

    public final ListProperty tourList = new SimpleListProperty();

    public ListProperty getTourList(){
        return tourList;
    }

    //Methoden
    public ObservableList searchForTour(String searchText){
        ArrayList searchedList = new Database_Tours().getSearchedTours(searchText);
        ObservableList obList = FXCollections.observableList(searchedList);
        return obList;
    }

    public ObservableList searchForLog(String text) {
        ArrayList searchedList = new Database_Logs().getSearchedTourLogs(text);
        ObservableList obList = FXCollections.observableList(searchedList);
        return obList;
    }

    public void exportTours(){
        new PdfGenerator().toursToPdf();
        new Allerts().allertExportSuccess();
    }

    public void exportToursTable(){
        new PdfGenerator().toursToPdfTable();
        new Allerts().allertExportSuccess();
    }

    public void importTours(){
        new PdfReader().pdfToTours();
    }

    public void getHelp() {

    }

}
