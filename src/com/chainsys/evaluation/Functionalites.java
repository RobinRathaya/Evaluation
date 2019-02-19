package com.chainsys.evaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Functionalites {

	public static void deleteTopic(Topics topic) throws Exception {

		try {
			Connection connection = ConnectionUtil.getConnection();
			String sql = "DELETE FROM EV_TOPICS WHERE name=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, topic.getName());
			preparedStatement.executeUpdate();
			ConnectionUtil.close(connection, preparedStatement, null);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to delete");
		}

	}

	public static Employee getDetails(Employee employee) throws Exception {

		String sql = "SELECT id FROM EV_EMPLOYEE WHERE name= ? ";
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			preparedStatement.setString(1, employee.getName());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
			}
			return employee;

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			throw new Exception("Unable to search by ID");
		}

	}

	public static Employee login(Employee employee) throws Exception {
		ResultSet resultSet = null;
		String sql = "SELECT id,name,email,password FROM EV_EMPLOYEE WHERE email= ? AND password=? ";
		Employee employeeDetails = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			preparedStatement.setString(1, employee.getEmail());
			preparedStatement.setString(2, employee.getPassword());

			resultSet = preparedStatement.executeQuery();
			employeeDetails = new Employee();

			if (resultSet.next()) {
				employeeDetails.setId(resultSet.getInt("id"));
				employeeDetails.setName(resultSet.getString("name"));

				if (resultSet.getString("email").equals(
						"admin1523@chainsys.com")
						&& resultSet.getString("password").equals("Admin@1234")) {
					Administrator.call();
				} else if (resultSet.getString("email").equals(
						"admin1523@chainsys.com")
						&& !resultSet.getString("password")
								.equals("Admin@1234")) {
					System.out.println("Invalid Admin password");
				} else if (resultSet.getString("email").equals(
						"hr@chainsys.com")
						&& resultSet.getString("password").equals("hr@1234")) {
					Hr.call();
				}

				else if (resultSet.getString("email").equals("hr@chainsys.com")
						&& !resultSet.getString("password").equals("hr@1234")) {
					System.out.println("Invalid Admin password");
				}

			} else {
				System.out.println("account not found");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			throw new Exception("Unable to find account");
		}
		return employeeDetails;

	}

	public static void addEmployee(Employee employee) throws Exception {
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO EV_EMPLOYEE values(?,?,?,?)");
			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setString(4, employee.getPassword());
			preparedStatement.executeUpdate();
			ConnectionUtil.close(connection, preparedStatement, null);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to Insert Employee");
		}

	}

	public static void addTopic(Topics topic) throws Exception {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO EV_TOPICS(name) VALUES(?)";

		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, topic.getName());
			preparedStatement.executeUpdate();
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("unable to insert");
		}

	}

	public static ArrayList<Topics> findTopics() throws Exception {

		String sql = "SELECT topicid,name FROM EV_TOPICS";

		try {
			ArrayList<Topics> topicList = new ArrayList<>();
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Topics topic = new Topics();
				topic.setId(resultSet.getInt("topicid"));
				topic.setName(resultSet.getString("name"));
				topicList.add(topic);

			}
			return topicList;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("No Record Found");
		}

	}

	public static void displayTopics(ArrayList<Topics> topicList) {
		// TODO Auto-generated method stub

		for (Topics topic : topicList) {
			System.out.println("ID" + "    " + "TOPIC");
			System.out.println(topic.getId() + "    " + topic.getName());
		}

	}

	public static void updateTopic(Topics topic) throws Exception {
		String sql = "UPDATE EV_TOPICS SET name=? WHERE topicid=? ";
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, topic.getName());
			preparedStatement.setInt(2, topic.getId());
			preparedStatement.executeUpdate();
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to update");
		}
	}

	public static void newStatus(EmployeeTopics employeeTopics)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO EV_EMPLOYEETOPICS(empid,topicid,statusid,createdon,modifiedon) values(?,(SELECT topicid FROM EV_TOPICS WHERE name=?),?,?,?)";

		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, employeeTopics.employee.getId());
			preparedStatement.setString(2, employeeTopics.topic.getName());
			preparedStatement.setInt(3, employeeTopics.status.getId());
			preparedStatement.setTimestamp(4,
					Timestamp.valueOf(employeeTopics.getCreatedOn()));
			preparedStatement.setTimestamp(5, null);
			preparedStatement.executeUpdate();
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to update");
		}
	}

	public static void updateStatus(EmployeeTopics employeeTopics)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "UPDATE EV_EMPLOYEETOPICS modifiedon=? WHERE empid=? AND topicid=(SELECT topicid FROM EV_TOPICS WHERE name=?) ";
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setTimestamp(1,
					Timestamp.valueOf(employeeTopics.getUpdatedOn()));
			preparedStatement.setInt(2, employeeTopics.employee.getId());
			preparedStatement.setString(3, employeeTopics.topic.getName());
			preparedStatement.executeUpdate();
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to update");
		}

	}

	public static ArrayList<EmployeeTopics> selectEmployeeEvaluation()
			throws Exception {
		// TODO Auto-generated method stub
		ArrayList<EmployeeTopics> employeeTopicsList = new ArrayList<>();
		String sql = "SELECT et.id,e.id ,e.name,t.name,s.name,et.createdon,et.modifiedon FROM EV_EMPLOYEETOPICS et JOIN EV_EMPLOYEE e ON et.empid=e.id JOIN EV_TOPICS t ON et.topicid=t.topicid JOIN EV_STATUS s ON et.statusid=s.id;";
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				EmployeeTopics employeeTopics = new EmployeeTopics();
				employeeTopics.employee = new Employee();
				employeeTopics.topic = new Topics();
				employeeTopics.status = new Status();
				employeeTopics.setId(resultSet.getInt("id"));
				employeeTopics.employee.setId(resultSet.getInt("e.id"));
				employeeTopics.employee.setName(resultSet.getString("e.name"));
				employeeTopics.topic.setName(resultSet.getString("t.name"));
				employeeTopics.status.setName(resultSet.getString("s.name"));
				employeeTopics.setCreatedOn(resultSet.getTimestamp("createdon")
						.toLocalDateTime());
				if (resultSet.getObject("modifiedon") != null) {
					employeeTopics.setUpdatedOn(resultSet.getTimestamp(
							"modifiedon").toLocalDateTime());
				} else {
					employeeTopics.setUpdatedOn(null);
				}
				employeeTopicsList.add(employeeTopics);

			}
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to update");
		}
		for (EmployeeTopics employeeTopics : employeeTopicsList) {
			System.out.println(employeeTopics.getId());
			System.out.println(employeeTopics.getCreatedOn());
		}
		return employeeTopicsList;
	}

	public static void displayEmployeeEvaluation(
			ArrayList<EmployeeTopics> employeeTopicsList) {
		// TODO Auto-generated method stub
		System.out.println("SNO" + " " + "EMPLOYEE ID" + " " + "EMPLOYEE NAME"
				+ " " + "TOPIC NAME" + " " + "STATUS" + " " + "STARTED" + " "
				+ "LAST UPDATE");
		for (EmployeeTopics employeeTopics : employeeTopicsList) {
			System.out.println(employeeTopics.getId() + " "
					+ employeeTopics.employee.getId() + " "
					+ employeeTopics.employee.getName() + " "
					+ employeeTopics.topic.getName() + " "
					+ employeeTopics.status.getName() + " "
					+ employeeTopics.getCreatedOn() + " "
					+ employeeTopics.getUpdatedOn());

		}
	}
	
	
}
