package com.alura.literalura.dto;

import java.util.List;

public record ResponseWrapper(
				Integer count,
				String next,
				String previous,
				List<BookDTO> results
) {
}
