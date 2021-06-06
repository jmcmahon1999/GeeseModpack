package net.ducko.geesemodpack;

import java.io.File;

public class InitialisationTask extends Task {

	String path;
	
	public InitialisationTask(String prefix, String path) {
		this.path = path;
		this.prefix = prefix;
	}
	
	@Override
	public int execute() {
		File minecraftDir = new File(path);
		if (minecraftDir.exists()) {
			return weight;
		} else {
			return -1;
		}
	}

	@Override
	public String getName() {
		return prefix;
	}
	
	

}
