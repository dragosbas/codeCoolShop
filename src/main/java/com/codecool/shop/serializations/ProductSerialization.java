//package com.codecool.shop.serializations;
//
//import com.codecool.shop.model.Product;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.List;
//
//public class ProductSerialization {
//
////    @Override
////    public News deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
////        JsonObject obj = jsonElement.getAsJsonObject();
////        String author = (obj.get("user") == null) ? "unknown" : obj.get("user").getAsString();
////        return new News(obj.get("title").getAsString(), obj.get("time_ago").getAsString(), obj.get("url").getAsString(), author);
////    }
//
////    @Override
////    public JsonElement serialize(News news, Type type, JsonSerializationContext jsonSerializationContext) {
////        JsonObject result = new JsonObject();
////        result.addProperty("title", news.getTitle());
////        result.addProperty("time_ago", news.getTime());
////        result.addProperty("url", news.getUrl());
////        result.addProperty("author", news.getAuthor());
////        return result;
////    }
//
//    public String serialize(List<Product> productList) {
//
//        Gson gson = new Gson();
//        String tempObject = gson.toJson(productList.get(0));
//        return tempObject;
//
//
////        Gson gson = new Gson();
////        JsonElement element = gson.toJsonTree(productList, new TypeToken<List<Product>>() {}.getType());
////
//////        if (! element.isJsonArray() ) {
////// fail appropriately
//////            throw new Exception();
//////        }
////
////        JsonArray jsonArray = element.getAsJsonArray();
////        return jsonArray;
//
//    }
//}
