package com.alura.literalura.api.gutendex.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
				Integer count,
				String next,
				String previous,
				List<BookApi> results
) {
}
