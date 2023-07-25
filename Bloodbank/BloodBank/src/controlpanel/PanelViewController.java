package controlpanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PanelViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void doDonate(ActionEvent event) {
    	try {
        	Parent root = FXMLLoader.load(getClass().getResource("/unitcollector/UnitCollectionView.fxml"));
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
    void doRegister(ActionEvent event) {
    	try {
        	Parent root = FXMLLoader.load(getClass().getResource("/donormaster/DonorMasterView.fxml"));
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
    void doIssue(ActionEvent event) {
    	try {
        	Parent root = FXMLLoader.load(getClass().getResource("/issueblood/IssueBloodView.fxml"));
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
    void doShowAvailable(ActionEvent event) {
    	try {
        	Parent root = FXMLLoader.load(getClass().getResource("/stock/BloodStockView.fxml"));
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
    void doShowHistory(ActionEvent event) {
    	try {
        	Parent root = FXMLLoader.load(getClass().getResource("/historypanel/HistoryPanelView.fxml"));
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
    void doMeetTheDeveloper(ActionEvent event) {
    	try {
        	Parent root = FXMLLoader.load(getClass().getResource("/meetthedeveloper/MeetTheDeveloperView.fxml"));
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
    void doExploreDonors(ActionEvent event) {
    	try {
        	Parent root = FXMLLoader.load(getClass().getResource("/exploredonors/DonorsInfoView.fxml"));
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