package com.codecool.shop.utils;
import com.codecool.shop.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LoggerService {

    private static LoggerService instance = null;
    private final Logger logger = Logger.getLogger("MyLog");
//    private final FileHandler fh = new FileHandler("C:/logs/shop.log");
    private final FileHandler fh = new FileHandler("src/assets/orders.log");

    private final SimpleFormatter formatter = new SimpleFormatter();

    private LoggerService() throws IOException {
        fh.setFormatter(formatter);
        logger.addHandler(fh);
    }

    public static LoggerService getInstance() throws IOException {
        if(instance == null){
            return new LoggerService();
        }
        return instance;
    }

    public void log(Order order) throws IOException {
        final StringWriter sw = new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sw, "Order ID: " + order.getOrderId().toString());
        mapper.writeValue(sw, "Status: " + order.isOrderConfirmed());
        mapper.writeValue(sw, order.getCartDao().getCart(UUID.randomUUID()));
        mapper.writeValue(sw, order.getClientDetails());
        sw.close();
        logger.info(sw.toString());
    }

}
