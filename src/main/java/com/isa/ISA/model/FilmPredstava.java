package com.isa.ISA.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class FilmPredstava {
	@Id
	@GeneratedValue
	private long id;

	@Column
	private String naziv;

	@Column
	private String spisakGlumaca;

	@Column
	private String zanr;

	@Column
	private String reditelj;

	@Column
	private int trajanje;

	@Column
	private String poster; // ??

	@Column
	private double prosecnaOcena;

	@Column
	private int brojOcena;

	@Column
	private String opis;

	@Column
	private int cena;

	@OneToMany
	@JsonBackReference
	private List<Projekcija> projekcije;

	@ManyToOne
	@JsonBackReference
	private Repertoar repertoar;

	private int nosiBodova;

	@ManyToOne
	@JsonBackReference
	private PozoristeBioskop mestoOdrzavanja;

	public FilmPredstava() {

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

	public String getSpisakGlumaca() {
		return spisakGlumaca;
	}

	public void setSpisakGlumaca(String spisakGlumaca) {
		this.spisakGlumaca = spisakGlumaca;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public String getReditelj() {
		return reditelj;
	}

	public void setReditelj(String reditelj) {
		this.reditelj = reditelj;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getBrojOcena() {
		return brojOcena;
	}

	public void setBrojOcena(int brojOcena) {
		this.brojOcena = brojOcena;
	}

	public List<Projekcija> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(List<Projekcija> projekcije) {
		this.projekcije = projekcije;
	}

	public Repertoar getRepertoar() {
		return repertoar;
	}

	public void setRepertoar(Repertoar repertoar) {
		this.repertoar = repertoar;
	}

	public int getNosiBodova() {
		return nosiBodova;
	}

	public void setNosiBodova(int nosiBodova) {
		this.nosiBodova = nosiBodova;
	}

	public PozoristeBioskop getMestoOdrzavanja() {
		return mestoOdrzavanja;
	}

	public void setMestoOdrzavanja(PozoristeBioskop mestoOdrzavanja) {
		this.mestoOdrzavanja = mestoOdrzavanja;
	}

	
}
