package xyz.openai.chatgpt.client.spring;

import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.enums.RoleEnum;
import xyz.openai.chatgpt.client.spring.conversation.ConversationMapper;
import xyz.openai.chatgpt.client.spring.conversation.ConversationMapperFactory;
import xyz.openai.chatgpt.client.spring.conversation.DefaultGPT35TurboConversationMapperFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * ${@link DefaultGPT35TurboConversationMapperFactory} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class DefaultConversationMapperTEST {
    
    public static void main(String[] args) {
        ConversationMapperFactory<GPT35TurboRequest.Message> conversationMapperFactory = new DefaultGPT35TurboConversationMapperFactory();
        final ConversationMapper<GPT35TurboRequest.Message> mapper = conversationMapperFactory
                .getConversationMapper();
        String convrsationId = "111111";
        GPT35TurboRequest.Message message1 = new GPT35TurboRequest.Message(convrsationId, RoleEnum.USER.getRoleName(),"hello");
        GPT35TurboRequest.Message message2 = new GPT35TurboRequest.Message(convrsationId, RoleEnum.USER.getRoleName(),"world");
        
        List<GPT35TurboRequest.Message> list = new LinkedList<>();
        list.add(message1);
        list.add(message2);
        mapper.saveContext(list);
        System.out.println(mapper.getContext(convrsationId));
    
        GPT35TurboRequest.Message message3 = new GPT35TurboRequest.Message(convrsationId, RoleEnum.USER.getRoleName(),"i'm");
        GPT35TurboRequest.Message message4 = new GPT35TurboRequest.Message(convrsationId, RoleEnum.USER.getRoleName(),"turbo");
        List<GPT35TurboRequest.Message> list1 = new LinkedList<>();
        list1.add(message3);
        list1.add(message4);
        
        mapper.appendContext(convrsationId,list1);
        System.out.println(mapper.getContext(convrsationId));
    }
}
