package xyz.openai.chatgpt.client.request;

import xyz.openai.chatgpt.client.auth.AuthorizationDelegate;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求头构造 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
public class RequestHeaderBuilder {
    
    private final Map<String, String> headers = new HashMap<>();
    
    private final OpenAISetting openAISetting;
    
    public RequestHeaderBuilder(OpenAISetting openAISetting) {
        this.openAISetting = openAISetting;
    }
    
    public Map<String, String> getTextDavinci002RenderShaHeaders() {
        headers.put("Accept", "text/event-stream");
        headers.put("Authorization", "Bearer " + AuthorizationDelegate.getInstance(openAISetting).getAuthorization());
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
        headers.put("Authorization", "Bearer " + AuthorizationDelegate.getInstance(openAISetting).getAuthorization());
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
