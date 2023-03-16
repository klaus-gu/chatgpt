package xyz.openai.chatgpt.client.enums;

/**
 * 会话角色 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public enum RoleEnum {
    USER("user"),
    SYSTEM("system"),
    ASSISTANT("assistant");
    
    private String roleName;
    
    RoleEnum(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleName() {
        return roleName;
    }
}
