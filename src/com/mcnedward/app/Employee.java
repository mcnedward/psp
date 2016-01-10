package com.mcnedward.app;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Base {
	
	private float salary;
	private List<Task> tasks;

	public Employee(int number) {
		super(number, "Employee");
		tasks = new ArrayList<>();
	}
	
	public void addTask(Task task) {
		tasks.add(task);
		if (!task.getEmployees().contains(this))
			task.addEmployee(this);
	}

	public float getDedication() {
		float dedication = (float) (Math.abs(((float)tasks.size() - (skills.size() * 1.9))) / 8);
		if (dedication > 1)
			dedication = 1;
		return dedication;
	}
	
	public float getHourlySalary() {
		return ((salary / 40) / 5) / 8;
	}
	
	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public String toString(boolean full) {
		if (full)
			return super.toString();
		else
			return String.format("Employee[%s] - Salary: $%s", number, salary);
	}

}