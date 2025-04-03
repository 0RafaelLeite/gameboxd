package br.com.rafael.gameboxd.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class testController {
    @PostMapping("/isWorking")
    public ResponseEntity isWorking() {
        return ResponseEntity.ok("Everything is working just fine");
    }
}
