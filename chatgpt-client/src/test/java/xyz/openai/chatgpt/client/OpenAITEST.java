package xyz.openai.chatgpt.client;

import com.alibaba.fastjson2.JSON;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIException;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.enums.RoleEnum;
import xyz.openai.chatgpt.client.setting.OpenAISetting;
import xyz.openai.chatgpt.client.setting.SettingConfiguration;

/**
 * ${@link OpenAI} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class OpenAITEST {
    
    public static void main(String[] args) throws OpenAIException {
        OpenAISetting openAISetting = new OpenAISetting();
        openAISetting.apiKey="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";
//        openAISetting.enableProxy=true;
//        openAISetting.proxyHostname="127.0.0.1";
//        openAISetting.proxyPort="7890";
//        openAISetting.proxyType= SettingConfiguration.SettingProxyType.SOCKS;
        final OpenAIResponse handle = OpenAI.ChatGPT.ChatGPT35Turbo.config(openAISetting)
                .handle(new GPT35TurboRequest.Message(RoleEnum.USER.getRoleName(), "who are you ?"));
        System.out.println(JSON.toJSONString(handle));
    }
}
