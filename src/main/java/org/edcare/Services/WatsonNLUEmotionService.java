package org.edcare.Services;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;

import java.util.ArrayList;
import java.util.List;

public class WatsonNLUEmotionService {

    private NaturalLanguageUnderstanding naturalLanguageUnderstanding;

    public WatsonNLUEmotionService(String apiKey, String serviceUrl) {
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2022-04-07", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(serviceUrl);
    }

    public AnalysisResults analyzeEmotion(String htmlText) {

        List<String> targets = new ArrayList<>();
        targets.add("Happy");
        targets.add("Sad");
        EmotionOptions emotionOptions = new EmotionOptions.Builder(
        )
                .targets(targets)
                .build();

        Features features = new Features.Builder()
                .emotion(emotionOptions)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .html(htmlText)
                .features(features)
                .build();

        return naturalLanguageUnderstanding.analyze(parameters).execute().getResult();
    }
}

