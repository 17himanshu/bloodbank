package bloodissuedview;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import bloodcollectiontblview.DonationsBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BloodIssuedViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combobgroup;

    @FXML
    private DatePicker datepckrdoi;
    
    @FXML
    private TableView<IssuedBeans> tblgrid;
    
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    
    ObservableList<IssuedBeans>allRecords;
    
    void getColumnns()
    {
    	tblgrid.getColumns().clear();
    	tblgrid.getItems().clear();
    	
    	TableColumn<IssuedBeans, String>nname=new TableColumn<IssuedBeans, String>("Needy Name");
    	nname.setCellValueFactory(new PropertyValueFactory<>("nname"));//same as bean property
    	nname.setMinWidth(80);
    	
    	TableColumn<IssuedBeans, String>mobile=new TableColumn<IssuedBeans, String>("Mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(80);
    	

    	TableColumn<IssuedBeans, String>hospital=new TableColumn<IssuedBeans, String>("Hospital");
    	hospital.setCellValueFactory(new PropertyValueFactory<>("hospital"));//same as bean property
    	hospital.setMinWidth(80);
    	
    	TableColumn<IssuedBeans, String>reason=new TableColumn<IssuedBeans, String>("Reason");
    	reason.setCellValueFactory(new PropertyValueFactory<>("reason"));//same as bean property
    	reason.setMinWidth(80);
    	
    	TableColumn<IssuedBeans, Date>doi=new TableColumn<IssuedBeans, Date>("Date Of Issue");
    	doi.setCellValueFactory(new PropertyValueFactory<>("doi"));//same as bean property
    	doi.setMinWidth(80);
    	
    	TableColumn<IssuedBeans, String>bgroup=new TableColumn<IssuedBeans, String>("Blood Group");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(80);
    	
    	tblgrid.getColumns().addAll(nname,mobile,hospital,reason,doi,bgroup);
    	
    	tblgrid.setItems(null);
    }

    @FXML
    void doListAll(ActionEvent event) {
    	
    	getColumnns();
    	allRecords=getAllObjects();	
    	tblgrid.setItems(allRecords);
    	
    }

    @FXML
    void doShowinExcel(ActionEvent event)
    {
    	try
    	{
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		}
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    }
    
    public void writeExcel( ObservableList<IssuedBeans> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("BloodIssuedView.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="nname,mobile,hospital,reason,doi,bgroup\n";
            writer.write(text);
            for (IssuedBeans p : list)
            {
				text = p.getNname()+ "," + p.getMobile()+ "," + p.getHospital()+ "," + p.getReason()+"," + p.getDoi()+"," + p.getBgroup()+"\n";
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
    
    ObservableList<IssuedBeans> getAllObjects()
   	{
       	ObservableList<IssuedBeans> ary=FXCollections.observableArrayList();
       	
       	LocalDate local=datepckrdoi.getValue();
		java.sql.Date date=java.sql.Date.valueOf(local);
		
		String bg=combobgroup.getSelectionModel().getSelectedItem();
       	
   	    
       	PreparedStatement pst;
   	    try
   	    {
   	    	if(bg.equals("Select all"))
   	    	{
   	    		pst=con.prepareStatement("select * from issued where doi=?");
   	    		pst.setDate(1, date);
   	    		table=pst.executeQuery();
   	    	}
   	    	else
   	    	{
   	    		pst=con.prepareStatement("select * from issued where doi=? and bgroup=?");
   	   	    	pst.setDate(1, date);
   	   	    	pst.setString(2, bg);
   	   	    	table=pst.executeQuery();
   	   	    	
			}
   	    	
   	    	while(table.next())
   	    	{
   	    		String nname=table.getString("nname");
   	    		String mobile=table.getString("mobile");
   	    		String hospital=table.getString("hospital");
   	    		String reason=table.getString("reason");
   	    		Date doi=table.getDate("doi");
   	    		String bgroup=table.getString("bgroup");
   	    		
   	    		IssuedBeans obj=new IssuedBeans(nname,mobile,hospital,reason,doi,bgroup);
   	    		ary.addAll(obj);
     		    		
   	    		System.out.println(nname+"  "+mobile+"  "+hospital+" "+reason+" "+doi+" "+bgroup);
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
    void initialize() {
    	con=DatabaseConnection.doConnect();
    	combobgroup.getItems().setAll("Select all","opos","one","apos","ane","bpos","bne","abpos","abne");
    }

}
