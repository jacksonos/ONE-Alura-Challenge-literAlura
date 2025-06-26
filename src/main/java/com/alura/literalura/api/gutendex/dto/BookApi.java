package com.alura.literalura.api.gutendex.dto;

import com.alura.literalura.model.Person;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookApi(
				String title,
				List<Person> authors,
				List<String> summaries,
				List<Person> translators,
				List<String> subjects,
				List<String> languages,
				List<String> bookshelves,
				Boolean copyright,
				Map<String, String> formats,
				@JsonAlias("media_type") String mediaType,
				@JsonAlias("download_count") Integer downloadCount
) {
}
