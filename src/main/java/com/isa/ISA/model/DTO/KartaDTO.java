package com.isa.ISA.model.DTO;

import com.isa.ISA.model.TipSedista;

/**
 * prenosi mesto, segmentUsali, projekciju i popust*/
public class KartaDTO {
	private long id;
	
	private long mesto;

	private int red;
	
	private long rezervacija;

	private long projekcija;

	private long pozoristeBioskop;

	private String vremeOdrzavanja; //date

	private String termin;
	
	private int punaCena;

	private int popust;

	private long segmentUsali;
	
	private String sala;
	
	private String pb;
	
	private String seg;
	
	private int mes;
	
	private String film;
	
	private TipSedista tip;
	
	public KartaDTO() {
		
	}

	public long getMesto() {
		return mesto;
	}

	public void setMesto(long mesto) {
		this.mesto = mesto;
	}

	public long getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(long rezervacija) {
		this.rezervacija = rezervacija;
	}

	public long getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(long projekcija) {
		this.projekcija = projekcija;
	}

	public long getPozoristeBioskop() {
		return pozoristeBioskop;
	}

	public void setPozoristeBioskop(long pozoristeBioskop) {
		this.pozoristeBioskop = pozoristeBioskop;
	}

	public String getVremeOdrzavanja() {
		return vremeOdrzavanja;
	}

	public void setVremeOdrzavanja(String vremeOdrzavanja) {
		this.vremeOdrzavanja = vremeOdrzavanja;
	}

	public int getPunaCena() {
		return punaCena;
	}

	public void setPunaCena(int punaCena) {
		this.punaCena = punaCena;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

	public long getSegmentUsali() {
		return segmentUsali;
	}

	public void setSegmentUsali(long segmentUsali) {
		this.segmentUsali = segmentUsali;
	}

	@Override
	public String toString() {
		return "KartaDTO [id=" + id + ", mesto=" + mesto + ", red=" + red + ", rezervacija=" + rezervacija
				+ ", projekcija=" + projekcija + ", pozoristeBioskop=" + pozoristeBioskop + ", vremeOdrzavanja="
				+ vremeOdrzavanja + ", termin=" + termin + ", punaCena=" + punaCena + ", popust=" + popust
				+ ", segmentUsali=" + segmentUsali + ", sala=" + sala + ", pb=" + pb + ", seg=" + seg + ", mes=" + mes
				+ ", film=" + film + ", tip=" + tip + "]";
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPb() {
		return pb;
	}

	public void setPb(String pb) {
		this.pb = pb;
	}

	public String getSeg() {
		return seg;
	}

	public void setSeg(String seg) {
		this.seg = seg;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public TipSedista getTip() {
		return tip;
	}

	public void setTip(TipSedista tip) {
		this.tip = tip;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}
	
	

}
