package xyz.openai.chatgpt.client;

import xyz.openai.chatgpt.client.entity.OpenAIException;
import xyz.openai.chatgpt.client.handler.GPTHandlerDelegate;
import xyz.openai.chatgpt.client.enums.ModelTypeEnum;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

/**
 * ${@link GPTHandlerDelegate} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class GPT35TurboHandlerTEST {
    
    public static void main(String[] args) throws OpenAIException {
        OpenAISetting openAISetting = new OpenAISetting();
        openAISetting.modelToUse = ModelTypeEnum.GPT35_TRUBO;
        openAISetting.apiKey = ("sk-*******PSY");
        //        openAIConfiguration.setEnableProxy(true);
        new GPTHandlerDelegate(openAISetting).handle("who are you?");
        
    }
}
