package xyz.openai.chatgpt.client.handler;

import xyz.openai.chatgpt.client.enums.ModelTypeEnum;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

/**
 * text-davinci-002-render-sha 模型处理 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
class TextDavinci002RenderShaHandler extends AbstractGPTHandler<String, String> {
    
    public TextDavinci002RenderShaHandler(OpenAISetting openAISetting) {
        super(openAISetting);
    }
    
    @Override
    ModelTypeEnum getType() {
        return ModelTypeEnum.TEXT_DAVINCI_002_RENDER_SHA;
    }
    
    @Override
    String handle(String... question) {
        return null;
    }
}
