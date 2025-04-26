package com.assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PredictorGUI implements ActionListener {
	
	private JTextField f1, f2, f3, f4, labelField;
    private JLabel resultLabel;
    private Predictor classifier;
    private JButton predictBtn, addRowBtn, recalBtn;
    private String newRow;
    private FileProcessor fp;
    

    public void run() {
        // Create the frame and set its properties
        JFrame frame = new JFrame("Package Damaged Predictor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(9, 2));

        // Create text fields for the features
        f1 = new JTextField();
        f2 = new JTextField();
        f3 = new JTextField();
        f4 = new JTextField();
        labelField = new JTextField();

        // Label to display the prediction result
        resultLabel = new JLabel("Prediction: ");
        predictBtn = new JButton("Predict");
        addRowBtn = new JButton("Add New Row");
        recalBtn = new JButton("Recalculate Data");

        // Add labels and text fields to the frame
        frame.add(new JLabel("Feature 1:"));
        frame.add(f1);
        frame.add(new JLabel("Feature 2:"));
        frame.add(f2);
        frame.add(new JLabel("Feature 3:"));
        frame.add(f3);
        frame.add(new JLabel("Feature 4:"));
        frame.add(f4);
        frame.add(new JLabel("Label (Yes/No)"));
        frame.add(labelField);

        frame.add(predictBtn);
        frame.add(addRowBtn);
        frame.add(recalBtn);
        frame.add(resultLabel);
        
        // Add ActionListener for the button
        predictBtn.addActionListener(this);
        addRowBtn.addActionListener(this);
        recalBtn.addActionListener(this);

        // Load and train classifier
        fp = new FileProcessor("package_data.csv");
        fp.connectToFile();
        ArrayList<String> lines = fp.readFile();
        classifier = new Predictor(lines);
        classifier.train();
        fp.closeReadFile();

        // Set the frame visible
        frame.setVisible(true);
    }

	public void actionPerformed(ActionEvent e) {
		// When the Predict Button is clicked
		if (e.getSource() == predictBtn) {
            // Get input from text fields
            String[] input = { f1.getText(), f2.getText(), f3.getText(), f4.getText() };

            // Get prediction from classifier
            String prediction = classifier.predict(input);

            // Update the result label
            resultLabel.setText("Prediction: " + prediction);
        }
		
		// When the Add Row Button is clicked
		if (e.getSource() == addRowBtn) {
            // Get input from text fields
            String feature1 = f1.getText();
            String feature2 = f2.getText();
            String feature3 = f3.getText();
            String feature4 = f4.getText();
            String label = labelField.getText();
            
            // Error Checking if text fields are empty
            if (feature1.isEmpty() || feature2.isEmpty() || feature3.isEmpty() || feature4.isEmpty() || label.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields (4 features + label)");
                return;
            }

            newRow = feature1 + "," + feature2 + "," + feature3 + "," + feature4 + "," + label;
            
            // Append to file
            fp.getFileWriter();
            fp.writeLineToFile("\n" + newRow);
            fp.closeWriteFile();

            JOptionPane.showMessageDialog(null, "New row added!");
        }
		
		// When the Recalculate Data Button is clicked
		if (e.getSource() == recalBtn) {
            // Reload data set and retrain
            fp.connectToFile();
            ArrayList<String> updatedLines = fp.readFile();
            classifier = new Predictor(updatedLines);
            classifier.train();
            fp.closeReadFile();
            
            JOptionPane.showMessageDialog(null, "Data Recalculated");
        }
    }

}