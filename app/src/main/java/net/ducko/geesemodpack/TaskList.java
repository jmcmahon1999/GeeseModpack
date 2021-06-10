package net.ducko.geesemodpack;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
	
	private static final long serialVersionUID = 3356218565968859042L;
	private int count;
	
	public TaskList() {
		count=0;
	}

	
	public void register(Task t) {
		count += t.weight;
		this.add(t);
	}
	
	@Override
	public int size() {
		return count;
	}
	
}
