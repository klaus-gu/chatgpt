package xyz.openai.chatgpt.client.auth;

import xyz.openai.chatgpt.client.enums.AuthorizationTypeEnum;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略代理 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class AuthorizationDelegate implements AuthorizationStrategy {
    
    private static AuthorizationDelegate authorizationDelegate = null;
    
    private static Map<AuthorizationTypeEnum, AuthorizationStrategy> strategyMap = new HashMap<>();
    
    private final OpenAISetting setting;
    
    private AuthorizationDelegate(OpenAISetting setting) {
        this.setting = setting;
        ApiKeyAuthorizationStrategy apiKeyAuthorizationStrategy = new ApiKeyAuthorizationStrategy(setting);
        TokenAuthorizationStrategy tokenAuthorizationStrategy = new TokenAuthorizationStrategy(setting);
        strategyMap.put(apiKeyAuthorizationStrategy.getAuthorizationType(), apiKeyAuthorizationStrategy);
        strategyMap.put(tokenAuthorizationStrategy.getAuthorizationType(), apiKeyAuthorizationStrategy);
    }
    
    public static AuthorizationDelegate getInstance(OpenAISetting setting) {
        if (authorizationDelegate == null) {
            authorizationDelegate = new AuthorizationDelegate(setting);
        }
        return authorizationDelegate;
    }
    
    @Override
    public AuthorizationTypeEnum getAuthorizationType() {
        if (setting.enableApiKeyAuthorization) {
            return AuthorizationTypeEnum.API_KEY;
        }
        if (setting.enableTokenAuthorization) {
            return AuthorizationTypeEnum.TOKEN;
        }
        throw new IllegalArgumentException(
                "【OpenAISetting】expect at least one authorization type been enabled, but found none.");
    }
    
    @Override
    public String getAuthorization() {
        return strategyMap.get(getAuthorizationType()).getAuthorization();
    }
}
