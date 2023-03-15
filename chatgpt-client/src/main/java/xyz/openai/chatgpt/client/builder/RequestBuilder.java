package xyz.openai.chatgpt.client.builder;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;
import xyz.openai.chatgpt.client.config.OpenAIConfiguration;
import xyz.openai.chatgpt.client.core.ConversationManager;

import java.util.UUID;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class RequestBuilder {
    
    public static JSONObject buildChatGPT(String text) {
        JSONObject result = new JSONObject();
        result.put("action", "next");
        
        JSONArray messages = new JSONArray();
        JSONObject message0 = new JSONObject();
        message0.put("id", UUID.randomUUID());
        message0.put("role", "user");
        
        JSONObject content = new JSONObject();
        content.put("content_type", "text");
        JSONArray parts = new JSONArray();
        parts.add(text);
        content.put("parts", parts);
        
        JSONObject author = new JSONObject();
        author.put("role", "user");
        message0.put("content", content);
        message0.put("author", author);
        messages.add(message0);
        result.put("messages", messages);
        
        result.put("parent_message_id", ConversationManager.getInstance().getParentMessageId());
        String conversationId = ConversationManager.getInstance().getConversationId();
        if (StringUtils.isNotEmpty(conversationId)) {
            result.put("conversation_id", conversationId);
        }
        OpenAIConfiguration settingsState = OpenAIConfiguration.getInstance();
        result.put("model", settingsState.getChatGptModel());
        return result;
    }
    
    public static JSONObject buildGpt35Turbo(String text) {
        JSONObject result = new JSONObject();
        result.put("model", "gpt-3.5-turbo");
        JSONArray messages = new JSONArray();
        JSONObject message0 = new JSONObject();
        message0.put("role", "user");
        message0.put("content", text);
        messages.add(message0);
        result.put("messages", messages);
        return result;
    }
    //
    //    public static JSONObject buildGpt35Turbo(String text) {
    //        JSONObject result = new JSONObject();
    //        OpenAIConfiguration settingsState = OpenAIConfiguration.getInstance();
    //        result.put("model",settingsState.gpt35Model);
    //        return result;
    //    }
    
    private static JSONObject message(String role, String text) {
        JSONObject message = new JSONObject();
        message.put("role", role);
        message.put("content", text);
        return message;
    }
    
    public static JSONObject userMessage(String text) {
        return message("user", text);
    }
    
    public static JSONObject systemMessage(String text) {
        return message("system", text);
    }
    
    public static JSONObject assistantMessage(String text) {
        return message("assistant", text);
    }
}
