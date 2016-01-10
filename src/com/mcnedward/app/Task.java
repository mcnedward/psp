package com.mcnedward.app;

import java.util.ArrayList;
import java.util.List;

public class Task extends Base {

	private float effort;
	private List<Employee> employees;
	
	private float daysToComplete;
	private float hoursToComlete;
	
	private float cost;
	
	private boolean feasible;

	private List<Task> dependencies;

	public Task(int number) {
		super(number, "Task");
		employees = new ArrayList<>();
		dependencies = new ArrayList<>();
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
		if (!employee.getTasks().contains(this))
			employee.addTask(this);
	}
	
	public void addDependency(Task task) {
		dependencies.add(task);
	}
	
	public float getEffort() {
		return effort;
	}

	public void setEffort(float effort) {
		this.effort = effort;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public List<Task> getDependencies() {
		return dependencies;
	}

	public float getDaysToComplete() {
		return daysToComplete;
	}

	public void setDaysToComplete(float daysToComplete) {
		this.daysToComplete = daysToComplete;
	}

	public float getHoursToComlete() {
		return hoursToComlete;
	}

	public void setHoursToComlete(float hoursToComlete) {
		this.hoursToComlete = hoursToComlete;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public boolean isFeasible() {
		return feasible;
	}
	
	public void setFeasible(boolean feasible) {
		this.feasible = feasible;
	}

	public String toString(boolean full) {
		if (full)
			return super.toString();
		else
			return String.format("Task[%s] - Effort: %s", number, effort);
	}

}
