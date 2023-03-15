package xyz.openai.chatgpt.client.core;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.openai.chatgpt.client.config.OpenAIConfiguration;
import xyz.openai.chatgpt.client.model.OpenAIAuth;
import xyz.openai.chatgpt.client.model.OpenAISession;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Used for ChatGPT request header .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class TokenManager {
    
    private static final Logger LOG = LoggerFactory.getLogger(TokenManager.class);
    
    private static final TokenManager TOKEN_MANAGER = new TokenManager();
    
    private OpenAIConfiguration openAIConfiguration = OpenAIConfiguration.getInstance();
    
    public static TokenManager getInstance() {
        return TOKEN_MANAGER;
    }
    
    public String refreshToken(String email, String password) {
        String title = StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password) ? "OpenAI: Login"
                : "OpenAI: Refresh Access Token";
        String refreshStatus;
        try {
        
        } catch (Exception e) {
            LOG.error("ChatGPT: refreshToken failed, message: {}", e.getMessage());
            refreshStatus = "Login or refresh token failed, please try it later. Use a proxy if necessary.";
        }
        return null;
    }
    
    public void refreshTokenAsync() {
        doRefreshToken(null, null);
    }
    
    public String doRefreshToken(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            if (StringUtils.isEmpty(openAIConfiguration.getEmail()) || StringUtils
                    .isEmpty(openAIConfiguration.getPassword())) {
                
                return "No login details provided! To login or refresh access token, the email and password are required, please configure it at first.";
            } else {
                email = openAIConfiguration.getEmail();
                password = openAIConfiguration.getPassword();
            }
        }
        OpenAIAuth auth;
        if (openAIConfiguration.getEnableProxy()) {
            auth = new OpenAIAuth(email, password, openAIConfiguration.getProxy());
        } else {
            auth = new OpenAIAuth(email, password);
        }
        try {
            OpenAISession sessions = auth.auth();
            openAIConfiguration.setExpireTime(sessions.getExpires());
            openAIConfiguration.setAccessToken(sessions.getAccessToken());
            String image = URLDecoder.decode(sessions.getUser().getImage(), StandardCharsets.UTF_8.toString());
            return "success";
        } catch (Exception e) {
            return "Refresh access token failed, please try it later.";
        }
    }
}
