package Proyek;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

public class Product extends JInternalFrame implements ActionListener {

	private JLabel lblProductID = new JLabel("Product ID");
	private JLabel lblProductName = new JLabel("Product Name");
	private JLabel lblQuantity = new JLabel("Quantity");
	private JLabel lblPrice = new JLabel("Price");

	private JTextField txtProductID = new JTextField(10);
	private JTextField txtProductName = new JTextField(10);
	private JTextField txtQuantity = new JTextField(10);
	private JTextField txtPrice = new JTextField(10);
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnAdd = new JButton("Add");
	private JButton btnEdit = new JButton("Edit");
	private JButton btnDelete = new JButton("Delete");

	JPanel pnlInput = new JPanel();
	JPanel pnlButton = new JPanel();
	JPanel pnlData = new JPanel();
	
	MainFrame fr;
	 
	String[] header = new String[] { "Product ID", "Product Name", "Quantity", "Price" };
	DefaultTableModel dtm = new DefaultTableModel(null, header);
	private JTable tableProduct = new JTable(dtm);

	JScrollPane pane = new JScrollPane(tableProduct);
	connector con;
	Statement st;
	
	boolean Edit=false;
	
	Product(MainFrame frame,connector connect){
		super("Form Product", true, true, true);
		this.fr=frame;
		this.con=connect;
		this.setSize(700, 700);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);

		pnlInput.setLayout(new GridLayout(5, 2, 5, 5));
		pnlInput.add(lblProductID);
		pnlInput.add(txtProductID);
		pnlInput.add(lblProductName);
		pnlInput.add(txtProductName);
		pnlInput.add(lblQuantity);
		pnlInput.add(txtQuantity);
		pnlInput.add(lblPrice);
		pnlInput.add(txtPrice);

		pnlInput.add(btnSave);
		pnlInput.add(btnCancel);

		pnlButton.setLayout(new FlowLayout());
		pnlButton.add(btnAdd);
		pnlButton.add(btnEdit);
		pnlButton.add(btnDelete);
		
		btnAdd.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		
//		try {
//			ResultSet st = con.stmt.executeQuery("select * from stok");
//			dtm.setRowCount(0);
//			while(st.next()){
//				Vector fill = new Vector();
//				fill.add(st.getString("ID"));
//				fill.add(st.getString("Nama"));
//				fill.add(st.getInt("Qty"));
//				fill.add(st.getInt("Harga"));
//				dtm.addRow(fill);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		refreshData();
		pnlData.setLayout(new FlowLayout());
		pnlData.add(pane, BorderLayout.CENTER);
		pnlData.add(pnlButton, BorderLayout.SOUTH);

		this.add(pnlInput);
		this.add(pnlData);
		
		enabledPanel(pnlData);
		disabledPanel(pnlInput);
	}
	
	public void disabledPanel(JPanel p){
		Component[] com = p.getComponents();  
		//Disable everything in a panel  
		for (int a = 0; a < com.length; a++) {  
		     com[a].setEnabled(false);  
		}  
	}
	
	public void enabledPanel(JPanel p){
		Component[] com = p.getComponents();  
		//Enable everything in a panel
		for (int a = 0; a < com.length; a++) {  
		     com[a].setEnabled(true);  
		}  
	}
	
	private void refreshData() {
		// TODO Auto-generated method stub
		try{
			con.sql="select * from stok";
			con.rs=con.stmt.executeQuery(con.sql);
			dtm.setRowCount(0);
			while (con.rs.next()) {
				// System.out.println(rs.getInt("name"));
				Vector temp = new Vector();
				temp.add(con.rs.getString("ID"));
				temp.add(con.rs.getString("Nama"));
				temp.add(con.rs.getInt("Qty"));
				temp.add(con.rs.getInt("Harga"));
				dtm.addRow(temp);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			// TODO: handle exception
		}	
	}
	
	public int getqty(String name){
		int qty=0;
		for(int i=0;i<dtm.getRowCount();i++){
			if(dtm.getValueAt(i, 1).equals(name)){
				qty=(Integer)dtm.getValueAt(i, 2);
				break;
			}
		}
		return qty;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnSave) {
			int flag = 0;
			String productName = txtProductName.getText();
			String productId = txtProductID.getText();
			String quantity = txtQuantity.getText();
			String price = txtPrice.getText();
			
//			if(productName.isEmpty() || quantity.isEmpty() || price.isEmpty()){
//				flag = -1;
//				JOptionPane.showMessageDialog(txtProductName,"Please fill every details needed!");
//			}
			
			//if(flag==0){
				if(Edit==false){
					try {
						con.stmt.executeUpdate("Insert into stok values(NULL,'"+productName+"',"+quantity+","+price+") ");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//				try {
//					ResultSet st = con.stmt.executeQuery("select * from stok");
//					dtm.setRowCount(0);
//					while(st.next()){
//						Vector fill = new Vector();
//						fill.add(st.getString("ID"));
//						fill.add(st.getString("Nama"));
//						fill.add(st.getInt("Qty"));
//						fill.add(st.getInt("Harga"));
//						dtm.addRow(fill);
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				else{
					String query="update stok set Nama='"+productName+"', Qty='"+quantity+"', Harga='"+price+"' where ID='"+productId+"'";
					try {
						con.stmt.executeUpdate(query);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			//}
			refreshData();
			disabledPanel(pnlInput);
			enabledPanel(pnlData);
			enabledPanel(pnlButton);
		}
		else if (arg0.getSource() == btnDelete) {
			try {
				int index=tableProduct.getSelectedRow();
				String id=((String)dtm.getValueAt(index, 0));
				con.stmt.executeUpdate("Delete from stok where ID='"+id+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			refreshData();
		}
		else if(arg0.getSource()==btnEdit){
			Edit=true;
			disabledPanel(pnlButton);
			enabledPanel(pnlInput);
			txtProductID.setEnabled(false);
			int index=tableProduct.getSelectedRow();
			txtProductID.setText((String) dtm.getValueAt(index, 0));
			txtProductName.setText((String) dtm.getValueAt(index, 1));
			txtQuantity.setText((String) dtm.getValueAt(index, 2).toString());
			txtPrice.setText((String) dtm.getValueAt(index, 3).toString());
		}
		else if(arg0.getSource()==btnAdd){
			Edit=false;
			disabledPanel(pnlButton);
			enabledPanel(pnlInput);
			txtProductID.setEnabled(false);
		}
		else if(arg0.getSource()==btnCancel){
			txtProductID.setText("");
			txtProductName.setText("");
			txtQuantity.setText("");
			txtPrice.setText("");
			disabledPanel(pnlInput);
			enabledPanel(pnlButton);
		}
	}
	
}
