package net.ducko.geesemodpack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ExtractTask extends Task {

	String path;
	
	public ExtractTask(String prefix, int weight, String path) {
		this.prefix = prefix;
		this.weight = weight;
		this.path = path;
	}
	
	@Override
	public int execute() {
		
		File dest = new File(path);
		File resources = new File(path+"resources.zip");
		
		ZipFile zip = new ZipFile(resources);
		try {
			zip.extractAll(path);
		} catch (ZipException e) {
			e.printStackTrace();
		}
		resources.delete();
		return weight;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return prefix;
	}

}
