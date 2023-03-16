package xyz.openai.chatgpt.client.request;

import com.alibaba.fastjson2.JSON;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

import java.util.Arrays;
import java.util.Map;

/**
 * 请求提供 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class RequestProvider<T> {
    
    private String url;
    
    private String data;
    
    private Map<String, String> header;
    
    private OpenAISetting setting;
    
    public RequestProvider(OpenAISetting setting) {
        this.setting = setting;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getData() {
        return data;
    }
    
    public Map<String, String> getHeader() {
        RequestHeaderBuilder requestHeaderBuilder = new RequestHeaderBuilder(setting);
        switch (setting.modelToUse) {
            case GPT35_TRUBO:
                header = requestHeaderBuilder.getGPT35TurboHeaders();
                break;
            case TEXT_DAVINCI_002_RENDER_SHA:
                header = requestHeaderBuilder.getTextDavinci002RenderShaHeaders();
                break;
        }
        return header;
    }
    
    public RequestProvider<T> create(T... messages) {
        switch (setting.modelToUse) {
            case GPT35_TRUBO:
                this.url = "https://api.openai.com/v1/chat/completions";
                GPT35TurboRequest gpt35TurboRequest = new GPT35TurboRequest();
                for (T t : Arrays.asList(messages)) {
                    if (t instanceof GPT35TurboRequest.Message) {
                        gpt35TurboRequest.addMsg((GPT35TurboRequest.Message) t);
                    }
                }
                this.data = JSON.toJSONString(gpt35TurboRequest);
                break;
            case TEXT_DAVINCI_002_RENDER_SHA:
                break;
        }
        return this;
    }
}
