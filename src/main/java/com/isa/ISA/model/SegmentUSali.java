package com.isa.ISA.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class SegmentUSali {
	@Id
	@GeneratedValue
	private long id;
	
	private String naziv;
	
	private boolean jeZatvoreno;
	
	private TipSedista tipSedista;
	
	private int redovi;
	
	private int kolone;
	
	@OneToMany
    private List<Mesto> mesta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private Sala sala;
	
	public SegmentUSali() {
		
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

	public boolean isJeZatvoreno() {
		return jeZatvoreno;
	}

	public void setJeZatvoreno(boolean jeZatvoreno) {
		this.jeZatvoreno = jeZatvoreno;
	}

	public TipSedista getTipSedista() {
		return tipSedista;
	}

	public void setTipSedista(TipSedista tipSedista) {
		this.tipSedista = tipSedista;
	}

	public List<Mesto> getMesta() {
		return mesta;
	}

	public void setMesta(List<Mesto> mesta) {
		this.mesta = mesta;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public int getRedovi() {
		return redovi;
	}

	public void setRedovi(int redovi) {
		this.redovi = redovi;
	}

	public int getKolone() {
		return kolone;
	}

	public void setKolone(int kolone) {
		this.kolone = kolone;
	}

	@Override
	public String toString() {
		return "SegmentUSali [id=" + id + ", naziv=" + naziv + ", jeZatvoreno=" + jeZatvoreno + ", tipSedista="
				+ tipSedista + ", redovi=" + redovi + ", kolone=" + kolone + ", mesta=" + mesta + "]";
	}
	
	
	

}
