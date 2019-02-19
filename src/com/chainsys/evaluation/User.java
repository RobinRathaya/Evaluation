package com.chainsys.evaluation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

	public static EmployeeTopics selection(Scanner scanner, Employee employee)
			throws Exception {

		EmployeeTopics employeeTopics = null;
		System.out.println("Enter the topic name");
		String topicName = scanner.next();

		System.out.println("Select from the status");
		System.out.println("1.Not yet started ");
		System.out.println("2.Beginner");
		System.out.println("3.Intermediate");
		System.out.println("4.Expert");
		employeeTopics = new EmployeeTopics();
		employeeTopics.topic = new Topics();
		employeeTopics.status = new Status();
		employeeTopics.employee = new Employee();
		int statusOption = scanner.nextInt();
		switch (statusOption) {

		case 1: {
			employeeTopics.employee.setId(employee.getId());
			employeeTopics.topic.setName(topicName);
			employeeTopics.status.setId(1);
			LocalDateTime localDateTime = LocalDateTime.now();
			employeeTopics.setCreatedOn(localDateTime);
			employeeTopics.setUpdatedOn(null);

		}
			break;
		case 2: {
			employeeTopics.employee.setId(employee.getId());
			employeeTopics.topic.setName(topicName);
			employeeTopics.status.setId(2);
			LocalDateTime localDateTime = LocalDateTime.now();
			employeeTopics.setCreatedOn(localDateTime);
			employeeTopics.setUpdatedOn(null);
			return employeeTopics;

		}
		case 3: {
			employeeTopics.employee.setId(employee.getId());
			employeeTopics.topic.setName(topicName);
			employeeTopics.status.setId(3);
			LocalDateTime localDateTime = LocalDateTime.now();
			employeeTopics.setCreatedOn(localDateTime);

		}
			break;
		case 4: {
			employeeTopics.employee.setId(employee.getId());
			employeeTopics.topic.setName(topicName);
			employeeTopics.status.setId(4);
			LocalDateTime localDateTime = LocalDateTime.now();
			employeeTopics.setCreatedOn(localDateTime);
			employeeTopics.setUpdatedOn(null);
			

		}
			break;
		}
		return employeeTopics;
	}

	public static void choice(Scanner scanner, Employee employee)
			throws Exception {

		char select;
		do {
			System.out.println("1.Display Topics");
			System.out.println("2.New status");
			System.out.println("3.Update status");

			int option = scanner.nextInt();

			switch (option) {
			case 1: {
				ArrayList<Topics> topicList = new ArrayList<>();
				topicList = Functionalites.findTopics();
				Functionalites.displayTopics(topicList);
			}
				break;
			case 2: {
				EmployeeTopics employeeTopics = selection(scanner, employee);

				Functionalites.newStatus(employeeTopics);
			}
				break;
			case 3: {
				EmployeeTopics employeeTopics = selection(scanner, employee);
				LocalDateTime localDateTime = LocalDateTime.now();
				employeeTopics.setUpdatedOn(localDateTime);
				Functionalites.updateStatus(employeeTopics);
			}

			}
			System.out.println("Do you want continue (Y/y)");
			select = scanner.next().charAt(0);
		} while (select == 'y' || select == 'Y');
		choice(scanner, employee);
	}

	public static void main(String[] args) {
		System.out.println("Thanks for using");
	}

}
