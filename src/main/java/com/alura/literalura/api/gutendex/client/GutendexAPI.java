package com.alura.literalura.api.gutendex.client;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class GutendexAPI {
	private final Dotenv dotenv = Dotenv.load();
	private final String URL_BASE = dotenv.get("API_BASE_URL");
	private final HttpClient httpClient = HttpClient.newHttpClient();

	//Fetches data from the specified URL using HTTP GET request and returns the response body as a string, throwing exceptions for errors
	private String fetchData(String url) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
							.uri(URI.create(url))
							.build();
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() >= 400) {
				throw new IllegalStateException("Error connecting to the Gutendex API. Status code: " + response.statusCode());
			}
			return response.body();
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException("Failed to retrieve data from the Gutendex API: " + url, e);
		}
	}

	//Fetches data for all books from the Gutendex API and returns the JSON response
	public String getAllBooksData() {
		return fetchData(URL_BASE);
	}

	//Searches for a specific book by name in the Gutendex API and returns the JSON response
	public String getSpecificBookData(String bookName) {
		String encodedQuery = URLEncoder.encode(bookName, StandardCharsets.UTF_8);
		String url = URL_BASE + "?search=" + encodedQuery.replace("+", "%20");
		return fetchData(url);
	}
}
