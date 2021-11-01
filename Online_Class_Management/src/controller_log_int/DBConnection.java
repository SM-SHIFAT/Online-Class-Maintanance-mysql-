package controller_log_int;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DBConnection
{

    private static Connection connection = null;// host, user,pass
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static String uid;

    public static void getconnection()
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
    public static int getSize(String classname)
    {
    	
        int num = 0;
        try
        {
            statement = (Statement) connection.createStatement();
            ResultSet count = statement.executeQuery("SELECT count(id)as count FROM `"+classname+"`");

            while (count.next())
            {
                num = Integer.parseInt(count.getString(1));
                //System.out.println(num);
            }
        }
        catch (Exception e)
        {
        }
        return num;
    }

    public static int getSize()
    {
    	String id=uid;
        int num = 0;
        try
        {
            statement = (Statement) connection.createStatement();
            ResultSet count = statement.executeQuery("SELECT count(`id`)as count FROM `classlist` WHERE id = '"+id+"'");

            while (count.next())
            {
                num = Integer.parseInt(count.getString(1));
                
            }
        }
        catch (Exception e)
        {
        }
        System.out.println("num= "+num);
        return num;
    }
    
    public static ResultSet updatet1(String classname)
    {
    	getconnection();
    	try {
    		//String sql="SELECT * FROM `"+cname+"`";
    		statement = (Statement) connection.createStatement();
    		resultSet  = statement.executeQuery("SELECT * FROM `"+classname+"`");
            //System.out.println("Resultset: "+resultSet);
    	}catch(Exception tt){ System.out.println("updatet1 function: "+tt);
    		
    	}
    	
    	
    	return resultSet;
    }

    public static String[][] getInfo(String classname)
    {
    	getconnection();
        int size=getSize(classname);
        //System.out.println(size);
        String data[][] = new String[size][4];
        try
        {
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `"+classname+"`");
            
            int i = 0;
            while (resultSet.next())
            {
                data[i][0] = resultSet.getString(1);
                data[i][1] = resultSet.getString(2);
                data[i][2] = resultSet.getString(3);
                data[i][3] = resultSet.getString(4);
                //System.out.println(data[i][4]);
                i++;
            }

        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        return data;
    }
    
    public static String[][] getClassInfo(String id)
    {
    	uid=id;
    	getconnection();
    	int size=getSize();
    	
        String data[][] = new String[size][2];
        try
        {
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `classlist` WHERE id='"+id+"'");
            
            int i = 0;
            while (resultSet.next())
            {
                data[i][0] = resultSet.getString(2);
                data[i][1] = resultSet.getString(3);

                //System.out.println(data[i][4]);
                i++;
            }

        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        return data;
    }
    
    public static void deleteData(String uid) {
    	getconnection();
    	try {
    	statement = (Statement) connection.createStatement();
        statement.execute("DELETE FROM `login` WHERE id='"+uid+"'");
    	}
    	catch(Exception dy) {
    		System.out.println("delete function:"+dy);
    	}
    	
    }
    
    public static void insertId(String classname) {
    	getconnection();
    	try {
    	statement = (Statement) connection.createStatement();
        statement.execute("DELETE FROM `login` WHERE id='"+classname+"'");
    	}
    	catch(Exception dy) {
    		System.out.println("delete function:"+dy);
    	}
    	
    }
    public static String getIp() {
    	String ip = null;
    	try {
	    				InetAddress myIP=InetAddress.getLocalHost();
	    			      //System.out.println("My IP Address is:");
	    			      ip = myIP.getHostAddress().toString();
	    				}
	    				catch(Exception y) {}
    	return ip;
    }
    
    public static void deleteClasslink() {
    	getconnection();
    	String date = getD();
    	System.out.println("d:"+date);
    	try {
    	statement = (Statement) connection.createStatement();
        statement.execute("DELETE FROM `classlink` WHERE date != '"+date+"'");
    	}
    	catch(Exception dy) {
    		System.out.println("delete function:"+dy);
    	}
    }
    public static String getD() {
    	Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd");  
        String strDate = dateFormat.format(date);  
    	
    	return strDate;
    }
}
