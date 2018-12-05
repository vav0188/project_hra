package Frogger;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import com.mysql.*;

public class Database {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	boolean active = true;
	public Database()
	{
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Frogger?useTimezone=true&serverTimezone=UTC","root","");
			st = con.createStatement();
			
			
		}catch(Exception ex)
		{
			active = false;
			System.out.println("Error"+ex);
		}
	}
	
	public String getData() 
	{
		String ret = "";
		if(active == true) {
		try 
		{
			String query = "select score from highscore order by score desc limit 5";
			rs = st.executeQuery(query);
			
			int i = 0;
			while(rs.next())
			{
				i++;
				String sc = rs.getString("score");
				ret += i+".    "+sc+"x";
				
			}
		}catch(Exception ex)
		{
			System.out.println("Error"+ex);
		}}
		
		return ret;
	}
	
	public int getFifth()
	{
		int ret = 0;
		if(active == true)
		{
		try 
		{
			String query = "select score from highscore order by score desc limit 5";
			rs = st.executeQuery(query);
			
			int i = 0;
			while(rs.next())
			{
				i++;
				if(i == 5)
					ret = rs.getInt("score");				
			}
		}catch(Exception ex)
		{
			System.out.println("Error"+ex);
		}
		}
		return ret;
	}
	
	public void setData(int score)
	{
		if(active == true) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();  
		int a = score;
		try {
		PreparedStatement post = con.prepareStatement("INSERT INTO highscore(score,date) VALUES('"+a+"','"+now+"')");
		post.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error");
		}}
	}
	
}