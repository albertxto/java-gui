package Proyek;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Proyek.Product;

//import Proyek.User;
//import Proyek.Product;
import Proyek.Login;

public class MainFrame extends JFrame implements ActionListener {
	
	Login log;
	data dat;
	connector con;
	
	private JMenuBar bar = new JMenuBar();
	private JMenu mnMenu = new JMenu("Menu");
	private JMenuItem mnTransaction = new JMenuItem("Transaction");
	private JMenuItem mnProduct = new JMenuItem("Product");

	MainFrame(Login log,data dat){
		this.log=log;
		this.dat=dat;
		this.con=log.connect;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setTitle("Menu");
		this.setVisible(true);
		this.setLayout(new FlowLayout());
		mnMenu.add(mnProduct);
		mnMenu.addSeparator();
		mnMenu.add(mnTransaction);
		bar.add(mnMenu);
		this.setJMenuBar(bar);
		
		mnTransaction.addActionListener(this);
		mnProduct.addActionListener(this);
	}
//	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		connector con = new connector();
//		con.excecute("SELECT * FROM db_user");
//		con.excecute("SELECT * FROM stok");
//		MainFrame app = new MainFrame();
//		app.setVisible(true);
//	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == mnProduct) {

			Product product = new Product(this,con);
			product.setVisible(true);
			product.setClosable(true);
			this.add(product);
		}
		
		else if (arg0.getSource() == mnTransaction) {
			Transaction transaction = new Transaction(this,con,new Product(this,con));
			transaction.setVisible(true);
			transaction.setClosable(true);
			this.add(transaction);
		}
	}

}
