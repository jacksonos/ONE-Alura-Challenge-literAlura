package com.alura.literalura.api;

import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.dto.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GutendexClient {
	private static final String BASE_URL = "https://gutendex.com/books/?languages=en&sort=popular";
	private final HttpClient client;
	private final ObjectMapper mapper;

	public GutendexClient() {
		this.client = HttpClient.newHttpClient();
		this.mapper = new ObjectMapper();
	}

	public List<BookDTO> getPopularBooks() {
		try {
			HttpRequest request = HttpRequest.newBuilder()
							.uri(URI.create(BASE_URL))
							.GET()
							.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() >= 400) {
				throw new IllegalStateException("API responded with status: " + response.statusCode());
			}

			ResponseWrapper wrapper = mapper.readValue(response.body(), ResponseWrapper.class);
			return wrapper.results();

		} catch (IOException | InterruptedException e) {
			throw new IllegalStateException("Failed to fetch data from Gutendex", e);
		}
	}

}
