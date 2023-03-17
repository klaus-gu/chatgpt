package xyz.openai.chatgpt.client.spring.factory;

import org.springframework.beans.factory.FactoryBean;

/**
 * ${@link FactoryBean} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ChatGPTClientFactoryBean implements FactoryBean {
    
    private Class chatGPTClient;
    
    private OpenAISettingFactory openAISettingFactory;
    
    public ChatGPTClientFactoryBean(Class chatGPTClient) {
        this.chatGPTClient = chatGPTClient;
    }
    
    @Override
    public Object getObject() throws Exception {
        return ChatGPTServiceProxyFactory.getProxy(chatGPTClient, openAISettingFactory);
    }
    
    @Override
    public Class<?> getObjectType() {
        return chatGPTClient;
    }
    
    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
    
    public void setChatGPTClient(Class chatGPTClient) {
        this.chatGPTClient = chatGPTClient;
    }
    
    public void setOpenAISettingFactory(OpenAISettingFactory openAISettingFactory) {
        this.openAISettingFactory = openAISettingFactory;
    }
}
