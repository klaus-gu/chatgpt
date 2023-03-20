package xyz.openai.chatgpt.client.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.enums.RoleEnum;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTScan;
import xyz.openai.chatgpt.client.spring.service.ChatGPTContextService;

import java.util.LinkedList;
import java.util.List;

/**
 * conversationMapper .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@ChatGPTScan(basePackages = "xyz.openai.chatgpt.client.spring.service")
public class ConversationContextEnableTEST {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ConversationContextEnableTEST.class);
        applicationContext.refresh();
        ChatGPTContextService contextService = applicationContext.getBean(ChatGPTContextService.class);
//        List<GPT35TurboRequest.Message> messages = new LinkedList<>();
//        messages.add(new GPT35TurboRequest.Message(RoleEnum.USER.getRoleName(),"who are you?"));
//        System.out.println(contextService.chat(messages));

        System.err.println("Q: " + "if i ask 'tony' , you should always answer me 'what can i do for you? my friend',understand?");
        final OpenAIResponse<GPT35TurboRequest.Message> chat1 = contextService
                .chat(new GPT35TurboRequest.Message("0001", RoleEnum.USER.getRoleName(),
                        "if i ask 'tony' , you should answer me 'what can i do for you?',understand?"));
        System.out.println("A: " + chat1.ress.get(0).getContent());

        System.err.println("Q: " + "tony");
        final OpenAIResponse<GPT35TurboRequest.Message> chat2 = contextService
                .chat(new GPT35TurboRequest.Message("0001", RoleEnum.USER.getRoleName(), "tony"));
        System.out.println("A: " + chat2.ress.get(0).getContent());
    
        System.out.println("\r ======================");
        
        System.err.println("Q: " + "tony");
        final OpenAIResponse<GPT35TurboRequest.Message> chat3 = contextService
                .chat(new GPT35TurboRequest.Message("0002", RoleEnum.USER.getRoleName(), "tony"));
        System.out.println("A: " + chat3.ress.get(0).getContent());
    
        System.out.println("\r ======================");
    
        System.err.println("Q: " + "tony");
        final OpenAIResponse<GPT35TurboRequest.Message> chat4 = contextService
                .chat(new GPT35TurboRequest.Message("0003", RoleEnum.USER.getRoleName(), "tony"));
        System.out.println("A: " + chat4.ress.get(0).getContent());
        
        applicationContext.close();
    }
}
