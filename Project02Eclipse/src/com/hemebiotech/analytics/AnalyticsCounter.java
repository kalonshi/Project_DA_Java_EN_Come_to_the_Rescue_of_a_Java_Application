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
	/**
	 *
	 * Method for calculating the number of occurrences per symptom
	 *
	 * @param listSymptoms a raw listing of all Symptoms obtained from a data source, duplicates are possible/probable
	 * @return  resultat a sort listing, TreeMap <String,Integer> Symptom  as key and counter as value
	 *
	 */
	public TreeMap<String, Integer> setNbOccurenceBySymptom(List<String> listSymptoms) {
		TreeMap<String, Integer> resultat = new TreeMap<>(); // initialize a TreeMap <TreeMap<symptom, counter>
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
	/**
	 * get a raw listing of all Symptoms obtained from a data source,
	 * 	      duplicates are possible/probable and null are not possible.
	 *If no data is available, return an empty List
	 * @return  listSymptoms <List>
	 *
	 * @See ReadSymptomDataFromFile
	 */
	public List<String> getListSymptoms() {
		ReadSymptomDataFromFile readSymptomDataFromFile = new ReadSymptomDataFromFile("symptoms.txt");
		List<String> rawLlistOfSymptoms = readSymptomDataFromFile.getSymptoms();
		return rawLlistOfSymptoms;
	}
	/**
	 * generate output of a sort listing of symptoms , a TreeMap <String,Integer>
	 *
	 * @param nbOccurenceBySymptoms  is a TreeMap <String , Integer> , where
	 *                               each  symptom  is the key and the nb of occurences
	 *                              is the value
	 */
	public void editResults(TreeMap<String, Integer> nbOccurenceBySymptoms) {
		FileWriter writer;
		try {
			writer = new FileWriter("result.out");


			for (Map.Entry<String, Integer> entry : (nbOccurenceBySymptoms).entrySet()) {
				String key = entry.getKey();  // Symptom label
				Integer value = entry.getValue();  // Nb of occurences of the symptom

				writer.write("number of " + key + ": " + value + "\n"); 	}
			writer.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
