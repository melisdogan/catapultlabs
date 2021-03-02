package com.melisdogan.catapult_labs.controller;

import com.melisdogan.catapult_labs.model.Client;
import com.melisdogan.catapult_labs.service.MeterReadingService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "frontend")
public class MeterReadingController {

    @Resource
    MeterReadingService meterReadingService;

    @GetMapping("/get-readings")
    @CrossOrigin(allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
    public List<Client> getReadings(){
        return meterReadingService.getReadings();
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/token")
    public Map<String,String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }

}
