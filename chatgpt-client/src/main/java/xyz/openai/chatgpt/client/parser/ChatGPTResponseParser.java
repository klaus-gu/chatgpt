package xyz.openai.chatgpt.client.parser;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import xyz.openai.chatgpt.client.ConversationManager;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.util.HtmlUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 解析 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ChatGPTResponseParser {
    
    private static final String PREFIX = "data: ";
    
    private static final String DETAIL = "detail";
    
    private static final String DONE = "[DONE]";
    
    public static ParseResult parseChatGPT(String response) {
        JSONObject jsonObject = JSON.parseObject(response);
        // Handler the error info from the proxy server.
        if (jsonObject.containsKey("detail")) {
            String detail = jsonObject.getString("detail");
            return null;
        }
        JSONArray partsArray = jsonObject.getJSONObject("message").getJSONObject("content").getJSONArray("parts");
        String conversationId = jsonObject.getString("conversation_id");
        String parentId = (jsonObject.getJSONObject("message")).getString("id");
        ConversationManager.getInstance().setParentMessageId(parentId);
        ConversationManager.getInstance().setConversationId(conversationId);
        
        if (partsArray.size() == 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < partsArray.size(); i++) {
            result.append(partsArray.getString(i));
        }
        ParseResult parseResult = new ParseResult();
        parseResult.source = HtmlUtil.md2html(result.toString());
        parseResult.html = HtmlUtil.md2html(result.toString());
        return parseResult;
    }
    
    public static ParseResult parseGPT35Turbo(String response) {
        JSONObject object = JSON.parseObject(response);
        StringBuilder result = new StringBuilder();
        JSONArray resultArray = object.getJSONArray("choices");
        for (Object s : resultArray) {
            JSONObject choice = JSON.parseObject(s.toString());
            JSONObject messages = choice.getJSONObject("message");
            String content = JSON.parseObject(messages.toString()).getString("content");
            result.append(content);
        }
        ParseResult parseResult = new ParseResult();
        parseResult.source = HtmlUtil.md2html(result.toString());
        parseResult.html = HtmlUtil.md2html(result.toString());
        parseResult.id = object.getString("id");
        return parseResult;
    }
    
    public static List<GPT35TurboRequest.Message> parseGPT35TurboToMessage(String response) {
        JSONObject object = JSON.parseObject(response);
        JSONArray resultArray = object.getJSONArray("choices");
        List<GPT35TurboRequest.Message> messageList = new LinkedList<>();
        for (Object s : resultArray) {
            JSONObject choice = JSON.parseObject(s.toString());
            JSONObject messages = choice.getJSONObject("message");
            GPT35TurboRequest.Message message = JSONObject
                    .parseObject(messages.toString(), GPT35TurboRequest.Message.class);
            messageList.add(message);
        }
        return messageList;
    }
    
    public static class ParseResult {
        
        private String id;
        
        private String source;
        
        private String html;
        
        public String getId() {
            return id;
        }
        
        public String getSource() {
            return source;
        }
        
        public String getHtml() {
            return html;
        }
    }
    
}
