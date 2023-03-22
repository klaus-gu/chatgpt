package xyz.openai.chatgpt.client.enums;

/**
 * 模型类型 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
public enum ModelTypeEnum {
    GPT35_TRUBO("gpt-3.5-turbo"),
    TEXT_DAVINCI_002_RENDER_SHA("text-davinci-002-render-sha");
    
    private String modelName;
    
    ModelTypeEnum(String modelName) {
        this.modelName = modelName;
    }
    
    public String getModelName() {
        return modelName;
    }
}
