package org.edcare.Services;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.CategoriesOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;

public class WatsonNLUService {

    private NaturalLanguageUnderstanding naturalLanguageUnderstanding;

    public WatsonNLUService(String apiKey, String serviceUrl) {
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2022-04-07", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(serviceUrl);
    }

    public AnalysisResults analyzeCategories(String urlToAnalyze) {
        CategoriesOptions categories = new CategoriesOptions.Builder()
                .limit(3)
                .build();

        Features features = new Features.Builder()
                .categories(categories)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .url(urlToAnalyze)
                .features(features)
                .build();

        return naturalLanguageUnderstanding.analyze(parameters).execute().getResult();
    }
}
