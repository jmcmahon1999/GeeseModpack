package net.ducko.geesemodpack;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;


public class OptifineDownloadTask extends Task {

	private String optifineURL = "https://optifine.net/downloadx?f=";
	private String optifineSuffix = "&x=6cd670eb3ac9c8f859170f159807d566";
	private String localPath;

	public OptifineDownloadTask(String type, int weight, String version) {
		this.name = "Optifine";
		this.type = type;
		this.weight = weight;
		this.filename = "Optifine-" + version + ".jar";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("files");
		localPath = url.getPath();
	}
	
	@Override
	public void execute() {
		
		final String url = optifineURL + filename + optifineSuffix;
    	final String filepath = localPath+"/mods/"+filename;
		
		/*AsyncHttpClient client = Dsl.asyncHttpClient();
		try {
			FileOutputStream stream = new FileOutputStream(filepath);
			System.out.println("Downloading Optifine");
			client.prepareGet(url).execute(new AsyncCompletionHandler<FileOutputStream>() {
				
				@Override
			    public State onStatusReceived(HttpResponseStatus responseStatus)
			      throws Exception {
			        return null;
			    }


			    @Override
			    public State onBodyPartReceived(HttpResponseBodyPart bodyPart) 
			      throws Exception {
			        stream.getChannel().write(bodyPart.getBodyByteBuffer());
			        return State.CONTINUE;
			    }

			    @Override
			    public FileOutputStream onCompleted(Response response) 
			      throws Exception {
			    	System.out.println("Optifine Downloaded");
			        return stream;
			    }
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
    	
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

	}

}
