package xyz.openai.chatgpt.client.spring.service;

import org.apache.commons.collections4.CollectionUtils;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.spring.conversation.ConversationMapper;
import xyz.openai.chatgpt.client.spring.conversation.ConversationMapperFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default ConversationMapperFactory for GPT35Turbo model .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
public class MyDefaultGPT35TurboConversationMapperFactory
        implements ConversationMapperFactory<GPT35TurboRequest.Message> {
    
    private final ConversationMapper<GPT35TurboRequest.Message> conversationMapper = new MyDefaultGPT35TurboConversationMapper();
    
    public MyDefaultGPT35TurboConversationMapperFactory() {
    }
    
    @Override
    public ConversationMapper<GPT35TurboRequest.Message> getConversationMapper() {
        return conversationMapper;
    }
    
    public static class MyDefaultGPT35TurboConversationMapper implements ConversationMapper<GPT35TurboRequest.Message> {
        
        private final ConcurrentHashMap<String, List<GPT35TurboRequest.Message>> conversationContextMap = new ConcurrentHashMap<>();
        
        public MyDefaultGPT35TurboConversationMapper() {
        }
        
        @Override
        public List<GPT35TurboRequest.Message> getContext(String conversationId) {
            return conversationContextMap.get(conversationId);
        }
        
        @Override
        public List<GPT35TurboRequest.Message> appendContext(String conversationId,
                List<GPT35TurboRequest.Message> newContexts) {
            final List<GPT35TurboRequest.Message> oldContext = getContext(conversationId);
            if (oldContext == null) {
                newContexts.forEach(message -> message.setConversationId(conversationId));
                conversationContextMap.put(conversationId,newContexts);
            }else {
                oldContext.addAll(newContexts);
                oldContext.forEach(message -> message.setConversationId(conversationId));
                conversationContextMap.put(conversationId,oldContext);
            }
            return getContext(conversationId);
        }
    }
}
