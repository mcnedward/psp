package com.mcnedward.app.util;

import java.util.Comparator;

import com.mcnedward.app.Skill;

public class SkillComparator implements Comparator<Skill> {

	@Override
	public int compare(Skill o1, Skill o2) {
		return o1.getNumber() < o2.getNumber() ? -1 : o1.getNumber() == o2.getNumber() ? 0 : 1;
	}
	
}
