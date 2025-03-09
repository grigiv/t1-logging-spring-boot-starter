package ru.t1.grigiv.starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.grigiv.starter.aspect.annotation.LogBefore;

@RestController
public class TestController {

    @LogBefore
    @GetMapping("/api/v1/hello")
    public String hello() {
        return String.format("Hello World!");
    }
}
