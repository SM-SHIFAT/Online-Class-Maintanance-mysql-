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

public class TeacherPanel extends JFrame implements ActionListener {

	    
	    Font font=new Font("Colabary",Font.PLAIN,20);
	    private JTextField userText=new JTextField();
	    private JTextField submitText=new JTextField();
	    private JTextField passText=new JPasswordField();
	    private	JLabel userLabel=new JLabel();
	    private JLabel phoneLabel=new JLabel();
	    private JButton submitButton=new JButton();
	    public static String classname=null;
	    private static String[][] data;
	    private static String[][] zdata = {{" "," "," "," "},{" "," "," "," "}};
	    private static JTable classtable;
	    private static JTable table;
	    JScrollPane cscrol=new JScrollPane();
	    
	    public static View_Log_In viewInfoObj;
	    public static String id;//= View_Log_In.uid;
	    
	    private JTextField cclassText =new JTextField();
	    private JTextField cattText =new JTextField();
    	private JTextField acnameText =new JTextField();
    	private JTextField phoneText =new JTextField();
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
					phoneText.setText(rs.getString(2));
					passText.setText(rs.getString(3));
					emailText.setText(rs.getString(4));
					
					}
					
					
				else
					JOptionPane.showMessageDialog(null, "error");
				con.close();
			}catch (Exception y) {JOptionPane.showMessageDialog(null, "this massage showing");}
    		
    	}
    	
    	public String classNamegen()
    	{
    		Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("ddMMMMyyyyhhmmss");  
            String classname = dateFormat.format(date);  
            System.out.println(classname);
            return classname;
    	}
    	
    	public void viewTable()
    	{ 
    		//table.removeAll();
    		//table.revalidate();
    		//table.repaint();
    		//if(cattText.getText().isEmpty())
    			data = zdata; //DBConnection.getInfo("0");
    		//else
    		//data = DBConnection.getInfo(cattText.getText().toString());

    		String col[]={"Id","Ip","sem","Date"};

    		table=new JTable(data, col);
    		
			/*
			 * table.setColumnSelectionAllowed(true); table.setEnabled(true);
			 * table.setEditingColumn(0);
			 */
            JScrollPane scrol=new JScrollPane();
            //scrol.setSize(500,235);
            scrol.setBounds(425, 200, 553, 235);
            //table.setBounds(500, 100, 500, 100);
            table.setFont(new Font("Tahoma",Font.PLAIN,15));
            table.setRowHeight(23);
            scrol.setViewportView(table);
            View_Log_In.viewLabel.add(scrol);

    	
            String data2[][] = DBConnection.getClassInfo(id);
            String col2[]={"ClassName","Status"};

    		classtable= new JTable(data2, col2);
    		
			/*
			 * table.setColumnSelectionAllowed(true); table.setEnabled(true);
			 * table.setEditingColumn(0);
			 */
            
            //cscrol.setSize(450,235);
            cscrol.setBounds(45, 390, 310, 185);
            //table.setBounds(500, 100, 500, 100);
            classtable.setFont(new Font("Tahoma",Font.PLAIN,17));
            classtable.setRowHeight(23);
            cscrol.setViewportView(classtable);
            View_Log_In.viewLabel.add(cscrol);
    	}

	    
	    public void viewButton()
	    {
	    	JButton cattText =new JButton();
	    	cattText.setText("search");
	    	cattText.setFont(new Font("Colabary",Font.PLAIN,17));
	    	cattText.setBounds(850,155,70,40);
	    	cattText.setBackground(new Color(77,183,168));
	    	cattText.setForeground(new Color(255,255,255));
	    	cattText.setBorder(null);
	        View_Log_In.viewLabel.add(cattText);
	        cattText.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						updatet();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
	        	}});
	    	
	    	JButton cclassButton =new JButton();
	    	cclassButton.setText("Create a Class");
	    	cclassButton.setFont(new Font("Colabary",Font.PLAIN,15));
	    	cclassButton.setBounds(689,65,150,30);
	    	cclassButton.setBackground(new Color(77,183,168));
	    	cclassButton.setForeground(new Color(255,255,255));
	    	cclassButton.setBorder(null);
	        View_Log_In.viewLabel.add(cclassButton);
	        cclassButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		classname = classNamegen();
	        		try {
	    				Class.forName("com.mysql.jdbc.Driver");
	    				
	    				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
	    				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ocm","root","");
	    				Statement stmt=con.createStatement();
	    				String sql="CREATE TABLE `ocm`.`"+classname+"` ( `id` VARCHAR(30) NOT NULL , `ip` VARCHAR(30) NOT NULL , `sem` VARCHAR(2) NOT NULL , `date` VARCHAR(30) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
	    				stmt.execute(sql);
	    				sql="INSERT INTO `classlist` (`id`, `classname`, `status`) VALUES ('"+id+"', '"+classname+"', '0')";
	    				stmt.executeUpdate(sql);
	    				stmt.close();
	    				System.out.println("table created");
	    				con.close();
	        		}
	        		catch(Exception t) {System.out.println(t);}
	        		
	        		cclassText.setText(classname);
	        	}
	        }
	        );
	    	
	    	JButton copyButton =new JButton();
	    	copyButton.setText("copy");
	    	copyButton.setFont(new Font("Colabary",Font.PLAIN,15));
	    	copyButton.setBounds(850,100,70,40);
	    	copyButton.setBackground(new Color(77,183,168));
	    	copyButton.setForeground(new Color(255,255,255));
	    	copyButton.setBorder(null);
	        View_Log_In.viewLabel.add(copyButton);
	        copyButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		cclassText.selectAll();
	        		cclassText.copy();
	        		
	        		//classtable.removeAll();
	        		//classtable.revalidate();
	        		//classtable.repaint();
	        		//classtable.setModel(DbUtils.resultSetToTableModel(rs));
					/*
					 * classtable.revalidate(); cscrol.revalidate(); classtable.repaint();
					 * cscrol.repaint();
					 */
	        		//classtable();//getText()
	        		try {
						updatet2();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

	        	}
	        }
	        );
	        
	        submitButton.setText("Submit");
	        submitButton.setFont(new Font("Colabary",Font.PLAIN,17));
	        submitButton.setBounds(850,500,70,40);
	        submitButton.setBackground(new Color(77,183,168));
	        submitButton.setForeground(new Color(255,255,255));
	        submitButton.setBorder(null);
	        View_Log_In.viewLabel.add(submitButton);
	        submitButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
		            try {
		            	String link = submitText.getText().toString();
		            	String date = getDate2();
						Class.forName("com.mysql.jdbc.Driver");
						
						//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ocm","root","");
						Statement stmt=con.createStatement();
						String sql="INSERT INTO `classlink` (`link`, `date`) VALUES ('"+link+"', '"+date+"')";
						
						int n =stmt.executeUpdate(sql);
						//System.out.println(n);
						if(n==0)
							{
							JOptionPane.showMessageDialog(null, "Already exist.");
							}
						else {
							JOptionPane.showMessageDialog(null, "Class link is shared");

						}
						con.close();
					}catch (Exception y) {System.out.print(y);
					JOptionPane.showMessageDialog(null, y);
					
					}

	        	}
	        }
	        );
	        

	        
			/*
			 * JButton regButton =new JButton(); regButton.setText("Log in");
			 * regButton.setFont(new Font("Tahoma", Font.BOLD, 28));
			 * regButton.setBorder(null); regButton.setForeground(new Color(255,255,255));
			 * regButton.setBackground(new Color(77,183,168));
			 * regButton.setBounds(29,505,310,75); //submitButton.setBorder(border);
			 * View_Log_In.viewLabel.add(regButton); regButton.addMouseListener(new
			 * MouseAdapter() {
			 * 
			 * @Override public void mouseEntered(MouseEvent arg0) {
			 * regButton.setForeground(new Color(200,200,200)); }
			 * 
			 * @Override public void mouseExited(MouseEvent e) { regButton.setForeground(new
			 * Color(255,255,255)); } }); regButton.addActionListener(new ActionListener() {
			 * public void actionPerformed(ActionEvent e) {
			 * 
			 * } } );
			 */
	        
	    }
	    public void viewTex()
	    {
	    	
	    	cclassText.setFont(font);
	    	cclassText.setText("");
	    	cclassText.setEditable(false);
	    	cclassText.setForeground(new Color(50,50,50));
	    	cclassText.setBackground(new Color(232, 248, 245));
	    	cclassText.setBounds(440,100,400,40);
	    	cclassText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(cclassText);
	        
	        cattText.setFont(font);
	        cattText.setText("");
	        cattText.setEditable(true);
	        cattText.setForeground(new Color(50,50,50));
	        cattText.setBackground(new Color(232, 248, 245));
	        cattText.setBounds(440,155,400,40);
	        cattText.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(cattText);
	    	    
	        acnameText.setFont(font);
	        acnameText.setText("");
	        acnameText.setForeground(new Color(50,50,50));
	        acnameText.setBounds(105,150,255,30);
	        acnameText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(acnameText);
	        
	        phoneText.setFont(font);
	        phoneText.setText("");
	        phoneText.setEditable(false);
	        phoneText.setForeground(new Color(50,50,50));
	        phoneText.setBounds(110,200,250,30);
	        phoneText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(phoneText);
	        
	        passText.setFont(font);
	        passText.setText("");
	        passText.setForeground(new Color(50,50,50));
	        passText.setBounds(140,250,220,30);
	        passText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(passText);

	        emailText.setFont(font);
	        emailText.setText("");
	        emailText.setForeground(new Color(50,50,50));
	        emailText.setBounds(110,300,250,30);
	        emailText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(emailText);
	        
	        submitText.setFont(font);
	        submitText.setText("   Submit your class link here...");
	        submitText.setEditable(true);
	        submitText.setForeground(new Color(133, 146, 158 ));
	        submitText.setBackground(new Color(232, 248, 245));
	        submitText.setBounds(440,500,400,40);
	        submitText.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(submitText);
	        submitText.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					submitText.setForeground(new Color(133, 146, 158));
				}
				@Override
				public void focusGained(FocusEvent e) {
					submitText.setForeground(new Color(80, 80, 80));
					if(submitText.getText().toString().compareTo("   Submit your class link here...")==0)
						submitText.setText(null);
					}
			});
	        
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
	        		
	        cclassLabel.setText("Create new class link: ");
	        cclassLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
	        cclassLabel.setBounds(445, 54, 310, 50);
	        cclassLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(cclassLabel);
	        
	        JSeparator separator3 = new JSeparator();
			separator3.setBounds(398, 150, 597, 1);
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
	        
	        phoneLabel.setText("Phone: ");
	        phoneLabel.setFont(font);
	        phoneLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        phoneLabel.setBounds(40,190,70,50);
	        View_Log_In.viewLabel.add(phoneLabel);
	        
	        passLabel.setText("Password: ");
	        passLabel.setFont(font);
	        passLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        passLabel.setBounds(40,240,100,50);
	        View_Log_In.viewLabel.add(passLabel);
	        
	        emailLabel.setText("Email: ");
	        emailLabel.setFont(font);
	        emailLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        emailLabel.setBounds(40,290,100,50);
	        View_Log_In.viewLabel.add(emailLabel);
	        
	        JSeparator separator2 = new JSeparator();
			separator2.setBounds(26, 360, 350, 1);
			separator2.setForeground(new Color(0, 128, 128));
			//separator2.setBackground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator2);
			
			JSeparator separator4 = new JSeparator();
			separator4.setBounds(398, 450, 597, 1);
			separator4.setForeground(new Color(0, 128, 128));
			//separator2.setBackground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator4);
	        
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
	    public String getDate2() {
	    	Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("dd");  
            String strDate = dateFormat.format(date);  
            System.out.println(strDate);
	    	
	    	return strDate;
	    }
	    public void updatet2() throws SQLException
	    {
    		try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ocm","root","");
				Statement stmt=con.createStatement();
				String sql="SELECT `classname`, `id` FROM `classlist` WHERE id = '"+id+"'";
				ResultSet rs = stmt.executeQuery(sql);
				classtable.setModel(DbUtils.resultSetToTableModel(rs));
				System.out.println("table2 updated");
				con.close();
    		}
    		catch(Exception t) {System.out.println("t2:"+t);}
	    }
	    
	    public void updatet() throws SQLException
	    {
	    	ResultSet rs2=null;
	    	String cname= cattText.getText().toString();
	    	System.out.println(cname);
    		try {
				
				//String sql="SELECT * FROM `13november2020121219`";
				//String sql="SELECT * FROM `"+cname+"`";
				rs2 = DBConnection.updatet1(cname);
				System.out.println("table1 updated" + rs2);
				table.setModel(DbUtils.resultSetToTableModel(rs2));
				
				
    		}
    		catch(Exception t) {System.out.println("t1:"+t);}
	    }

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
		}

}
	        
	    

