package net.ducko.geesemodpack;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class InstallForgeTask extends Task {

	private String path;
	
	public InstallForgeTask(String prefix, String path, int weight) {
		this.prefix = prefix;
		this.path = path;
		this.weight = weight;
	}
	
	@Override
	public int execute() {
		
		File minecraftDir = new File(path);
		
		File forge = minecraftDir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return !pathname.isDirectory() && pathname.getName().startsWith("forge");
			}
			
		})[0];
		try {
			Process proc = Runtime.getRuntime().exec("javaw -jar "+forge.getAbsolutePath());
			System.out.println(proc.waitFor());
			proc.destroy();
			forge.delete();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		return weight;
	}

	@Override
	public String getName() {
		return prefix;
	}

}
