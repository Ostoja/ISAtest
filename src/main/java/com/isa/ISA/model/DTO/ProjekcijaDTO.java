package com.isa.ISA.model.DTO;

import java.sql.Time;
import java.util.Date;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.isa.ISA.CustomerDateAndTimeDeserialize;



public class ProjekcijaDTO {
	private long id;
	private String datum;
	
	private String termin;

	private int cena;
	
	private Long filmPredstava;
	
    private Long sala;
    
    private String naziv;
    
    private int brMesta;
    
    private String sName;

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public Long getFilmPredstava() {
		return filmPredstava;
	}

	public void setFilmPredstava(Long filmPredstava) {
		this.filmPredstava = filmPredstava;
	}

	public Long getSala() {
		return sala;
	}

	public void setSala(Long sala) {
		this.sala = sala;
	}
    
	
	
    public ProjekcijaDTO() {
    	
    }

	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getBrMesta() {
		return brMesta;
	}

	public void setBrMesta(int brMesta) {
		this.brMesta = brMesta;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
