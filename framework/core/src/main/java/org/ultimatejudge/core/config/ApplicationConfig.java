package org.ultimatejudge.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ultimatejudge.core.handler.CodeJudgeEntity;

@Configuration
public class ApplicationConfig {
    @Bean
    CodeJudgeEntity provideCodeJudgeEntity() {
        return new CodeJudgeEntity();
    }
}
