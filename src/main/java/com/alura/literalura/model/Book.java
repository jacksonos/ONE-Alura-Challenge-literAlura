package com.alura.literalura.model;

import com.alura.literalura.api.gutendex.dto.BookApi;
import com.alura.literalura.model.enums.Language;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false, length = 500)
	private String title;
	//	private List<String> subjects;
	@Column(length = 350)
	private String summaries;
	//	private List<Person> translators;
//	private List<String> bookshelves;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"))
	@Column(name = "language_codes", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<Language> languages = new HashSet<>();
	private Boolean copyright;
	@Column(name = "media_type")
	private String mediaType;
	@Column(name = "download_count")
	private Integer downloadCount;
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
					name = "book_persons",
					joinColumns = @JoinColumn(name = "book_id"),
					inverseJoinColumns = @JoinColumn(name = "person_id")
	)
	private Set<Person> persons = new HashSet<>();

	public Book() {
	}

	public Book(BookApi bookApi) {
		this.title = bookApi.title();
		this.summaries = bookApi.summaries().get(0);
		if (bookApi.languages() != null && !bookApi.languages().isEmpty()) {
			this.languages = bookApi.languages().stream()
							.map(Language::fromCode)
							.collect(Collectors.toSet());
		}
//		this.bookshelves = bookApi.bookshelves();
		this.copyright = bookApi.copyright();
		this.mediaType = bookApi.mediaType();
		this.downloadCount = bookApi.downloadCount();
	}

	public void addPerson(Person person) {
		this.persons.add(person);
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCopyright() {
		return copyright;
	}

	public void setCopyright(Boolean copyright) {
		this.copyright = copyright;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String media_type) {
		this.mediaType = media_type;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer download_count) {
		this.downloadCount = download_count;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public String getSummaries() {
		return summaries;
	}

	public void setSummaries(String summaries) {
		this.summaries = summaries;
	}

	public Set<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}

	@Override
	public String toString() {
		return
						"Title: " + title + '\n' +
						"Summaries: " + summaries + '\n' +
						"Languages: " + languages.stream().map(Language::getDisplayName).collect(Collectors.joining(", ")) + '\n' +
						"Copyright: " + copyright + '\n' +
						"Media type: " + mediaType + '\n' +
						"Download count: " + downloadCount + '\n' +
						"Author(s): " + '\n' +
						persons.stream().map(Person::toString).collect(Collectors.joining("\n"));
	}

	// Crucial equals and hasCode implementation for Sets
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Book book = (Book) obj;
		return id != null && id.equals(book.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
