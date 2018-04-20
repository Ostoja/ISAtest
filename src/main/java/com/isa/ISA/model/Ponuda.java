package com.isa.ISA.model;

import javax.persistence.*;

@Entity
public class Ponuda {
	@Id
	@GeneratedValue
	private long id;
	
	private int ponuda;
	
	@ManyToOne
	private Licitacija licitacija;
	
	@ManyToOne
	private Korisnik ponudio;
	
	private boolean prihvatio;
	
	@ManyToOne
	private Rekvizit rekvizit;
	
	public Ponuda() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPonuda() {
		return ponuda;
	}

	public void setPonuda(int ponuda) {
		this.ponuda = ponuda;
	}

	public Korisnik getPonudio() {
		return ponudio;
	}

	public void setPonudio(Korisnik ponudio) {
		this.ponudio = ponudio;
	}

	public boolean isPrihvatio() {
		return prihvatio;
	}

	public void setPrihvatio(boolean prihvatio) {
		this.prihvatio = prihvatio;
	}

	public Rekvizit getRekvizit() {
		return rekvizit;
	}

	public void setRekvizit(Rekvizit rekvizit) {
		this.rekvizit = rekvizit;
	}

	public Licitacija getLicitacija() {
		return licitacija;
	}

	public void setLicitacija(Licitacija licitacija) {
		this.licitacija = licitacija;
	}
	
	
}
