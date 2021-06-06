package net.ducko.geesemodpack;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.google.common.io.Files;

public class MoveFilesTask extends Task {

	private String minecraftPath;
	private String localPath;
	
	public MoveFilesTask(String prefix, String path) {
		this.prefix = prefix;
		minecraftPath = path;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("files");
		localPath = url.getPath();
		
	}
	
	@Override
	public int execute() {
		
		
		File localDir = new File(localPath);
		File minecraftDir = new File(minecraftPath);
		for (File f : localDir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
			
		})) {
			String path = minecraftPath + f.getName();
		}
		
		moveFiles(localDir, minecraftDir);
		
		File forge = minecraftDir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return !pathname.isDirectory() && pathname.getName().startsWith("forge");
			}
			
		})[0];
		
		return weight;
	}
	
	private boolean deleteDirectory(File directory) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				deleteDirectory(file);
			}
		}
		return directory.delete();
	}
	
	private ArrayList<String> indexFiles(File file) {
		ArrayList<String> fileList = new ArrayList<String>();
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				fileList.addAll(indexFiles(f));
			}
		} else {
			fileList.add(file.getAbsolutePath());
		}
		return fileList;
	}
	
	private boolean moveFiles(File localDir, File minecraftDir) {
		ArrayList<String> filePaths = indexFiles(localDir);
		for (String p : filePaths) {
			File file = new File(p);
			String path = minecraftDir.getAbsolutePath() + file.getAbsolutePath().replace(localDir.getAbsolutePath(), "");
			File newFile = new File(path);
			newFile.getParentFile().mkdirs();
			try {
				Files.move(file, newFile);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}	
		}
		return true;
	}

	@Override
	public String getName() {
		return prefix;
	}
	
}
