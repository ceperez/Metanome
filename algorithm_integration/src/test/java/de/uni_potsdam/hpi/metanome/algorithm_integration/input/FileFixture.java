package de.uni_potsdam.hpi.metanome.algorithm_integration.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileFixture {

	protected String fileData;
	
	public FileFixture(String fileData) {
		this.fileData = fileData;
	}
	
	public String getTestDataPath(String fileName) throws FileNotFoundException {
		String filePath = ClassLoader.getSystemResource("").getPath();
		filePath += fileName;
		File file = new File(filePath);
		// Mark files for deletion once vm exits.
		file.deleteOnExit();
		
		PrintWriter writer = new PrintWriter(file);
		
		writer.print(fileData);
		writer.close();
		
		return filePath;
	}
}
