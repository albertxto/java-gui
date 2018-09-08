package Proyek;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Transaction extends JInternalFrame implements ActionListener {
	
	private JLabel lblProductName = new JLabel("Product Name");
	private JLabel lblQuantity = new JLabel("Quantity");

	private JTextField txtProductName = new JTextField(10);
	private JTextField txtQuantity = new JTextField(10);
	
	private JButton btnSave = new JButton("Buy");
	private JButton btnCancel = new JButton("Cancel");

	JPanel pnlInput = new JPanel();
	JPanel pnlButton = new JPanel();
	JPanel pnlData = new JPanel();

	String[] header = new String[] { "Name", "Quantity" };
	DefaultTableModel dtm = new DefaultTableModel(null, header);
	private JTable tableUser = new JTable(dtm);

	JScrollPane pane = new JScrollPane();
	connector con = new connector();
	MainFrame frame;
	Product product;
	
	Transaction(MainFrame frame,connector connect,Product prod){
		super("Form Transaction", true, true, true);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.con=connect;
		this.frame=frame;

		pnlInput.setLayout(new GridLayout(3, 2, 5, 5));
		pnlInput.add(lblProductName);
		pnlInput.add(txtProductName);
		pnlInput.add(lblQuantity);
		pnlInput.add(txtQuantity);

		pnlInput.add(btnSave);
		pnlInput.add(btnCancel);

		pnlButton.setLayout(new FlowLayout());

		pnlData.setLayout(new FlowLayout());
		pnlData.add(new JScrollPane(tableUser), BorderLayout.CENTER);
		pnlData.add(pnlButton, BorderLayout.SOUTH);

		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		this.add(pnlInput);
		this.add(pnlData);
		
		refreshData();
	}
	
	private void refreshData() {
		// TODO Auto-generated method stub
		try{
			con.sql="select * from transaksi";
			con.rs=con.stmt.executeQuery(con.sql);
			dtm.setRowCount(0);
			while (con.rs.next()) {
				// System.out.println(rs.getInt("name"));
				Vector temp = new Vector();
				temp.add(con.rs.getString("ProductName"));
				temp.add(con.rs.getInt("Qty"));
				dtm.addRow(temp);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
			
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnSave) {
			int flag = 0;
			String productName = txtProductName.getText();
			String quantity = txtQuantity.getText();
			int quantities=Integer.parseInt(quantity);
			int qty=0;
			if(productName.isEmpty() || quantity.isEmpty()){
				flag = -1;
				JOptionPane.showMessageDialog(txtProductName,"Please fill every details needed!");
			}
			
			if(flag==0){
				
				try {
					con.sql="select * from stok where Nama='"+productName+"'";
					con.rs=con.stmt.executeQuery(con.sql);
					while (con.rs.next()) {
						// System.out.println(rs.getInt("name"));
						qty=(Integer)con.rs.getInt("Qty");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try{
					con.stmt.executeUpdate("update stok set Qty="+(qty-quantities)+" where Nama='"+productName+"'");
				}catch(SQLException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try{
					con.stmt.executeUpdate("insert into transaksi values('"+productName+"',"+quantity+")");
				}catch(SQLException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				refreshData();
			}	
		}
		else if(arg0.getSource()==btnCancel){
			txtProductName.setText("");
			txtQuantity.setText("");
		}
	}
}
