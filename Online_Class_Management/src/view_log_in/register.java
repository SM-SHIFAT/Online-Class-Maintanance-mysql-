
package view_log_in;

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

import model_log_in.TeacherPanel;

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
import java.sql.Statement;

	public class register extends JFrame implements ActionListener
	{
	    
	    Font font=new Font("Colabary",Font.PLAIN,20);
	    private JTextField userText=new JTextField();
	    private JTextField passText=new JPasswordField();
	    private	JLabel userLabel=new JLabel();
	    private JLabel passLabel=new JLabel();
	    private JLabel semLabel=new JLabel();
	    private JButton submitButton=new JButton();
	    private JComboBox semText = new JComboBox();
	    private JTextField acnameText =new JTextField();
	    private JTextField cpassText =new JPasswordField();
	    private JTextField emailText =new JTextField();
	    private static String type=null;
	    int tc=0;
	    public static View_Log_In viewInfoObj;
	    //public static TeacherPanel tpObj;
	    
	    JRadioButton r1=new JRadioButton("Student");  
    	JRadioButton r2=new JRadioButton("Teacher"); 
	    
	    
	    public void viewButton()
	    {
	        
	        submitButton.setText("Submit");
	        submitButton.setFont(font);
	        submitButton.setBounds(625,520,150,40);
	        submitButton.setBackground(new Color(77,183,168));
	        submitButton.setForeground(new Color(255,255,255));
	        //submitButton.setBorder(border);
	        View_Log_In.viewLabel.add(submitButton);
	        submitButton.addActionListener(this);
	        
	        JButton regButton =new JButton();
	        regButton.setText("Log in");
	        regButton.setFont(new Font("Tahoma", Font.BOLD, 28));
	        regButton.setBorder(null);
	        regButton.setForeground(new Color(255,255,255));
	        regButton.setBackground(new Color(77,183,168));
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
					System.out.println("Register page");
					
					View_Log_In.viewLabel.removeAll();
					View_Log_In.viewLabel.revalidate();
					View_Log_In.viewLabel.repaint();
	                
	                
					viewInfoObj=new View_Log_In();
					viewInfoObj.viewLabel();
					viewInfoObj.viewTex();
					viewInfoObj.viewButton();
					viewInfoObj.viewImage();
	        	}
	        }
	        );
	        
	    }
	    public void viewTex()
	    {
	    	
	    	
	    	
	        
	        userText.setFont(font);
	        userText.setText("");
	        userText.setForeground(new Color(50,50,50));
	        userText.setBounds(640,200,200,30);
	        userText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(userText);
	        
	        acnameText.setFont(font);
	        acnameText.setText("");
	        acnameText.setForeground(new Color(50,50,50));
	        acnameText.setBounds(640,250,200,30);
	        acnameText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(acnameText);
	        
	        passText.setFont(font);
	        passText.setText("");
	        passText.setForeground(new Color(50,50,50));
	        passText.setBounds(640,300,200,30);
	        passText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(passText);
	        
	        cpassText.setFont(font);
	        cpassText.setText("");
	        cpassText.setForeground(new Color(50,50,50));
	        cpassText.setBounds(640,350,200,30);
	        cpassText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(cpassText);
	        cpassText.addFocusListener(null);
	        
	        emailText.setFont(font);
	        emailText.setText("");
	        emailText.setForeground(new Color(50,50,50));
	        emailText.setBounds(640,400,200,30);
	        emailText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(emailText);
	        
	        
	        semText.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8"}));
	        semText.setBounds(640, 450, 200, 30);
	        View_Log_In.viewLabel.add(semText);
	        
	        emailText.addFocusListener(new FocusAdapter() {
	        	@Override
				public void focusLost(FocusEvent arg0) {
	        		//System.out.println(emailText.getText().indexOf('@'));
	        		if(!emailText.getText().isEmpty())
					if(emailText.getText().toString().indexOf('@')<0)
					{  // if(emailText.getText().toString().indexOf('.')<0)
						JOptionPane.showMessageDialog(null,"Invalid Email Address");
					}

					}});
	        
	        passText.addFocusListener(new FocusAdapter() {
	        	@Override
				public void focusLost(FocusEvent arg0) {
					if(cpassText.getText().isEmpty() == true);

					else {
						if (passText.getText().toString().compareTo(cpassText.getText().toString()) != 0) {
							JOptionPane.showMessageDialog(null,"Password doesn't match!");
						}
					}
					}});
	        cpassText.addFocusListener(new FocusAdapter() {
	        	@Override
				public void focusLost(FocusEvent arg0) {
					if(passText.getText().isEmpty() == true);

					else {
						if (passText.getText().toString().compareTo(cpassText.getText().toString()) != 0) {
							JOptionPane.showMessageDialog(null,"Password doesn't match!");
						}
					}
					}});

	        
	    }
	    public void viewRadio()
	    {
	    	   
	    	r1.setBounds(640,140,90,20);
	    	r2.setBounds(730,140,90,20);
	    	r1.setBackground(new Color(255,255,255));
	    	r2.setBackground(new Color(255,255,255));
	    	r1.setFont(new Font("Helvetica", Font.BOLD, 15));
	    	r2.setFont(new Font("Helvetica", Font.BOLD, 15));
	    	ButtonGroup bg=new ButtonGroup();    
	    	bg.add(r1);bg.add(r2);    
	    	View_Log_In.viewLabel.add(r1);
	    	View_Log_In.viewLabel.add(r2);      
	    	//View_Log_In.viewLabel.setSize(300,300);    
	    	//View_Log_In.viewLabel.setLayout(null);    
	    	//View_Log_In.viewLabel.setVisible(true);
////////////////////////////////////////////
	    	r1.addActionListener(new ActionListener() {
	    		@Override
	    		public void actionPerformed(ActionEvent arg0) {

	    			//userLabel.setText("User Id:jghjgfhjfgj ");
	    			if(r1.isSelected()){    
	    				System.out.println("You are Student."); 
	    				userLabel.setText("User Id: ");
	    				semText.setVisible(true);
	    				semLabel.setVisible(true);
	    				submitButton.setBounds(625,520,150,40);
	    				type="s";
	    				tc=1;

	    			}
	    		}
	    	}); 
	    	r2.addActionListener(new ActionListener() {
	    		@Override
	    		public void actionPerformed(ActionEvent arg0) {

	    			//userLabel.setText("User Id:jghjgfhjfgj ");
	    			if(r2.isSelected()){    
	    				System.out.println("You are Student."); 
	    				userLabel.setText("Phone No.: ");
	    				semText.setVisible(false);
	    				semLabel.setVisible(false);
	    				submitButton.setBounds(625,460,150,40);
	    				type="t";
	    				tc=2;
	    				//if(r2.isSelected()){    
	    			    	//			System.out.println("You are Teacher.");
	    			    	//			////////////////userLabel.setText("Phone No.:");
	    			    	//			///////////////userText.setVisible(true);
	    			    	//			//////////////userText.setText("nammmmmmmmmmmmm");
	    			    	//			///////////////////////userText.setVisible(false);
	    			    	//			try {
	    			    	//			InetAddress myIP=InetAddress.getLocalHost();
	    			    	//		      System.out.println("My IP Address is:");
	    			    	//		      System.out.println(myIP.getHostAddress().toString());
	    			    	//			}
	    			    	//			catch(Exception y) {}
	    			//}
	    		}
	    		}
	    	
	    	});
	    	
	    	}
	    
	    
	    
	    public void viewLabel()
	    {
	        
	        //JLabel userLabel=new JLabel();
	        
	        JLabel nameLabel= new JLabel();
	        JLabel titleLabel=new JLabel();
	        JLabel sugLabel= new JLabel();
	        JLabel regasLabel =new JLabel();
	        
	        JLabel acnameLabel=new JLabel();
	        JLabel cpassLabel=new JLabel();
	        JLabel emailLabel=new JLabel();
	        
	        
	        titleLabel.setText("Welcome to");
	        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        titleLabel.setBounds(635, 40, 500, 50);
	        titleLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(titleLabel);
	        
	        //nameLabel.setForeground(Color.BLACK);
	        //nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        //nameLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
	        nameLabel.setText("Online Class Maintenance System");
	        nameLabel.setFont(new Font("Helvetica", Font.BOLD, 28));
	        nameLabel.setBounds(450, 70, 500, 50);
	        nameLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(nameLabel);
	        
	        JSeparator separator = new JSeparator();
			separator.setBounds(430, 120, 510, 1);
			separator.setForeground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator);
	        
	        
			regasLabel.setText("Register as: ");
			regasLabel.setFont(font);
			regasLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			regasLabel.setBounds(500,120,130,50);
	        View_Log_In.viewLabel.add(regasLabel);
	        
	        userLabel.setText("User Id: ");
	        userLabel.setFont(font);
	        userLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        userLabel.setBounds(520,190,110,50);
	        View_Log_In.viewLabel.add(userLabel);
	        
	        acnameLabel.setText("Name: ");
	        acnameLabel.setFont(font);
	        acnameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        acnameLabel.setBounds(530,240,100,50);
	        View_Log_In.viewLabel.add(acnameLabel);
	        
	        passLabel.setText("Password: ");
	        passLabel.setFont(font);
	        passLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        passLabel.setBounds(520,290,110,50);
	        View_Log_In.viewLabel.add(passLabel);
	        
	        cpassLabel.setText("Confirm Password: ");
	        cpassLabel.setFont(font);
	        cpassLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        cpassLabel.setBounds(450,340,180,50);
	        View_Log_In.viewLabel.add(cpassLabel);
	        
	        emailLabel.setText("Email: ");
	        emailLabel.setFont(font);
	        emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        emailLabel.setBounds(520,390,110,50);
	        View_Log_In.viewLabel.add(emailLabel);
	        
	        semLabel.setText("Semester: ");
	        semLabel.setFont(font);
	        semLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        semLabel.setBounds(520,440,110,50);
	        View_Log_In.viewLabel.add(semLabel);
	        
	        sugLabel.setText("Already have an account!");
	        sugLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        sugLabel.setForeground(new Color(255, 255, 255));
	        sugLabel.setBounds(100,300,200,20);
	        View_Log_In.viewLabel.add(sugLabel);
	        
	    }
	    
	    public void viewFrame()
	    {
	    	viewRadio();
	        viewLabel();
	        viewTex();
	        viewButton();
	        
	        Image image=null;
	        ImageIcon icon=new ImageIcon(getClass().getResource("/Image/icon.png"));
	        image=icon.getImage();
	        icon.setImage(image.getScaledInstance(1100,700,Image.SCALE_SMOOTH));
	        View_Log_In.viewLabel.setIcon(icon);
	        
	        JLabel bgLabel = new JLabel("");
			bgLabel.setIcon(new ImageIcon(View_Log_In.class.getResource("/Image/loginbg.png")));
			bgLabel.setBounds(10, 10, 1000, 600);
			//lblNewLabel.setIcon();
			View_Log_In.viewLabel.add(bgLabel);

	    }
	     
	    

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	
	    	   
	        if(e.getActionCommand()=="Submit")
	        {
	        	if(type.compareTo("t")==0)
	        		semText.setSelectedIndex(0);
	        	
	        	String name=acnameText.getText().toString();
	            String id=userText.getText().toString();
	            String pass=passText.getText().toString();
	            String email=emailText.getText().toString();
	            String sem=semText.getItemAt(semText.getSelectedIndex()).toString();  
	            //System.out.println(type.compareTo(null));
	            
	            if(type.compareTo("s")==0 && semText.getItemAt(semText.getSelectedIndex()).toString().compareTo("0")==0 )
	            	JOptionPane.showMessageDialog(this,"Fill all the box");
	            else {
	            if(userText.getText().isEmpty() == true || acnameText.getText().isEmpty() == true ||
	               passText.getText().isEmpty() == true || cpassText.getText().isEmpty() == true)
	            	JOptionPane.showMessageDialog(this,"Fill all the box");
	            else {
	            	if(emailText.getText().isEmpty() == true || tc== 0)
	            		JOptionPane.showMessageDialog(this,"Fill all the box");
	            	else {
	            try {
					Class.forName("com.mysql.jdbc.Driver");
					
					//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ocm","root","");
					Statement stmt=con.createStatement();
					String sql="INSERT INTO `login` (`name`, `id`, `password`, `email`, `semester`, `type`) VALUES ('"+name+"', '"+id+"', '"+pass+"', '"+email+"', '"+sem+"', '"+type+"')";
					
					int n =stmt.executeUpdate(sql);
					System.out.println(n);
					if(n==0)
						{
						JOptionPane.showMessageDialog(null, "Account already exist. Log-in instead.");
						}
					else {
						JOptionPane.showMessageDialog(null, "Welcome, "+name+" .Your account is successfully created");
							View_Log_In.viewLabel.removeAll();
							View_Log_In.viewLabel.revalidate();
							View_Log_In.viewLabel.repaint();
							
							viewInfoObj=new View_Log_In();
							viewInfoObj.viewLabel();
							viewInfoObj.viewTex();
							viewInfoObj.viewButton();
							viewInfoObj.viewImage();
					}
					con.close();
				}catch (Exception y) {System.out.print(y);
				JOptionPane.showMessageDialog(null, y);
				//JOptionPane.showMessageDialog(null, "Account already exist. Log-in instead.");
				}
	            
	            	}}}
	        }
	    }
	    
	}
