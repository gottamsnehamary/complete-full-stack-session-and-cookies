package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import utility.ConnectionManager;

public class UserDao implements UserDaoInterface  {

	@Override
	public int signup(User user) throws SQLException, Exception {
		String INSERT_USERS_SQL="INSERT INTO USER_USER(email,password)VALUES(?,?)";
		int result=0;
		try {
			Connection con =ConnectionManager.getConnection();
		    PreparedStatement ps = con.prepareStatement(INSERT_USERS_SQL);
		    ps.setString(1, user.getEmail());
		    ps.setString(2, user.getPassword());
		    System.out.println(ps);
		    result=ps.executeUpdate();
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean loginUser(User user) throws SQLException, Exception {
		boolean status=false;
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from USER_USER where email=? and password=?");
			ps.setString(1, user.getEmail());
		    ps.setString(2, user.getPassword());
		    System.out.println(ps);
		    ResultSet rs=ps.executeQuery();
		    status=rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	}
	
	
	

	
	



