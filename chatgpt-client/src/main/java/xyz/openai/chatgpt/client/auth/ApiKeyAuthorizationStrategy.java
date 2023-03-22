package xyz.openai.chatgpt.client.auth;

import org.apache.commons.lang3.StringUtils;
import xyz.openai.chatgpt.client.enums.AuthorizationTypeEnum;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

/**
 * API-KEY 认证方式 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
class ApiKeyAuthorizationStrategy implements AuthorizationStrategy {
    
    private final OpenAISetting setting;
    
    public ApiKeyAuthorizationStrategy(OpenAISetting setting) {
        this.setting = setting;
    }
    
    @Override
    public AuthorizationTypeEnum getAuthorizationType() {
        return AuthorizationTypeEnum.API_KEY;
    }
    
    @Override
    public String getAuthorization() {
        if (StringUtils.isEmpty(setting.apiKey)) {
            throw new IllegalArgumentException("【OpenAISetting】apikey has not been inited");
        }
        return setting.apiKey;
    }
}
