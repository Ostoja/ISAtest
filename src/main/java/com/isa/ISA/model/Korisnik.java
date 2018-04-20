package com.isa.ISA.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
public class Korisnik extends User {
	
	@Enumerated(EnumType.STRING)
	private RangKorisnika rangKorisnika;
	
	//private ArrayList<Korisnik> prijatelji;
	
	private int bodovi;
	
	@OneToMany
	private List<Rezervacija> rezervacije;
	
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Korisnik(){
		super();
	}

	public int getBodovi() {
		return bodovi;
	}

	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}

	
	
}
