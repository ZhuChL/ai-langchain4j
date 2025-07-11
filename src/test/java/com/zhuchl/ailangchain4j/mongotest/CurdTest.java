package com.zhuchl.ailangchain4j.mongotest;

import com.zhuchl.ailangchain4j.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @desc
 * 
 * @date 2025/5/7 20:41
 */

@SpringBootTest
public class CurdTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testInsert1() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("聊天记录列表");
        mongoTemplate.insert(chatMessages);
    }
}
