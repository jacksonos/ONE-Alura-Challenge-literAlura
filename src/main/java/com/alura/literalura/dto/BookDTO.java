package com.alura.literalura.dto;

import java.util.List;
import java.util.Map;

public record BookDTO(
				Integer id,
				String title,
				List<String> subjects,
				List<AuthorDTO> authors,
				List<String> summaries,
				List<AuthorDTO> translators,
				List<String> bookshelves,
				List<String> languages,
				Boolean copyright,
				String media_type,
				Map<String, String> formats,
				Integer download_count

) {
}
