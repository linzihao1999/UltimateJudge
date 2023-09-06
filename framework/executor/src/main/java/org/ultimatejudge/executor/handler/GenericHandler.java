package org.ultimatejudge.executor.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ultimatejudge.executor.handler.model.response.PingResponse;

import static org.ultimatejudge.executor.constant.API.PING;

@Log4j2
@RestController
public class GenericHandler {

    @GetMapping(PING)
    PingResponse ping() {
        return new PingResponse();
    }
}
