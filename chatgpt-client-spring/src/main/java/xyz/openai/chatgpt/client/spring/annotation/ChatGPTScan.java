package xyz.openai.chatgpt.client.spring.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.core.convert.support.DefaultConversionService;
import xyz.openai.chatgpt.client.spring.registry.ChatGPTServiceConfigurer;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Scanner 注解入口 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(ChatGPTServiceConfigurer.class)
public @interface ChatGPTScan {
    
    String[] basePackages() default {};
    
    Class<?> annotationClass() default ChatGPTClient.class;
    
}
