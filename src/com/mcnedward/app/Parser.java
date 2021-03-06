package com.mcnedward.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

public class Parser {

	private Properties file;
	
	private List<Employee> employees;
	private List<Task> tasks;
	private List<Graph> graphs;
	public static int employeeCount, taskCount, graphCount;

	public Parser(Properties file) {
		this.file = file;
		employees = new ArrayList<>();
		tasks = new ArrayList<>();
		graphs = new ArrayList<>();
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public List<Graph> getGraphs() {
		return graphs;
	}
	
	private Employee getEmployeeByNumber(int number) {
		for (Employee e : employees) {
			if (e.getNumber() == number)
				return e;
		}
		return null;
	}

	private Task getTaskByNumber(int number) {
		for (Task t : tasks) {
			if (t.getNumber() == number)
				return t;
		}
		return null;
	}
	
	public void parseFile() {
		for(Entry<Object, Object> entry : file.entrySet()) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
		    
		    String[] args = key.split("\\.|=");
			
			if (key.contains("graph")) {
				if (key.contains("number")) {
					graphCount = Integer.parseInt(value);
					continue;
				}
				String[] values = value.split(" ");
				graphs.add(new Graph(Integer.parseInt(args[2]), Integer.parseInt(values[0]), Integer.parseInt(values[1])));
				continue;
			}

			String type = args[0];
			switch (type) {
			case "employee":
				String arg2 = args[1];
				int number = 0;
				if (arg2.equals("number")) {
					employeeCount = Integer.parseInt(value);
					continue;
				} else {
					number = Integer.parseInt(args[1]);
				}
				Employee employee = getEmployeeByNumber(number);
				if (employee == null)
					employee = new Employee(number);

				String arg3 = args[2];
				if (arg3.equals("salary"))
					employee.setSalary(Float.parseFloat(value));
				if (arg3.equals("skill"))
					employee.addSkill(args[3], value);

				if (!employees.contains(employee))
					employees.add(employee);

				break;
			case "task":
				String taskArg2 = args[1];
				int taskNumber = 0;
				if (taskArg2.equals("number")) {
					taskCount = Integer.parseInt(value);
					continue;
				} else {
					taskNumber = Integer.parseInt(args[1]);
				}
				Task task = getTaskByNumber(taskNumber);
				if (task == null)
					task = new Task(taskNumber);

				String taskArg3 = args[2];
				if (taskArg3.equals("cost"))
					task.setEffort(Math.abs(Float.parseFloat(value)));
				if (taskArg3.equals("skill"))
					task.addSkill(args[3], value);

				if (!tasks.contains(task))
					tasks.add(task);

				break;
			}
		}
	}
	
}
