package com.isa.ISA.model.DTO;

public class SalaDTO {
	private String naziv;
	
	private Long pozoristeBioskop;

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Long getPozoristeBioskop() {
		return pozoristeBioskop;
	}

	public void setPozoristeBioskop(Long pozoristeBioskop) {
		this.pozoristeBioskop = pozoristeBioskop;
	}
	
	public SalaDTO() {
		
	}
}
