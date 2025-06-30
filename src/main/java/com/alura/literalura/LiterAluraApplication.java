package com.alura.literalura;

import com.alura.literalura.application.Main;
import com.alura.literalura.service.BookService;
import com.alura.literalura.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;
	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(bookService, personService);
		main.displayMenu();
	}
}
