package xyz.openai.chatgpt.client.spring.conversation;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry for ConversationMapper .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ConversationMapperRegistry {
    
    private final Map<Method, Class<? extends ConversationMapper>> conversationMapperClazzs = new ConcurrentHashMap<>();
    
    private final Map<Method, ConversationMapper> conversationMappers = new ConcurrentHashMap<>();
    
    protected void registry(Method method, Class<? extends ConversationMapper> clazz) {
        conversationMapperClazzs.put(method, clazz);
    }
    
    protected ConversationMapper getConversationMapper(Method method) {
        return conversationMappers.get(method);
    }
}
