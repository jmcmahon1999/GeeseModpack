package net.ducko.geesemodpack;

import java.io.File;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

import com.therandomlabs.curseapi.CurseAPI;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFiles;
import com.therandomlabs.curseapi.project.CurseProject;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JarDownloadTask extends Task {

	private String name;
	private String type;
	private int weight;
	private JSONObject json;
	private int modID;
	private int fileID;
	private String filename;
	private String localPath;
	
	public JarDownloadTask(String type, int weight, int modID, int fileID, String filename) {
		this.type = type;
		this.weight = weight;
		this.modID = modID;
		this.fileID = fileID;
		this.filename = filename;
		CurseProject mod = getInfo();
		this.name = mod.name();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("files");
		localPath = url.getPath();
	}
	
	@Override
	public void execute() {
		try {
			Optional<CurseFile> file = CurseAPI.file(modID, fileID);
			if (file.isPresent()) {
				CurseFile mod = file.get();
				File f = new File(localPath + "/mods");
				Path path = Paths.get(f.getAbsolutePath());
				mod.downloadToDirectory(path);
				System.out.println("Done");
			}
		} catch (CurseException e) {
			e.printStackTrace();
		}
	}
	
	private CurseProject getInfo() {
		try {
			Optional<CurseProject> project = CurseAPI.project(modID);
			if (project.isPresent()) {
				CurseProject mod = project.get();
				return mod;
			}
		} catch (CurseException e) {
			e.printStackTrace();
		} 
		return null;
	}

}
