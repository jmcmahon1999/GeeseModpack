package net.ducko.geesemodpack;

import java.io.IOException;
import java.util.List;
import javax.swing.SwingWorker;

import org.apache.commons.lang3.SystemUtils;
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
				if (!SystemUtils.IS_OS_MAC) {
					Task forgeTask = new ForgeDownloadTask("Downloading: ", 10, json.getString("forge_version"), path);
					tasks.register(forgeTask);
				}
				
				JSONArray mods = json.getJSONArray("mods");
				for (int i=0; i<mods.length(); i++) {
					JSONObject mod = mods.getJSONObject(i);
					Task jarTask = new JarDownloadTask("Downloading: ", 5, mod.getInt("mod_id"), mod.getInt("file_id"), mod.getString("filename"), path);
					tasks.register(jarTask);
				}
				Task resourceTask = new ResourceDownloadTask("Downloading: ", 5, path);
				tasks.register(resourceTask);
				Task extractTask = new ExtractTask("Extracting", 5, path);
				tasks.register(extractTask);
				
				display.progressBar.setMaximum(tasks.size());
				for (Task t : tasks) {
					String s = t.getName();
					Integer i = t.weight;
					publish(new Pair(i, s));
					t.execute();
				}
			} else {
				display.progressLabel.setText("Failed");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
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
		Task installForgeTask = new ForgeInstallTask("Installing Minecraft Forge", path, 0, json.getString("forge_version"));
		display.progressLabel.getName();
		display.progressBar.setValue(display.progressBar.getValue() + installForgeTask.weight);
		installForgeTask.execute();
		display.progressLabel.setText("Complete");
	}
	
	private boolean loadJSON() throws IOException, JSONException {
    	json = JsonReader.readJsonFromUrl("https://www.ducko.net/geese/manifest.json");
    	return json != null;
    }
	
	

}
