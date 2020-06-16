package com.jspiders.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jspiders.entity.User;

public class UserDb {

	public static int addUser(User user) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			String querry = " insert into users " + " values(?,?) ";
			PreparedStatement psmt = con.prepareStatement(querry);
			psmt.setString(1, user.getId());
			psmt.setString(2, user.getPassword());
			status = psmt.executeUpdate();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return status;
	}

	public static boolean userAuthentication(String id, String pswrd) {
		boolean status = false;
		try {

			Connection con = DB.getCon();
			String querry = " select * from users " + " where user_id = ? and password = ? ";

			PreparedStatement psmt = con.prepareStatement(querry);
			psmt.setString(1, id);
			psmt.setString(2, pswrd);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
