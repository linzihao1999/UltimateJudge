package org.ultimatejudge.framework.services;

import org.springframework.stereotype.Service;

@Service
public class CodeProcessService {
    public Boolean processCode(String code) {
        return sendCodeToJudge(code);
    }

    private Boolean sendCodeToJudge(String code) {
        return Boolean.TRUE;
    }
}
