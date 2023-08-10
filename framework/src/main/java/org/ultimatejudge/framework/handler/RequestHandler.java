package org.ultimatejudge.framework.handler;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ultimatejudge.framework.services.CodeProcessService;

@RestController
@Log4j2
public class RequestHandler {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/code_judge")
    public String codeJudge(@RequestBody CodeJudgeEntity codeJudge, @NonNull CodeProcessService codeProcessService) {
        return codeProcessService.processCode(codeJudge.getCode()).toString();
    }
}
