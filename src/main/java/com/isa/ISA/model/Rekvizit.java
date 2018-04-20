package com.isa.ISA.model;

import javax.persistence.*;


@Entity
public class Rekvizit {
	@Id
	@GeneratedValue
	private long id;
	
	private String naziv;
	
	private String opis;
	
	private String slika; //??
	
	private boolean jePolovan;
	
	private boolean jeOdobren;
	
	@ManyToOne
    private Korisnik postavio;
	
	@OneToOne
	private Licitacija licitacija;
	
	
	
	public Rekvizit() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public boolean isJePolovan() {
		return jePolovan;
	}

	public void setJePolovan(boolean jePolovan) {
		this.jePolovan = jePolovan;
	}

	public boolean isJeOdobren() {
		return jeOdobren;
	}

	public void setJeOdobren(boolean jeOdobren) {
		this.jeOdobren = jeOdobren;
	}

	public Korisnik getPostavio() {
		return postavio;
	}

	public void setPostavio(Korisnik postavio) {
		this.postavio = postavio;
	}

	public Licitacija getLicitacija() {
		return licitacija;
	}

	public void setLicitacija(Licitacija licitacija) {
		this.licitacija = licitacija;
	}
	
	
}
