package org.edcare.Services;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.ClassificationsOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;

public class WatsonNLUClassificationService {

    private NaturalLanguageUnderstanding naturalLanguageUnderstanding;

    public WatsonNLUClassificationService(String apiKey, String serviceUrl) {
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
//        System.out.println("API Key: " + apiKey);
        naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2022-04-07", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(serviceUrl);
          System.out.println("Service URL: " + serviceUrl);
    }

    public AnalysisResults analyzeClassifications(String stringToAnalyze, String modelId) {
        System.out.println("Model ID: " + modelId);
        ClassificationsOptions classifications = new ClassificationsOptions.Builder()
                .model(modelId)
                .build();

        Features features = new Features.Builder()
                .classifications(classifications)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(stringToAnalyze)
                .features(features)
                .build();

        return naturalLanguageUnderstanding.analyze(parameters).execute().getResult();
    }
}
