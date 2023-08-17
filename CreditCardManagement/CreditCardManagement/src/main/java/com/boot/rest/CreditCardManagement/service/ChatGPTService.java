package com.boot.rest.CreditCardManagement.service;

import com.boot.rest.CreditCardManagement.dao.CustomerRepository;
import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.entity.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatGPTService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Autowired
    private CustomerRepository customerRepository;
    private JSONArray messagesHistory = new JSONArray();  // to store chat history

    private String loadDataAttributes(Class<?> entityClass, String entityName) {
        Field[] fields = entityClass.getDeclaredFields();
        List<String> fieldNames = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
        String attributesMessage = "The attributes of the " + entityName + " are: " + String.join(", ", fieldNames) + ".";

        JSONObject msgObj = new JSONObject();
        msgObj.put("role", "system");
        msgObj.put("content", attributesMessage);
        messagesHistory.put(msgObj);

        return entityName + " data loaded successfully, you can ask some questions now!";
    }

    public synchronized String getChatGPTResponse(String userMessage) throws Exception {
        try {
            if (userMessage.equals("load customer data")) {
                return loadDataAttributes(Customer.class, "Customer");
            } else if (userMessage.equals("load transaction data")) {
                return loadDataAttributes(Transaction.class, "Transaction");
            }
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
            jsonInput.put("messages", messagesHistory);  // Send the entire chat history

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
            } else {
                System.out.println("HTTP Response Code: " + responseCode);
                System.out.println("HTTP Response Message: " + con.getResponseMessage());
                // Read and print the error response content
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        errorResponse.append(responseLine.trim());
                    }
                    System.out.println("Error Response Content: " + errorResponse.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
        }
        return "Error: Unable to get response from ChatGPT.";
    }


}
