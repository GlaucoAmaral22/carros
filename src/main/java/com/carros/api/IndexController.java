package com.carros.api;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get(){
        return "Hello World Spring Boot";
    }

    @PostMapping
    public String post(){
        return "post World Spring Boot";
    }

    @PutMapping
    public String put(){
        return "put World Spring Boot";
    }

    @DeleteMapping
    public String delete(){
        return "delete World Spring Boot";
    }


}
