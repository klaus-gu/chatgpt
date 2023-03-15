package xyz.openai.chatgpt.client;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.openai.chatgpt.client.parser.ChatGPTResponseParser;

import java.nio.charset.StandardCharsets;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class GPT35TurboHandler extends AbstractGPTHandler {
    
    private static final Logger LOG = LoggerFactory.getLogger(GPT35TurboHandler.class);
    
    @Override
    public String  handle(String question) {
        RequestProvider provider = new RequestProvider().create(question);
        try {
            LOG.info("ChatGPT Request: question={}", question);
            HttpResponse response = HttpUtil.createPost(provider.getUrl()).headerMap(provider.getHeader(), true)
                    .setProxy(getProxy()).body(provider.getData().getBytes(StandardCharsets.UTF_8)).executeAsync();
            LOG.info("ChatGPT Response: answer={}", response.body());
            if (response.getStatus() != 200) {
                LOG.info("ChatGPT: Request failure. Url={}, response={}", provider.getUrl(), response.body());
              
                return null;
            }
            ChatGPTResponseParser.ParseResult parseResult = ChatGPTResponseParser.parseGPT35Turbo(response.body());
            return parseResult.getHtml();
        } catch (Exception e) {
        
        }
        return null;
    }
}
