package xyz.openai.chatgpt.client;

import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIException;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.enums.ModelTypeEnum;
import xyz.openai.chatgpt.client.handler.GPTHandlerDelegate;
import xyz.openai.chatgpt.client.setting.OpenAISetting;
import xyz.openai.chatgpt.client.util.OpenAISettingCheckUtil;

/**
 * OpenAI 开放功能 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class OpenAI {
    
    static class ChatGPT {
        
        private static OpenAISetting openAISetting = null;
        
        static class ChatGPT35Turbo extends ChatGPT {
            
            public static ChatGPT35Turbo chatGPT35Turbo = null;
            
            public static ChatGPT35Turbo config(OpenAISetting openAISetting) {
                chatGPT35Turbo = new ChatGPT35Turbo();
                ChatGPT.openAISetting = openAISetting;
                openAISetting.modelToUse = ModelTypeEnum.GPT35_TRUBO;
                return chatGPT35Turbo;
            }
            
            public OpenAIResponse<GPT35TurboRequest.Message> handle(GPT35TurboRequest.Message... message)
                    throws OpenAIException {
                OpenAISettingCheckUtil.check(openAISetting);
                GPTHandlerDelegate<GPT35TurboRequest.Message,OpenAIResponse<GPT35TurboRequest.Message>> delegate = new GPTHandlerDelegate<GPT35TurboRequest.Message,OpenAIResponse<GPT35TurboRequest.Message>>(openAISetting);
                return delegate.handle(message);
            }
        }
        
        static class TextDavinci002RenderSha extends ChatGPT {
            
            public static TextDavinci002RenderSha textDavinci002RenderSha = null;
            
            public static TextDavinci002RenderSha config(OpenAISetting openAISetting) {
                textDavinci002RenderSha = new TextDavinci002RenderSha();
                ChatGPT.openAISetting = openAISetting;
                openAISetting.modelToUse = ModelTypeEnum.TEXT_DAVINCI_002_RENDER_SHA;
                return textDavinci002RenderSha;
            }
            
            public OpenAIResponse handle(String... message) throws OpenAIException {
                OpenAISettingCheckUtil.check(openAISetting);
                GPTHandlerDelegate delegate = new GPTHandlerDelegate(openAISetting);
                final String handle = (String) delegate.handle(message);
                return new OpenAIResponse();
            }
        }
    }
}
