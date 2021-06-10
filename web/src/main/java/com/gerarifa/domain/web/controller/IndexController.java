package com.gerarifa.domain.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "sorteio/list";
    }

    @PostMapping("/login")
    public String login() {
        return "sorteio/list";
    }
}
