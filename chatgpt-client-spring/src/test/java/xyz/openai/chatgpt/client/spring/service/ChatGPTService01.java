package xyz.openai.chatgpt.client.spring.service;

import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTClient;
import xyz.openai.chatgpt.client.spring.core.annotation.GPT35Turbo;

import java.util.List;

/**
 * Mock .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@ChatGPTClient(settingFactory = MyOpenAISettingFactory.class)
public interface ChatGPTService01 {
    
    @GPT35Turbo
    OpenAIResponse<GPT35TurboRequest.Message> chat(GPT35TurboRequest.Message message);
    
    @GPT35Turbo
    OpenAIResponse<GPT35TurboRequest.Message> chat(List<GPT35TurboRequest.Message> message);
}
