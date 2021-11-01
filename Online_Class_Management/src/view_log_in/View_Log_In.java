package view_log_in;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import controller_log_int.Get_Value;
import model_log_in.AdminPanel;
import model_log_in.StudentPanel;
import model_log_in.TeacherPanel;
import view_log_in.*;

public class View_Log_In extends JFrame implements ActionListener
{
	
    public static JLabel viewLabel=new JLabel();
    Font font=new Font("Colabary",Font.PLAIN,20);
    public static JTextField userText=new JTextField();
    public static JTextField passText=new JPasswordField();
    //view_info viewInfoObj=new view_info();
    public static TeacherPanel tpObj=new TeacherPanel();
    public static StudentPanel spObj=new StudentPanel();
    public static AdminPanel apObj=new AdminPanel();
    
    
    register regObj=new register();
    public static String uid;
    public static String ltype;
    
    public void viewButton()
    {
    	
        JButton submitButton=new JButton();
        submitButton.setText("Submit");
        submitButton.setFont(font);
        submitButton.setBounds(625,420,100,40);
        submitButton.setBackground(new Color(77,183,168));
        submitButton.setForeground(new Color(255,255,255));
        //submitButton.setBorder(border);
        viewLabel.add(submitButton);
        submitButton.addActionListener(this);
        
        JButton regButton =new JButton();
        regButton.setText("Register");
        regButton.setFont(new Font("Tahoma", Font.BOLD, 28));
        regButton.setBorder(null);
        regButton.setForeground(new Color(255,255,255));
        regButton.setBackground(new Color(77,183,168));
        regButton.setBounds(29,345,310,75);
        //submitButton.setBorder(border);
        viewLabel.add(regButton);
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
        		userText.setText(null);
				passText.setText(null);
				System.out.println("Register page");
				
                viewLabel.removeAll();
                viewLabel.revalidate();
                viewLabel.repaint();

                regObj.viewFrame();
                
                //spObj.viewFrame();//delete This
                //tpObj.viewFrame();//delete This
                //apObj.viewFrame();
                
        	}
        }
        );
        
    }
    
    public void viewTex()
    {
        
        
        userText.setFont(font);
        userText.setText("id/phone");
        userText.setForeground(new Color(100,100,100));
        userText.setBounds(675,310,200,30);
        userText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
        viewLabel.add(userText);
        
        passText.setFont(font);
        passText.setText(" password");
        passText.setForeground(new Color(208, 236, 231));
        passText.setBounds(675,360,200,30);
        passText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
        viewLabel.add(passText);
        
        userText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(userText.getText().toString().compareTo("id/phone")==0) {
					userText.setForeground(new Color(208, 236, 231));
				}
				//txtName.setForeground(new Color(230,230,230));
				if(userText.getText().isEmpty()) {
					userText.setText("id/phone");
					userText.setForeground(new Color(208, 236, 231));
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				userText.setForeground(new Color(50,50,50));
				
				
				if(userText.getText().toString().compareTo("id/phone")==0) {
					userText.setText(null);
				}
			}
		});
        
        passText.addFocusListener(new FocusAdapter() {
        	@Override
			public void focusLost(FocusEvent arg0) {
				if(passText.getText().toString().compareTo(" password")==0) {
					passText.setForeground(new Color(208, 236, 231));
				}
				//txtName.setForeground(new Color(230,230,230));
				if(passText.getText().isEmpty()) {
					passText.setText(" password");
					passText.setForeground(new Color(208, 236, 231));
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				passText.setForeground(new Color(50,50,50));
				
				
				if(passText.getText().toString().compareTo(" password")==0) {
					passText.setText(null);
				}
			}
		});
        
    }
    
    public void viewLabel()
    {
        
        JLabel loginLabel=new JLabel();
        JLabel userLabel=new JLabel();
        JLabel passLabel=new JLabel();
        JLabel nameLabel= new JLabel();
        JLabel titleLabel=new JLabel();
        JLabel sugLabel= new JLabel();
        
        titleLabel.setText("Welcome to");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        titleLabel.setBounds(635, 40, 500, 50);
        titleLabel.setForeground(new Color(0, 128, 128));
        viewLabel.add(titleLabel);
        
        //nameLabel.setForeground(Color.BLACK);
        //nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //nameLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        nameLabel.setText("Online Class Maintenance System");
        nameLabel.setFont(new Font("Helvetica", Font.BOLD, 28));
        nameLabel.setBounds(450, 70, 500, 50);
        nameLabel.setForeground(new Color(0, 128, 128));
        viewLabel.add(nameLabel);
        
        JSeparator separator = new JSeparator();
		separator.setBounds(430, 120, 510, 1);
		separator.setForeground(new Color(0, 128, 128));
		viewLabel.add(separator);
        
        loginLabel.setText("Login");
        loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        loginLabel.setBounds(650,250,100,50);
        viewLabel.add(loginLabel);
        
        userLabel.setText("User Id: ");
        userLabel.setFont(font);
        userLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        userLabel.setBounds(570,300,100,50);
        viewLabel.add(userLabel);
        
        passLabel.setText("Password: ");
        passLabel.setFont(font);
        passLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        passLabel.setBounds(570,350,100,50);
        viewLabel.add(passLabel);
        
        sugLabel.setText("Don't have an account!");
        sugLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        sugLabel.setForeground(new Color(255, 255, 255));
        sugLabel.setBounds(100,300,200,20);
        viewLabel.add(sugLabel);
        
    }
    public void viewImage()
    {
    	Image image=null;
        ImageIcon icon=new ImageIcon(getClass().getResource("/Image/icon.png"));
        image=icon.getImage();
        icon.setImage(image.getScaledInstance(1100,700,Image.SCALE_SMOOTH));
        viewLabel.setIcon(icon);
        
        
        
        
        
        //    Image bg=null;
         //   ImageIcon lbg=new ImageIcon(getClass().getResource("/Image/loginbg.png"));
       //     bg=lbg.getImage();
        //    lbg.setImage(bg.getScaledInstance(950,550,Image.SCALE_SMOOTH));
            ////viewLabel.setBounds(100, 100, 200, 200);
        //    viewLabel.setIcon(lbg);
            
            JLabel bgLabel = new JLabel("");
    		bgLabel.setIcon(new ImageIcon(View_Log_In.class.getResource("/Image/loginbg.png")));
    		bgLabel.setBounds(10, 10, 1003, 600);
    		//lblNewLabel.setIcon();
    		viewLabel.add(bgLabel);
    		setTitle("    Online Class Management - Login");
            ////
            
            
    }
    
    public void viewFrame()
    {
        viewLabel();
        viewTex();
        viewButton();
        viewImage();
        
        viewLabel.setSize(500,600);
        
        add(viewLabel);
        setSize(1040,659);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }
    public static String value()
    {
    	
    	return getUid();
    }
    

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand()=="Submit")
        {
            String user=userText.getText().toString();
            String pass=passText.getText().toString();
            if(userText.getText().isEmpty() == true || userText.getText().toString().compareTo("User Name")== 0)
            	JOptionPane.showMessageDialog(this,"UserName box can't be blank");
            else {
            	if(passText.getText().isEmpty() == true || passText.getText().toString().compareTo(" password")== 0)
            		JOptionPane.showMessageDialog(this,"Password box can't be blank");
            	else {
            try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ocm","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from login where id='"+user+"' and password='"+pass+"'";
				ResultSet rs=stmt.executeQuery(sql);
				
				
				if(rs.next())
					{//JOptionPane.showMessageDialog(null, "Login Successfull.");
					 
					uid=rs.getString(2);
					TeacherPanel.id = uid;
					StudentPanel.id = uid;
					AdminPanel.id = uid;
					
					ltype= rs.getString(6);
					//System.out.println(uid+ltype);
					
					userText.setText(null);
					passText.setText(null);
					System.out.println("Success");
					
	                viewLabel.removeAll();
	                viewLabel.revalidate();
	                viewLabel.repaint();
	                
	                if(ltype.compareTo("t")==0)
	                tpObj.viewFrame();
	                else if(ltype.compareTo("s")==0)
		                spObj.viewFrame();
	                else if(ltype.compareTo("a")==0)
	                	apObj.viewFrame();
	                	
	                
					}
				else
					JOptionPane.showMessageDialog(null, "Incorrect Login info");
				con.close();
			}catch (Exception y) {System.out.print(y);}}}
        }
    }

	public static String getUid() {
		return uid;
	}

	public static void setUid(String uid) {
		View_Log_In.uid = uid;
	}
    
}
