package org.edcare;


import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.CategoriesResult;
import io.github.cdimascio.dotenv.Dotenv;
import org.edcare.Services.*;
import org.edcare.util.BearerTokenGenerator;

public class Main {
    public static void main(String[] args) {
        try {
            Dotenv dotenv = Dotenv.load();
            String APIKEY = dotenv.get("API_KEY");
            String urlToAnalyze = "https://en.wikipedia.org/wiki/IBM";

            String bearerToken = BearerTokenGenerator.generateBearerToken(APIKEY);
            System.out.println("Generated Bearer Token: " + bearerToken);

            String NLUServiceUrl = dotenv.get("NLU_SERVICE_URL");
            System.out.println("NLU Service URL: " + NLUServiceUrl);
            String NLUAPIKEY = dotenv.get("NLU_API_KEY");

            WatsonNLUService nluService = new WatsonNLUService(NLUAPIKEY, NLUServiceUrl);
            AnalysisResults results = nluService.analyzeCategories(urlToAnalyze);


            printDesiredResults(results);

            String AnalyzeString = "IBM is an American multinational technology company that produces and sells computer hardware, middleware, and software. IBM is also a major technology research organization.";

            WatsonNLUClassificationService nluClassificationService = new WatsonNLUClassificationService(NLUAPIKEY, NLUServiceUrl);
            AnalysisResults classificationResults = nluClassificationService.analyzeClassifications(AnalyzeString, "tone-classifications-en-v1");

            System.out.println("Classifications:");
            System.out.println("-----------");
            System.out.println(classificationResults);


            WatsonNLUConceptService nluConceptService = new WatsonNLUConceptService(NLUAPIKEY, NLUServiceUrl);
            AnalysisResults conceptResults = nluConceptService.analyzeConcepts(AnalyzeString);

            System.out.println("Concepts:");
            System.out.println("-----------");
            System.out.println(conceptResults);


            WatsonNLUEmotionService nluEmotionService = new WatsonNLUEmotionService(NLUAPIKEY, NLUServiceUrl);
            String analyzeStringEmotion = "Today started off well, but then things took a turn. " +
                    "I feel happy about finishing my project, but sad that the weather ruined my plans.";

            AnalysisResults emotionResults = nluEmotionService.analyzeEmotion(analyzeStringEmotion);

            System.out.println("Emotion:");
            System.out.println("-----------");
            System.out.println(emotionResults);

            WatsonNLUEntityService nluEntityService = new WatsonNLUEntityService(NLUAPIKEY, NLUServiceUrl);
            AnalysisResults entityResults = nluEntityService.analyzeEntities(AnalyzeString);

            System.out.println("Entities:");
            System.out.println("-----------");
            System.out.println(entityResults);






        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void printDesiredResults(AnalysisResults response) {
        System.out.println("{");
        System.out.println("    \"usage\": {");
        System.out.println("        \"text_units\": " + response.getUsage().getTextUnits() + ",");
        System.out.println("        \"text_characters\": " + response.getUsage().getTextCharacters() + ",");
        System.out.println("        \"features\": " + response.getUsage().getFeatures());
        System.out.println("    },");
        System.out.println("    \"retrieved_url\": \"" + response.getRetrievedUrl() + "\",");
        System.out.println("    \"language\": \"" + response.getLanguage() + "\",");
        System.out.println("    \"categories\": [");

        for (int i = 0; i < response.getCategories().size(); i++) {
            CategoriesResult category = response.getCategories().get(i);
            System.out.println("        {");
            System.out.println("            \"score\": " + category.getScore() + ",");
            System.out.println("            \"label\": \"" + category.getLabel() + "\"");
            System.out.println("        }" + (i < response.getCategories().size() - 1 ? "," : ""));
        }

        System.out.println("    ]");
        System.out.println("}");
    }
}