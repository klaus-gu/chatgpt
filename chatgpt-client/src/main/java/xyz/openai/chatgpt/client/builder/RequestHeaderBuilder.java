package xyz.openai.chatgpt.client.builder;

import xyz.openai.chatgpt.client.config.OpenAIConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class RequestHeaderBuilder {
    
    private static final RequestHeaderBuilder REQUEST_HEADER_BUILDER = new RequestHeaderBuilder();
    
    private final Map<String, String> headers = new HashMap<String, String>();
    
    private OpenAIConfiguration openAIConfiguration = OpenAIConfiguration.getInstance();
    
    public static RequestHeaderBuilder getInstance() {
        return REQUEST_HEADER_BUILDER;
    }
    
    public Map<String, String> getChatGPTHeaders() {
        headers.put("Accept", "text/event-stream");
        headers.put("Authorization", "Bearer " + openAIConfiguration.getAccessToken());
        headers.put("Content-Type", "application/json");
        headers.put("X-Openai-Assistant-App-Id", "");
        headers.put("Connection", "close");
        headers.put("Accept-Language", "en-US,en;q=0.9");
        headers.put("Referer", "https://chat.openai.com/chat");
        headers.put("User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.1 Safari/605.1.15");
        return headers;
    }
    
    public Map<String, String> getGPT35TurboHeaders() {
        headers.put("Authorization", "Bearer " + openAIConfiguration.getApiKey());
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
