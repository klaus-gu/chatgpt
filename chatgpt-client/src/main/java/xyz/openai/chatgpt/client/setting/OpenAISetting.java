package xyz.openai.chatgpt.client.setting;

import xyz.openai.chatgpt.client.entity.OpenAIProxy;
import xyz.openai.chatgpt.client.enums.ModelTypeEnum;

import java.net.Proxy;

/**
 * Configuration for open ai operate .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
public class OpenAISetting {
    
    public String readTimeout = "50000";
    
    public String connectionTimeout = "50000";
    
    public String email = "";
    
    public String password = "";
    
    public boolean enableProxy = false;
    
    public String proxyHostname = "";
    
    public String proxyPort = "10000";
    
    public String accessToken = "";
    
    public String expireTime = "";
    
    public String apiKey = "";
    
    public boolean enableContext = false;
    
    public String assistantApiKey = "";
    
    public Boolean enableTokenAuthorization = Boolean.FALSE;
    
    /**
     * 默认的认证方式.
     */
    public Boolean enableApiKeyAuthorization = Boolean.TRUE;
    
    public ModelTypeEnum modelToUse;
    
    public SettingConfiguration.SettingProxyType proxyType = SettingConfiguration.SettingProxyType.DIRECT;
    
    public Proxy getProxy() {
        Proxy proxy = null;
        if (enableProxy) {
            Proxy.Type type = proxyType == SettingConfiguration.SettingProxyType.HTTP ? Proxy.Type.HTTP
                    : proxyType == SettingConfiguration.SettingProxyType.SOCKS ? Proxy.Type.SOCKS : Proxy.Type.DIRECT;
            proxy = new OpenAIProxy(proxyHostname, Integer.parseInt(proxyPort), type).build();
        }
        return proxy;
    }
    
    
    @Override
    public String toString() {
        return "OpenAISetting{" + "readTimeout='" + readTimeout + '\'' + ", connectionTimeout='" + connectionTimeout
                + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", enableProxy=" + enableProxy
                + ", proxyHostname='" + proxyHostname + '\'' + ", proxyPort='" + proxyPort + '\'' + ", accessToken='"
                + accessToken + '\'' + ", expireTime='" + expireTime + '\'' + ", apiKey='" + apiKey + '\''
                + ", enableContext=" + enableContext + ", assistantApiKey='" + assistantApiKey + '\''
                + ", enableTokenAuthorization=" + enableTokenAuthorization + ", enableApiKeyAuthorization="
                + enableApiKeyAuthorization + ", modelToUse=" + modelToUse + ", proxyType=" + proxyType + '}';
    }
}
