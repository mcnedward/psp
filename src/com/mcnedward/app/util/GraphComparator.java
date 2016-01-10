package com.mcnedward.app.util;

import java.util.Comparator;

import com.mcnedward.app.Graph;

public class GraphComparator implements Comparator<Graph> {

	@Override
	public int compare(Graph o1, Graph o2) {
		return o1.getArcNum() < o2.getArcNum() ? -1 : o1.getArcNum() == o2.getArcNum() ? 0 : 1;
	}

}
