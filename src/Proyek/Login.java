package Proyek;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Proyek.Register;

public class Login extends JFrame implements ActionListener{
	
	Register register;
	connector connect;
	
	private JLabel lblUsername = new JLabel("Username");
	private JLabel lblPassword = new JLabel("Password");
	private JTextField txtUsername = new JTextField(10);
	private JPasswordField txtPassword = new JPasswordField(10);
	private JButton btnLogin = new JButton("Login");
	private JButton btnRegister = new JButton("Register");
	private int au=0;
	
	Login() {
		connect = new connector();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setSize(240,120);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("Login");
		this.setLocationRelativeTo(null);
		
		this.add(lblUsername);
		this.add(txtUsername);
		this.add(lblPassword);
		this.add(txtPassword);
		this.add(btnLogin);
		this.add(btnRegister);
		
		btnLogin.addActionListener(this);
		btnRegister.addActionListener(this);
	}
	
	int search(String username, String password){
		int flag=connect.excecute("SELECT * FROM db_user WHERE Username = '"+username+"' and Password = '"+password+"';");
		System.out.println(flag);
		if (flag==0)
			return -1;
		else
			return flag;
	}
	
	public static void main(String[] args) {
		new Login();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnLogin) {
			
			String username = txtUsername.getText();
			String password = new String(txtPassword.getPassword());
			au = search(username, password);
			if(au >= 0){
				this.setVisible(false);
				dispose();
				new MainFrame(this,connect.getDat());
			}
			else if(username.isEmpty() || password.isEmpty()){
				JOptionPane.showMessageDialog(txtPassword,"Username and password must be filled!");
			}
			else{
				JOptionPane.showMessageDialog(txtPassword,"Username and password are wrong!");
			}
			
//			else {
//				this.setVisible(false);
//				new MainFrame(au,this,);
//				}			
		}
						
		else if(arg0.getSource() == btnRegister){
			this.setVisible(false);
			register = new Register(this);
		}
	}
}