package product.model.dao;

import java.sql.*;
import java.util.ArrayList;

import product.model.vo.Product;

public class ProductDAO {
	public ProductDAO(){}
	
	public Product selectOne(String id){
		Product p = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe",
							"student", "student");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(
					"SELECT * FROM PRODUCT WHERE PRODUCT_ID = '"+id+"'");
			if(rset != null){
				while(rset.next()){
					p = new Product(rset.getString("PRODUCT_ID"),rset.getString("P_NAME"),
							rset.getInt("PRICE"),rset.getString("DESCRIPTION"));
				}
			}
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return p;
	}
	
	public ArrayList<Product> selectAll(){
		ArrayList<Product> pList = new ArrayList<Product>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe",
							"student", "student");
			stmt = conn.createStatement();
			rset = stmt.executeQuery("SELECT * FROM PRODUCT");
			if(rset != null){
				while(rset.next()){
					pList.add(new Product(rset.getString("PRODUCT_ID"),rset.getString("P_NAME"),
							rset.getInt("PRICE"),rset.getString("DESCRIPTION")));
				}
			}
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return pList;
	}
	
	public int insertProduct(Product p){
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe",
							"student", "student");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(
					"INSERT INTO PRODUCT "
					+"VALUES('"+p.getProductId()+"', '"
					+p.getpName()+"', "+p.getPrice()+", '"
					+p.getDescription()+"')");
			if(result > 0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int updateProduct(String id, int price){
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe",
							"student", "student");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(
					"UPDATE PRODUCT SET "
					+"PRICE = "+price
					+" WHERE PRODUCT_ID = '"+id+"'");
			if(result > 0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int deleteProduct(String id){
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe",
							"student", "student");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(
					"DELETE FROM PRODUCT WHERE PRODUCT_ID = '"+id+"'");
			if(result > 0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		return result;
	}
}
