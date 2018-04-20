package com.isa.ISA.model;

import java.util.List;

import javax.persistence.*;


@Entity
public class Repertoar {
	@Id
    @GeneratedValue
    private long id;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<FilmPredstava> filmPredstava;
	
	@OneToOne //pogledati jos jednom.
	private PozoristeBioskop pozoristeBioskop;

	public Repertoar() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<FilmPredstava> getFilmPredstava() {
		return filmPredstava;
	}

	public void setFilmPredstava(List<FilmPredstava> filmPredstava) {
		this.filmPredstava = filmPredstava;
	}

	public PozoristeBioskop getPozoristeBioskop() {
		return pozoristeBioskop;
	}

	public void setPozoristeBioskop(PozoristeBioskop pozoristeBioskop) {
		this.pozoristeBioskop = pozoristeBioskop;
	}
	
	
}
