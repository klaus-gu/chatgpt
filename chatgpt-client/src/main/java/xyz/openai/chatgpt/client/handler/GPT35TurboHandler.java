package xyz.openai.chatgpt.client.handler;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIException;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.enums.ModelTypeEnum;
import xyz.openai.chatgpt.client.parser.ChatGPTResponseParser;
import xyz.openai.chatgpt.client.request.RequestProvider;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

import java.nio.charset.StandardCharsets;

/**
 * gpt-35-turbo 模型处理 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
class GPT35TurboHandler
        extends AbstractGPTHandler<GPT35TurboRequest.Message, OpenAIResponse<GPT35TurboRequest.Message>> {
    
    public GPT35TurboHandler(OpenAISetting openAISetting) {
        super(openAISetting);
    }
    
    @Override
    OpenAIResponse<GPT35TurboRequest.Message> handle(GPT35TurboRequest.Message... messages) throws OpenAIException {
        RequestProvider<GPT35TurboRequest.Message> provider = new RequestProvider<GPT35TurboRequest.Message>(
                openAISetting).create(messages);
        HttpResponse response = HttpUtil.createPost(provider.getUrl()).headerMap(provider.getHeader(), true)
                .setConnectionTimeout(Integer.parseInt(openAISetting.connectionTimeout))
                .setReadTimeout(Integer.parseInt(openAISetting.readTimeout)).setProxy(getProxy())
                .body(provider.getData().getBytes(StandardCharsets.UTF_8)).executeAsync();
        if (response == null) {
            throw new OpenAIException("GPT35TurboHandler", 0, "no response");
        }
        if (response.getStatus() != 200) {
            throw new OpenAIException("GPT35TurboHandler", response.getStatus(), response.body());
        }
        OpenAIResponse<GPT35TurboRequest.Message> messageOpenAIResponse = new OpenAIResponse<>();
        messageOpenAIResponse.ress = ChatGPTResponseParser.parseGPT35TurboToMessage(response.body());
        messageOpenAIResponse.id = ChatGPTResponseParser.parseGPT35TurboConversationId(response.body());
        return messageOpenAIResponse;
    }
    
    @Override
    ModelTypeEnum getType() {
        return ModelTypeEnum.GPT35_TRUBO;
    }
}
