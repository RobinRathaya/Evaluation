package com.chainsys.evaluation;

import java.util.Scanner;

public class User {
	public static void choice(Scanner scanner) throws Exception {

		char select;
		do {
			System.out.println("Choose from the action");
			System.out.println("1.Sign up");
			System.out.println("2.Login");
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				registerDetails(scanner);
				break;
			case 2:
				login(scanner);
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
			select = scanner.next().charAt(0);
		} while (select == 'y' || select == 'Y');

		choice(scanner);
	}

	
	private static void login(Scanner scanner) {
		// TODO Auto-generated method stub
		Employee employee=new Employee();
		
		System.out.println("Enter Email");
		String email=scanner.next();
		employee.setEmail(email);
		
		System.out.println("Enter Password");
		String password=scanner.next();
		employee.setEmail(password);
		
		Functionalites.login(employee);
	}


	private static void registerDetails(Scanner scanner) throws Exception {
		// TODO Auto-generated method stub
		Employee employee =new Employee();
		
		System.out.println("Enter employee ID");
		int id =scanner.nextInt();
		employee.setId(id);
		
		System.out.println("Enter the Name");
		String name=scanner.next();
		employee.setName(name);
		
		System.out.println("Enter the Email");
		String email=scanner.next();
		employee.setName(email);
		
		System.out.println("Enter the password");
		String password=scanner.next();
		employee.setName(password);
		
		Functionalites.addEmployee(employee);
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		choice(scanner);
	}

}
