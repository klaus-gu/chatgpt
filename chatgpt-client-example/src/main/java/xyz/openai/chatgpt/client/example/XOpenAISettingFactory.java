package xyz.openai.chatgpt.client.example;

import org.springframework.stereotype.Component;
import xyz.openai.chatgpt.client.setting.OpenAISetting;
import xyz.openai.chatgpt.client.spring.core.factory.OpenAISettingFactory;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@Component
public class XOpenAISettingFactory implements OpenAISettingFactory {
    
    @Override
    public OpenAISetting getSetting() {
        final OpenAISetting openAISetting = new OpenAISetting();
        openAISetting.apiKey = "******";
        return openAISetting;
    }
}
