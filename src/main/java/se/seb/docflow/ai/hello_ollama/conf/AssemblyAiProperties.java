package se.seb.docflow.ai.hello_ollama.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "assemblyai")
@Getter
@Setter
public class AssemblyAiProperties {

    private String apiKey;
}
