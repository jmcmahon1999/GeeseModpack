package net.ducko.geesemodpack;

public abstract class Task {
	public String prefix;
	public int weight;
	public String filename;
	
	public abstract int execute();
	
	public abstract String getName();
}
