package xyz.openai.chatgpt.client.auth;

import org.apache.commons.lang3.StringUtils;
import xyz.openai.chatgpt.client.entity.OpenAIAuth;
import xyz.openai.chatgpt.client.entity.OpenAISession;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Used for ChatGPT request header .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class TokenManager {
    
    private final OpenAISetting openAISetting;
    
    public TokenManager(OpenAISetting openAISetting) {
        this.openAISetting = openAISetting;
    }
    
    
    public String refreshToken(String email, String password) {
        String title = StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password) ? "OpenAI: Login"
                : "OpenAI: Refresh Access Token";
        String refreshStatus;
        try {
        
        } catch (Exception e) {
            refreshStatus = "Login or refresh token failed, please try it later. Use a proxy if necessary.";
        }
        return null;
    }
    
    public void refreshTokenAsync() {
        doRefreshToken(null, null);
    }
    
    public String doRefreshToken(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            if (StringUtils.isEmpty(openAISetting.email) || StringUtils.isEmpty(openAISetting.password)) {
                
                return "No login details provided! To login or refresh access token, the email and password are required, please configure it at first.";
            } else {
                email = openAISetting.email;
                password = openAISetting.password;
            }
        }
        OpenAIAuth auth;
        if (openAISetting.enableContext) {
            auth = new OpenAIAuth(email, password, openAISetting.getProxy());
        } else {
            auth = new OpenAIAuth(email, password);
        }
        try {
            OpenAISession sessions = auth.auth();
            openAISetting.expireTime = (sessions.getExpires());
            openAISetting.accessToken = (sessions.getAccessToken());
            String image = URLDecoder.decode(sessions.getUser().getImage(), StandardCharsets.UTF_8.toString());
            return "success";
        } catch (Exception e) {
            return "Refresh access token failed, please try it later.";
        }
    }
}
