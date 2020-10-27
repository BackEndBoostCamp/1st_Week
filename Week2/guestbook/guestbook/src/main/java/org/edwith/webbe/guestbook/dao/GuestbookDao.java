package org.edwith.webbe.guestbook.dao;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GuestbookDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false&serverTimezone=UTC";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
    public List<Guestbook> getGuestbooks(){
    	ArrayList<Guestbook> list = new ArrayList<>();

        try {
        	Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e) {
        	e.printStackTrace();
        }
        
        String sql = "SELECT id, name, content, regdate FROM guestbook";
        try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
        		PreparedStatement ps = conn.prepareStatement(sql)) {
        	try (ResultSet rs = ps.executeQuery()) {
        		while (rs.next()) {
        			long id = rs.getLong("id");
        			String name = rs.getString("name");
        			String content = rs.getString("content");
        			Date regdate = rs.getDate("regdate");
        			Guestbook book = new Guestbook(id, name, content, regdate);
        			list.add(book);
        		}
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
        
        return list;
    }

    @SuppressWarnings("deprecation")
	public void addGuestbook(Guestbook guestbook){
   		try {
   			Class.forName("com.mysql.jdbc.Driver");
   		} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	String sql = "INSERT INTO guestbook (id, name, content, regdate) VALUES ( ?, ?, ?, ? )";
    	
   		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
    		PreparedStatement ps = conn.prepareStatement(sql)) {
    		ps.setLong(1, guestbook.getId());
    		ps.setString(2, guestbook.getName());
    		ps.setString(3, guestbook.getContent());
    		ps.setDate(4, new Date(guestbook.getRegdate().getTime()));

    		ps.executeUpdate();

    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
    public void DeleteGuestbook(long i){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection ( dburl, dbUser, dbpasswd );
			
			String sql = "DELETE FROM guestbook WHERE id = ?";

			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, i);
			ps.executeUpdate();

		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				}catch(Exception ex) {}
			}
			
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception ex) {}
			}
		} 
    }
    
    public void SortIDGuestbook(long a){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection (dburl, dbUser, dbpasswd);
			
			String sql = "update guestbook set id = ? where id = ?";

			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, a - 1);
			ps.setLong(2, a);
			ps.executeUpdate();

		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				}catch(Exception ex) {}
			}
			
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception ex) {}
			}
		} 
    }
   
}
