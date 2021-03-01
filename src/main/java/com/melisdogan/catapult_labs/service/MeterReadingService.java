package com.melisdogan.catapult_labs.service;

import com.melisdogan.catapult_labs.model.Client;
import com.melisdogan.catapult_labs.repository.ClientRepository;
import com.melisdogan.catapult_labs.repository.MeterReadingRepository;
import com.melisdogan.catapult_labs.repository.MeterRepository;
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
        List<Client> clients = clientRepository.findAll();
        return clients;
    }
}
