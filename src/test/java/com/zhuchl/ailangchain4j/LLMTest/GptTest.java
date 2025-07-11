package com.zhuchl.ailangchain4j.LLMTest;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @desc
 * 
 * @date 2025/4/24 22:21
 */
@SpringBootTest
public class GptTest {

    @Test
    public void testDemo() {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String res = model.chat("你好，你是谁？");

        System.out.println(res);
    }

    @Autowired
    OpenAiChatModel openAiChatModel;

    @Test
    public void testSpringDemo() {

        String res = openAiChatModel.chat("你好，你是谁？");

        System.out.println(res);

    }


    /**
     * ollama接入
     */
    @Autowired
    private OllamaChatModel ollamaChatModel;

    @Test
    public void testOllama() {

        String answer = ollamaChatModel.chat("你好");
        System.out.println(answer);
    }
}
