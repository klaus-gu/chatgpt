package xyz.openai.chatgpt.client.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.enums.RoleEnum;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTScan;
import xyz.openai.chatgpt.client.spring.service.ChatGPTContextService;
import xyz.openai.chatgpt.client.spring.service.MyDefaultGPT35TurboConversationMapperFactory;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@ChatGPTScan(basePackages = "xyz.openai.chatgpt.client.spring.service")
public class MapperBeanTEST {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MapperBeanTEST.class);
        applicationContext.refresh();
        ChatGPTContextService contextService = applicationContext.getBean(ChatGPTContextService.class);
        System.out
                .println(contextService.chat(new GPT35TurboRequest.Message("001", RoleEnum.USER.getRoleName(), "你好")));
        
        applicationContext.close();
    }
    
    @Bean
    public MyDefaultGPT35TurboConversationMapperFactory myDefaultGPT35TurboConversationMapperFactory() {
        return new MyDefaultGPT35TurboConversationMapperFactory();
    }
}
