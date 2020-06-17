package com.jspiders.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jspiders.entity.Product;

public class ProductDb {

	public static int addProduct(Product product) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			String querry = " insert into products " + " values(?,?,?,?,?) ";
			PreparedStatement psmt = con.prepareStatement(querry);
			psmt.setInt(1, product.getId());
			psmt.setString(2, product.getName());
			psmt.setString(3, product.getType());
			psmt.setInt(4, product.getPrice());
			psmt.setInt(5, product.getQuantity());

			status = psmt.executeUpdate();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return status;
	}

	public static List<Product> viewProducts() {
		List<Product> list = new ArrayList<Product>();
		try {

			Connection con = DB.getCon();
			String querry = " select * from products ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(querry);
			while (rs.next()) {
				int id = rs.getInt("product_id");
				String name = rs.getString("product_name");
				String type = rs.getString("product_type");
				int price = rs.getInt("price");
				int quan = rs.getInt("quantity");

				Product product = new Product(id, name, type, price, quan);
				list.add(product);

			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static boolean checkAvailability(int id, int quantity) {
		boolean status = false;
		try {
			Connection con = DB.getCon();

			String querry = " select * from products " + " where product_id=? and quantity>=? ";
			PreparedStatement psmt = con.prepareStatement(querry);
			psmt.setInt(1, id);
			psmt.setInt(2, quantity);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static boolean checkAvailability(int id) {
		boolean status = false;
		try {
			Connection con = DB.getCon();

			String querry = " select * from products " + " where product_id=? ";
			PreparedStatement psmt = con.prepareStatement(querry);
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static int editProduct(Product product) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			String querry = " update products set product_name=?, product_type=?, price=?, quantity=? "
					+ " where product_id=? ";
			PreparedStatement psmt = con.prepareStatement(querry);
			psmt.setString(1, product.getName());
			psmt.setString(2, product.getType());
			psmt.setInt(3, product.getPrice());
			psmt.setInt(4, product.getQuantity());
			psmt.setInt(5, product.getId());

			status = psmt.executeUpdate();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return status;
	}

	public static int deleteProduct(Product product) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			String querry = " delete from products where product_id=? ";
			PreparedStatement psmt = con.prepareStatement(querry);
			psmt.setInt(1, product.getId());

			status = psmt.executeUpdate();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return status;
	}

}
