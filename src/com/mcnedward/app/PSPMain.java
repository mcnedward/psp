package com.mcnedward.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.mcnedward.app.pg.ingsw.ProblemGenerator;
import com.mcnedward.app.util.BaseComparator;
import com.mcnedward.app.util.GraphComparator;
import com.mcnedward.app.util.TaskDepComparator;

public class PSPMain {

	public final static String RESOURCE_DIRECTORY = "resources/";
	public final static String RESOURCE_FILE = "int-gen-20-15.conf";
	public final static String OUTPUT_FILE_PREFIX = "int-gen-20-15-output_";

	private List<Employee> employees;
	private List<Task> tasks;
	private List<Graph> graphs;

	private float totalDaysToComplete;

	private float totalCost;

	public PSPMain() {
		employees = new ArrayList<>();
		tasks = new ArrayList<>();
		graphs = new ArrayList<>();
		
		Properties file = createFile();
		
		parse(file);
		
		evaluate();
	}
	
	public static Properties createFile() {
		Random random = new Random();
		String outputFileName = RESOURCE_DIRECTORY + OUTPUT_FILE_PREFIX + random.nextInt() + ".txt";
		Properties prop = null;
		try {
			String[] parameters = new String[] { RESOURCE_DIRECTORY + RESOURCE_FILE, outputFileName };
			prop = new ProblemGenerator().main(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public void parse(Properties file) {
		Parser parser = new Parser(file);
		parser.parseFile();

		employees = parser.getEmployees();
		tasks = parser.getTasks();
		graphs = parser.getGraphs();
	}
	
	public void evaluate() {
		employees.sort(new BaseComparator());
		tasks.sort(new BaseComparator());
		graphs.sort(new GraphComparator());

		findEmployeesWorkingOnTasks();

		setupTaskDependencies();
		tasks.sort(new TaskDepComparator());

		calculateTasks();

//		System.out.printf("Total Hours: %s\nTotal Days: %s\nTotal Cost: %s\n", totalHoursToComplete, totalDaysToComplete,
//				totalCost);
//		
//		System.out.println("Fitness: " + calculateFeasibleFitness());
	}
	
	public void calculateTasks() {
		float totalDays = 0f;
		float projectCost = 0f;
		for (Task task : tasks) {
			float cost = 0f;
			float dedication = 0f;
			for (Employee e : task.getEmployees()) {
				cost += e.getHourlySalary();
				dedication += e.getDedication();
			}
			projectCost += cost;
			task.setCost(cost);
			if (task.getEffort() != 0) {
				float daysToComplete = dedication / task.getEffort();
				totalDays += daysToComplete;
				float hoursToComplete = daysToComplete * 8;
				task.setDaysToComplete(daysToComplete);
				task.setHoursToComlete(hoursToComplete);
			}
		}
		totalDaysToComplete = totalDays;
		totalCost = projectCost;
	}

	/**
	 * Calculate the feasible fitness.
	 * q = Wco * Pco + Wdu * Pdu
	 */
	public float calculateFeasibleFitness() {
		return (float) ((Math.pow(10, -6) * totalCost) + (0.1 * totalDaysToComplete)); 
	}

	private void findEmployeesWorkingOnTasks() {
		for (Task task : tasks) {
			for (Skill skillInTask : task.getSkills()) {
				for (Employee employee : employees) {
					if (task.getEmployees().contains(employee))
						continue;
					for (Skill employeeSkill : employee.getSkills()) {
						if (employeeSkill.getId() == skillInTask.getId())
							task.addEmployee(employee);
					}
				}
			}
		}
	}

	private void setupTaskDependencies() {
		for (Graph graph : graphs) {
			Task waitingTask = getTaskByNumber(graph.getTaskToWait());
			Task toCompleteTask = getTaskByNumber(graph.getTaskToComplete());
			waitingTask.addDependency(toCompleteTask);
		}
	}

	public Employee getEmployeeByNumber(int number) {
		for (Employee e : employees) {
			if (e.getNumber() == number)
				return e;
		}
		return null;
	}

	public Task getTaskByNumber(int number) {
		for (Task t : tasks) {
			if (t.getNumber() == number)
				return t;
		}
		return null;
	}
	
}
