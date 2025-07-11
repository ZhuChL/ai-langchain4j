package com.zhuchl.ailangchain4j.assistant;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * @desc
 * 
 * @date 2025/5/5 20:49
 */

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = "ollamaChatModel")
public interface Assistant {
    String chat(String userMessage);
}
