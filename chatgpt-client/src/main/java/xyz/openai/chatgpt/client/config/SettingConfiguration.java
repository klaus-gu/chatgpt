package xyz.openai.chatgpt.client.config;

/**
 * Enums .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class SettingConfiguration {
    
    public enum SettingURLType {
        OFFICIAL,
        CUSTOMIZE,
    }
    
    public enum SettingProxyType {
        DIRECT,
        HTTP,
        SOCKS
    }
}