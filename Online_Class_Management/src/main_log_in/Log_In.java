package main_log_in;

import controller_log_int.DBConnection;
import view_log_in.View_Log_In;


public class Log_In
{
    public static View_Log_In viewObj;

    
    public static void main(String[] args)
    {
    	DBConnection.deleteClasslink();
        viewObj=new View_Log_In();
        viewObj.viewFrame();
        
    	
        
        
        
        //while(true)
        	//{      
        	   //System.out.println("working");

        	//}

        
    }
    
}
