package xyz.openai.chatgpt.client.spring.service;

import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.spring.annotation.ChatGPTClient;
import xyz.openai.chatgpt.client.spring.annotation.GPT35Turbo;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@ChatGPTClient
public interface ChatGPTService01 {
    
    @GPT35Turbo
    OpenAIResponse<GPT35TurboRequest.Message> chat(GPT35TurboRequest.Message message);
    
}
