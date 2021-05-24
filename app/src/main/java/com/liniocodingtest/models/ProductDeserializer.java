package com.liniocodingtest.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ProductDeserializer implements JsonDeserializer<List<Product>> {
    @Override
    public List<Product> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = json.getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            jsonArray.add(entry.getValue());
        }
        return context.deserialize(jsonArray, typeOfT);
    }
}
