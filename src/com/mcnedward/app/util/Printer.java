package com.mcnedward.app.util;

import java.util.List;

import com.mcnedward.app.Employee;
import com.mcnedward.app.Task;

public class Printer {

	public static void printOutEmployees(List<Employee> employees) {
		employees.sort(new BaseComparator());
		for (Employee employee : employees) {
			System.out.println(employee.toString());
		}
	}

	public static void printOutTasks(List<Task> tasks) {
		tasks.sort(new BaseComparator());
		for (Task task : tasks) {
			System.out.println(task.toString());
		}
	}

	public static void printEmployeesOnTask(List<Task> tasks) {
		for (Task task : tasks) {
			System.out.println(String.format("********** Employees on Task %s [%s] **********", task.getNumber(),
					task.getEmployees().size()));
			task.getEmployees().sort(new BaseComparator());
			for (Employee employee : task.getEmployees()) {
				System.out.println(employee.toString(false));
			}
			System.out.println();
		}
	}

	public static void printTaskCountForEmployees(List<Employee> employees) {
		System.out.println("********** Task Count for Employees **********");
		for (Employee employee : employees)
			System.out.println(
					String.format("Employee[%s] - Task Count: %s", employee.getNumber(), employee.getTasks().size()));
		System.out.println();
	}

	public static void printTaskDependencies(List<Task> tasks) {
		System.out.println("********** Task Dependencies **********");
		for (Task task : tasks) {
			if (task.getDependencies().size() == 0) {
				System.out.println(String.format("Task[%s] - Effort: %s has no dependencies.", task.getNumber(), task.getEffort()));
			} else {
				System.out.println(
						String.format("Task[%s] - Effort: %s depends on the following tasks to be complete:", task.getNumber(), task.getEffort()));
				for (Task t : task.getDependencies()) {
					System.out.println(String.format(t.toString(false)));
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
