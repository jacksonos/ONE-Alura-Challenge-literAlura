package com.alura.literalura.api.gutendex.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PersonApi(
				String name,
				@JsonAlias("birth_year") Integer birthYear,
				@JsonAlias("death_year") Integer deathYear
) {
}
