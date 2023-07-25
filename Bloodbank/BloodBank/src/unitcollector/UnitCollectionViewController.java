package unitcollector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UnitCollectionViewController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboRmn;

    @FXML
    private TextField txtbGroup;

    @FXML
    private ImageView viewImg;
    
    @FXML
    private DatePicker datepckr;
    
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.ERROR);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }

    
    void getRmn()
    {
    	ArrayList<String>mobileno=new ArrayList<String>();
    	try
    	{
			pst=con.prepareStatement("select mobile from donors");
			table=pst.executeQuery();
			while(table.next())
    		{
    			String str=table.getString("mobile");
    			mobileno.add(str);
    			
    		}
			comboRmn.getItems().addAll(mobileno);
		}
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }
    
    @FXML
    void doSearch(ActionEvent event) 
    {
    	try
    	{
			pst=con.prepareStatement("select * from donors where mobile=?");
			pst.setString(1, comboRmn.getEditor().getText());
			table=pst.executeQuery();
			
			while(table.next())
			{
				txtbGroup.setText(table.getString("bgroup"));
				try
				{
					Image img=new Image(new FileInputStream(table.getString("picpath")));
					viewImg.setImage(img);
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
		
			}
		}
    	catch (SQLException e)
    	{
			e.printStackTrace();
		}
    }
    
    @FXML
    void doClear(ActionEvent event)
    {
    	comboRmn.getSelectionModel().select("");
    	txtbGroup.setText("");
    	viewImg.setImage(null);
    	datepckr.getEditor().clear();
    }

    @FXML
    void doUpload(ActionEvent event)
    {
    	String bg=txtbGroup.getText();
    	try
    	{
			pst=con.prepareStatement("insert into donations values(?,?,?)");
			pst.setString(1, comboRmn.getEditor().getText());
			pst.setString(2, txtbGroup.getText());
			LocalDate local=datepckr.getValue();
			java.sql.Date date=java.sql.Date.valueOf(local);
			pst.setDate(3, date);
			pst.executeUpdate();
			showMsg("Uploaded Data");
			
			
		}
    	catch (SQLException e)
    	{
			e.printStackTrace();
		}
    	
    	try
    	{
    		pst=con.prepareStatement("update total_blood_record set "+bg+"="+bg+"+?");
    		pst.setInt(1, 1);
    		pst.executeUpdate();
    		showMsg("Updated Total table");
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    

    
    @FXML
    void initialize()
    {
       con=DatabaseConnection.doConnect();
       getRmn();
       
    }

}
