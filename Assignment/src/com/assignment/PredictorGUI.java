package com.assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PredictorGUI implements ActionListener {
	
	private JTextField f1, f2, f3, f4;
    private JLabel resultLabel;
    private Predictor classifier;
    private JButton predictBtn;

    public void run() {
        // Create the frame and set its properties
        JFrame frame = new JFrame("Naive Bayes Predictor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(7, 2));

        // Create text fields for the features
        f1 = new JTextField();
        f2 = new JTextField();
        f3 = new JTextField();
        f4 = new JTextField();

        // Label to display the prediction result
        resultLabel = new JLabel("Prediction: ");
        predictBtn = new JButton("Predict");

        // Add labels and text fields to the frame
        frame.add(new JLabel("Feature 1:"));
        frame.add(f1);
        frame.add(new JLabel("Feature 2:"));
        frame.add(f2);
        frame.add(new JLabel("Feature 3:"));
        frame.add(f3);
        frame.add(new JLabel("Feature 4:"));
        frame.add(f4);

        frame.add(predictBtn);
        frame.add(resultLabel);
        
        // Add ActionListener for the button
        predictBtn.addActionListener(this);

        // Load and train classifier
        FileProcessor fp = new FileProcessor("package_data.csv");
        fp.connectToFile();
        ArrayList<String> lines = fp.readFile();
        classifier = new Predictor(lines);
        classifier.train();
        fp.closeReadFile();

        // Set the frame visible
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == predictBtn) {
            // Get input from text fields
            String[] input = { f1.getText(), f2.getText(), f3.getText(), f4.getText() };

            // Get prediction from classifier
            String prediction = classifier.predict(input);

            // Update the result label
            resultLabel.setText("Prediction: " + prediction);
        }
    }

}