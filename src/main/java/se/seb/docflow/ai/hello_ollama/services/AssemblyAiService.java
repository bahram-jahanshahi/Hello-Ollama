package se.seb.docflow.ai.hello_ollama.services;

import com.assemblyai.api.RealtimeTranscriber;
import com.assemblyai.api.resources.transcripts.types.Transcript;
import com.assemblyai.api.resources.transcripts.types.TranscriptOptionalParams;
import com.assemblyai.api.resources.transcripts.types.TranscriptStatus;
import org.springframework.stereotype.Service;

import com.assemblyai.api.AssemblyAI;
import se.seb.docflow.ai.hello_ollama.conf.AssemblyAiProperties;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class AssemblyAiService {

    private final AssemblyAiProperties assemblyAiProperties;

    public AssemblyAiService(AssemblyAiProperties assemblyAiProperties) {
        this.assemblyAiProperties = assemblyAiProperties;
    }

    public Optional<String> speechToText() {

        AssemblyAI aai = AssemblyAI.builder()
                .apiKey(assemblyAiProperties.getApiKey())
                .build();

        var params = TranscriptOptionalParams.builder()
                .speakerLabels(true)
                .build();

        try {

            // Or use a publicly-accessible URL:
            String audioUrl = "https://assembly.ai/sports_injuries.mp3";
            Transcript transcript = aai.transcripts().transcribe(audioUrl, params);

            if (transcript.getStatus().equals(TranscriptStatus.ERROR)) {
                System.err.println(transcript.getError().get());
                System.exit(1);
            }

            transcript.getUtterances().ifPresent(transcriptUtterances -> {
                transcriptUtterances.forEach(utterance -> {
                    System.out.println("Speaker " + utterance.getSpeaker() + ": " + utterance.getText());
                });
            });

            return transcript.getText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<String> speechToTextLocally(String fileAddress) {

        AssemblyAI aai = AssemblyAI.builder()
                .apiKey(assemblyAiProperties.getApiKey())
                .build();

        var params = TranscriptOptionalParams.builder()
                .speakerLabels(true)
                .build();

        try {

            File audiuFile = new File(fileAddress);


            Transcript transcript = aai.transcripts().transcribe(
                    audiuFile, params
            );

            if (transcript.getStatus().equals(TranscriptStatus.ERROR)) {
                System.err.println(transcript.getError().get());
                System.exit(1);
            }

            return transcript.getText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void realTimeTranscribe() {

        RealtimeTranscriber realtimeTranscriber;

    }
}
