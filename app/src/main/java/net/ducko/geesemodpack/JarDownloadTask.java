package net.ducko.geesemodpack;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

import com.therandomlabs.curseapi.CurseAPI;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import java.nio.file.Paths;

public class JarDownloadTask extends Task {

	private int modID;
	private int fileID;
	private String filename;
	private String path;
	
	public JarDownloadTask(String prefix, int weight, int modID, int fileID, String filename, String path) {
		this.prefix = prefix;
		this.weight = weight;
		this.modID = modID;
		this.fileID = fileID;
		this.filename = filename;
		this.path = path;
	}
	
	@Override
	public int execute() {
		try {
			Optional<CurseFile> file = CurseAPI.file(modID, fileID);
			File f = new File(path + "mods");
			if (file.isPresent()) {
				CurseFile mod = file.get();
				Path path = Paths.get(f.getAbsolutePath());
				File modFile = new File(path.toString() +"/"+ filename);
				if (!modFile.exists()) {
					mod.downloadToDirectory(path);
				}
			}
		} catch (CurseException e) {
			e.printStackTrace();
		}
		
		return weight;
	}

	@Override
	public String getName() {
		return prefix + filename;
	}

}
