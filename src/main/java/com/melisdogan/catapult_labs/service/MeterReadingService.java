package com.melisdogan.catapult_labs.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.melisdogan.catapult_labs.model.Client;
import com.melisdogan.catapult_labs.model.MeterReading;
import com.melisdogan.catapult_labs.repository.ClientRepository;
import com.melisdogan.catapult_labs.repository.MeterReadingRepository;
import com.melisdogan.catapult_labs.repository.MeterRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MeterReadingService {

    @Resource
    MeterReadingRepository meterReadingRepository;

    @Resource
    ClientRepository clientRepository;

    @Resource
    MeterRepository meterRepository;

    public List<Client> getReadings(){
        return clientRepository.findAll();
    }

    @JmsListener(destination = "test-queue")
    public void listen(final String jsonMessage) {
        var receivedReading = new Gson().fromJson(jsonMessage, JsonObject.class);
        if(meterRepository.existsById(receivedReading.get("meterId").getAsLong())) {
            MeterReading meterReading = new MeterReading(null, receivedReading.get("timestamp").getAsLong(), receivedReading.get("reading").getAsInt(), meterRepository.getOne(receivedReading.get("meterId").getAsLong()));
            meterReadingRepository.save(meterReading);
        }
    }
}
