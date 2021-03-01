package com.melisdogan.catapult_labs.producer;

import com.google.gson.Gson;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class MeterReadingProducer {

    @Resource
    JmsTemplate jmsTemplate;


    @Scheduled(cron = "0 0 * * * *")
    public void sendMessage() {
        for (int i = 1; i < 11; i++) {
            /*MeterReading meterReading = new MeterReading(null, System.currentTimeMillis(), new Random().nextInt(501), (long) i);
            String message = new Gson().toJson(meterReading, MeterReading.class);
            jmsTemplate.convertAndSend("test-queue", message);*/
            Map<String, String> readingData = new HashMap<>();
            readingData.put("timestamp", String.valueOf(System.currentTimeMillis()));
            readingData.put("meterId", String.valueOf(i));
            readingData.put("reading", String.valueOf(new Random().nextInt(501)));
            jmsTemplate.convertAndSend("test-queue", new Gson().toJson(readingData));
        }
    }
}
