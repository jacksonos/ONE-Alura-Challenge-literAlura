package com.alura.literalura.api.gemini.client;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Optional;

public class GeminiApiHttpClient implements GeminiAPIClient {
	Dotenv dotenv = Dotenv.load();
	private final String apiKey = dotenv.get("GEMINI_API_KEY");
	private final Client client = new Client.Builder().apiKey(apiKey).build();

	@Override
	public Optional<String> summarizeText(String originalText) {
		if (originalText == null || originalText.isEmpty()) {
			return Optional.empty();
		}
		try {
			String prompt = "Summarize the next text, limit 200 characters, return only the result:" + originalText;
			GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash", prompt, null);
			String summary = response.text();
			if (summary != null && !summary.isBlank()) {
				return Optional.of(summary);
			}
		} catch (Exception e) {
			System.err.println("Error while generating content with Gemini API: " + e.getMessage());
		}
		return Optional.empty();
	}
}
