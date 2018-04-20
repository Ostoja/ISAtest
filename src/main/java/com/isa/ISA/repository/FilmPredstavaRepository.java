package com.isa.ISA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.ISA.model.FilmPredstava;

public interface FilmPredstavaRepository extends JpaRepository<FilmPredstava, Long> {
	List<FilmPredstava> findByNaziv(String naziv);
}
