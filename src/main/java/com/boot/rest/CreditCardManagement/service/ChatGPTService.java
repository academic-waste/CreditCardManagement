package com.boot.rest.CreditCardManagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ChatGPTService {

    @Value("${openai.api.key}")
    private String apiKey;

    private JSONArray messagesHistory = new JSONArray(); // to store chat history

    public synchronized String getChatGPTResponse(String userMessage) {
        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Use JSONObject to build the JSON string
            JSONObject jsonInput = new JSONObject();
            jsonInput.put("model", "gpt-3.5-turbo");

            JSONObject userMsgObj = new JSONObject();
            userMsgObj.put("role", "user");
            userMsgObj.put("content", userMessage);

            messagesHistory.put(userMsgObj);  // Add new user message to history
            jsonInput.put("messages", messagesHistory); // Send the entire chat history

            String jsonInputString = jsonInput.toString();

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    JSONObject jsonResponse = new JSONObject(response.toString());
                    String gptResponse = jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");

                    JSONObject gptMsgObj = new JSONObject();
                    gptMsgObj.put("role", "assistant");
                    gptMsgObj.put("content", gptResponse);
                    messagesHistory.put(gptMsgObj);  // Add GPT response to history

                    return gptResponse;
                }
            }
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
        }
        return "Error: Unable to get response from ChatGPT.";
    }
}
