package com.isa.ISA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.ISA.model.Korisnik;
import com.isa.ISA.model.User;
import com.isa.ISA.repository.KorisnikRepository;

@Service
public class KorisnikService {
	@Autowired
    private KorisnikRepository kr;


    public List<User> getAllUsers(){
        List<User> allUsers = new ArrayList<>();
        kr.findAll().forEach(allUsers::add);
        return allUsers;

    }

    public Korisnik getUser(String username){
        return kr.findByUsername(username);
    }


    public void addUser(Korisnik u){
        kr.save(u);
    }

	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return kr.findByEmail(email);
	}
	
	public List<Korisnik> searchImePrezime(String ime, String prezime){
		return kr.findByImeAndPrezimeIgnoreCase(ime, prezime);
	}
	public List<Korisnik> searchIme(String ime){
		return kr.findByImeIgnoreCase(ime);
	}
	public List<Korisnik> searchPrezime(String prezime){
		return kr.findByPrezimeIgnoreCase(prezime);
	}

}
