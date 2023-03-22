package xyz.openai.chatgpt.client.handler;

import xyz.openai.chatgpt.client.entity.OpenAIException;
import xyz.openai.chatgpt.client.enums.ModelTypeEnum;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

import java.util.HashMap;
import java.util.Map;

/**
 * GPT proxy .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
public class GPTHandlerDelegate<M,R> {
    
    protected final OpenAISetting openAISetting;
    
    private Map<ModelTypeEnum, AbstractGPTHandler> handlerMap = new HashMap<>();
    
    public GPTHandlerDelegate(OpenAISetting openAISetting) {
        this.openAISetting = openAISetting;
        GPT35TurboHandler gpt35TurboHandler = new GPT35TurboHandler(openAISetting);
        handlerMap.put(gpt35TurboHandler.getType(), gpt35TurboHandler);
        TextDavinci002RenderShaHandler textDavinci002RenderShaHandler = new TextDavinci002RenderShaHandler(
                openAISetting);
        handlerMap.put(textDavinci002RenderShaHandler.getType(), textDavinci002RenderShaHandler);
    }
    
    ModelTypeEnum getType() {
        return openAISetting.modelToUse;
    }
    
    public R handle(M... objects) throws OpenAIException {
        return (R)handlerMap.get(getType()).handle(objects);
    }
}
