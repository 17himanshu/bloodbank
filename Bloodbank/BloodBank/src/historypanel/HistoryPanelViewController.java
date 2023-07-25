package historypanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HistoryPanelViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void doShowCollectionHistory(ActionEvent event) {
    	try {
        	Parent root = FXMLLoader.load(getClass().getResource("/bloodcollectiontblview/BloodCollectionTblView.fxml"));
        	Scene scene = new Scene(root);
        	Stage stage = new Stage();
        	stage.setScene(scene);
        	stage.show();
        	
        	//---------------------------------
        	
        	/*Scene scene1 = (Scene)lblLogo.getScene();
        	scene1.getWindow().hide();
        	*/
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }

    @FXML
    void doShowIssueHistory(ActionEvent event) {
    	try {
        	Parent root = FXMLLoader.load(getClass().getResource("/bloodissuedview/BloodIssuedView.fxml"));
        	Scene scene = new Scene(root);
        	Stage stage = new Stage();
        	stage.setScene(scene);
        	stage.show();
        	
        	//---------------------------------
        	
        	/*Scene scene1 = (Scene)lblLogo.getScene();
        	scene1.getWindow().hide();
        	*/
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

}
