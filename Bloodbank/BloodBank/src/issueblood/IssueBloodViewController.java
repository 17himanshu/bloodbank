package issueblood;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

public class IssueBloodViewController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combobGroup;

    @FXML
    private TextField txthospital;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtnname;

    @FXML
    private TextField txtnumber;

    @FXML
    private TextField txtreason;

    Connection con;
    PreparedStatement pst;
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.ERROR);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    @FXML
    void doUpload(ActionEvent event)
    {
    	String bg=combobGroup.getSelectionModel().getSelectedItem();
    	int number=Integer.parseInt(txtnumber.getText());
    	try
    	{
			pst=con.prepareStatement("insert into issued values(?,?,?,?,current_date(),?)");
			pst.setString(1, txtnname.getText());
			pst.setString(2, txtmobile.getText());
			pst.setString(3, txthospital.getText());
			pst.setString(4, txtreason.getText());
			pst.setString(5, combobGroup.getSelectionModel().getSelectedItem());
			pst.executeUpdate();
			
			showMsg("Updated Successfuly.....");
		} 
    	catch (SQLException e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try
    	{
    		pst=con.prepareStatement("update total_blood_record set "+bg+"="+bg+"-?");
    		pst.setInt(1, number);                                    
    		pst.executeUpdate();
    		showMsg("Updated Total table");
    	}
    	catch(SQLException e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
    	con=DatabaseConnection.doConnect();
    	combobGroup.getItems().setAll("opos","one","apos","ane","bpos","bne","abpos","abne");
    }

}
