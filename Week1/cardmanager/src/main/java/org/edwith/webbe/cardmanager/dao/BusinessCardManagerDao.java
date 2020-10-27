package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=UTC";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";

    public List<BusinessCard> searchBusinessCard(String keyword){
    	List<BusinessCard> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT name, phone, companyName FROM card WHERE name = ?";
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, keyword);
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					String name = rs.getString(1);
					String phone = rs.getString("phone");
					String companyName = rs.getString("companyName");
					BusinessCard card = new BusinessCard(name, phone, companyName);
					list.add(card); // list에 반복할때마다 card인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
    }

    public int addBusinessCard(BusinessCard businessCard){
    	int insertCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO card (name, phone, companyname) VALUES ( ?, ?, ? )";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, businessCard.getName());
			ps.setString(2, businessCard.getPhone());
			ps.setString(3, businessCard.getCompanyName());

			insertCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
    }
    
    public int updateBusinessCard(BusinessCard businessCard, String Keyword, String Keyword2){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "UPDATE card set name = ?, phone = ?, companyname = ? WHERE name = ? AND phone = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(4, Keyword);
			ps.setString(5, Keyword2);
			ps.setString(1, businessCard.getName());
			ps.setString(2, businessCard.getPhone());
			ps.setString(3, businessCard.getCompanyName());
			
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
    }
    
    public int deleteBusinessCard(String Keyword, String Keyword2){
    	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "delete from card where name = ? AND phone = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, Keyword);
			ps.setString(2, Keyword2);

			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
    }
}
