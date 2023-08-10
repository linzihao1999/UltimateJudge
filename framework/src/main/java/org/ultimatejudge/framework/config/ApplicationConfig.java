package org.ultimatejudge.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ultimatejudge.framework.handler.CodeJudgeEntity;

@Configuration
public class ApplicationConfig {
    @Bean
    CodeJudgeEntity provideCodeJudgeEntity() {
        return new CodeJudgeEntity();
    }
}
