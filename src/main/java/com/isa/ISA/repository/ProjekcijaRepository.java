package com.isa.ISA.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.ISA.model.FilmPredstava;
import com.isa.ISA.model.Projekcija;
import com.isa.ISA.model.Sala;

public interface ProjekcijaRepository extends JpaRepository<Projekcija, Long> {
	List<Projekcija> findByFilmPredstava(FilmPredstava fp);
    List<Projekcija> findBySala(Sala s);
    List<Projekcija> findBySalaAndFilmPredstava(Sala s, FilmPredstava fp);
    List<Projekcija> findBySalaAndDatumAfter(Sala s, Date d);
    List<Projekcija> findBySalaAndDatumBefore(Sala s, Date d);
    List<Projekcija> findBySalaAndDatumBetween(Sala s, Date d, Date b);
    List<Projekcija> findByDatumBetween(Date pocetak, Date kraj);
    List<Projekcija> findByDatumAfter(Date kraj);
    List<Projekcija> findByDatumBefore(Date pocetak);
    Projekcija getProjekcijaById(Long id);
}
