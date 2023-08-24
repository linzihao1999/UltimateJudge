package org.ultimatejudge.core.services;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CodeProcessService {

    private final CodeExecutorManager codeExecutorManager;

    public CodeProcessService(@NonNull CodeExecutorManager codeExecutorManager) {
        this.codeExecutorManager = codeExecutorManager;
    }

    public String processCode(String code) {
        return sendCodeToJudge(code);
    }

    private String sendCodeToJudge(String code) {
        String response = "";
        codeExecutorManager.createCodeExecutor();
        codeExecutorManager.getCodeExecutor().judgeCode(code);
        return response;
    }
}
