package stock;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BloodStockViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtabne;

    @FXML
    private TextField txtabpos;

    @FXML
    private TextField txtane;

    @FXML
    private TextField txtapos;

    @FXML
    private TextField txtbne;

    @FXML
    private TextField txtbpos;

    @FXML
    private TextField txtone;

    @FXML
    private TextField txtopos;
    
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    
    void showAll() 
    {
    	try
    	{
    		pst=con.prepareStatement("select * from total_blood_record");
        	table=pst.executeQuery();
        	
        	while(table.next())
        	{
        		txtabne.setText(table.getString("abne"));

        	    txtabpos.setText(table.getString("abpos"));
        	    
        	    txtane.setText(table.getString("ane"));
        	    
        	    txtapos.setText(table.getString("apos"));

        	    txtbne.setText(table.getString("bne"));

        	    txtbpos.setText(table.getString("bpos"));

        	    txtone.setText(table.getString("one"));

        	    txtopos.setText(table.getString("opos"));
        	}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	
    }
    @FXML
    void initialize() {
       
        con=DatabaseConnection.doConnect();
        showAll();
    }

}
