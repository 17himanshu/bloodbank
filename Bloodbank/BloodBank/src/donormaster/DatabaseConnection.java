package donormaster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection 
{
	public static Connection doConnect()
	{
		Connection con=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/bloodbank","root","");
			System.out.println("Connected to Database Successfuly......");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args)
	{
		doConnect();
	}
	
}
