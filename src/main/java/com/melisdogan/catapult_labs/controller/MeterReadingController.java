package com.melisdogan.catapult_labs.controller;

import com.melisdogan.catapult_labs.model.Client;
import com.melisdogan.catapult_labs.service.MeterReadingService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MeterReadingController {

    @Resource
    MeterReadingService meterReadingService;

    @GetMapping("/get-readings")
    public List<Client> getReadings(){
        return meterReadingService.getReadings();
    }
}
