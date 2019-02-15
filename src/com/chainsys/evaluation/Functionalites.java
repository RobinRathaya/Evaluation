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
			String sql = "DELETE FROM ev_topics WHERE name=?";
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

		String sql = "SELECT id FROM ev_employee WHERE name= ? ";
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

	// public static Employee searchByName(String name) throws Exception {
	// Employee employee = null;
	// String sql =
	// "SELECT EMPLOYEE_ID,FIRST_NAME,LAST_NAME,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,MANAGER_ID ,DEPARTMENT_ID,DATE_OF_BIRTH,GENDER FROM CHAINSYS_EMPLOYEE WHERE first_name =? ";
	// try {
	// Connection connection = ConnectionUtil.getConnection();
	// PreparedStatement preparedStatement = connection
	// .prepareStatement(sql);
	//
	// preparedStatement.setString(1, name);
	//
	// ResultSet resultSet = preparedStatement.executeQuery();
	// while (resultSet.next()) {
	// employee = new Employee();
	// employee.setId(resultSet.getInt("employee_id"));
	// employee.setFirstName(resultSet.getString("first_name"));
	//
	// employee.setLastName(resultSet.getString("last_name"));
	// employee.setPhoneNumber(resultSet.getLong("phone_number"));
	// employee.setHireDate(resultSet.getDate("hire_date").toLocalDate());
	// employee.setJobId(resultSet.getString("job_id"));
	// employee.setSalary(resultSet.getFloat("salary"));
	// employee.setManagerId(resultSet.getInt("manager_id"));
	// employee.setDeptId(resultSet.getInt("department_id"));
	// employee.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH").toLocalDate());
	// employee.setGender(resultSet.getString("gender"));
	// }
	// return employee;
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new Exception("Unable to search");
	// }
	//
	// }

	public static Employee login(Employee employee) throws Exception {
		ResultSet resultSet = null;
		String sql = "SELECT empid,name,email,password FROM ev_employee WHERE email= ? AND password=? ";
		Employee employeeDetails = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			preparedStatement.setString(1, employee.getEmail());
			preparedStatement.setString(2, employee.getPassword());
			
			resultSet = preparedStatement.executeQuery();
			employeeDetails = new Employee();
			
			
			if (resultSet == null) {
			System.out.println("account not found");
				
			}
			else
			{
				System.out.println(resultSet.getInt("empid"));
				System.out.println(resultSet.getString("name"));
				
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
					.prepareStatement("INSERT INTO ev_employee values(?,?,?,?)");
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

	public static void insertDepartment(String name, int manager_Id,
			int location_Id) throws Exception {
		// TODO Auto-generated method stub
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("Insert into chainsys_departments values(CHAINSYSDEPARTMENTS_DEPTID_SEQ.nextval,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, manager_Id);
			preparedStatement.setInt(3, location_Id);
			preparedStatement.executeUpdate();
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Unable to Insert Department");
		}

	}

	public static void addTopic(Topics topic) throws Exception {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO ev_topics VALUES(EV_TOPICS_ID_SEQ.nextval,?)";

		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, topic.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("unable to insert");
		}

	}

	public static ArrayList<Topics> findTopics() throws Exception {

		String sql = "SELECT id,name FROM ev_topics";

		try {
			ArrayList<Topics> topicList = new ArrayList<>();
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Topics topic = new Topics();
				topic.setId(resultSet.getInt("id"));
				topic.setName(resultSet.getString("name"));

				topicList.add(topic);
			}
			return topicList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("No Record Found");
		}

	}

	//
	// public static void displayAll() {
	// // TODO Auto-generated method stub
	// ArrayList<Employee> employeeList=new ArrayList<>();
	// for (Employee e : employeeList) {
	// System.out.println(e.getId() + " " + e.getFirstName() + " "
	// + e.getLastName() + " " + e.getPhoneNumber() + " "
	// + e.getHireDate() + " " + e.getJobId() + " "
	// + e.getSalary() + " " + e.getDeptId() + " "
	// + e.getManagerId() + " " + e.getDateOfBirth() + " "
	// + e.getGender());
	// }
	// }

	public static void displayTopics(ArrayList<Topics> topicList) {
		// TODO Auto-generated method stub

		for (Topics topic : topicList) {
			System.out.println("ID" + "   " + "TOPIC");
			System.out.println(topic.getId() + " " + topic.getName());
		}

	}

	public static void updateTopic(Topics topic) throws Exception {
		String sql = "UPDATE ev_topics SET name=? WHERE id=? ";
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

	public static void newStatus(EmployeeTopics employeeTopics) throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO ev_employeetopics values(ev_employeetopics_id_seq.nextval,?,(SELECT topicid FROM ev_topics WHERE name=?),(SELECT id FROM ev_status where id=?),?,?,)";
		
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, employeeTopics.employee.getId());
			preparedStatement.setString(2, employeeTopics.topic.getName());
			preparedStatement.setInt(3,employeeTopics.status.getId());
			preparedStatement.setTimestamp(4,Timestamp.valueOf(employeeTopics.getCreatedOn()));
			preparedStatement.executeUpdate();
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to update");
		}
	}

	public static void updateStatus(EmployeeTopics employeeTopics) throws Exception {
		// TODO Auto-generated method stub
		String sql = "UPDATE ev_employeetopics modifiedon=? WHERE empid=? AND topicid=(SELECT topicid FROM ev_topics WHERE name=?) ";
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setTimestamp(1,Timestamp.valueOf(employeeTopics.getUpdatedOn()));
			preparedStatement.setInt(2,employeeTopics.employee.getId());
			preparedStatement.setString(3,employeeTopics.topic.getName());
			preparedStatement.executeUpdate();
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to update");
		}
		
	}

	public static ArrayList<EmployeeTopics> selectEmployeeEvaluation() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<EmployeeTopics> employeeTopicsList=new ArrayList<>();
		String sql = "SELECT id,empid,topicid,statusid,createdon,modifiedon FROM ev_employeetopics ";
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				EmployeeTopics employeeTopics=new EmployeeTopics();
				employeeTopics.employee=new Employee();
				employeeTopics.topic=new Topics();
				employeeTopics.setId(resultSet.getInt("id"));
				employeeTopics.employee.setId(resultSet.getInt("empid"));
				employeeTopics.topic.setId(resultSet.getInt("topicid"));
				employeeTopics.status.setId(resultSet.getInt("statusid"));
				employeeTopics.setCreatedOn(resultSet.getTimestamp("createdon").toLocalDateTime());
				employeeTopics.setUpdatedOn(resultSet.getTimestamp("modifiedon").toLocalDateTime());
				employeeTopicsList.add(employeeTopics);
			}
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to update");
		}
		
		return employeeTopicsList;
	}

	public static void displayEmployeeEvaluation(
			ArrayList<EmployeeTopics> employeeTopicsList) {
		// TODO Auto-generated method stub
		System.out.println("SNO"+" "+"EMPLOYEE ID"+" "+"EMPLOYEE NAME"+" "+"TOPIC NAME"+" "+"STATUS"+" "+"STARTED"+" "+"LAST UPDATE");
	}
}
