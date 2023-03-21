package xyz.openai.chatgpt.client.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTScan;


@ChatGPTScan(basePackages = "xyz.openai.chatgpt.client.example")
@SpringBootApplication
public class ChatgptClientExampleApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ChatgptClientExampleApplication.class, args);
    }
}
