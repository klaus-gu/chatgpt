package xyz.openai.chatgpt.client.spring.conversation;

/**
 * Factory for ${@link ConversationMapper} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
public interface ConversationMapperFactory<T> {
      ConversationMapper<T> getConversationMapper();
}
