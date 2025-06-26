package com.alura.literalura.model.enums;

import java.util.Arrays;

public enum Language {
	EN("en", "English"),
	ES("es", "Español"),
	FR("fr", "Français"),
	PT("pt", "Português"),
	DE("de", "Deutsch"),
	IT("it", "Italiano"),
	RU("ru", "Русский"),
	ZH("zh", "中文"),
	JA("ja", "日本語"),
	AR("ar", "العربية"),
	UNKNOWN("und", "Desconocido");

	private final String code;
	private final String displayName;

	Language(String code, String displayName) {
		this.code = code;
		this.displayName = displayName;
	}

	public String getCode() {
		return code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Language fromCode(String code) {
		return Arrays.stream(Language.values())
						.filter(l -> l.code.equalsIgnoreCase(code))
						.findFirst()
						.orElse(UNKNOWN);
	}
}
