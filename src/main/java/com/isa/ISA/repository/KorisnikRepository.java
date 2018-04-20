package com.isa.ISA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.ISA.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	Korisnik findByUsername(String username);
    Korisnik findByEmail(String email);
    List<Korisnik> findByImeIgnoreCase(String ime);
    List<Korisnik> findByPrezimeIgnoreCase(String prezime);
    List<Korisnik> findByImeAndPrezimeIgnoreCase(String ime, String prezime);
}
