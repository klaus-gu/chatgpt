package xyz.openai.chatgpt.client.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTScan;
import xyz.openai.chatgpt.client.spring.service.ChatGPTService01;

/**
 * TEST .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@ChatGPTScan(basePackages = "xyz.openai.chatgpt.client.spring.service")
public class OpenAIServiceTEST {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(OpenAIServiceTEST.class);
        applicationContext.refresh();
        final ChatGPTService01 bean = applicationContext.getBean(ChatGPTService01.class);
        System.out.println(bean.chat(new GPT35TurboRequest.Message("user", "who are you?")));
        applicationContext.close();
    }
}
