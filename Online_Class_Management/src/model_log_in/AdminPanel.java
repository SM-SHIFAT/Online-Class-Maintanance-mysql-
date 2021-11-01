package model_log_in;
import view_log_in.View_Log_In;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import controller_log_int.DBConnection;
import net.proteanit.sql.DbUtils;

import static main_log_in.Log_In.viewObj;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminPanel extends JFrame implements ActionListener {

	    
	    Font font=new Font("Colabary",Font.PLAIN,20);
	    private JTextField userText=new JTextField();
	    private JButton submitButton=new JButton();
	    public static String id;
	    JTable classtable;
	    JScrollPane cscrol=new JScrollPane();
	    public static View_Log_In viewInfoObj;
	    
	    
	    public void viewButton()
	    {  
	        JButton regButton =new JButton();
	        regButton.setText("Delete");
	        regButton.setFont(new Font("Tahoma", Font.BOLD, 28));
	        regButton.setBorder(null);
	        regButton.setForeground(new Color(255,255,255));
	        regButton.setBackground(new Color(231, 76, 60 ));
	        regButton.setBounds(29,345,310,75);
	        //submitButton.setBorder(border);
	        View_Log_In.viewLabel.add(regButton);
	        regButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					regButton.setForeground(new Color(200,200,200));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					regButton.setForeground(new Color(255,255,255));
				}
			});
	        regButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		DBConnection.deleteData(userText.getText().toString());
	        		updatet();

	        	}
	        }
	        );
	        
	    }
	    
	    public void viewTable()
	    {
            String data2[][] = {{"0","0","0","0","0"}};
            String col2[]={"Name","ID","Email","Semester","type"};

    		classtable = new JTable(data2, col2);
            
            //cscrol.setSize(450,235);
            cscrol.setBounds(400, 25, 598, 570);
            //table.setBounds(500, 100, 500, 100);
            classtable.setFont(new Font("Tahoma",Font.PLAIN,17));
            classtable.setRowHeight(23);
            cscrol.setViewportView(classtable);
            View_Log_In.viewLabel.add(cscrol);
            updatet();
	    }
	    
	    public void viewTex()
	    {
 
	        userText.setFont(font);
	        userText.setText("");
	        userText.setForeground(new Color(50,50,50));
	        userText.setBounds(29,200,310,40);
	        userText.setToolTipText("insert an id that you want to delete");
	        userText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(userText);

	    }

	    
	    public void viewLabel()
	    {
	        
	        
	    }
	    
	    public void viewFrame()
	    {
	        viewLabel();
	        viewTex();
	        viewButton();
	        viewTable();
	        
	        Image image=null;
	        ImageIcon icon=new ImageIcon(getClass().getResource("/Image/icon.png"));
	        image=icon.getImage();
	        icon.setImage(image.getScaledInstance(1100,700,Image.SCALE_SMOOTH));
	        View_Log_In.viewLabel.setIcon(icon);
	        
	        JLabel bgLabel = new JLabel("");
			bgLabel.setIcon(new ImageIcon(View_Log_In.class.getResource("/Image/whitebg.png")));
			bgLabel.setBounds(10, 10, 1003, 600);
			//lblNewLabel.setIcon();
			View_Log_In.viewLabel.add(bgLabel);

	    }
	    public void updatet()
	    {
    		try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ocm","root","");
				Statement stmt=con.createStatement();
				String sql="SELECT `name`, `id`, `email`, `semester`, `type` FROM `login`";
				ResultSet rs = stmt.executeQuery(sql);
				classtable.setModel(DbUtils.resultSetToTableModel(rs));
				
				System.out.println("table updated");
				con.close();
    		}
    		catch(Exception t) {System.out.println("t:"+t);}
	    }
	     
	    

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	   
	        if(e.getActionCommand()=="Submit")
	        {
	        }
	    }
	    
	}
