package Proyek;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Proyek.Login;

public class Register extends JFrame implements ActionListener {

	Login login;

	private JLabel lblUsername = new JLabel("    Username");
	private JLabel lblYourname = new JLabel("   Full name");
	private JLabel lblPassword = new JLabel("    Password");
	private JLabel lblGender = new JLabel("  Gender                   ");
	private JLabel lblPhone = new JLabel("    Phone      ");
	private JLabel lblEmail = new JLabel("     Email       ");
	private JLabel lblAddress = new JLabel("     Address  ");
	private JLabel lblCity = new JLabel("City                                ");

	private JTextField txtUsername = new JTextField(15);
	private JTextField txtYourname = new JTextField(15);
	private JPasswordField txtPassword = new JPasswordField(15);
	private JRadioButton radMale = new JRadioButton("Male");
	private JRadioButton radFemale = new JRadioButton("Female");
	private JTextField txtPhone = new JTextField(15);
	private JTextField txtEmail = new JTextField(15);
	private JTextField txtAddress = new JTextField(15);
	
	private String[] city = new String[]{"Jakarta", "Surabaya", "Bandung", "Solo", "Pekanbaru", "Jayapura"};
	private JComboBox<String> cmbCity = new JComboBox<String>(city);
	
	private JButton btnRegister = new JButton("Register");
	private JButton btnCancel = new JButton("Cancel");
	
	private ButtonGroup groupGender = new ButtonGroup();
	JPanel pnlGender = new JPanel();
	JPanel pnlUsername = new JPanel();
	JPanel pnlYourname = new JPanel();
	JPanel pnlPassword = new JPanel();
	JPanel pnlPhone = new JPanel();
	JPanel pnlEmail = new JPanel();
	JPanel pnlAddress = new JPanel();
	JPanel pnlCity = new JPanel();
	JPanel pnlButton = new JPanel();
	
	connector con = new connector();
	
	Register(Login login){
		
		con.excecute("SELECT * FROM db_user");
		con.getDbfn();
		this.login = login;
		this.setTitle("Register");
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(280, 380);
		this.setLayout(new GridLayout(9,2,5,5));
		this.setLocationRelativeTo(null);
		
		pnlYourname.setLayout(new FlowLayout());
		pnlYourname.add(lblYourname);
		pnlYourname.add(txtYourname);
		
		pnlUsername.setLayout(new FlowLayout());
		pnlUsername.add(lblUsername);
		pnlUsername.add(txtUsername);
		
		pnlPassword.setLayout(new FlowLayout());
		pnlPassword.add(lblPassword);
		pnlPassword.add(txtPassword);
		
		pnlPhone.setLayout(new FlowLayout());
		pnlPhone.add(lblPhone);
		pnlPhone.add(txtPhone);
		
		pnlEmail.setLayout(new FlowLayout());
		pnlEmail.add(lblEmail);
		pnlEmail.add(txtEmail);
		
		pnlAddress.setLayout(new FlowLayout());
		pnlAddress.add(lblAddress);
		pnlAddress.add(txtAddress);
		
		pnlCity.setLayout(new FlowLayout());
		pnlCity.add(lblCity);
		pnlCity.add(cmbCity);
		
		groupGender.add(radMale);
		groupGender.add(radFemale);
		pnlGender.add(lblGender);
		radMale.setSelected(true);
		pnlGender.add(radMale);
		pnlGender.add(radFemale);
		
		pnlButton.setLayout(new FlowLayout());
		pnlButton.add(btnRegister);
		pnlButton.add(btnCancel);
	
		this.add(pnlYourname);
		this.add(pnlGender);
		this.add(pnlPhone);
		this.add(pnlEmail);
		this.add(pnlAddress);
		this.add(pnlUsername);
		this.add(pnlPassword);
		this.add(pnlCity);
		this.add(pnlButton);
		
		btnRegister.addActionListener(this);
		btnCancel.addActionListener(this);
	}
	
	boolean isEmail(int at, int dot){
		if(at == -1 || dot == -1 || at>dot)
			return false;
		
		else
			return true;
	}
	
	void isNumeric(String phone, int flag){		
		try {
		    int ph = Integer.parseInt(phone);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(txtPhone,"Please input a valid Phone number!");
			flag = -1;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btnRegister){
			int flag = 0;
			String yourname = txtYourname.getText(); //nama
			String name = txtUsername.getText(); //username
			String gender = "M";
			String address = txtAddress.getText();
			int city = (int) cmbCity.getSelectedIndex();
			
			if (radFemale.isSelected())
				gender = "F";
			
			String password = new String(txtPassword.getPassword());
			String phone = txtPhone.getText();
			String email = txtEmail.getText();
			
			int at=-1, dot=-1;
			
			at = email.indexOf("@");
			dot = email.lastIndexOf(".");
			
			if(!isEmail(at,dot) && !email.isEmpty()){
				JOptionPane.showMessageDialog(txtEmail,"Invalid Email \n Please input a valid email address!");
				flag = -1;
			}
				
			if(yourname.isEmpty() || name.isEmpty() || address.isEmpty() || password.isEmpty() || phone.isEmpty() || email.isEmpty()){
				JOptionPane.showMessageDialog(txtUsername,"Please fill every details needed!");
				flag = -1;
			}
			
			if (name.equals(password) && !name.isEmpty() && !password.isEmpty()){
				JOptionPane.showMessageDialog(txtPassword,"Your password is similar to username!");
				flag = -1;
			}
				
			if(!phone.isEmpty())
				isNumeric(phone,flag);
			
			if(flag == 0){
				this.setVisible(false);
				login.setVisible(true);
				con.excecute("INSERT INTO db_user VALUE('"+yourname+"', '"+gender+"', '"+phone+"', '"+email+"', '"+address+"', '"+name+"', '"+password+"', '"+city+"')");
				JOptionPane.showMessageDialog(txtUsername,"Registration Successful!\nYou can now log in.");
			}
		
		}else if(arg0.getSource()==btnCancel){
			this.setVisible(false);
			login.setVisible(true);
		}
	}
}