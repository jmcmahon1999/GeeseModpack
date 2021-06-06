package net.ducko.geesemodpack;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;


public class OptifineDownloadTask extends Task {

	private String optifineURL = "https://optifine.net/downloadx?f=";
	private String optifineSuffix = "&x=6cd670eb3ac9c8f859170f159807d566";
	private String path;

	public OptifineDownloadTask(String prefix, int weight, String version, String path) {
		this.prefix = prefix;
		this.weight = weight;
		this.filename = "Optifine-" + version + ".jar";
		this.path = path;
	}
	
	@Override
	public int execute() {
		
		final String url = optifineURL + filename + optifineSuffix;
    	final String filepath = path+"\\mods\\";
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
