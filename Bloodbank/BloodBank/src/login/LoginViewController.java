package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    
    @FXML
    private Label lblLogo;
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    @FXML
    void doLogin(ActionEvent event)
    {
    	
    	if(txtUsername.getText().toString().equals("abc123")&&txtPassword.getText().toString().equals("12345678"))
    	{
    		showMsg("login successful...");
    		try {
            	Parent root = FXMLLoader.load(getClass().getResource("/controlpanel/PanelView.fxml"));
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
    	else 
    	{
			showMsg("Incorrect Password");
		}
    }

    @FXML
    void initialize() {
       
    }

}
