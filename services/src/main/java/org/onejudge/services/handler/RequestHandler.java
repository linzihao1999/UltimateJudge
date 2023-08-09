package org.onejudge.services.handler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestHandler {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
