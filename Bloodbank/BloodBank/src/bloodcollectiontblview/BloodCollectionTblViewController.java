package bloodcollectiontblview;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import bloodissuedview.IssuedBeans;
import exploredonors.DonorsBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BloodCollectionTblViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<DonationsBean> tblgrid;

    @FXML
    private TextField txtmobile;
    
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    ObservableList<DonationsBean>allRecords;
    ObservableList<DonationsBean>Records;
    boolean jasoos=false;
    
      
    void getColumnns()
    {
    	tblgrid.getColumns().clear();
    	tblgrid.getItems().clear();
    	TableColumn<DonationsBean, String>mobile=new TableColumn<DonationsBean, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(100);
    	
    	TableColumn<DonationsBean, String>bgroup=new TableColumn<DonationsBean, String>("Blood Group");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<DonationsBean, Date>dateofdonation=new TableColumn<DonationsBean, Date>("Date Of Donation");
    	dateofdonation.setCellValueFactory(new PropertyValueFactory<>("dateofdonation"));//same as bean property
    	dateofdonation.setMinWidth(100);
    	
    	
    	tblgrid.getColumns().addAll(mobile,bgroup,dateofdonation);
    	
    	tblgrid.setItems(null);
    }
    

    @FXML
    void doShowAll(ActionEvent event) {
    	getColumnns();
    	allRecords=getAllObjects();	
    	tblgrid.setItems(allRecords);
    	jasoos=true;
    	
    }

    @FXML
    void doShowReccent(ActionEvent event) {
    	getColumnns();
    	ObservableList<DonationsBean>Records=getSelectedObjects();	
    	tblgrid.setItems(Records);
    	jasoos=false;
    }
    
    ObservableList<DonationsBean> getAllObjects()
   	{
       	ObservableList<DonationsBean> ary=FXCollections.observableArrayList();
   	    
       	PreparedStatement pst;
   	    try
   	    {
   	    	pst=con.prepareStatement("select * from donations");
   	    	table=pst.executeQuery();
   	    	while(table.next())
   	    	{
   	    		String mobile=table.getString("mobile");
   	    		String bgroup=table.getString("bgroup");
   	    		Date dateofdonation=table.getDate("dateofdonation");
   	    		
   	    		DonationsBean obj=new DonationsBean(mobile,bgroup,dateofdonation);
   	    		ary.addAll(obj);
     		    		
   	    		System.out.println(mobile+"  "+bgroup+"  "+dateofdonation);
   	    	}
   	    }
   	    catch(Exception exp)
   	    { 	
   	    	System.out.println(exp);
   	    }
   	    System.out.println(ary.size());
   	    return ary;
   	 }
     
    
    
    ObservableList<DonationsBean> getSelectedObjects()
   	{
    	String mobileno=txtmobile.getText();
       	ObservableList<DonationsBean> ary=FXCollections.observableArrayList();
   	    
       	PreparedStatement pst;
   	    try
   	    {
   	    	pst=con.prepareStatement("select * from donations where mobile=?");
   	    	pst.setString(1, mobileno);
   	    	table=pst.executeQuery();
   	    	while(table.next())
   	    	{
   	    		String mobile=table.getString("mobile");
   	    		String bgroup=table.getString("bgroup");
   	    		Date dateofdonation=table.getDate("dateofdonation");
   	    		
   	    		DonationsBean obj=new DonationsBean(mobile,bgroup,dateofdonation);
   	    		ary.addAll(obj);
     		    		
   	    		System.out.println(mobile+"  "+bgroup+"  "+dateofdonation);
   	    	}
   	    }
   	    catch(Exception exp)
   	    { 	
   	    	System.out.println(exp);
   	    }
   	    System.out.println(ary.size());
   	    return ary;
   	 }
    
    @FXML
    void doShowinExcel(ActionEvent event)
    {
    	try
    	{
    		if(jasoos==true)
    			writeExcel(allRecords);
    		else {
				writeExcel(Records);
			}
			System.out.println("Exported to excel..");
			
		}
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    }
    
    public void writeExcel( ObservableList<DonationsBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("BLoodCollectionTblView.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="mobile,bgroup,dateofdonation\n";
            writer.write(text);
            for (DonationsBean p : list)
            {
				text = p.getMobile()+ "," + p.getBgroup()+ "," + p.getDateofdonation()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
            writer.close();
        }
    }
    
    @FXML
    void initialize() {
        con=DatabaseConnection.doConnect();
        
    }

}
