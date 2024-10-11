package se.seb.docflow.ai.hello_ollama;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.seb.docflow.ai.hello_ollama.services.AssemblyAiService;
import se.seb.docflow.ai.hello_ollama.services.OllamaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class HelloOllamaApplication implements CommandLineRunner {

	private final OllamaService ollamaService;
	private final AssemblyAiService assemblyAiService;

    public HelloOllamaApplication(OllamaService ollamaService, AssemblyAiService assemblyAiService) {
        this.ollamaService = ollamaService;
        this.assemblyAiService = assemblyAiService;
    }

    public static void main(String[] args) {
		SpringApplication.run(HelloOllamaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// ollama();

		Optional<String> speechToText = assemblyAiService.speechToTextLocally("C:/ws/Bahram/Files/sports_injuries.mp3");
		//Optional<String> speechToText = assemblyAiService.speechToText();

		System.out.println(speechToText);

	}

	private void ollama() {
		List<Message> messages = new ArrayList<>();
		messages.add(
				new AssistantMessage("Response length is less than 100 words. You are a psychologist. Your advices are related to have a happy and better life. you tell a joke. ")
		);
		messages.add(
				new UserMessage("Why sky is blue?")
		);

		Prompt prompt = new Prompt(
				messages
		);

		System.out.println(
				ollamaService.sendMessage(prompt)
		);
	}
}
