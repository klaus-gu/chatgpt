package xyz.openai.chatgpt.client.spring.core.annotation;

import org.springframework.context.annotation.Import;
import xyz.openai.chatgpt.client.spring.core.scan.ChatGPTServiceConfigurer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Scanner Configuration .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(ChatGPTServiceConfigurer.class)
public @interface ChatGPTScan {
    
    String[] basePackages() default {};
    
    Class<?> annotationClass() default ChatGPTClient.class;
    
}
