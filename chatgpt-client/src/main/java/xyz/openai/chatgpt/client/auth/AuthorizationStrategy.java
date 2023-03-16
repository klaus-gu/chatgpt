package xyz.openai.chatgpt.client.auth;

import xyz.openai.chatgpt.client.enums.AuthorizationTypeEnum;

/**
 * 校验策略 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
interface AuthorizationStrategy {
    
    AuthorizationTypeEnum getAuthorizationType();
    
    String getAuthorization();
}
