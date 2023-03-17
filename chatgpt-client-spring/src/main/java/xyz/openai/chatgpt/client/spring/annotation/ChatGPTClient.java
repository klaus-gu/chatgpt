package xyz.openai.chatgpt.client.spring.annotation;

import xyz.openai.chatgpt.client.spring.factory.OpenAISettingFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate an ChatGPT client service .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface ChatGPTClient {
    
    /**
     * factory for init openai client service.
     * @return
     */
    Class<? extends OpenAISettingFactory> settingFactory();
    
}
