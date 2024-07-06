package org.edcare.Services;

import okhttp3.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WatsonXAIService {

    private static final String SERVICE_URL = "https://eu-de.ml.cloud.ibm.com/ml/v1/text/generation?version=2023-05-02";
    private String bearerToken;

    public WatsonXAIService(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public void generateText() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("max_new_tokens", 2000);

        Map<String, Object> journalEntry = new HashMap<>();
        journalEntry.put("year", 1965);
        journalEntry.put("place", "the beach");
        journalEntry.put("companions", new String[]{"George", "Sarah", "Linda"});
        journalEntry.put("activity", "Picnic and playing games");
        journalEntry.put("specialMemory", "Building a magnificent sandcastle and singing around the bonfire");

        Map<String, String> details = new HashMap<>();
        details.put("dress", "a favorite blue dress");
        details.put("food", "egg salad sandwiches with a touch of mustard");
        details.put("drink", "homemade lemonade");

        journalEntry.put("details", details);

        String input = "Based on " + new Gson().toJson(journalEntry) + " create a story like narration for remembering that day for the user in a third person narrative style like 'Do you remember'.";

        Map<String, Object> data = new HashMap<>();
        data.put("model_id", "meta-llama/llama-2-70b-chat");
        data.put("input", input);
        data.put("parameters", parameters);
        data.put("project_id", "a946decb-49d1-4aac-9410-c48b1d576295");

        String jsonData = new Gson().toJson(data);

        RequestBody body = RequestBody.create(jsonData, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(SERVICE_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        String responseBody = response.body().string();
        System.out.println("\n\n***** TEXT RESPONSE FROM MODEL *****");
        System.out.println(responseBody);
    }
}
