# EdCare Watson NLU Services

## Overview
This project integrates IBM Watson's Natural Language Understanding (NLU) services into a Java application, providing a suite of services for text analysis. It includes classification, entity recognition, and emotion analysis functionalities, leveraging IBM Watson's powerful NLU API.

## Features
- **Text Classification**: Classify texts into custom categories using machine learning models.
- **Entity Recognition**: Identify and extract entities from text, such as names, locations, and dates, with sentiment analysis.
- **Emotion Analysis**: Analyze the emotions conveyed in text, targeting specific keywords or phrases.

## Prerequisites
Before you begin, ensure you have met the following requirements:
- Java JDK 11 or higher installed
- Maven 3.6.3 or higher
- An IBM Cloud account with access to Watson NLU services

## Setting Up the Project
To set up the project for development on your local machine, follow these steps:
1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run `mvn clean install` to build the project.

## Running the Application
To run the application, you need to set up your IBM Watson NLU service credentials (API key and service URL). Then, execute the following command:
Replace `org.edcare.MainClass` with the fully qualified name of your main class. Ensure you have configured the application with your IBM Watson NLU API key and service URL.

## Contributing to the Project
We welcome contributions! To contribute:
1. Fork the repository.
2. Create a new branch for your feature (`git checkout -b feature/YourFeatureName`).
3. Make your changes and commit them (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeatureName`).
5. Create a new Pull Request.
