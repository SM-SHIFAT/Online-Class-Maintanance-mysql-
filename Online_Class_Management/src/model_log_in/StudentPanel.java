package model_log_in;
import view_log_in.View_Log_In;
import controller_log_int.DBConnection;
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

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;

import controller_log_int.DBConnection;
import controller_log_int.Get_Value;
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
import java.util.Calendar;

public class StudentPanel extends JFrame implements ActionListener {

	    
	    Font font=new Font("Colabary",Font.PLAIN,20);
	    private JTextField semText=new JTextField();
	    private JTextField submitText=new JTextField();
	    private JTextField passText=new JPasswordField();
	    private	JLabel semLabel=new JLabel();
	    private JLabel idLabel=new JLabel();
	    public static String classname=null;
	    public static String sem=null;
		JTable table;
	    //private static String[][] zdata = {{"0","0","0","0"},{"0","0","0","0"}};
	    
	    public static View_Log_In viewInfoObj;
	    public static String id;//= View_Log_In.uid;
	    
	    private JTextField cclassText =new JTextField();
    	private JTextField acnameText =new JTextField();
    	private JTextField idText =new JTextField();
    	private JTextField emailText =new JTextField();
	    
    	public void setInfo()
    	{
    		try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ocm","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from login where id='"+id+"'";
				System.out.println(id);
				ResultSet rs=stmt.executeQuery(sql);
				
				if(rs.next())
					{
					
					acnameText.setText(rs.getString(1));
					idText.setText(rs.getString(2));
					passText.setText(rs.getString(3));
					semText.setText(rs.getString(4));
					emailText.setText(rs.getString(5));
					sem= rs.getString(5);
					}
					
					
				else
					JOptionPane.showMessageDialog(null, "error");
				con.close();
			}catch (Exception y) {JOptionPane.showMessageDialog(null, "this massage showing");}
    		
    	}
    	
    	
    	public void viewTable()
    	{ 
    		String[][] data = {{" "," "}};

    		String col[]={"Class Link","Date/Time"};

    		table=new JTable(data, col);
            JScrollPane scrol=new JScrollPane();
            scrol.setSize(500,235);
            table.setRowHeight(23);
            scrol.setBounds(425, 240, 553, 330);
            //table.setBounds(500, 100, 500, 100);
            table.setFont(new Font("Tahoma",Font.PLAIN,17));
            scrol.setViewportView(table);
            View_Log_In.viewLabel.add(scrol);
            updatet();
    	}

	    
	    public void viewButton()
	    {
	    	JButton refreshButton =new JButton();
	    	refreshButton.setText("Refresh");
	    	refreshButton.setFont(new Font("Colabary",Font.PLAIN,15));
	    	refreshButton.setBounds(880,205,95,30);
	    	refreshButton.setBackground(new Color(77,183,168));
	    	refreshButton.setForeground(new Color(255,255,255));
	    	refreshButton.setBorder(null);
	        View_Log_In.viewLabel.add(refreshButton);
	        refreshButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		updatet();
	        		DBConnection.deleteClasslink();
	        	}});
	    	
	    	JButton submitButton =new JButton();
	    	submitButton.setText("Submit");
	    	submitButton.setFont(new Font("Colabary",Font.BOLD,18));
	    	submitButton.setBounds(880,100,95,90);
	    	submitButton.setBackground(new Color(77,183,168));
	    	submitButton.setForeground(new Color(255,255,255));
	    	submitButton.setBorder(null);
	        View_Log_In.viewLabel.add(submitButton);
	        submitButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if(submitText.getText().isEmpty()==true)
	        			JOptionPane.showMessageDialog(null, "Class Code can't be blank.");
	        		else {
		            try {
		            	String nid = id;
		            	String nip = DBConnection.getIp();
		            	String nsem = sem;
		            	//System.out.println("Sem212="+ sem);
		            	String ndate = getDate();
		            	String nclassname = submitText.getText().toString();
						Class.forName("com.mysql.jdbc.Driver");
						
						//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ocm","root","");
						Statement stmt=con.createStatement();
						String sql="INSERT INTO `"+nclassname+"`(`id`, `ip`, `sem`, `date`) VALUES ('"+nid+"','"+nip+"','"+nsem+"','"+ndate+"')";
						
						int n =stmt.executeUpdate(sql);
						//System.out.println(n);
						if(n==0)
							{
							JOptionPane.showMessageDialog(null, "Already Submitted.");
							}
						else {
							JOptionPane.showMessageDialog(null, "Succesfully Submitted.");

						}
						con.close();
					}catch (Exception y) {System.out.print("Submit button: "+y);
					JOptionPane.showMessageDialog(null, y);}}}});

	    }
	    public void viewTex()
	    {
	    	
	    	cclassText.setFont(font);
	    	cclassText.setText(id);
	    	cclassText.setEditable(false);
	    	cclassText.setForeground(new Color(50,50,50));
	    	cclassText.setBackground(new Color(232, 248, 245));
	    	cclassText.setBounds(520,100,350,40);
	    	cclassText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(cclassText);
	        
	    	    
	        acnameText.setFont(font);
	        acnameText.setText("");
	        acnameText.setForeground(new Color(50,50,50));
	        acnameText.setBounds(105,150,255,30);
	        acnameText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(acnameText);
	        
	        idText.setFont(font);
	        idText.setText("");
	        idText.setEditable(false);
	        idText.setForeground(new Color(50,50,50));
	        idText.setBounds(75,200,285,30);
	        idText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(idText);
	        
	        passText.setFont(font);
	        passText.setText("");
	        passText.setForeground(new Color(50,50,50));
	        passText.setBounds(140,250,220,30);
	        passText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(passText);

	        emailText.setFont(font);
	        emailText.setText("");
	        emailText.setForeground(new Color(50,50,50));
	        emailText.setBounds(140,300,220,30);
	        emailText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(emailText);
	        
	        semText.setFont(font);
	        semText.setText("");
	        semText.setForeground(new Color(50,50,50));
	        semText.setBounds(100,350,260,30);
	        semText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(semText);
	        
	        submitText.setFont(font);
	        submitText.setText("");
	        submitText.setToolTipText("Ask your class Teacher for the Class code");
	        submitText.setEditable(true);
	        submitText.setForeground(new Color(50,50,50));
	        submitText.setBackground(new Color(232, 248, 245));
	        submitText.setBounds(520,150,350,40);
	        submitText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(submitText);
	        
	        
	    }
	    		    
	    
	    
	    public void viewLabel()
	    {
	        
	        //JLabel userLabel=new JLabel();
	    	
	        
	        JLabel nameLabel= new JLabel();
	        JLabel titleLabel=new JLabel();
	        JLabel cclassLabel =new JLabel();
	        
	        JLabel acnameLabel=new JLabel();
	        JLabel passLabel=new JLabel();
	        JLabel emailLabel=new JLabel();
	        JLabel profileLabel=new JLabel();
	        JLabel idnameLabel=new JLabel();
	        JLabel classLabel=new JLabel();
	        
	        idnameLabel.setText("ID: ");
	        idnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
	        idnameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        idnameLabel.setBounds(425,100,95,50);
	        View_Log_In.viewLabel.add(idnameLabel);
	        
	        classLabel.setText("Class Code: ");
	        classLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
	        classLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        classLabel.setBounds(425,150,95,50);
	        View_Log_In.viewLabel.add(classLabel);
	        		
	        cclassLabel.setText("Give your attendance: ");
	        cclassLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
	        cclassLabel.setBounds(520, 54, 310, 50);
	        cclassLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(cclassLabel);
	        
	        JSeparator separator3 = new JSeparator();
			separator3.setBounds(398, 200, 597, 1);
			separator3.setForeground(new Color(0, 128, 128));
			//separator2.setBackground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator3);
	        
	        titleLabel.setText("Welcome to");
	        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        titleLabel.setBounds(145, 30, 500, 50);
	        titleLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(titleLabel);
	        
	        //nameLabel.setForeground(Color.BLACK);
	        //nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        //nameLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
	        nameLabel.setText("Online Class Maintenance System");
	        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        nameLabel.setBounds(50, 55, 310, 50);
	        nameLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(nameLabel);
	        
	        JSeparator separator = new JSeparator();
			separator.setBounds(26, 100, 350, 1);
			separator.setForeground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator);
			
			profileLabel.setText("Profile ");
			profileLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
			profileLabel.setBounds(161, 100, 100, 50);
			profileLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(profileLabel);
	        
	        
	        acnameLabel.setText("Name: ");
	        acnameLabel.setFont(font);
	        acnameLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        acnameLabel.setBounds(40,140,100,50);
	        View_Log_In.viewLabel.add(acnameLabel);
	        
	        idLabel.setText("ID: ");
	        idLabel.setFont(font);
	        idLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        idLabel.setBounds(40,190,34,50);
	        View_Log_In.viewLabel.add(idLabel);
	        
	        passLabel.setText("Password: ");
	        passLabel.setFont(font);
	        passLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        passLabel.setBounds(40,240,100,50);
	        View_Log_In.viewLabel.add(passLabel);
	        
	        emailLabel.setText("Semester: ");
	        emailLabel.setFont(font);
	        emailLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        emailLabel.setBounds(40,290,100,50);
	        View_Log_In.viewLabel.add(emailLabel);
	        
	        semLabel.setText("Email: ");
	        semLabel.setFont(font);
	        semLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        semLabel.setBounds(40,340,100,50);
	        View_Log_In.viewLabel.add(semLabel);
	        
	        JSeparator separator2 = new JSeparator();
			separator2.setBounds(26, 400, 350, 1);
			separator2.setForeground(new Color(0, 128, 128));
			//separator2.setBackground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator2);
	        
	    }
	    
	    public void viewFrame()
	    {
	        viewLabel();
	        viewTex();
	        viewButton();
	        setInfo();
	        viewTable();
	        
	        Image image=null;
	        ImageIcon icon=new ImageIcon(getClass().getResource("/Image/icon.png"));
	        image=icon.getImage();
	        icon.setImage(image.getScaledInstance(1100,700,Image.SCALE_SMOOTH));
	        View_Log_In.viewLabel.setIcon(icon);
	        
	        JLabel bgLabel = new JLabel("");
			bgLabel.setIcon(new ImageIcon(View_Log_In.class.getResource("/Image/infopanel.png")));
			bgLabel.setBounds(10, 10, 1003, 600);
			//lblNewLabel.setIcon();
			View_Log_In.viewLabel.add(bgLabel);

	    }
	    public String getDate() {
	    	Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("dd-m-yyyy+hh.mm.ss");  
            String strDate = dateFormat.format(date);  
            System.out.println(strDate);
	    	
	    	return strDate;
	    }
	    
	    public void updatet()
	    {
    		try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ocm","root","");
				Statement stmt=con.createStatement();
				String sql="SELECT * FROM `classlink`";
				ResultSet rs = stmt.executeQuery(sql);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				System.out.println("table updated");
				con.close();
    		}
    		catch(Exception t) {System.out.println("table:"+t);}
	    }

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
		}

}
	        
	    

