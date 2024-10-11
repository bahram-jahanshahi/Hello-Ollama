package se.seb.docflow.ai.hello_ollama.services;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.chat.prompt.Prompt;

@Service
public class OllamaService {


    public String sendMessage(Prompt prompt) {

        OllamaApi ollamaApi = new OllamaApi();

        OllamaChatModel ollamaChatModel = new OllamaChatModel(ollamaApi, new OllamaOptions().withModel("bahram32").withTemperature(0.3));

        //ChatResponse response = ollamaChatModel.call(new Prompt(message));
        String response = "";
        System.out.println();
        ollamaChatModel.stream(prompt)
                .subscribe(chatResponse -> {
                    System.out.print(chatResponse.getResult().getOutput().getContent());
                });

        return response.toString();
    }
}
