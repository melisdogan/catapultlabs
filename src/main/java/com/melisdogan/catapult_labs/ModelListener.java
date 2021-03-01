package com.melisdogan.catapult_labs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.melisdogan.catapult_labs.model.MeterReading;
import com.melisdogan.catapult_labs.repository.MeterReadingRepository;
import com.melisdogan.catapult_labs.repository.MeterRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ModelListener {
    @Resource
    MeterRepository meterRepository;

    @Resource
    MeterReadingRepository meterReadingRepository;

    @JmsListener(destination = "test-queue")
    public void listen(final String jsonMessage) {
        var receivedReading = new Gson().fromJson(jsonMessage, JsonObject.class);
        if(meterRepository.existsById(receivedReading.get("meterId").getAsLong())) {
           MeterReading meterReading = new MeterReading(null, receivedReading.get("timestamp").getAsLong(), receivedReading.get("reading").getAsInt(), meterRepository.getOne(receivedReading.get("meterId").getAsLong()));
           meterReadingRepository.save(meterReading);
        }
    }
}
