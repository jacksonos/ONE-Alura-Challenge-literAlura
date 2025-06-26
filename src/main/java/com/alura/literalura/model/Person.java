package com.alura.literalura.model;

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
	private Integer birth_year;
	private Integer death_year;
	@ManyToMany(mappedBy = "persons")
	private Set<Book> books = new HashSet<>();

	public Person() {
	}

	public Person(String name, Integer birth_year, Integer death_year) {
		this.name = name;
		this.birth_year = birth_year;
		this.death_year = death_year;
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

	public Integer getBirth_year() {
		return birth_year;
	}

	public void setBirth_year(Integer birth_year) {
		this.birth_year = birth_year;
	}

	public Integer getDeath_year() {
		return death_year;
	}

	public void setDeath_year(Integer death_year) {
		this.death_year = death_year;
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
		String birthYearStr = (birth_year != null) ? String.valueOf(birth_year) : "N/A";
		String deathYearStr = (death_year != null) ? String.valueOf(death_year) : "N/A";

		return "  - Name: " + name + " (Birth: " + birthYearStr + ", Death: " + deathYearStr + ")";
	}
}
