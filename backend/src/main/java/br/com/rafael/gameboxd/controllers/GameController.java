package br.com.rafael.gameboxd.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
public class GameController {

    @GetMapping("/list")
    public ResponseEntity getGamesTest(@RequestParam int size){
        //TODO: implement gateway and more parameters
        return ResponseEntity.ok().build();
    }
}
