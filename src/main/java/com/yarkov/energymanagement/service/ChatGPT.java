package com.yarkov.energymanagement.service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPT {

    private static final String URL = "https://api.openai.com/v1/completions";
    private static final String TOKEN = "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";

    public static String chatGPT(String text) throws Exception {

        HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TOKEN);

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        return new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
    }
}
