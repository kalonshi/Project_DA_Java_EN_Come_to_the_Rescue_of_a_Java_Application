package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;
	
	/**
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile (String filepath) {
		this.filepath = filepath;
	}

	@Override
	public List<String> getSymptoms() {
		ArrayList<String> listOfSymptomsUploaded = new ArrayList();

		if (filepath != null) {  // check if the path is not empty
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filepath));
				String line = reader.readLine();
				while (line != null) {
					if (!line.isEmpty())  // check if the line is not empty
						listOfSymptomsUploaded.add(line);  // add a symptom to the list listOfSymptomsUploaded
					line = reader.readLine();
				}

				reader.close();

				return listOfSymptomsUploaded;
			} catch (IOException e) {
				e.printStackTrace();

			}
		}

		return listOfSymptomsUploaded;
	}

}
