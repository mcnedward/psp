package com.mcnedward.app;

public class Graph {

	private int arcNum;
	private int taskToComplete;
	private int taskToWait;

	public Graph(int arcNum, int taskToComplete, int taskToWait) {
		this.arcNum = arcNum;
		this.taskToComplete = taskToComplete;
		this.taskToWait = taskToWait;
	}

	public int getArcNum() {
		return arcNum;
	}

	public void setArcNum(int arcNum) {
		this.arcNum = arcNum;
	}

	public int getTaskToComplete() {
		return taskToComplete;
	}

	public void setTaskToComplete(int taskToComplete) {
		this.taskToComplete = taskToComplete;
	}

	public int getTaskToWait() {
		return taskToWait;
	}

	public void setTaskToWait(int taskToWait) {
		this.taskToWait = taskToWait;
	}

	@Override
	public String toString() {
		return String.format("Graph[%s] - To Complete: %s | To Wait: %s", arcNum, taskToComplete, taskToWait);
	}
	
}
