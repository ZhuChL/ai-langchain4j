package com.zhuchl.ailangchain4j.tools;

import com.zhuchl.ailangchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @desc
 * 
 * @date 2025/5/13 11:22
 */
@SpringBootTest
public class ToolsTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testCalculatorTools() {
        String chat = separateChatAssistant.chat(3, "1+2等于几，475695037565的平方根是多少？");
        System.out.println(chat);
    }

}
