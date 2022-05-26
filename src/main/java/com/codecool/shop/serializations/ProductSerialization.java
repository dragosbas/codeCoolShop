package com.codecool.shop.serializations;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductSerialization {

    public Map<String, String> parseReqParams(HttpServletRequest req) throws IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        System.out.println(buffer.toString());

        return objectMapper.readValue(buffer.toString(), HashMap.class);
    }
}