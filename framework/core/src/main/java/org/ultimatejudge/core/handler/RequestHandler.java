package org.ultimatejudge.core.handler;

import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ultimatejudge.core.services.CodeProcessService;

@RestController
@RequestMapping("/")
public class RequestHandler {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/codeJudge")
    public String codeJudge(@RequestBody CodeJudgeEntity codeJudge, @NonNull CodeProcessService codeProcessService) {
        return codeProcessService.processCode(codeJudge.getCode());
    }
}
