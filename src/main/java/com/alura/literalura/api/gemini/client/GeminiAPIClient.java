package com.alura.literalura.api.gemini.client;

import java.util.Optional;

public interface GeminiAPIClient {

	Optional<String> summarizeText(String text);
}