package com.chainsys.evaluation;

import java.util.ArrayList;


public class Hr {
		public static void call() throws Exception
		{
			System.out.println("Welcome");
			ArrayList<EmployeeTopics> employeeTopicsList = new ArrayList<EmployeeTopics>();
			employeeTopicsList=Functionalites.selectEmployeeEvaluation();
			Functionalites.displayEmployeeEvaluation(employeeTopicsList);
		}
		public static void main(String[] args)  {
			
			System.out.println("thanks");
			
		}
}
