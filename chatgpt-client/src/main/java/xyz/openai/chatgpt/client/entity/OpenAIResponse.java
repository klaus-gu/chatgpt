package xyz.openai.chatgpt.client.entity;

import java.util.List;

/**
 * 响应 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class OpenAIResponse<T> {
    
    public String id;
    
    public T res;
    
    public List<T> ress;
    
    @Override
    public String toString() {
        return "OpenAIResponse{" + "id='" + id + '\'' + ", res=" + res + ", ress=" + ress + '}';
    }
}
