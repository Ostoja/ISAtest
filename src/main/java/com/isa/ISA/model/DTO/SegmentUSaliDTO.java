package com.isa.ISA.model.DTO;

import com.isa.ISA.model.TipSedista;

public class SegmentUSaliDTO {
	
	private String naziv;
	
	private boolean jeZatvoreno;
	
	private TipSedista tipSedista;
	
	private Long sala;
	
	private int redovi;
	
	private int kolone;
	
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

	public Long getSala() {
		return sala;
	}

	public void setSala(Long sala) {
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

	public SegmentUSaliDTO() {
		
	}

	@Override
	public String toString() {
		return "SegmentUSaliDTO [naziv=" + naziv + ", jeZatvoreno=" + jeZatvoreno + ", tipSedista=" + tipSedista
				+ ", sala=" + sala + ", redovi=" + redovi + ", kolone=" + kolone + "]";
	}
	
	
}
