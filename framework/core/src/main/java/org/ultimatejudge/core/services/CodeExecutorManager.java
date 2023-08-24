package org.ultimatejudge.core.services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CodeExecutorManager {
    private final List<CodeExecutor> codeExecutors;

    CodeExecutorManager() {
        codeExecutors = new ArrayList<CodeExecutor>();
    }

    public void createCodeExecutor() {
        codeExecutors.add(CodeExecutor.builder().serverAddress("127.0.0.1").serverPort(9125).build());
    }

    public CodeExecutor getCodeExecutor() {
        if (!codeExecutors.isEmpty()) return codeExecutors.get(0);
        return null;
    }
}
