package se.seb.docflow.ai.hello_ollama.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "assemblyai")

public class AssemblyAiProperties {

    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
