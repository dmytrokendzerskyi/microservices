package com.pricepopulator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeathCheckController {

    @GetMapping("/liveness")
    public ResponseEntity healthCheck(){
        return ResponseEntity.ok().build();
    }

}
