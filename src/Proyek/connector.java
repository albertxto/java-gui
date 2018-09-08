package Proyek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connector {
	
//	frame Frame;
//	login log;
	data dat = new data();

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/database";
	static final String USER = "root";
	static final String PASS = "";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs;
	String sql;
	
	connector(){
				
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
//			finally{
//		      //finally block used to close resources
//		      try{
//		         if(stmt!=null)
//		            stmt.close();
//		      }catch(SQLException se2){
//		      }// nothing we can do
//		      try{
//		         if(conn!=null)
//		            conn.close();
//		      }catch(SQLException se){
//		         se.printStackTrace();
//		      }//end finally try
//		   }//end try
	}
	String dbfn, dbGender, dbPhone, dbEmail, dbAddress, dbUsername, dbPass;
	int dbCity;
		
	public int excecute(String sql) {
		int flag = 0;
		this.sql=sql;
		String sub = new String(sql.substring(0, 9));
		sub.toUpperCase();
			
		if(sub.contains("SELECT")){
			try{
			
				rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					flag++;
			    	//Retrieve by column name
					//System.out.println(rs.getString("Full name")+"-------"+rs.getString("Gender"));
			        
			         dbfn = rs.getString("Full name");
			         dbGender = rs.getString("Gender");
			         dbPhone = rs.getString("Phone");
			         dbEmail = rs.getString("Email");
			         dbAddress = rs.getString("Address");
			         dbUsername = rs.getString("Username");
			         dbPass = rs.getString("Password");
			         dbCity = rs.getInt("City");
			      }
			
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}else if(sub.contains("INSERT IN")|| sub.contains("UPDATE") || sub.contains("DELETE FR")){
			try{
				stmt.executeUpdate(sql);
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
				
		return flag;
	
	}
	
	private String getString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	String getDbfn(){
		return dbfn;
	}

	public data getDat(){
		return dat;
	}

}