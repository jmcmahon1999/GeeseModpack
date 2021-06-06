package net.ducko.geesemodpack;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ForgeDownloadTask extends Task {
	
	private final String forgeURL = "https://maven.minecraftforge.net/net/minecraftforge/forge/";
	private String version;
	private String filepath;

	public ForgeDownloadTask(String prefix, int weight, String version) {
		this.prefix = prefix;
		this.weight = weight;
		this.version = version;
		this.filename = "forge-" + version + "-installer.jar";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("files");
		String localPath = url.getPath();
		this.filepath =  localPath + "/" + filename;
	}
	
	@Override
	public int execute() {
		
		final String url = forgeURL + version + "/" + filename;
		
		try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
				  FileOutputStream fileOutputStream = new FileOutputStream(filepath)) {
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
