package com.alura.literalura.repository;

import com.alura.literalura.model.Book;
import com.alura.literalura.model.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByTitle(String title);
	List<Book> findByLanguagesContains(Language languages);
	List<Book> findTop10ByOrderByDownloadCountDesc();
}
