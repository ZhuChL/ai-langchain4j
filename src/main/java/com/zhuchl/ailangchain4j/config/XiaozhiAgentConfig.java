package com.zhuchl.ailangchain4j.config;

import com.zhuchl.ailangchain4j.store.MongoChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc
 * 
 * @date 2025/5/5 23:44
 */

@Configuration
public class XiaozhiAgentConfig {

    @Autowired
    MongoChatMemoryStore mongoChatMemoryStore;

    @Bean
    public ChatMemoryProvider chatMemoryProviderXiaozhi() {
        return memroyId -> MessageWindowChatMemory.builder()
                .id(memroyId)
                .maxMessages(20)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }


}
