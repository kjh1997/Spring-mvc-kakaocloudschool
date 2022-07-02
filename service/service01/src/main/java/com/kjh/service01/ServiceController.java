package com.kjh.service01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class ServiceController {
    private Environment env;
    public ServiceController(Environment env) {
        this.env = env;
    }


    @GetMapping("/welcome")
    public String service01() {
        return "welcome to service01";
    }
    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "Hello world in first service";
    }
    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        System.out.println(env.getProperty("local.server.port"));
        log.info("Server port : ", request.getServerPort());
        return "hi. there. this is a message from first service"+ env.getProperty("local.server.port");
    }
}
