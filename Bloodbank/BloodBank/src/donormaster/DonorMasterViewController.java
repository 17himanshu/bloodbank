package donormaster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class DonorMasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBGroup;

    @FXML
    private ComboBox<String> comboGender;

    @FXML
    private TextField txtDisease;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtname;

    @FXML
    private ImageView view;
    
    @FXML
    private TextField txtAge;
    
    
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    String str="C:\\Users\\lenovo\\Downloads\\Insert-Photo-Here.jpg";
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.ERROR);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }

    @FXML
    void doClear(ActionEvent event)
    {
    	txtaddress.setText("");
    	txtAge.setText("");
    	txtcity.setText("");
    	txtDisease.setText("");
    	txtmobile.setText("");
    	txtname.setText("");
    	comboBGroup.getSelectionModel().select("");
    	comboGender.getSelectionModel().select("");
    	Image img=new Image(str);
		view.setImage(img);
    	
    }

    @FXML
    void doDelete(ActionEvent event) 
    {
    	int count=0;
    	try
    	{
			pst=con.prepareStatement("delete * from donors where mobile=?");
			pst.setString(1, txtmobile.getText());
			showMsg("Deleted Successfuly..");
		    count=pst.executeUpdate();
					
			
		} 
    	catch (SQLException e)
    	{
			e.printStackTrace();
		}
    	if(count==0)
    		showMsg("Already Deleted...");
    		
    }
    
    boolean findRecord(String mobile) throws FileNotFoundException
    {
    	boolean jasoos=false;
    	try 
    	{
			pst=con.prepareStatement("select * from donors where mobile=?");
			pst.setString(1, txtmobile.getText());
			table=pst.executeQuery();
			while(table.next())
			{
				jasoos=true;
				txtname.setText(table.getString("name"));
				txtmobile.setText(table.getString("mobile"));
				txtaddress.setText(table.getString("address"));
				txtcity.setText(table.getString("city"));
				txtDisease.setText(table.getString("disease"));
				txtAge.setText(table.getString("age"));
				comboBGroup.getSelectionModel().select(table.getString("bgroup"));
				comboGender.getSelectionModel().select(table.getString("gender"));
				
				Image img=new Image(new FileInputStream(table.getString("picpath")));
				view.setImage(img);
				
	
			}
		}
    	catch (SQLException e)
    	{
			e.printStackTrace();
		}
    	return jasoos;
    }
    
    
    boolean chkfiles(String mobile)
    {  
    	boolean chk=false;
    	try 
    	{
			pst=con.prepareStatement("select * from donors where mobile=?");
			pst.setString(1, txtmobile.getText());
			
			table=pst.executeQuery();
			while(table.next())
			{
				chk=true;
			}
		} 
    	catch (SQLException e)
    	{
		
			e.printStackTrace();
		}
    	
    	return chk;
    }
    @FXML
    void doFind(ActionEvent event)
    {
    	String mobile=txtmobile.getText();
    	try {
			if(findRecord(mobile)==false)
			{
				showMsg("invalid mobile no");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    }
    String path;
    @FXML
    void doInsertImage(ActionEvent event) throws FileNotFoundException
    {
    	FileChooser chooser=new FileChooser();
        //chooser.setTitle("Open File");
    	chooser.getExtensionFilters().add(new ExtensionFilter("JPG Files","*.jpg"));
    	  File f = chooser.showOpenDialog(new Stage());
        if(f != null)
        {
        	Image img=new Image(new FileInputStream(f.getAbsoluteFile()));
        	view.setImage(img);
        	path=new String(f.getAbsolutePath());
        }
    }

    @FXML
    void doRegister(ActionEvent event)
    {
    	String mobile=txtmobile.getText();
    	if(chkfiles(mobile)==true)
    	{
    		showMsg("Mobile Number already registered with us...");
    		return;
    	}
    	try
    	{
			pst=con.prepareStatement("insert into donors values(?,?,?,?,?,?,?,?,?,current_date())");
			pst.setString(1, txtmobile.getText());
			pst.setString(2,path);
			pst.setString(3,txtname.getText());
			pst.setString(4,comboGender.getSelectionModel().getSelectedItem());
			pst.setString(5,txtaddress.getText());
			pst.setString(6,txtcity.getText());
			pst.setString(7,comboBGroup.getSelectionModel().getSelectedItem());
			pst.setString(8, txtAge.getText());
			pst.setString(9, txtDisease.getText());
			pst.executeUpdate();
			showMsg("Record Inserted Successfuly....");
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doUpdate(ActionEvent event) 
    {
    	try 
    	{
    		pst=con.prepareStatement("update donors set name=?,gender=?,address=?,city=?,age=?,bgroup=?,disease=? where mobile=?");
        	pst.setString(8, txtmobile.getText());
        	pst.setString(1, txtname.getText());
        	pst.setString(2, comboGender.getSelectionModel().getSelectedItem());
        	pst.setString(3, txtaddress.getText());
        	pst.setString(4, txtcity.getText());
        	pst.setString(5, txtAge.getText());
        	pst.setString(6,comboBGroup.getSelectionModel().getSelectedItem());
        	pst.setString(7, txtDisease.getText());
        	int count=pst.executeUpdate();
        	showMsg("updated successfuly");
        	if(count==0)
        		showMsg("Invalid mobile no.");
		} 
    	catch (SQLException e)
    	{
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void initialize()
    {
    	con=DatabaseConnection.doConnect();
    	comboBGroup.getItems().setAll("opos","one","apos","ane","bpos","bne","abpos","abne");
    	comboGender.getItems().setAll("Male","Female","Other");

    }

}

