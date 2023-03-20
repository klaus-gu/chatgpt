package xyz.openai.chatgpt.client.spring;

import com.alibaba.fastjson2.JSON;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.enums.RoleEnum;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTScan;
import xyz.openai.chatgpt.client.spring.service.ChatGPTService01;

/**
 * conversationMapper .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@ChatGPTScan(basePackages = "xyz.openai.chatgpt.client.spring.service")
public class ConversationMapperTEST {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ConversationMapperTEST.class);
        applicationContext.refresh();
        ChatGPTService01 chatGPTService01 = applicationContext.getBean(ChatGPTService01.class);
        final OpenAIResponse<GPT35TurboRequest.Message> chat = chatGPTService01
                .chat(new GPT35TurboRequest.Message(RoleEnum.USER.getRoleName(), "who are you?"));
        
        System.out.println(JSON.toJSONString(chat));
        applicationContext.close();
    }
}
