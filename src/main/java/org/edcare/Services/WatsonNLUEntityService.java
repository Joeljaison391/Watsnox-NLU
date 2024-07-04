package org.edcare.Services;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;

public class WatsonNLUEntityService {

    private NaturalLanguageUnderstanding naturalLanguageUnderstanding;

    public WatsonNLUEntityService(String apiKey, String serviceUrl) {
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2022-04-07", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(serviceUrl);
    }

    public AnalysisResults analyzeEntities(String stringToAnalyze) {
        EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
                .sentiment(true)
                .limit(1)  // Adjust limit as per your requirements
                .build();

        Features features = new Features.Builder()
                .entities(entitiesOptions)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(stringToAnalyze)
                .features(features)
                .build();

        return naturalLanguageUnderstanding.analyze(parameters).execute().getResult();
    }
}
