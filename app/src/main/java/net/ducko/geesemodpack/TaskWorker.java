package net.ducko.geesemodpack;

import java.io.IOException;
import java.util.List;
import javax.swing.SwingWorker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TaskWorker extends SwingWorker<Object, Pair> {

	TaskList tasks;
	String path;
	String duckoURL;
	Display display;
	JSONObject json;
	Integer prevWeight = 0;
	
	public TaskWorker(Display display, String path) {
		this.display = display;
		this.path = path;
		tasks = new TaskList();
	}
	
	@Override
	protected Object doInBackground() throws Exception {
		tasks = new TaskList();
        try {
			if (loadJSON()) {
				Task initTask = new InitialisationTask("Initialising", path);
				tasks.register(initTask);
				Task forgeTask = new ForgeDownloadTask("Downloading: ", 10, json.getString("forge_version"));
				tasks.register(forgeTask);
				Task optifineTask = new OptifineDownloadTask("Downloading: ", 5, json.getString("optifine_version"));
				tasks.register(optifineTask);
				JSONArray mods = json.getJSONArray("mods");
				for (int i=0; i<mods.length(); i++) {
					JSONObject mod = mods.getJSONObject(i);
					Task jarTask = new JarDownloadTask("Downloading: ", 5, mod.getInt("mod_id"), mod.getInt("file_id"), mod.getString("filename"));
					tasks.register(jarTask);
				}
				Task moveTask = new MoveFilesTask("Extracting Files", path);
				tasks.register(moveTask);
				Task installForgeTask = new InstallForgeTask("Installing Minecraft Forge", path, 0);
				tasks.register(installForgeTask);
				display.progressBar.setMaximum(tasks.size());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        
		for (Task t : tasks) {
			String s = t.getName();
			Integer i = t.weight;
			publish(new Pair(i, s));
			t.execute();
		}
		return null;
	}
	
	@Override
	protected void process(List<Pair> chunks) {
		int index = chunks.size()-1;
		
		int val = display.progressBar.getValue();
		display.progressLabel.setText(chunks.get(index).s);
		display.progressBar.setValue(val + prevWeight);
		prevWeight = chunks.get(index).i;
	}
	
	@Override
	protected void done() {
		display.progressLabel.setText("Complete");
	}
	
	private boolean loadJSON() throws IOException, JSONException {
    	json = JsonReader.readJsonFromUrl("http://www.ducko.net/geese_modpack/mod_list.json");
    	return json != null;
    }

}
