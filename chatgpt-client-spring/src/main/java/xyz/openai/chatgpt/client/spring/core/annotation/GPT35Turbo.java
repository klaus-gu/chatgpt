package xyz.openai.chatgpt.client.spring.core.annotation;

import xyz.openai.chatgpt.client.spring.conversation.ConversationMapperFactory;
import xyz.openai.chatgpt.client.spring.conversation.DefaultGPT35TurboConversationMapperFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to process GPT35Turbo model .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GPT35Turbo {

    boolean enableContext() default false;
    
    Class<? extends ConversationMapperFactory> conversationMapperFactory() default DefaultGPT35TurboConversationMapperFactory.class;
    
}
