package org.thomas.tender.card.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public HelloResponse getHello() {
        return new HelloResponse("Hello from card-service");
    }
}
