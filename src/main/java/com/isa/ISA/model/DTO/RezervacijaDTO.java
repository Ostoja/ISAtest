package com.isa.ISA.model.DTO;

public class RezervacijaDTO {
	private long id;
	
	private int ocenaAmbijenta;
    
	private int ocenaProjekcije;
	
	private String pbname; //pozoristebioskop
	
	private String fname; //film
	
	private String sname; //sala
	
	private long projekcija; //id projekcije
	
	private long mesto; //id mesta
	
	private int broj; //broj mesta
	
	private String segname;
	
	private String datum;
	
	private String termin;
	
	private int red;
	
	private int cena;
	
	private int popust;
	
	private long korisnik; //id korisnika
	
	private boolean jePotvrdjena; //aka da li je ocenio, verovatno ne treba
	
	public RezervacijaDTO() {
		
	}

	public int getOcenaAmbijenta() {
		return ocenaAmbijenta;
	}

	public void setOcenaAmbijenta(int ocenaAmbijenta) {
		this.ocenaAmbijenta = ocenaAmbijenta;
	}

	public int getOcenaProjekcije() {
		return ocenaProjekcije;
	}

	public void setOcenaProjekcije(int ocenaProjekcije) {
		this.ocenaProjekcije = ocenaProjekcije;
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

	public long getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(long projekcija) {
		this.projekcija = projekcija;
	}

	public long getMesto() {
		return mesto;
	}

	public void setMesto(long mesto) {
		this.mesto = mesto;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public long getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(long korisnik) {
		this.korisnik = korisnik;
	}

	public boolean isJePotvrdjena() {
		return jePotvrdjena;
	}

	public void setJePotvrdjena(boolean jePotvrdjena) {
		this.jePotvrdjena = jePotvrdjena;
	}

	public String getPbname() {
		return pbname;
	}

	public void setPbname(String pbname) {
		this.pbname = pbname;
	}

	@Override
	public String toString() {
		return "RezervacijaDTO [id=" + id + ", ocenaAmbijenta=" + ocenaAmbijenta + ", ocenaProjekcije="
				+ ocenaProjekcije + ", pbname=" + pbname + ", fname=" + fname + ", sname=" + sname + ", projekcija="
				+ projekcija + ", mesto=" + mesto + ", broj=" + broj + ", segname=" + segname + ", datum=" + datum
				+ ", termin=" + termin + ", red=" + red + ", cena=" + cena + ", popust=" + popust + ", korisnik="
				+ korisnik + ", jePotvrdjena=" + jePotvrdjena + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSegname() {
		return segname;
	}

	public void setSegname(String segname) {
		this.segname = segname;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	
	
	
	
}
