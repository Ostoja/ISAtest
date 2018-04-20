package com.isa.ISA.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class PozoristeBioskop {
	@Id
	@GeneratedValue
	private long id;

	private String naziv;

	private String adresa;

	private String promotivniOpis;

	private String gmaps;

	@Enumerated(EnumType.STRING)
	private VrstaAmbijenta vrstaAmbijenta;

	@OneToMany
	@JsonBackReference
	private List<Sala> sale;

	@OneToMany(cascade = CascadeType.REMOVE)// pogledati jos jednom.
	@JsonBackReference
	private List<Projekcija> repertoar;

	private double prosecnaOcena;

	private int brojOcena;

	private int bronzeThreshold;

	private int silverThreshold;

	private int goldThreshold;

	private int bronzePopust;

	private int silverPopust;

	private int goldPopust;

	@ManyToMany
	@JsonBackReference
	private List<Admin> admini;

	@OneToMany
	@JsonBackReference
	private List<Karta> karte;
	
	public PozoristeBioskop() {

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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPromotivniOpis() {
		return promotivniOpis;
	}

	public void setPromotivniOpis(String promotivniOpis) {
		this.promotivniOpis = promotivniOpis;
	}

	public VrstaAmbijenta getVrstaAmbijenta() {
		return vrstaAmbijenta;
	}

	public void setVrstaAmbijenta(VrstaAmbijenta vrstaAmbijenta) {
		this.vrstaAmbijenta = vrstaAmbijenta;
	}

	public List<Sala> getSale() {
		return sale;
	}

	public void setSale(List<Sala> sale) {
		this.sale = sale;
	}

	public List<Projekcija> getRepertoar() {
		return repertoar;
	}

	public void setRepertoar(List<Projekcija> repertoar) {
		this.repertoar = repertoar;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public int getBrojOcena() {
		return brojOcena;
	}

	public void setBrojOcena(int brojOcena) {
		this.brojOcena = brojOcena;
	}

	public String getGmaps() {
		return gmaps;
	}

	public void setGmaps(String gmaps) {
		this.gmaps = gmaps;
	}

	public int getBronzeThreshold() {
		return bronzeThreshold;
	}

	public void setBronzeThreshold(int bronzeThreshold) {
		this.bronzeThreshold = bronzeThreshold;
	}

	public int getSilverThreshold() {
		return silverThreshold;
	}

	public void setSilverThreshold(int silverThreshold) {
		this.silverThreshold = silverThreshold;
	}

	public int getGoldThreshold() {
		return goldThreshold;
	}

	public void setGoldThreshold(int goldThreshold) {
		this.goldThreshold = goldThreshold;
	}

	public int getBronzePopust() {
		return bronzePopust;
	}

	public void setBronzePopust(int bronzePopust) {
		this.bronzePopust = bronzePopust;
	}

	public int getSilverPopust() {
		return silverPopust;
	}

	public void setSilverPopust(int silverPopust) {
		this.silverPopust = silverPopust;
	}

	public int getGoldPopust() {
		return goldPopust;
	}

	public void setGoldPopust(int goldPopust) {
		this.goldPopust = goldPopust;
	}

	public List<Admin> getAdmini() {
		return admini;
	}

	public void setAdmini(List<Admin> admini) {
		this.admini = admini;
	}

	public List<Karta> getKarte() {
		return karte;
	}

	public void setKarte(List<Karta> karte) {
		this.karte = karte;
	}

	
}
