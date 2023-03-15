package xyz.openai.chatgpt.client.core;

import java.util.UUID;

/**
 * Conversation manage .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ConversationManager {
    
    private static final ConversationManager CONVERSATION_MANAGER = new ConversationManager();
    
    private String parentMessageId = UUID.randomUUID().toString();
    
    private String conversationId = "";
    
    public static ConversationManager getInstance() {
        return ConversationManager.CONVERSATION_MANAGER;
    }
    
    public String getParentMessageId() {
        return parentMessageId;
    }
    
    public void setParentMessageId(String parentMessageId) {
        this.parentMessageId = parentMessageId;
    }
    
    public String getConversationId() {
        return conversationId;
    }
    
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}