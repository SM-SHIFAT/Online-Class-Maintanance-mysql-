package controller_log_int;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class aDB {
    private static Connection connection = null;// host, user,pass
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    
abstract class connect {

	abstract void connect();
	
	public void getCon()
	{
		String url = "jdbc:mysql://localhost:3306/ocm";
        try
        {
            connection = (Connection) DriverManager.getConnection(url, "root", "");

        }
        catch (Exception e)
        {
            System.err.println(e);
        }
	}
}


public class gconnection extends connect
{
	public void connect()
	{
		try {
			statement = (Statement) connection.createStatement();
		}
		catch(Exception rt) {
			
		}
	}
	
	
}

public class addD extends gconnection
{
	public void connect()
	{
		
	}
}

public class deleteD extends gconnection
{
	public void connect()
	{
		
	}
}

public class updateD extends gconnection
{
	public void connect()
	{
		
	}
}


}