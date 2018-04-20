package com.isa.ISA.model;

import javax.persistence.*;

@Entity
@Table(name = "jusr")
public class User {

	@Id
    @GeneratedValue
    private long id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String ime;
	
	private String prezime;
	
	private String grad;
	
	private String brojTelefona;
	
	@Enumerated(EnumType.STRING)
	private TipKorisnika tip;
	
	private Boolean jeAktivan;
	
	public User(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public TipKorisnika getTip() {
		return tip;
	}

	public void setTip(TipKorisnika tip) {
		this.tip = tip;
	}

	public Boolean getJeAktivan() {
		return jeAktivan;
	}

	public void setJeAktivan(Boolean jeAktivan) {
		this.jeAktivan = jeAktivan;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", ime="
				+ ime + ", prezime=" + prezime + ", grad=" + grad + ", brojTelefona=" + brojTelefona + ", tip=" + tip
				+ ", jeAktivan=" + jeAktivan + "]";
	}
	
	
}
