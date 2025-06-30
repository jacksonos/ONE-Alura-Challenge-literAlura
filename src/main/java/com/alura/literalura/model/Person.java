package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;
	@JsonAlias("birth_year")
	private Integer birthYear;
	@JsonAlias("death_year")
	private Integer deathYear;
	@ManyToMany(mappedBy = "persons", fetch = FetchType.EAGER)
	private Set<Book> books = new HashSet<>();

	public Person() {
	}

	public Person(String name, Integer birth_year, Integer death_year) {
		this.name = name;
		this.birthYear = birth_year;
		this.deathYear = death_year;
	}

	//Utility methods for managing relationships (IMPORTANT for @ManyToMany)
	public void addBook(Book book) {
		this.books.add(book);
		book.getPersons().add(this); //Ensures bidirectionality
	}

	public void removeBook(Book book) {
		this.books.remove(book);
		book.getPersons().remove(this); //Ensures bidirectionality
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birth_year) {
		this.birthYear = birth_year;
	}

	public Integer getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(Integer death_year) {
		this.deathYear = death_year;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	// Crucial equals and hasCode implementation for Sets
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Person person = (Person) obj;
		return id != null && id.equals(person.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		String birthYearStr = (birthYear != null) ? String.valueOf(birthYear) : "N/A";
		String deathYearStr = (deathYear != null) ? String.valueOf(deathYear) : "N/A";

		return "- Name: " + name + " (Birth: " + birthYearStr + ", Death: " + deathYearStr + ")";
	}
}
