package com.isa.ISA.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Karta {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Mesto mesto;

    @OneToOne(optional = true)
    private Rezervacija rezervacija;
    
    @ManyToOne
    private Projekcija projekcija;

    @ManyToOne 
    private PozoristeBioskop pozoristeBioskop;
    
    private Date vremeOdrzavanja;
    
    private String termin;
    
    private int punaCena;
    
    private int popust;
    
    private boolean izvrsena;
    
    public Karta(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public PozoristeBioskop getPozoristeBioskop() {
        return pozoristeBioskop;
    }

    public void setPozoristeBioskop(PozoristeBioskop pozoristeBioskop) {
        this.pozoristeBioskop = pozoristeBioskop;
    }

    public Date getVremeOdrzavanja() {
        return vremeOdrzavanja;
    }

    public void setVremeOdrzavanja(Date vremeOdrzavanja) {
        this.vremeOdrzavanja = vremeOdrzavanja;
    }

    public int getPunaCena() {
        return punaCena;
    }

    public void setPunaCena(int punaCena) {
        this.punaCena = punaCena;
    }

	public Projekcija getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}
/*
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
*/
	public boolean isIzvrsena() {
		return izvrsena;
	}

	public void setIzvrsena(boolean izvrsena) {
		this.izvrsena = izvrsena;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}
    
    

}
