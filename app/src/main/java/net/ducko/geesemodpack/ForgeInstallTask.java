package net.ducko.geesemodpack;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.SystemUtils;

public class ForgeInstallTask extends Task {

	private String path;
	Process proc;
	
	public ForgeInstallTask(String prefix, String path, int weight, String version) {
		this.prefix = prefix;
		this.path = path;
		this.weight = weight;
		this.filename = "forge-" + version + "-installer.jar";
	}
	
	@Override
	public int execute() {
		
		try {
			if (SystemUtils.IS_OS_MAC) {
				try {
					URI uri = new URI("https://files.minecraftforge.net/net/minecraftforge/forge/");
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(uri);
						} catch (IOException err) { err.printStackTrace(); }
					} else { System.out.println("No browser.");}
				} catch (URISyntaxException err) {
					err.printStackTrace();
				}
			} else {
				String forgePath = path + filename;
				File forge = new File(forgePath);
				String cmd = "java -jar "+forge.getAbsolutePath();
				proc = Runtime.getRuntime().exec(cmd);
				ReadStream s1 = new ReadStream("stdin", proc.getInputStream());
				ReadStream s2 = new ReadStream("stderr", proc.getErrorStream());
				s1.start();
				s2.start();
				proc.waitFor();
				forge.deleteOnExit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		} finally {
			if(proc != null) {
				proc.destroy();
			}
		}
		
		return weight;
	}

	@Override
	public String getName() {
		return prefix;
	}

}
