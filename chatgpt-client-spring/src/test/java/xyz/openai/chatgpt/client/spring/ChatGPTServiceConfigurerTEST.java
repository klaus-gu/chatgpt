package xyz.openai.chatgpt.client.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTScan;
import xyz.openai.chatgpt.client.spring.core.scan.ChatGPTServiceConfigurer;
import xyz.openai.chatgpt.client.spring.service.ChatGPTService01;

/**
 * ${@link ChatGPTServiceConfigurer} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
@ChatGPTScan(basePackages = "xyz.openai.chatgpt.client.spring.service")
public class ChatGPTServiceConfigurerTEST {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ChatGPTServiceConfigurerTEST.class);
        applicationContext.refresh();
        ChatGPTService01 chatGPTService01 = applicationContext.getBean(ChatGPTService01.class);
        //        final OpenAIResponse<GPT35TurboRequest.Message> chat = chatGPTService01
        //                .chat(new GPT35TurboRequest.Message(RoleEnum.USER.getRoleName(), "who are you?"));
        //        System.out.println(JSON.toJSONString(chat));
        applicationContext.close();
    }
}
