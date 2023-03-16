package xyz.openai.chatgpt.client.request;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;
import xyz.openai.chatgpt.client.ConversationManager;
import xyz.openai.chatgpt.client.enums.ModelTypeEnum;

import java.util.UUID;

/**
 * 构造请求 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class RequestBuilder {
    
    public static JSONObject buildTextDavinci002RenderSha(String text) {
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
        result.put("model", ModelTypeEnum.TEXT_DAVINCI_002_RENDER_SHA.getModelName());
        return result;
    }
    
}
