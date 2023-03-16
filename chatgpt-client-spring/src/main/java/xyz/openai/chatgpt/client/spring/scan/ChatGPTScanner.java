package xyz.openai.chatgpt.client.spring.scan;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ChatGPTScanner extends ClassPathBeanDefinitionScanner {
    
    public ChatGPTScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }
    
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }
}
