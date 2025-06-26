package com.alura.literalura.util;

import com.alura.literalura.service.IConvertFromJsonToClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertFromJsonToClass implements IConvertFromJsonToClass {
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public <T> T fromJsonToClass(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
