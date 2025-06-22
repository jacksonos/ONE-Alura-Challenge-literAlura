package com.alura.literalura.service;

import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CatalogService {
	public Book mapFromDTO(BookDTO dto) {
		Book book = new Book();
		book.setId(dto.id());
		book.setTitle(dto.title());
		book.setSubjects(new HashSet<>(dto.subjects()));
		book.setLanguage(dto.languages().isEmpty() ? "unknown" : dto.languages().get(0));
		book.setCopyright(dto.copyright());
		book.setMediaType(dto.media_type());
		book.setDownloadCount(dto.download_count());

		Set<Author> authors = dto.authors().stream()
						.map(a -> new Author(a.name(), a.birth_year(), a.death_year()))
						.collect(Collectors.toSet());
		book.setAuthors(authors);
		return book;
	}


}
