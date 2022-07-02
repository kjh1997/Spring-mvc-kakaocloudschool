package com.kjh.service02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class ServiceController {
    @GetMapping("/welcome")
    public String service02() {
        return "welcome to service02";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "Hello world in first service";
    }

    @GetMapping("/check")
    public String check() {
        return "hi. there. this is a message from second service";
    }
}
