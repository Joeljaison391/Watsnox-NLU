package org.edcare.Services;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;

public class WatsonNLUConceptService {

    private NaturalLanguageUnderstanding naturalLanguageUnderstanding;

    public WatsonNLUConceptService(String apiKey, String serviceUrl) {
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2022-04-07", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(serviceUrl);
    }

    public AnalysisResults analyzeConcepts(String textToAnalyze) {
        ConceptsOptions concepts = new ConceptsOptions.Builder()
                .limit(3)
                .build();

        Features features = new Features.Builder()
                .concepts(concepts)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(textToAnalyze)
                .features(features)
                .build();

        return naturalLanguageUnderstanding.analyze(parameters).execute().getResult();
    }
}
