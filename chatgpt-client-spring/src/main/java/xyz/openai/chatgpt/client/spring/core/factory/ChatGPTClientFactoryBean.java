package xyz.openai.chatgpt.client.spring.core.factory;

import org.springframework.beans.factory.FactoryBean;

/**
 * ${@link FactoryBean} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
public class ChatGPTClientFactoryBean implements FactoryBean {
    
    private Class chatGPTClient;
    
    public ChatGPTClientFactoryBean(Class chatGPTClient) {
        this.chatGPTClient = chatGPTClient;
    }
    
    @Override
    public Object getObject() throws Exception {
        return ChatGPTServiceProxyFactory.getProxy(chatGPTClient);
    }
    
    @Override
    public Class<?> getObjectType() {
        return chatGPTClient;
    }
    
    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
    
    public Class getChatGPTClient() {
        return chatGPTClient;
    }
    
    public void setChatGPTClient(Class chatGPTClient) {
        this.chatGPTClient = chatGPTClient;
    }
    
}
