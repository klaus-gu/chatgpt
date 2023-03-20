package xyz.openai.chatgpt.client.spring.service;

import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTClient;
import xyz.openai.chatgpt.client.spring.core.annotation.GPT35Turbo;

/**
 * 多上下文测试 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@ChatGPTClient(settingFactory = MyOpenAISettingFactory.class)
public interface MultiContextConversationService {
    
    @GPT35Turbo(enableContext = true, conversationMapperFactory = MyDefaultGPT35TurboConversationMapperFactory.class)
    OpenAIResponse<GPT35TurboRequest.Message> chat(GPT35TurboRequest.Message message);
    
    @GPT35Turbo(enableContext = true)
    OpenAIResponse<GPT35TurboRequest.Message> chat1(GPT35TurboRequest.Message message);
}
