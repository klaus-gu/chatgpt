package xyz.openai.chatgpt.client.spring.conversation;

import java.util.LinkedList;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public interface ConversationMapper<T> {
    
    LinkedList<T> getContext(String conversationId);
    
    void saveContext(LinkedList<T> contexts);
    
    LinkedList<T> appendContext(String conversationId,LinkedList<T> contexts);

}
