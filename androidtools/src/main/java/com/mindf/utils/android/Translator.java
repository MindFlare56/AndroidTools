package com.mindf.utils.android;

import android.os.AsyncTask;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public abstract class Translator {

    @Getter @Setter private String sourceLang = "en";
    @Getter @Setter private String destinationLang = "fr";

    public abstract void onResult(String result);

    public Translator(String text) {
        createTask(text);
    }

    public Translator(String text, String sourceLang, String destinationLang) {
        this.sourceLang = sourceLang;
        this.destinationLang = destinationLang;
        createTask(text);
    }

    private void createTask(String text) {
        Task task = new Task();
        task.text = text;
        task.execute("");
    }



private class Task extends AsyncTask<String, Void, Task> {

    private String result;
    private String text;

    @SneakyThrows
    @Override
    protected Task doInBackground(String... strings) {
        result = callUrlAndParseResult(text);
        onResult(result);
        return this;
    }

    private String callUrlAndParseResult(String word) throws Exception {
        String url = "https://translate.googleapis.com/translate_a/single?"+
                "client=gtx&"+
                "sl=" + sourceLang +
                "&tl=" + destinationLang +
                "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return parseResult(response.toString());
    }

    private String parseResult(String inputJson) throws Exception {
        JSONArray jsonArray = new JSONArray(inputJson);
        JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
        JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);
        return jsonArray3.get(0).toString();
    }
}
}
