package com.codecool.shop.utils;

import com.codecool.shop.dao.ProductDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HandleApiGet {

    private static HttpURLConnection connection;


    public void customDoGet(HttpServletRequest request, HttpServletResponse response, String sentUrl) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        ProductDao productDao;
//        INews newsDao = new NewsDao();
//        NewsSerializer newsSerializer = new NewsSerializer();

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL(sentUrl);
            connection = (HttpURLConnection) url.openConnection();

            //Request Setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            // Deserialize and serialize again
//            List<News> newsList = newsDao.getAll(responseContent.toString());
//            out.println(newsSerializer.serialize(newsList));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }
}