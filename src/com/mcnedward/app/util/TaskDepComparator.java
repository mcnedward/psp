package com.mcnedward.app.util;

import java.util.Comparator;

import com.mcnedward.app.Task;

public class TaskDepComparator implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {
		return o1.getDependencies().size() < o2.getDependencies().size() ? -1 : o1.getDependencies().size() == o2.getDependencies().size() ? 0 : 1;
	}
	
}
