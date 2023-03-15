package xyz.openai.chatgpt.client;

import com.alibaba.fastjson2.JSON;
import xyz.openai.chatgpt.client.builder.RequestBuilder;
import xyz.openai.chatgpt.client.builder.RequestHeaderBuilder;
import xyz.openai.chatgpt.client.config.OpenAIConfiguration;

import java.util.Map;

/**
 * 请求提供 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class RequestProvider {
    
    private String url;
    
    private String data;
    
    private Map<String, String> header;
    
    public String getUrl() {
        return url;
    }
    
    public String getData() {
        return data;
    }
    
    public Map<String, String> getHeader() {
        return header;
    }
    
    public RequestProvider create(String question) {
        RequestProvider provider = new RequestProvider();
        OpenAIConfiguration instance = OpenAIConfiguration.getInstance();
        provider.url = "https://api.openai.com/v1/chat/completions";
        provider.header = RequestHeaderBuilder.getInstance().getGPT35TurboHeaders();
        if (instance.getEnableContext()) {
            provider.data = JSON.toJSONString(RequestBuilder.buildGpt35Turbo(question));
        } else {
            provider.data = JSON.toJSONString(RequestBuilder.buildGpt35Turbo(question));
        }
        
        return provider;
    }
}
