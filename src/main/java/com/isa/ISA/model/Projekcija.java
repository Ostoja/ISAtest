package com.isa.ISA.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Projekcija {
	@Id
	@GeneratedValue
	private long id;
	
	private int popust;
	
	private Date datum;
	

	private String termin;
	
	
	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

	private int cena;
	
	private String fname;
	
	private String sname;
	@ManyToOne
	@JsonBackReference
	private FilmPredstava filmPredstava;
	
	@ManyToOne
	@JsonBackReference
    private Sala sala;
	
	public Projekcija() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public FilmPredstava getFilmPredstava() {
		return filmPredstava;
	}

	public void setFilmPredstava(FilmPredstava filmPredstava) {
		this.filmPredstava = filmPredstava;
		this.fname = filmPredstava.getNaziv();
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
		this.sname = sala.getNaziv();
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	
	
	
}
