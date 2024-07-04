package org.edcare.Services;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
import com.ibm.watson.natural_language_understanding.v1.model.KeywordsOptions;

public class WatsonNLUKeywordService {

    private NaturalLanguageUnderstanding naturalLanguageUnderstanding;

    public WatsonNLUKeywordService(String apiKey, String serviceUrl) {
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2022-04-07", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(serviceUrl);
    }

    public AnalysisResults analyzeKeywords(String urlToAnalyze, int limit, boolean includeSentiment, boolean includeEmotion) {
        KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
                .sentiment(includeSentiment)
                .emotion(includeEmotion)
                .limit(limit)
                .build();

        Features features = new Features.Builder()
                .keywords(keywordsOptions)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .url(urlToAnalyze)
                .features(features)
                .build();

        return naturalLanguageUnderstanding.analyze(parameters).execute().getResult();
    }
}
