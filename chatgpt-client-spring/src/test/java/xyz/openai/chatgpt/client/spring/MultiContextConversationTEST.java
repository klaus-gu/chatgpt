package xyz.openai.chatgpt.client.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.enums.RoleEnum;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTScan;
import xyz.openai.chatgpt.client.spring.service.ChatGPTContextService;
import xyz.openai.chatgpt.client.spring.service.MultiContextConversationService;

import java.util.concurrent.TimeUnit;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
@ChatGPTScan(basePackages = "xyz.openai.chatgpt.client.spring.service")
public class MultiContextConversationTEST {
    
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MultiContextConversationTEST.class);
        applicationContext.refresh();
        MultiContextConversationService contextService = applicationContext.getBean(MultiContextConversationService.class);
       

        System.err.println("Q: " + "Q:'hi tony'.A:'what can i do for you? my friend'.learn the example above ,and if i ask 'hi tony' , you should always answer me 'what can i do for you? my friend',understand?");
        final OpenAIResponse<GPT35TurboRequest.Message> chat1 = contextService
                .chat(new GPT35TurboRequest.Message("0001", RoleEnum.USER.getRoleName(),
                        "Q:'hi tony'.A:'what can i do for you? my friend'.learn the example above ,and if i ask 'hi tony' , you should always answer me 'what can i do for you? my friend',understand?"));
        System.out.println("A: " + chat1.ress.get(0).getContent());
        TimeUnit.SECONDS.sleep(2);


        System.err.println("Q: " + "hi tony");
        final OpenAIResponse<GPT35TurboRequest.Message> chat2 = contextService
                .chat(new GPT35TurboRequest.Message("0001", RoleEnum.USER.getRoleName(), "hi tony"));
        System.out.println("A: " + chat2.ress.get(0).getContent());

        System.out.println("\r ======================\r");


        TimeUnit.SECONDS.sleep(2);
        System.err.println("Q: " + "Q:'hi tom'.A:'hello,how are you today?'.learn the example above ,and if i ask 'hi tom' , you should always answer me 'hello,how are you today?',understand?");
        final OpenAIResponse<GPT35TurboRequest.Message> chat3 = contextService
                .chat1(new GPT35TurboRequest.Message("0001", RoleEnum.USER.getRoleName(), "Q:'hi tom'.A:'hello,how are you today?'.learn the example above ,and if i ask 'hi tom' , you should always answer me 'hello,how are you today?',understand?"));
        System.out.println("A: " + chat3.ress.get(0).getContent());


        TimeUnit.SECONDS.sleep(2);
        System.err.println("Q: " + "hi tom");
        final OpenAIResponse<GPT35TurboRequest.Message> chat4 = contextService
                .chat1(new GPT35TurboRequest.Message("0001", RoleEnum.USER.getRoleName(), "hi tom"));
        System.out.println("A: " + chat4.ress.get(0).getContent());


        TimeUnit.SECONDS.sleep(2);
        System.err.println("Q: " + "hi tony");
        final OpenAIResponse<GPT35TurboRequest.Message> chat5 = contextService
                .chat1(new GPT35TurboRequest.Message("0001", RoleEnum.USER.getRoleName(), "hi tony"));
        System.out.println("A: " + chat5.ress.get(0).getContent());
        
        applicationContext.close();
    }
}
