package com.isa.ISA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.ISA.model.Admin;
import com.isa.ISA.model.Korisnik;
import com.isa.ISA.model.User;
import com.isa.ISA.service.AdminService;
import com.isa.ISA.service.KorisnikService;

@RestController
public class RegisterController {
	@Autowired
	private KorisnikService ks;
	@Autowired
	private AdminService as;

	public RegisterController() {
	}


	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public String registerR(@RequestBody User kor) {
		System.out.println("AAAAAAA");
		System.out.println("AA "+kor.toString());
		Korisnik reg = ks.getUser(kor.getUsername());
		Admin adm = as.getAdmin(kor.getUsername());
		if (reg != null || adm != null) {

			return "Username is already taken.";
		}
		User korEmail = ks.findByEmail(kor.getEmail());
		Admin adminEmail = as.getAdminByEmail(kor.getEmail());
		if (korEmail != null || adminEmail != null) {

			return "Email is already taken.";
		}
		Korisnik k = new Korisnik();
		k.setUsername(kor.getUsername());
		k.setPassword(kor.getPassword());
		k.setEmail(kor.getEmail());
		k.setJeAktivan(false);
		k.setBrojTelefona(kor.getBrojTelefona());
		k.setGrad(kor.getGrad());
		k.setIme(kor.getIme());
		k.setPrezime(kor.getPrezime());
		ks.addUser(k);
		//EmailService es = new EmailService(k.getEmail());
		System.out.println("Account with username " + k.getUsername() + "has been created");
		return "";

	}

}
