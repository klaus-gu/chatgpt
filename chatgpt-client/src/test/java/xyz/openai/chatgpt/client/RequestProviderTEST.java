package xyz.openai.chatgpt.client;

import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.enums.ModelTypeEnum;
import xyz.openai.chatgpt.client.enums.RoleEnum;
import xyz.openai.chatgpt.client.request.RequestProvider;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

/**
 * ${@link RequestProvider} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class RequestProviderTEST {
    
    public static void main(String[] args) {
        final OpenAISetting openAISetting = new OpenAISetting();
        openAISetting.modelToUse = ModelTypeEnum.GPT35_TRUBO;
        RequestProvider provider = new RequestProvider(openAISetting)
                .create(new GPT35TurboRequest.Message(RoleEnum.USER.getRoleName(), "who are you?"));
        System.out.println(provider.getData());
    }
}
