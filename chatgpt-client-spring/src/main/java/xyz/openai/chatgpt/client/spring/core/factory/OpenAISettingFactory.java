package xyz.openai.chatgpt.client.spring.core.factory;

import xyz.openai.chatgpt.client.setting.OpenAISetting;

/**
 * Factory for ${@link xyz.openai.chatgpt.client.setting.OpenAISetting}  .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
public interface OpenAISettingFactory {
    
    OpenAISetting getSetting();
}
