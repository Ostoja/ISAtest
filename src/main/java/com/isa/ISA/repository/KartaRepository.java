package com.isa.ISA.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.ISA.model.Karta;
import com.isa.ISA.model.Mesto;
import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.Projekcija;

public interface KartaRepository extends JpaRepository<Karta, Long> {
	List<Karta> findByPozoristeBioskopAndVremeOdrzavanjaBetween(PozoristeBioskop pb, Date pocetak, Date kraj);
	List<Karta> findByPozoristeBioskopAndVremeOdrzavanjaAfter(PozoristeBioskop pb, Date pocetak);
	List<Karta> findByPozoristeBioskopAndVremeOdrzavanjaBefore(PozoristeBioskop pb, Date kraj);
	List<Karta> findByProjekcijaAndMesto(Projekcija pr, Mesto me);
	List<Projekcija> findByProjekcija(Projekcija pr);
}
