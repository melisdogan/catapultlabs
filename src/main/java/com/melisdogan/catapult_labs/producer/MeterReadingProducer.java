package com.melisdogan.catapult_labs.producer;

import com.google.gson.Gson;
import com.melisdogan.catapult_labs.model.Meter;
import com.melisdogan.catapult_labs.repository.MeterRepository;
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

    @Resource
    MeterRepository meterRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void sendMessage() {
        for(Meter meter: meterRepository.findAll()) {
            Map<String, String> readingData = new HashMap<>();
            readingData.put("timestamp", String.valueOf(System.currentTimeMillis()));
            readingData.put("meterId", meter.getId().toString());
            readingData.put("reading", String.valueOf(new Random().nextInt(501)));
            jmsTemplate.convertAndSend("test-queue", new Gson().toJson(readingData));
        }
    }
}
