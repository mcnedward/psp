package com.mcnedward.app;

import java.util.ArrayList;
import java.util.List;

import com.mcnedward.app.util.SkillComparator;

public abstract class Base {

	protected int number;
	protected List<Skill> skills;
	protected int skillCount;
	private String type;

	public Base(int number, String type) {
		this.number = number;
		this.type = type;
		skills = new ArrayList<>();
	}

	public void addSkill(String key, String value) {
		int valueInt = Integer.parseInt(value);
		if (key.equals("number"))
			skillCount = valueInt;
		else
			skills.add(new Skill(Integer.parseInt(key), valueInt));
	}
	
	public Skill findSkillByNumber(int number) {
		for (Skill skill : skills)
			if (skill.getNumber() == number)
				return skill;
		return null;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public int getSkillCount() {
		return skillCount;
	}

	public void setSkillCount(int skillCount) {
		this.skillCount = skillCount;
	}
	
	@Override
	public String toString() {
		skills.sort(new SkillComparator());
		StringBuilder builder = new StringBuilder(String.format("%s: Number[%s]\nSkill Count[%s]\n", type, number, skillCount));
		for (Skill skill : skills) {
			builder.append(String.format("Skill[%s] - Id: %s\n", skill.getNumber(), skill.getId()));
		}
		return builder.toString();
	}

}
