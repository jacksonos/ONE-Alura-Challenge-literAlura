package com.alura.literalura.service;

public interface IConvertFromJsonToClass {
	<T> T fromJsonToClass(String json, Class<T> clazz);
}