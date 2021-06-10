package net.ducko.geesemodpack;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.SystemUtils;

public class ForgeInstallTask extends Task {

	private String path;
	
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
				Process proc = Runtime.getRuntime().exec("java -jar "+forge.getAbsolutePath());
				System.out.println(proc.waitFor());
				proc.destroy();
				forge.deleteOnExit();
			}
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
