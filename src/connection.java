import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connection {
	
	String id = "root";
	String password = "";
	Connection con = null;
	Statement st = null;
	PreparedStatement pst = null;
	String host = "localhost";
	String port = "3306";
	String db_name="ebookshelf";
	
	public void adminAdd(String UserName, String Password)
	{
		String query = "INSERT INTO tbowners(UserName, Password) VALUES (?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, UserName);
			pst.setString(2, Password);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adminDelete(String UserName, String Password)
	{
		String query = "DELETE from tbowners WHERE UserName=? AND Password=?";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, UserName);
			pst.setString(2, Password);
			pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void bookDelete(String BookName, String BookWriter, String BookType, String BookPublisher)
	{
		String query = "DELETE from tbbooks WHERE BookName=? AND BookWriter=? AND BookType=? AND BookPublisher=?";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, BookName);
			pst.setString(2, BookWriter);
			pst.setString(3, BookType);
			pst.setString(4, BookPublisher);
			pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void bookUpdate(String uname, String uwriter, String utype, String upublisher, String BookName, String BookWriter, String BookType, String BookPublisher)
	{
		String query = "UPDATE tbbooks SET BookName=?,BookWriter=?,BookType=?,BookPublisher=? WHERE BookName=? AND BookWriter=? AND BookType=? AND BookPublisher=?";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, uname);
			pst.setString(2, uwriter);
			pst.setString(3, utype);
			pst.setString(4, upublisher);
			pst.setString(5, BookName);
			pst.setString(6, BookWriter);
			pst.setString(7, BookType);
			pst.setString(8, BookPublisher);
			pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void bookAdd(String name, String writer, String type, String publisher)
	{
		String query = "INSERT INTO tbbooks(BookName, BookWriter, BookType, BookPublisher) VALUES (?,?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, writer);
			pst.setString(3, type);
			pst.setString(4, publisher);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Books> bringbook()
	{
		ArrayList<Books> list = new ArrayList<Books>();
		String query = "Select * From tbbooks";
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				int id = rs.getInt("id");
				String BookName = rs.getString("BookName");
				String BookWriter = rs.getString("BookWriter");
				String BookType = rs.getString("BookType");
				String BookPublisher = rs.getString("BookPublisher");
				list.add(new Books(id,BookName,BookWriter,BookType,BookPublisher));
			}
			return list;
		} catch (SQLException e) {
			
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE,null,e);
			return null;
		}
		
	}
	
	public ArrayList<Managers> bringadmin()
	{
		ArrayList<Managers> list = new ArrayList<Managers>();
		String query = "Select * From tbowners";
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				String UserName = rs.getString("UserName");
				String Password = rs.getString("Password");
				list.add(new Managers(UserName, Password));
			}
			return list;
		} catch (SQLException e) {
			
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE,null,e);
			return null;
		}
		
	}
	
	public boolean Login(String username, String password)
	{
		try {
		String query = "Select * From tbowners Where UserName=? And Password=?";
		pst = con.prepareStatement(query);
		pst.setString(1,username);
		pst.setString(2,password);
		ResultSet rs = pst.executeQuery();
		return rs.next();
		}
		catch (SQLException ex)
		{
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE,null,ex);
			return false;
		}
		
	}
	public connection()
	{
		String url="jdbc:mysql://"+host+":"+port+"/"+db_name;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,id,password);
			System.out.println("Connection Was Succesfuly!");
		} catch (ClassNotFoundException e) {
			
			System.out.println("Error While Connecting");
		} catch (SQLException e) {
			System.out.println("Error While Connecting");
			
		}
	}
	
	public static void main(String[] args) {
		connection conn = new connection();
	}
	
}
