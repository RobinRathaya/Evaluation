package com.chainsys.evaluation;

import java.awt.DisplayMode;
import java.util.ArrayList;


public class Hr {
		
		public static void main(String[] args) {
			
			ArrayList<EmployeeTopics> employeeTopicsList = new ArrayList<EmployeeTopics>();
			employeeTopicsList=Functionalites.selectEmployeeEvaluation();
			Functionalites.displayEmployeeEvaluation(employeeTopicsList);
			
		}
}
