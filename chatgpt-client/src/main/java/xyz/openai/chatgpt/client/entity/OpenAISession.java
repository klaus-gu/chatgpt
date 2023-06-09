package xyz.openai.chatgpt.client.entity;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * Seesion .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 **/
public class OpenAISession {
    
    private User user = new User();
    
    private String expires;
    
    private String accessToken;
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getExpires() {
        return expires;
    }
    
    public void setExpires(String expires) {
        this.expires = expires;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    /**
     * Parse the response to object
     * @param response response content from server
     */
    public OpenAISession parse(String response) {
        try {
            JSONObject jsonObject = JSON.parseObject(response);
            this.accessToken = jsonObject.getString("accessToken");
            this.expires = jsonObject.getString("expires");
            
            JSONObject user = jsonObject.getJSONObject("user");
            this.user.id = user.getString("id");
            this.user.name = user.getString("name");
            this.user.email = user.getString("email");
            this.user.image = user.getString("image");
            this.user.picture = user.getString("picture");
            this.user.groups = user.getJSONArray("groups");
            this.user.features = user.getJSONArray("features");
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Get access token failed. Please try again later.");
        }
    }
    
    public static class User {
        
        private String id;
        
        private String name;
        
        private String email;
        
        private String image;
        
        private String picture;
        
        private JSONArray groups;
        
        private JSONArray features;
        
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
        
        public String getImage() {
            return image;
        }
        
        public void setImage(String image) {
            this.image = image;
        }
        
        public String getPicture() {
            return picture;
        }
        
        public void setPicture(String picture) {
            this.picture = picture;
        }
        
        public JSONArray getGroups() {
            return groups;
        }
        
        public void setGroups(JSONArray groups) {
            this.groups = groups;
        }
        
        public JSONArray getFeatures() {
            return features;
        }
        
        public void setFeatures(JSONArray features) {
            this.features = features;
        }
    }
}
