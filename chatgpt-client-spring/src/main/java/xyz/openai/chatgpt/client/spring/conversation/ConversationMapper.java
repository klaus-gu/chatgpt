package xyz.openai.chatgpt.client.spring.conversation;

import java.util.List;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public interface ConversationMapper<T> {
    
    List<T> getContext(String conversationId);
    
    List<T> appendContext(String conversationId, List<T> contexts);
    
}
