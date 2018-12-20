package com.mindf.utils.android.Expirimental;

import android.os.Build;
import android.support.annotation.RequiresApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;

public class JsonHandler {

    @SneakyThrows
    public static String createJson(Object object) {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(object);
    }

    @SneakyThrows
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String readJson(String fileName) {
        byte[] mapData;
        Map<String,String> myMap;
        ObjectMapper objectMapper = new ObjectMapper();
        mapData = Files.readAllBytes(Paths.get(fileName));
        myMap = objectMapper.readValue(mapData, HashMap.class);
        return "Map is: " + myMap;
    }

    @SneakyThrows
    public static String readJsonUnderAndroidOreo(String fileName) {
        byte[] mapData = new byte[0];
        Map<String,String> myMap;
        ObjectMapper objectMapper = new ObjectMapper();
        myMap = objectMapper.readValue(mapData, new TypeReference<HashMap<String,String>>() {});
        return "Map using TypeReference: " + myMap;
    }

    @SneakyThrows
    public static ArrayList<JSONObject> createJSONObjects(JSONArray jsonArray) {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObjects.add(jsonArray.getJSONObject(i));
        }
        return jsonObjects;
    }
}
