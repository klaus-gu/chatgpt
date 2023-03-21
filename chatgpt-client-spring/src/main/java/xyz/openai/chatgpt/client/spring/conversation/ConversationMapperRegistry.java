package xyz.openai.chatgpt.client.spring.conversation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry for ConversationMapper .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ConversationMapperRegistry implements BeanFactoryAware, SmartInitializingSingleton {
    
    private static final Map<Method, Class<? extends ConversationMapperFactory>> conversationMapperClazzs = new ConcurrentHashMap<>();
    
    private static final Map<Method, Class<? extends ConversationMapperFactory>> conversationMapperBeanClazzs = new ConcurrentHashMap<>();
    
    private static final Map<Method, ConversationMapperFactory> conversationMappers = new ConcurrentHashMap<>();
    
    private BeanFactory beanFactory;
    
    public static void registerMapperClazz(Method method, Class<? extends ConversationMapperFactory> clazz) {
        conversationMapperClazzs.put(method, clazz);
    }
    
    public static void registerMapperBeanClazz(Method method, Class<? extends ConversationMapperFactory> clazz) {
        conversationMapperBeanClazzs.put(method, clazz);
    }
    
    public static ConversationMapper getConversationMapper(Method method) {
        return conversationMappers.get(method).getConversationMapper();
    }
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
    
    @Override
    public void afterSingletonsInstantiated() {
        
        for (Map.Entry<Method, Class<? extends ConversationMapperFactory>> entry : conversationMapperClazzs
                .entrySet()) {
            conversationMappers.put(entry.getKey(), BeanUtils.instantiateClass(entry.getValue()));
        }
        for (Map.Entry<Method, Class<? extends ConversationMapperFactory>> entry : conversationMapperBeanClazzs
                .entrySet()) {
            conversationMappers.put(entry.getKey(), this.beanFactory.getBean(entry.getValue()));
        }
    }
    
}
