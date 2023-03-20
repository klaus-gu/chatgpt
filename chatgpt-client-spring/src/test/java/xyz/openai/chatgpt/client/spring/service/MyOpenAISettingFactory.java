package xyz.openai.chatgpt.client.spring.service;

import xyz.openai.chatgpt.client.setting.OpenAISetting;
import xyz.openai.chatgpt.client.spring.core.factory.OpenAISettingFactory;

/**
 * Mock .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class MyOpenAISettingFactory implements OpenAISettingFactory {
    
    @Override
    public OpenAISetting getSetting() {
        final OpenAISetting openAISetting = new OpenAISetting();
        openAISetting.apiKey = "sk-7**********************5vNAyZrEqU3AU";
        return openAISetting;
    }
}
