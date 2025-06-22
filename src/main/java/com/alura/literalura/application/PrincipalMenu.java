package com.alura.literalura.application;

import com.alura.literalura.api.GutendexClient;
import com.alura.literalura.dto.BookDTO;

import java.util.List;

public class PrincipalMenu {
	public static void main(String[] args) {
		GutendexClient client = new GutendexClient();
		List<BookDTO> books = client.getPopularBooks();

		System.out.println("📚 Most popular Books:");
		books.forEach(book -> System.out.println("- " + book.title()));
	}
}
