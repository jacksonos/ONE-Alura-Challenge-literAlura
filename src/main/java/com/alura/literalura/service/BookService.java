package com.alura.literalura.service;

import com.alura.literalura.api.gemini.client.GeminiApiHttpClient;
import com.alura.literalura.api.gutendex.client.GutendexAPI;
import com.alura.literalura.api.gutendex.dto.ApiResponse;
import com.alura.literalura.api.gutendex.dto.BookApi;
import com.alura.literalura.model.Book;
import com.alura.literalura.model.Person;
import com.alura.literalura.model.enums.Language;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.repository.PersonRepository;
import com.alura.literalura.util.ConvertFromJsonToClass;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
	private final GutendexAPI gutendexAPI = new GutendexAPI();
	private final BookRepository bookRepository;
	private final PersonRepository personRepository;
	private final GeminiApiHttpClient geminiApiHttpClient = new GeminiApiHttpClient();

	public BookService(BookRepository bookRepository, PersonRepository personRepository) {
		this.bookRepository = bookRepository;
		this.personRepository = personRepository;
	}

	public List<Book> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		System.out.println(books.size() + " books were found in the database.");
		return books;
	}

	@Transactional
	public Optional<Book> searchAndSaveBook(String title) {
		try {
			String json = gutendexAPI.getSpecificBookData(title);
			ApiResponse apiResponse = new ConvertFromJsonToClass().fromJsonToClass(json, ApiResponse.class);
			if (apiResponse != null && !apiResponse.results().isEmpty()) {
				//Pending to improve this search!
				BookApi apiBook = apiResponse.results().get(0);
				//Check if the book exists in the database
				Optional<Book> existingBook = bookRepository.findByTitle(apiBook.title());
				if (existingBook.isPresent()) {
					System.out.println("The book with title '" + apiBook.title() + "' already exists.");
					return existingBook;
				}
				//Mapping BookApi record to Book entity
				Book book = new Book(apiBook);
				Optional<String> geminiSummaries = geminiApiHttpClient.summarizeText(book.getSummaries());
				if (geminiSummaries.isPresent() && !geminiSummaries.get().isEmpty()) {
					if (geminiSummaries.get().length() > 250) {
						book.setSummaries(geminiSummaries.get().substring(0, 220));
					}
					book.setSummaries(geminiSummaries.get());
				}
				//Mapping persons and persist them if they do not exist
				Set<Person> people = apiBook.authors().stream()
								.map(apiPerson -> {
									Optional<Person> existingPerson = personRepository.findByName(apiPerson.getName());
									if (existingPerson.isPresent()) {
										return existingPerson.get();
									} else {
										Person newPerson = new Person(
														apiPerson.getName(),
														apiPerson.getBirthYear(),
														apiPerson.getDeathYear()
										);
										return personRepository.save(newPerson);
									}
								}).collect(Collectors.toSet());
				if (people.isEmpty()) {
					Optional<Person> unknownPerson = personRepository.findByName("Unknown Author");
					Person authorToAssign;
					if (unknownPerson.isPresent()) {
						authorToAssign = unknownPerson.get();
						System.out.println(authorToAssign.getName() + " is assigned to unknown author.");
					} else {
						authorToAssign = new Person("Unknown Author", null, null);
						authorToAssign = personRepository.save(authorToAssign);
						System.out.println("Creating and assigning new unknown author to book: " + authorToAssign.getName());
					}
					people.add(authorToAssign);
				}
				book.setPersons(people);
				for (Person person : people) {
					person.addBook(book);
				}
				Book savedBook = bookRepository.save(book);
				System.out.println("Book saved successfully in database.");
				return Optional.of(savedBook);
			} else {
				System.out.println("No results were found for the search: " + title);
				return Optional.empty();
			}
		} catch (Exception e) {
			System.err.println("Error consuming the API or processing data: " + e.getMessage());
			return Optional.empty();
		}
	}

	@Transactional
	public List<Book> getBooksByLanguage(Language language) {
		List<Book> bookList = bookRepository.findByLanguagesContains(language);
		System.out.println(bookList.size() + " books were found with the language: " + language);
		return bookList;
	}

	@Transactional
	public List<Book> getTopTenMostDownLoadedBooks() {
		return bookRepository.findTop10ByOrderByDownloadCountDesc();
	}

}

