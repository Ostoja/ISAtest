package com.isa.ISA.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.ISA.model.Admin;
import com.isa.ISA.model.Korisnik;
import com.isa.ISA.model.TipKorisnika;
import com.isa.ISA.model.User;
import com.isa.ISA.service.AdminService;
import com.isa.ISA.service.KorisnikService;

@RestController
public class LoginController {
	@Autowired
	private KorisnikService ks;
	@Autowired
	private AdminService as;

	@RequestMapping(value = "/returnRoleUser")
	public boolean returnRoleUser(HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedUser") == null) {
			return false;
		}
		if (((User) request.getSession().getAttribute("loggedUser")).getTip().equals(TipKorisnika.Admin)
				|| (((User) request.getSession().getAttribute("loggedUser")).getTip().equals(TipKorisnika.AdminBioPoz))
				|| (((User) request.getSession().getAttribute("loggedUser")).getTip().equals(TipKorisnika.AdminFanZone))
				|| (((User) request.getSession().getAttribute("loggedUser")).getTip().equals(TipKorisnika.Obican))) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/returnAdmin")
	public boolean returnModerOrAdmin(HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedUser") == null) {
			return false;
		}
		if (((User) request.getSession().getAttribute("loggedUser")).getTip().equals(TipKorisnika.AdminBioPoz)) {
			// System.out.println("jetseee");
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/returnAdmin1")
	public boolean returnModerOrAdminFstTime(HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedUser") == null) {
			System.out.println("Nije ulogovan");
			return false;
		}
		if (((User) request.getSession().getAttribute("loggedUser")).getTip().equals(TipKorisnika.AdminBioPoz)) {
			System.out.println("jetseee");
			User u = (User) request.getSession().getAttribute("loggedUser");
			Admin a = as.getAdmin(u.getUsername());
			
			if (a.getJeAktivan()) {
				System.out.println("ulogovan i aktivan");
				return true;
			} 
			else {
				System.out.println("ulogovan i neaktivan");
				return false;
			}
		} 
		else {
			System.out.println("ulogovan");
			return true;
		}
	}

	@RequestMapping(value = "/logout")
	public boolean logOutUser(HttpServletRequest request) {
		System.out.println("You successfully logged out. This session will be invalidated");
		request.getSession().invalidate();
		return true;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/api/login")
	public User login(@RequestBody User us, HttpServletRequest request) {
		System.out.println("BBB " + us);
		String username = us.getUsername();
		String password = us.getPassword();

		User u;
		Korisnik reg = ks.getUser(username);
		Admin adm = as.getAdmin(username);
		if (reg == null && adm == null)
			return null;

		else {
			u = (reg != null) ? reg : adm;
		}
		if (u.getPassword().equals(password)) {

			request.getSession().setAttribute("loggedUser", u);
			System.out.println("User je " + u.getIme() + " " + request.getSession().getAttribute("loggedUser"));
			return u;
		}

		else
			return null;
	}
}