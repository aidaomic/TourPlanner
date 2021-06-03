package Models;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class LoggingLevelViewModel {
    public final BooleanProperty all = new SimpleBooleanProperty();
    public final BooleanProperty debug = new SimpleBooleanProperty();
    public final BooleanProperty info = new SimpleBooleanProperty();
    public final BooleanProperty error = new SimpleBooleanProperty();

    private LoggingHandler log = new LoggingHandler();

    public void changeToMain(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("mainWindow");
        log.logDebug("Changin Stage to Main -LoggingLevelViewModel-");
    }

    public void changeLogLevel(Stage stage, String level){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("file:///C:/Users/omaid/Dropbox/Mein%20PC%20(LAPTOP-45NNE962)/Documents/Studium/SS21/SWEI/TourPlanner//src/BusinessLayer/Logging/logging.conf.xml");

            Node root = document.getElementsByTagName("Root").item(0);
            NamedNodeMap conf = root.getAttributes();
            Node lev = conf.getNamedItem("level");
            lev.setTextContent(level);

            new StageLoader(stage).changeStage("mainWindow");
            //Node Logge = document.getElementsByTagName("Configuration").item(0);

            log.logDebug("Log Level changed -LoggingLevelViewModel-");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public BooleanProperty allProperty() {
        return all;
    }

    public BooleanProperty debugProperty() {
        return debug;
    }

    public BooleanProperty infoProperty() {
        return info;
    }

    public BooleanProperty errorProperty() {
        return error;
    }
}
