package com.mcnedward.app.util;

import java.util.Comparator;

import com.mcnedward.app.Base;

public class BaseComparator implements Comparator<Base> {

	@Override
	public int compare(Base o1, Base o2) {
		return o1.getNumber() < o2.getNumber() ? -1 : o1.getNumber() == o2.getNumber() ? 0 : 1;
	}
	
}
