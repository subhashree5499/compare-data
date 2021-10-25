package com.subhashree;

import java.util.Set;
import java.util.TreeSet;

public class ListDiffer<T> {

	private Set<T> addedList = new TreeSet<>();
	private Set<T> unchangedList = new TreeSet<>();
	private Set<T> removedList = new TreeSet<>();

	public ListDiffer(Set<T> beforeList, Set<T> afterList) {
		addedList.addAll(afterList);

		beforeList.forEach(e -> {
			@SuppressWarnings("unused")
			boolean b = addedList.remove(e) ? unchangedList.add(e) : removedList.add(e);
		});
	}

	public Set<T> getAddedList() {
		return addedList;
	}

	public Set<T> getUnchangedList() {
		return unchangedList;
	}

	public Set<T> getRemovedList() {
		return removedList;
	}
}