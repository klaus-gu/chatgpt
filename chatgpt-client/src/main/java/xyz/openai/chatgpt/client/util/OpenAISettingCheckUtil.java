package xyz.openai.chatgpt.client.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

/**
 * 检查设置 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class OpenAISettingCheckUtil {
    
    public static void check(OpenAISetting openAISetting) {
        if (ObjectUtils.allNull(openAISetting.enableApiKeyAuthorization, openAISetting.enableTokenAuthorization)) {
            throw new IllegalArgumentException(
                    "【OpenAISetting init error】at least one authorization type should be setted");
        }
        if (openAISetting.enableTokenAuthorization != null && openAISetting.enableTokenAuthorization) {
            throw new IllegalArgumentException("【OpenAISetting init error】token authorization is not support");
            //            if(StringUtils.isAllEmpty(openAISetting.password,openAISetting.email)){
            //                throw new IllegalArgumentException("【OpenAISetting init error】email and password should not be empty when token authorization type is enabled");
            //            }
        }
        
        if (openAISetting.enableApiKeyAuthorization != null && !openAISetting.enableApiKeyAuthorization) {
            throw new IllegalArgumentException("【OpenAISetting init error】api-key authorization support only");
        }
        
        if (openAISetting.enableApiKeyAuthorization && StringUtils.isEmpty(openAISetting.apiKey)) {
            throw new IllegalArgumentException(
                    "【OpenAISetting init error】api-key should not be empty when api-key authorization type is enabled");
        }
        
        if (openAISetting.modelToUse == null) {
            throw new IllegalArgumentException("【OpenAISetting init error】at least one model type should be setted");
        }
        
        if (openAISetting.enableProxy && StringUtils.isAnyEmpty(openAISetting.proxyPort, openAISetting.proxyHostname)) {
            throw new IllegalArgumentException(
                    "【OpenAISetting init error】proxy port and hostname should not be null when proxy is enabled");
        }
    }
}
