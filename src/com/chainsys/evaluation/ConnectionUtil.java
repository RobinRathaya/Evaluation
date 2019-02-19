package com.chainsys.evaluation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() throws Exception {
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://13.126.90.207:3306/chainsys_team2_db";
			connection = DriverManager
					.getConnection(url, "chainsys_team2", "chainsys_team2");
			

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Unable to connect");
		}
		return connection;

	}
	public static void close(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) throws SQLException {
		if(connection != null)
		{
			connection.close();
		}
		if (preparedStatement != null) {
			
			preparedStatement.close();
		}
		if (resultSet !=null) {
			
			resultSet.close();
			
		}
	}

}
