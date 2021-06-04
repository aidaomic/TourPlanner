package DataAccessLayer;

import com.itextpdf.text.pdf.PdfPTable;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Database {
    void save(ArrayList list);
    void delete(String name) throws SQLException;
    ArrayList getSearched(String searchText);
    String toFile();
    PdfPTable toFileTable(PdfPTable exportTable);
}
