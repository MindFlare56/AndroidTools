package com.mindf.utils.android;

import android.os.AsyncTask;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public abstract class Translator {

    @Getter @Setter private String source = "en";
    @Getter @Setter private String destination = "fr";
    @Getter @Setter private boolean json = false;

    public abstract void onResult(String result);

    public Translator(String text) {
        new Task().execute(text, source, destination);
    }

    public Translator(String text, boolean json) {
        this.json = json;
        new Task().execute(text, source, destination);
    }

    public Translator(String text, String sourceLang, String destinationLang) {
        this.source = sourceLang;
        this.destination = destinationLang;
        new Task().execute(text, sourceLang, destinationLang);
    }

    public Translator(String text, String sourceLang, String destinationLang, boolean json) {
        this.json = json;
        this.source = sourceLang;
        this.destination = destinationLang;
        new Task().execute(text, sourceLang, destinationLang);
    }

    public void add(String text) {
        new Task().execute(text, source, destination);
    }

    public void add(String text, boolean json) {
        this.json = json;
        new Task().execute(text, source, destination);
    }

    public void add(String text, String sourceLang, String destinationLang) {
        this.source = sourceLang;
        this.destination = destinationLang;
        new Task().execute(text, sourceLang, destinationLang);
    }

    public void add(String text, String sourceLang, String destinationLang, boolean json) {
        this.json = json;
        this.source = sourceLang;
        this.destination = destinationLang;
        new Task().execute(text, sourceLang, destinationLang);
    }

    private class Task extends AsyncTask<String, Void, Task> {

    private String result;
    private String[] args;

    @SneakyThrows
    @Override
    protected Task doInBackground(String... strings) {
        args = strings;
        if (json) {
            String nonJson = getStringsInJson(strings[0]);
            String tempResult = callUrlAndParseResult(nonJson);
            result = replaceStringsInJson(nonJson, tempResult, strings[0]);
        } else {
            result = callUrlAndParseResult(strings[0]);
        }
        onResult(result);
        return this;
    }

    private String getStringsInJson(String json) {
        String parts = "";
        String seperator = ": \"";
        char end = '\"';
        for (int i = 0; i < json.length(); i++) {
            if (json.charAt(i) == seperator.charAt(0)) {
                ++i;
                if (json.charAt(i) == seperator.charAt(1)) {
                    ++i;
                    if (json.charAt(i) == seperator.charAt(2)) {
                        ++i;
                        String substr = "";
                        while (json.charAt(i) != end) {
                            substr += json.charAt(i);
                            ++i;
                        }
                        parts += substr + "¤";
                    }
                }
            }
        }
        return parts.substring(0, parts.length() - 1);
    }

    private String replaceStringsInJson(String nonJson, String result, String json) {
        String nonJsonParts[] = nonJson.split("¤");
        String resultParts[] = result.split("¤");
        for (int i = 0; i < nonJsonParts.length - 1; i++) {
            json = json.replace(nonJsonParts[i], resultParts[i]);
        }
        return json;
    }

    private String callUrlAndParseResult(String word) throws Exception {
        String url = "https://translate.googleapis.com/translate_a/single?"+
            "client=gtx&"+
            "sl=" + args[1] +
            "&tl=" + args[2] +
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
