package com.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Predictor {
    private List<String[]> dataset;
    private Map<String, Integer> labelCounts = new HashMap<>();
    private Map<String, Map<String, Integer>> featureCounts = new HashMap<>();
    private Set<String> possibleLabels = new HashSet<>();

    // Constructor to initialise dataset from raw data
    public Predictor(List<String> rawData) {
        dataset = new ArrayList<>();
        
        // Loop through each line in the raw data
        for (String line : rawData) {
            if (!line.trim().isEmpty()) { // Check if line is not empty
                String[] row = line.split(","); // Split the line into array by commas
                if (row.length == 5) { // Ensure there are exactly 5 columns
                    dataset.add(row); // Add the row to dataset
                    possibleLabels.add(row[4]); // Add label (5th column) to possible labels
                }
            }
        }
    }

    // Train the Predictor
    public void train() {
        labelCounts.clear(); // Clear label counts
        featureCounts.clear(); // Clear feature counts

        // Loop through each row in the dataset
        for (String[] row : dataset) {
            String label = row[4]; // Get the label from 5th column
            
            // Increment the count for this label
            labelCounts.put(label, labelCounts.getOrDefault(label, 0) + 1);

            // Loop through the first 4 columns (features)
            for (int i = 0; i < 4; i++) {
                String featureValue = row[i]; // Get the feature value
                // Ensure the map for the label exists
                featureCounts.putIfAbsent(label, new HashMap<>());
                
                // Get the map for this label
                Map<String, Integer> featureLabelCount = featureCounts.get(label);
                
                // Increment the count for this feature value under the label
                featureLabelCount.put(featureValue, featureLabelCount.getOrDefault(featureValue, 0) + 1);
            }
        }
    }

    // Predict the label for a set of input features
    public String predict(String[] inputFeatures) {
        double totalRows = dataset.size(); // Total number of rows in dataset
        Map<String, Double> labelProbabilities = new HashMap<>();

        // Loop through each possible label
        for (String label : possibleLabels) {
            double labelCount = labelCounts.getOrDefault(label, 0); // Get count for label (0 if not found)
            double probability = labelCount / totalRows; // Calculate the base probability for this label

            // Loop through the input features
            for (int i = 0; i < 4; i++) {
                String featureValue = inputFeatures[i]; // Get the feature value
                // Get the map for the label
                Map<String, Integer> featureLabelCount = featureCounts.getOrDefault(label, new HashMap<>());
                int featureCount = featureLabelCount.getOrDefault(featureValue, 0); // Get count for feature-label pair

                // Multiply the current probability by the feature probability
                probability *= (double) featureCount / labelCounts.get(label);
            }

            // Store the calculated probability for the label
            labelProbabilities.put(label, probability);
        }

        // Find the label with the highest probability
        String bestLabel = "unknown"; // Default label if no valid label found
        double maxProbability = -1; // Track the maximum probability

        for (Map.Entry<String, Double> entry : labelProbabilities.entrySet()) {
            if (entry.getValue() > maxProbability) {
                maxProbability = entry.getValue();
                bestLabel = entry.getKey(); // Update the best label if a higher probability is found
            }
        }

        return bestLabel;
    }
}
