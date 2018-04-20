package com.isa.ISA.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Mesto {
	@Id
	@GeneratedValue
	private long id;
	
	private int broj;
	
	private int red;
	
	private int kolona;
	
	private TipSedista tipSedista;
	
	@ManyToOne
	@JsonBackReference
	private SegmentUSali segmentUSali;
	
	/*@ManyToOne
	@JsonBackReference
	private Sala sala;
	*/
	public Mesto() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getKolona() {
		return kolona;
	}

	public void setKolona(int kolona) {
		this.kolona = kolona;
	}

	public TipSedista getTipSedista() {
		return tipSedista;
	}

	public void setTipSedista(TipSedista tipSedista) {
		this.tipSedista = tipSedista;
	}

	public SegmentUSali getSegmentUSali() {
		return segmentUSali;
	}

	public void setSegmentUSali(SegmentUSali segmentUSali) {
		this.segmentUSali = segmentUSali;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	/*public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}*/

	
	
	
	
}
