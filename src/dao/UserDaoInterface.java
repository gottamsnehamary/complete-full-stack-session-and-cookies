package dao;

import java.sql.SQLException;

import model.User;

public interface UserDaoInterface {

	int signup(User user) throws SQLException, Exception;
	boolean loginUser(User user) throws SQLException, Exception;
}
