package exploredonors;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import bloodissuedview.IssuedBeans;
import donormaster.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DonorsInfoViewController {

	Connection con;
	PreparedStatement pst;
	ResultSet table;
	boolean jasoos=false;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combobgroup;

    @FXML
    private TableView<DonorsBean> tblGrid;
    
    ObservableList<DonorsBean>allRecords;
    ObservableList<DonorsBean>selectedRecords;
    
    void getColumnns()
    {
    	tblGrid.getColumns().clear();
    	tblGrid.getItems().clear();
    	TableColumn<DonorsBean, String>mobile=new TableColumn<DonorsBean, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(100);
    	
    	TableColumn<DonorsBean, String>name=new TableColumn<DonorsBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	name.setMinWidth(100);
    	
    	TableColumn<DonorsBean, String>gender=new TableColumn<DonorsBean, String>("Gender");
    	gender.setCellValueFactory(new PropertyValueFactory<>("gender"));//same as bean property
    	gender.setMinWidth(100);
    	
    	TableColumn<DonorsBean, String> address=new TableColumn<DonorsBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//same as bean property
    	address.setMinWidth(100);
    	
    	TableColumn<DonorsBean, String> bgroup=new TableColumn<DonorsBean, String>("Blood Group");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<DonorsBean, String>age=new TableColumn<DonorsBean, String>("Age");
    	age.setCellValueFactory(new PropertyValueFactory<>("age"));//same as bean property
    	age.setMinWidth(100);
    	
    	tblGrid.getColumns().addAll(mobile,name,gender,address,bgroup,age);
    	
    	tblGrid.setItems(null);
    }
    
    @FXML
    void doFetch(ActionEvent event)
    {
    	getColumnns();
    	selectedRecords=getSelectedObjects();	
    	tblGrid.setItems(selectedRecords);
    	jasoos=false;
    }
    
    ObservableList<DonorsBean> getSelectedObjects()
  	{
    	String bg=combobgroup.getSelectionModel().getSelectedItem();
      	ObservableList<DonorsBean> ary=FXCollections.observableArrayList();
  	    
      	PreparedStatement pst;
  	    try
  	    {
  	    	pst=con.prepareStatement("select * from donors where bgroup=?");
  	    	pst.setString(1, bg);
  	    	table=pst.executeQuery();
  	    	while(table.next())
  	    	{
  	    		String mobile=table.getString("mobile");
  	    		String name=table.getString("name");
  	    		String gender=table.getString("gender");
  	    		String address=table.getString("address");
  	    		String bgroup=table.getString("bgroup");
  	    		String age=table.getString("age");
  	    		DonorsBean obj=new DonorsBean(mobile, name, gender, address,bgroup,age);
  	    		ary.add(obj);
    		    		
  	    		System.out.println(mobile+"  "+name+"  "+gender+"  "+address+" "+bgroup+" "+age);
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
    void doShowAll(ActionEvent event)
    {
    	getColumnns();
    	allRecords=getAllObjects();	
    	tblGrid.setItems(allRecords);
    	jasoos=true;
    }
    
    ObservableList<DonorsBean> getAllObjects()
	{
    	ObservableList<DonorsBean> ary=FXCollections.observableArrayList();
	    
    	PreparedStatement pst;
	    try
	    {
	    	pst=con.prepareStatement("select * from donors");
	    	table=pst.executeQuery();
	    	while(table.next())
	    	{
	    		String mobile=table.getString("mobile");
	    		String name=table.getString("name");
	    		String gender=table.getString("gender");
	    		String address=table.getString("address");
	    		String bgroup=table.getString("bgroup");
	    		String age=table.getString("age");
	    		DonorsBean obj=new DonorsBean(mobile, name, gender, address,bgroup,age);
	    		ary.add(obj);
  		    		
	    		System.out.println(mobile+"  "+name+"  "+gender+"  "+address+" "+bgroup+" "+age);
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
    			writeExcel(selectedRecords);
			}
			System.out.println("Exported to excel..");
			
		}
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    }
    
    public void writeExcel( ObservableList<DonorsBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("ExploreDonors.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="mobile, name, gender, address,bgroup,age\n";
            writer.write(text);
            for (DonorsBean p : list)
            {
				text = p.getMobile()+ "," + p.getName()+ "," + p.getGender()+ "," + p.getAddress()+"," + p.getBgroup()+"," + p.getAge()+"\n";
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
    	combobgroup.getItems().setAll("opos","one","apos","ane","bpos","bne","abpos","abne");
    }

}
