package net.ducko.geesemodpack;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
	
	private int count;
	
	public TaskList() {
		count=0;
	}
	
	public void execute() {
		for (Task t : this) {
			t.execute();
		}
	}
	
	public void register(Task t) {
		count += t.weight;
		this.add(t);
	}
	
}
