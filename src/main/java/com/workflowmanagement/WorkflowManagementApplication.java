package com.workflowmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.workflowmanagement.WorkflowManagementApplication"})
public class WorkflowManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkflowManagementApplication.class, args);
	}

}
