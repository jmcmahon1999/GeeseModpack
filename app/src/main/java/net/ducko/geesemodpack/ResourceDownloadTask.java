package net.ducko.geesemodpack;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class ResourceDownloadTask extends Task {

	private String filepath;
	private final String url = App.DUCKO_URL + "resources.zip";
	private final String filename = "resources.zip";
	
	public ResourceDownloadTask(String prefix, int weight, String path) {
		this.prefix = prefix;
		this.weight = weight;
		this.filepath = path;
	}
	
	@Override
	public int execute() {
		
		File file = new File(filepath, filename);
		
		try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
				  FileOutputStream fileOutputStream = new FileOutputStream(file)) {
		    byte dataBuffer[] = new byte[1024];
		    int bytesRead;
		    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
		        fileOutputStream.write(dataBuffer, 0, bytesRead);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return weight;
	}

	@Override
	public String getName() {
		return prefix + filename;
	}

}
