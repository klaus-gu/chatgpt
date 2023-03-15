package xyz.openai.chatgpt.client.model;

/**
 * 自定义异常 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class OpenAIException extends Exception {
    
    private final String location;
    
    private final Integer statusCode;
    
    private final String details;
    
    public OpenAIException(String location, Integer statusCode, String details) {
        this.location = location;
        this.statusCode = statusCode;
        this.details = details;
    }
    
    @Override
    public String toString() {
        return "OpenAIException{" + "location='" + location + '\'' + ", statusCode=" + statusCode + ", details='"
                + details + '\'' + '}';
    }
}

