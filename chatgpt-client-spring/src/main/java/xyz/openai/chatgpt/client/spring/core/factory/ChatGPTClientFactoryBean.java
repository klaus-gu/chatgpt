package xyz.openai.chatgpt.client.spring.core.factory;

import org.springframework.beans.factory.FactoryBean;
import xyz.openai.chatgpt.client.spring.conversation.ConversationMapperFactory;

/**
 * ${@link FactoryBean} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ChatGPTClientFactoryBean implements FactoryBean {
    
    private Class chatGPTClient;
    
    private OpenAISettingFactory openAISettingFactory;
    
    private ConversationMapperFactory conversationMapperFactory;
    
    public ChatGPTClientFactoryBean(Class chatGPTClient) {
        this.chatGPTClient = chatGPTClient;
    }
    
    @Override
    public Object getObject() throws Exception {
        return ChatGPTServiceProxyFactory.getProxy(chatGPTClient, openAISettingFactory, conversationMapperFactory);
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
    
    public OpenAISettingFactory getOpenAISettingFactory() {
        return openAISettingFactory;
    }
    
    public void setOpenAISettingFactory(OpenAISettingFactory openAISettingFactory) {
        this.openAISettingFactory = openAISettingFactory;
    }
    
    public ConversationMapperFactory getConversationMapperFactory() {
        return conversationMapperFactory;
    }
    
    public void setConversationMapperFactory(ConversationMapperFactory conversationMapperFactory) {
        this.conversationMapperFactory = conversationMapperFactory;
    }
}
