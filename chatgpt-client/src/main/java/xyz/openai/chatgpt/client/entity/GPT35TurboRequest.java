package xyz.openai.chatgpt.client.entity;

import xyz.openai.chatgpt.client.enums.ModelTypeEnum;

import java.util.LinkedList;
import java.util.List;

/**
 * GPT35Turbo 模型请求包装 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class GPT35TurboRequest {
    
    private String model = ModelTypeEnum.GPT35_TRUBO.getModelName();
    
    private Float temperature;
    
    private List<Message> messages = new LinkedList<>();
    
    public void addMsg(Message message) {
        this.messages.add(message);
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public Float getTemperature() {
        return temperature;
    }
    
    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }
    
    public List<Message> getMessages() {
        return messages;
    }
    
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    
    @Override
    public String toString() {
        return "GPT35TurboRequest{" + "model='" + model + '\'' + ", temperature=" + temperature + ", messages="
                + messages + '}';
    }
    
    public static class Message {
        
        private String role;
        
        private String content;
        
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
        
        public Message() {
        
        }
        
        public String getRole() {
            return role;
        }
        
        public void setRole(String role) {
            this.role = role;
        }
        
        public String getContent() {
            return content;
        }
        
        public void setContent(String content) {
            this.content = content;
        }
        
        @Override
        public String toString() {
            return "Message{" + "role='" + role + '\'' + ", content='" + content + '\'' + '}';
        }
    }
}
