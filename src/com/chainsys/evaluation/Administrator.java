package com.chainsys.evaluation;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrator {

	public static void choice(Scanner scanner) throws Exception {

		char select;
		do {
			System.out.println("1.Insert new Topic");
			System.out.println("2.Display Topic");
			System.out.println("3.Delete Topic");
			System.out.println("4.Update Topic");

			int option = scanner.nextInt();

			switch (option) {
			case 1: {
				Topics topic = new Topics();
				System.out.println("Enter the topic name");
				String topicName = scanner.next();
				topic.setName(topicName);
				Functionalites.addTopic(topic);
				}
			
				break;
			case 2: {
				ArrayList<Topics> topicList = new ArrayList<>();
				topicList = Functionalites.findTopics();
				Functionalites.displayTopics(topicList);
			}
				break;
			case 3: {
				Topics topic = new Topics();
				System.out.println("Enter the topic name");
				String topicName = scanner.next();
				topic.setName(topicName);
				Functionalites.deleteTopic(topic);
				System.out.println("Successfully Deleted");
			}
				break;

			case 4: {
				Topics topic = new Topics();
				System.out.println("Enter the topic ID");
				int topicId = scanner.nextInt();
				topic.setId(topicId);
				System.out.println("Enter the topic name");
				String name = scanner.next();
				topic.setName(name);
				Functionalites.updateTopic(topic);
				System.out.println("Successfully Updated");
			}
				break;
			}
			System.out.println("Do you want continue (Y/y)");
			select = scanner.next().charAt(0);
		} while (select == 'y' || select == 'Y');
		  choice(scanner);
	}

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		choice(scanner);	
		System.out.println("Thanks for using");

	}

}
