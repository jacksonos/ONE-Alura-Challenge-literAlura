package com.alura.literalura.application;

import com.alura.literalura.model.Book;
import com.alura.literalura.model.Person;
import com.alura.literalura.model.enums.Language;
import com.alura.literalura.service.BookService;
import com.alura.literalura.service.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
	private final BookService bookService;
	private final PersonService personService;
	private final Scanner scanner = new Scanner(System.in);

	public Main(BookService bookService, PersonService personService) {
		this.bookService = bookService;
		this.personService = personService;
	}

	public void displayMenu() {
		int option = -1;
		while (option != 0) {
			System.out.println("\n--- Gutendex System ---");
			System.out.println("1. Search book by title and save 🔎");
			System.out.println("2. List all books 📚");
			System.out.println("3. List all authors ✍️");
			System.out.println("4. Search book by language 🌎");
			System.out.println("5. List Top 10 downloaded books 🔝");
			System.out.println("6. Search living authors in a specific year 😇");
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
					case 3:
						listAllAuthors();
						break;
					case 4:
						listBooksByLanguage();
						break;
					case 5:
						listTopTenDownloadedBooks();
						break;
					case 6:
						searchLivingAuthors();
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
			System.out.println(foundBook.get());
//			System.out.println("Title: " + foundBook.get().getTitle());
//			System.out.println("Authors: ");
//			foundBook.get().getPersons().forEach(System.out::println);
//			System.out.println("Languages: " + foundBook.get().getLanguages());
//			System.out.println("Copyright: " + foundBook.get().getCopyright());
//			System.out.println("Summaries: " + foundBook.get().getSummaries());
//			System.out.println("Media type: " + foundBook.get().getMediaType());
//			System.out.println("Download count: " + foundBook.get().getDownloadCount());
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

	private void listAllAuthors() {
		System.out.println("\n--- Registered books 📚 ---");
		List<Person> persons = personService.getAllPersons();
		if (persons.isEmpty()) {
			System.out.println("There are not authors registered in the database!");
		} else {
			persons.forEach(person -> {
				System.out.println("\n" + person);
				System.out.println("Book(s): ");
				person.getBooks().forEach(personBook -> System.out.println("* " + personBook.getTitle()));
			});
		}
	}

	private void listBooksByLanguage() {
		System.out.println("\n--- Book List By Language 🌎 ---");
		System.out.println("Enter the language code (ex. 'es' for Spanish, 'en' para English):");
		String langCode = scanner.nextLine().trim().toLowerCase();

		// Convert the input String to Enum Language
		Language language = Language.fromCode(langCode);

		if (language == Language.UNKNOWN) {
			System.out.println("Language code invalid or does not exist: " + langCode);
			System.out.println("Languages supported: ");
			for (Language l : Language.values()) {
				if (l != Language.UNKNOWN) {
					System.out.println("- " + l.getDisplayName() + " (" + l.getCode() + ")");
				}
			}
			return;
		}

		List<Book> books = bookService.getBooksByLanguage(language);

		if (books.isEmpty()) {
			System.out.println("No books were found with the language '" + language.getDisplayName() + "'.");
		} else {
			books.forEach(System.out::println);
		}
	}

	private void listTopTenDownloadedBooks() {
		System.out.println("\n--- Top 10 downloaded books 🔝 ---");
		List<Book> books = bookService.getTopTenMostDownLoadedBooks();
		if (books.isEmpty() || books.size() < 10) {
			System.out.println("There are fewer than 10 downloaded books in the database!");
		} else {
			books.forEach(System.out::println);
		}
	}

	private void searchLivingAuthors() {
		System.out.println("\n--- Authors alive in ---");
		System.out.println("Enter year: ");
		String year = scanner.nextLine();
		if (year.isBlank()) {
			System.out.println("Year must not be blank!");
			return;
		}
		List<Person> persons = personService.getLivingPersons(Integer.parseInt(year));
		if (persons.isEmpty()) {
			System.out.println("There are not authors registered in the database!");
		} else {
			persons.forEach(System.out::println);
		}
	}
}
