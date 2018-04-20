package com.isa.ISA.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Rezervacija {
	@Id
	@GeneratedValue
	private long id;
	
	private boolean jePotvrdjena;
	
	private int ocenaAmbijenta;
    
	private int ocenaProjekcije;
	
	@OneToOne
	private Karta karta;
	
    @ManyToOne
    private Korisnik rezervisao;

    @ManyToOne
    private Projekcija projekcija;
    
    @ManyToOne
    private Mesto mesto;
	
	public Rezervacija() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isJePotvrdjena() {
		return jePotvrdjena;
	}

	public void setJePotvrdjena(boolean jePotvrdjena) {
		this.jePotvrdjena = jePotvrdjena;
	}

	public Korisnik getRezervisao() {
		return rezervisao;
	}

	public void setRezervisao(Korisnik rezervisao) {
		this.rezervisao = rezervisao;
	}

	public Projekcija getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}

	public Mesto getMesto() {
		return mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public Karta getKarta() {
		return karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
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
	
	
}
