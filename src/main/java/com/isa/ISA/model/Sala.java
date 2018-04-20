package com.isa.ISA.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Sala {
	@Id
	@GeneratedValue
	private long id;
	
	private String naziv;
	
	private int brojRedova;
	
	private int brojKolona;
	
	@ManyToOne
	private PozoristeBioskop pozoristeBioskop;
	
	@OneToMany
	@JsonBackReference
	private List<SegmentUSali> segmenti;
	
	/*@OneToMany
	@JsonBackReference
    private List<Mesto> mesta;
	*/
	public Sala() {
		
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

	public int getBrojRedova() {
		return brojRedova;
	}

	public void setBrojRedova(int brojRedova) {
		this.brojRedova = brojRedova;
	}

	public int getBrojKolona() {
		return brojKolona;
	}

	public void setBrojKolona(int brojKolona) {
		this.brojKolona = brojKolona;
	}

	public PozoristeBioskop getPozoristeBioskop() {
		return pozoristeBioskop;
	}

	public void setPozoristeBioskop(PozoristeBioskop pozoristeBioskop) {
		this.pozoristeBioskop = pozoristeBioskop;
	}

	public List<SegmentUSali> getSegmenti() {
		return segmenti;
	}

	public void setSegmenti(List<SegmentUSali> segmenti) {
		this.segmenti = segmenti;
	}

	/*public List<Mesto> getMesta() {
		return mesta;
	}

	public void setMesta(List<Mesto> mesta) {
		this.mesta = mesta;
	}*/
	
	
}
