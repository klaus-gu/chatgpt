package xyz.openai.chatgpt.client.auth;

import org.apache.commons.lang3.StringUtils;
import xyz.openai.chatgpt.client.enums.AuthorizationTypeEnum;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

/**
 * TOKEN 认证方式 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
class TokenAuthorizationStrategy implements AuthorizationStrategy {
    
    private final OpenAISetting setting;
    
    public TokenAuthorizationStrategy(OpenAISetting setting) {
        this.setting = setting;
    }
    
    @Override
    public AuthorizationTypeEnum getAuthorizationType() {
        return AuthorizationTypeEnum.TOKEN;
    }
    
    @Override
    public String getAuthorization() {
        if (StringUtils.isEmpty(setting.accessToken)) {
            throw new IllegalArgumentException("【OpenAISetting】token has not been inited");
        }
        return setting.accessToken;
    }
}
