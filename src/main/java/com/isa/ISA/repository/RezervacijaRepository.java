package com.isa.ISA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.ISA.model.Karta;
import com.isa.ISA.model.Rezervacija;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
	Rezervacija findByKarta(Karta k);
}
