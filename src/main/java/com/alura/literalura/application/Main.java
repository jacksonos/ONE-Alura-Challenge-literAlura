package com.alura.literalura.application;

import com.alura.literalura.model.Book;
import com.alura.literalura.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
	private final BookService bookService;
	private final Scanner scanner = new Scanner(System.in);

	public Main(BookService bookService) {
		this.bookService = bookService;
	}

	public void displayMenu() {
		int option = -1;
		while (option != 0) {
			System.out.println("\n--- Gutendex System ---");
			System.out.println("1. Search book by title and save 🔎");
			System.out.println("2. List all books 📚");
			System.out.println("3. Delete a book {in progress...}");
			System.out.println("0. Exit");
			System.out.println("Enter your option: ");
			try {
				option = Integer.parseInt(scanner.nextLine());
				switch (option) {
					case 1:
						searchBookAndSave();
						break;
					case 2:
						listAllBooks();
						break;
					case 0:
						System.out.println("Goodbye!");
						break;
					default:
						System.out.println("Invalid option!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid option");
			}
		}
		scanner.close();
	}

	private void searchBookAndSave() {
		System.out.println("\n--- Searching a book 🔎 ---");
		System.out.println("Enter the book title: ");
		String title = scanner.nextLine();
		if (title.isBlank()) {
			System.out.println("Title must not be blank!");
			return;
		}
		Optional<Book> foundBook = bookService.searchAndSaveBook(title);
		if (foundBook.isPresent()) {
			System.out.println("\n--- Book details 📑 ---");
			System.out.println("Title: " + foundBook.get().getTitle());
			System.out.println("Authors: ");
			foundBook.get().getPersons().forEach(System.out::println);
			System.out.println("Languages: " + foundBook.get().getLanguages());
			System.out.println("Copyright: " + foundBook.get().getCopyright());
			System.out.println("Summaries: " + foundBook.get().getSummaries());
			System.out.println("Media type: " + foundBook.get().getMedia_type());
			System.out.println("Download count: " + foundBook.get().getDownload_count());
		} else {
			System.out.println("The book with that title could not be found or saved.");
		}
	}

	private void listAllBooks() {
		System.out.println("\n--- Registered books 📚 ---");
		List<Book> books = bookService.getAllBooks();
		if (books.isEmpty()) {
			System.out.println("There are not books registered in the database!");
		} else {
			books.forEach(System.out::println);
		}
	}
}
