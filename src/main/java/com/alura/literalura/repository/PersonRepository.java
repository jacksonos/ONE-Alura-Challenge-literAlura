package com.alura.literalura.repository;

import com.alura.literalura.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
	Optional<Person> findByName(String name);
	@Query("SELECT p FROM Person p WHERE p.birthYear <= :targetYear AND (p.deathYear >= :targetYear OR p.deathYear IS NULL)")
	List<Person> findPersonsAliveInYear(@Param("targetYear") Integer targetYear);
}
