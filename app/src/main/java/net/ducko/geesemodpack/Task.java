package net.ducko.geesemodpack;

public abstract class Task {
	public String type;
	public String name;
	public int weight;
	public String filename;
	
	public abstract void execute();
	
}
