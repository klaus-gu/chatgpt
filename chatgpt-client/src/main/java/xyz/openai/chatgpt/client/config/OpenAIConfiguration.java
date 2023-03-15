package xyz.openai.chatgpt.client.config;

import xyz.openai.chatgpt.client.model.OpenAIProxy;

import java.net.Proxy;

/**
 * Configuration for open ai operate .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class OpenAIConfiguration {
    
    public static final String CHATGPT_CONTENT_NAME = "ChatGPT";
    
    public static final String GPT35_TRUBO_CONTENT_NAME = "GPT-3.5-Turbo";
    
    public static final String ONLINE_CHATGPT_CONTENT_NAME = "Online ChatGPT";
    
    private static OpenAIConfiguration openAIConfiguration = null;
    
    private String readTimeout = "50000";
    
    private String connectionTimeout = "50000";
    
    private String email = "";
    
    private String password = "";
    
    private Boolean enableProxy = false;
    
    private String proxyHostname = "";
    
    private String proxyPort = "10000";
    
    private String accessToken = "";
    
    private String expireTime = "";
    
    private String apiKey = "";
    
    private String chatGptModel = "text-davinci-002-render-sha";
    
    private String gpt35Model = "gpt-3.5-turbo";
    
    private Boolean enableContext = false;
    
    private String assistantApiKey = "";
    
    public SettingConfiguration.SettingProxyType proxyType = SettingConfiguration.SettingProxyType.DIRECT;
    
    private OpenAIConfiguration() {
    }
    
    public static OpenAIConfiguration getInstance() {
        if (OpenAIConfiguration.openAIConfiguration == null) {
            OpenAIConfiguration.openAIConfiguration = new OpenAIConfiguration();
        }
        return openAIConfiguration;
    }
    
    public static void update(OpenAIConfiguration openAIConfiguration) {
        OpenAIConfiguration.openAIConfiguration = openAIConfiguration;
    }
    
    public Proxy getProxy() {
        Proxy proxy = null;
        if (enableProxy) {
            Proxy.Type type = proxyType == SettingConfiguration.SettingProxyType.HTTP ? Proxy.Type.HTTP
                    : proxyType == SettingConfiguration.SettingProxyType.SOCKS ? Proxy.Type.SOCKS : Proxy.Type.DIRECT;
            proxy = new OpenAIProxy(proxyHostname, Integer.parseInt(proxyPort), type).build();
        }
        return proxy;
    }
    
    public static String getChatgptContentName() {
        return CHATGPT_CONTENT_NAME;
    }
    
    public static String getGpt35TruboContentName() {
        return GPT35_TRUBO_CONTENT_NAME;
    }
    
    public static String getOnlineChatgptContentName() {
        return ONLINE_CHATGPT_CONTENT_NAME;
    }
    
    public String getReadTimeout() {
        return readTimeout;
    }
    
    public void setReadTimeout(String readTimeout) {
        this.readTimeout = readTimeout;
    }
    
    public String getConnectionTimeout() {
        return connectionTimeout;
    }
    
    public void setConnectionTimeout(String connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean getEnableProxy() {
        return enableProxy;
    }
    
    public void setEnableProxy(Boolean enableProxy) {
        this.enableProxy = enableProxy;
    }
    
    public String getProxyHostname() {
        return proxyHostname;
    }
    
    public void setProxyHostname(String proxyHostname) {
        this.proxyHostname = proxyHostname;
    }
    
    public String getProxyPort() {
        return proxyPort;
    }
    
    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getExpireTime() {
        return expireTime;
    }
    
    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String getChatGptModel() {
        return chatGptModel;
    }
    
    public void setChatGptModel(String chatGptModel) {
        this.chatGptModel = chatGptModel;
    }
    
    public String getGpt35Model() {
        return gpt35Model;
    }
    
    public void setGpt35Model(String gpt35Model) {
        this.gpt35Model = gpt35Model;
    }
    
    public Boolean getEnableContext() {
        return enableContext;
    }
    
    public void setEnableContext(Boolean enableContext) {
        this.enableContext = enableContext;
    }
    
    public String getAssistantApiKey() {
        return assistantApiKey;
    }
    
    public void setAssistantApiKey(String assistantApiKey) {
        this.assistantApiKey = assistantApiKey;
    }
    
    public SettingConfiguration.SettingProxyType getProxyType() {
        return proxyType;
    }
    
    public void setProxyType(SettingConfiguration.SettingProxyType proxyType) {
        this.proxyType = proxyType;
    }
}
