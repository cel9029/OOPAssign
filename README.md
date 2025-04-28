# Naive Bayes Classifier Predictor

This project implements a **Naive Bayes Classifier** to predict whether a package is damaged based on four categorical features. The classifier is trained using a dataset where the last column (label) indicates whether the package is damaged (`yes` or `no`), and the first four columns represent different features related to the package (e.g., color, size, shape, etc.).

The project also includes a graphical user interface (GUI) that allows users to input feature values and get a prediction of whether the package is damaged or not.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [How to Use](#how-to-use)
- [File Description](#file-description)
- [Code Structure](#code-structure)
- [Reflection](#reflection)

## Introduction

This project implements a simple Naive Bayes Classifier to predict whether a package is damaged based on categorical features. The classifier is trained on a dataset of labeled examples and can predict the label (`"yes"` or `"no"`) for new instances based on their feature values.

The graphical user interface (GUI) allows users to enter feature values, and the classifier will output the prediction (whether the package is damaged or not). The GUI also allows users to add new rows to the data and recalculate.

### Key Components:
- **Predictor**: The classifier that performs the learning and prediction using the Naive Bayes algorithm.
- **FileProcessor**: A utility class to read the dataset from a CSV file.
- **PredictorGUI**: A graphical user interface (GUI) for interacting with the classifier.

## Features

- **Naive Bayes Classification**: Uses a probabilistic model to classify instances based on observed feature distributions and labels.
- **Categorical Data**: All data (features and labels) are categorical, and the classifier uses Laplace smoothing to handle unseen feature-label combinations.
- **GUI for Prediction**: A simple GUI to input feature values and get predictions from the classifier.

## How to Use

1. **Prepare Your Dataset**: Ensure the dataset is in CSV format with 5 columns:
   - **Columns 1-4**: Features (categorical values).
   - **Column 5**: Label (e.g., `"yes"` or `"no"`).

2. **Run the Program**:
   - Ensure you have the required classes (`Predictor`, `FileProcessor`, `PredictorGUI`) in the same directory.
   - Run the `PredictorGUI` class, which will initialize the GUI.
   - Enter the values for the four features in the provided text fields.
   - Click the **"Predict"** button to get the classifier's prediction.
  
3. **Add New Data and Recalculate**:
   - Enter the values for the four features and label in the provided text fields.
   - Click the **Add New Row** button to add a new row.
   - Click the **Recalculate Data** button to recalculate your data with the new rows added.

3. **Example**:
   - If the feature values are:
     - Feature 1: `red`
     - Feature 2: `small`
     - Feature 3: `soft`
     - Feature 4: `round`
   - The program will output whether the package is predicted to be damaged (`"yes"`) or not (`"no"`).

## File Description

- **`Predictor.java`**: The core logic for training and predicting with a Naive Bayes classifier.
- **`FileProcessor.java`**: A utility class for reading the dataset from a CSV file.
- **`PredictorGUI.java`**: The graphical user interface that allows users to input data and view predictions.
- **`package_data.csv`**: Example dataset used for training the classifier. This file contains 200 rows of data with 4 feature columns and 1 label column.

## Code Structure

- **Predictor.java**: 
   - The classifier that processes the data and computes probabilities for each label based on feature values.

- **FileProcessor.java**: 
   - Reads the dataset from the provided CSV file.
   - Parses the data and returns it as a list of strings.

- **PredictorGUI.java**:
   - The main GUI for interacting with the classifier.
   - Allows users to input feature values and see the predicted label.
   - Allows users to add new rows and recalculate the data

- **Control.java**:
   - Manages the interaction between the GUI and the classifier.

## Reflection
If I had more time, I think I would like to research and prepare more for Level 4 which I couldn't successfully do.

## Frequency Table
[https://github.com/cel9029/OOPAssign/blob/main/Assignment/package_data.csv](https://github.com/cel9029/OOPAssign/blob/main/freq_table.csv)

