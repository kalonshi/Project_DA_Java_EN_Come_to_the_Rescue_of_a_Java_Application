package com.hemebiotech.analytics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class AnalyticsCounter {

	
	public static void main(String args[]) throws Exception {
		AnalyticsCounter analyticsCounter = new AnalyticsCounter();
		List<String> symptoms = analyticsCounter.getListSymptoms();
		TreeMap<String, Integer> nbOccurenceBySymptoms = analyticsCounter.setNbOccurenceBySymptom(symptoms);
		analyticsCounter.editResults(nbOccurenceBySymptoms);

	}
	public TreeMap<String, Integer> setNbOccurenceBySymptom(List<String> listSymptoms) {
		TreeMap<String, Integer> resultat = new TreeMap<>();
		if (!listSymptoms.isEmpty()) {
			for (String symptom : listSymptoms) {
				if (resultat.containsKey(symptom)) {
					int counter;
					counter = resultat.get(symptom).intValue() + 1;
					resultat.put(symptom, counter);
				} else {
					resultat.put(symptom, 1);
				}
			}
			return resultat;
		}
		return resultat;
	}
	public List<String> getListSymptoms() {
		ReadSymptomDataFromFile readSymptomDataFromFile = new ReadSymptomDataFromFile("symptoms.txt");
		List<String> rawLlistOfSymptoms = readSymptomDataFromFile.getSymptoms();
		return rawLlistOfSymptoms;
	}
	public void editResults(TreeMap<String, Integer> nbOccurenceBySymptoms) {
		FileWriter writer;
		try {
			writer = new FileWriter("result.out");


			for (Map.Entry<String, Integer> entry : (nbOccurenceBySymptoms).entrySet()) {
				String key = entry.getKey();
				Integer value = entry.getValue();

				writer.write("number of " + key + ": " + value + "\n"); 	}
			writer.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
