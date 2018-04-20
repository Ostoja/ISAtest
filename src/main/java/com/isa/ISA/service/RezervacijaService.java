package com.isa.ISA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.isa.ISA.model.Karta;
import com.isa.ISA.model.Korisnik;
import com.isa.ISA.model.Rezervacija;
import com.isa.ISA.repository.KartaRepository;
import com.isa.ISA.repository.RezervacijaRepository;

@Service
public class RezervacijaService {

	@Autowired
	private RezervacijaRepository rRepository;
	@Autowired
	private KartaRepository kRepository;
	public void deleteSala(long id) {
		if(rRepository.getOne(id).isJePotvrdjena()) {
			return;
		}
		Karta k = kRepository.getOne(rRepository.getOne(id).getKarta().getId());
		k.setIzvrsena(false);
		kRepository.save(k);
		rRepository.delete(id);
		System.out.println("ÄAAAA +++ "+kRepository.findAll().size());
	}
	@Transactional(readOnly = false, isolation=Isolation.READ_UNCOMMITTED)
	public void rezervisi(Korisnik ko, Long idl) {
		// TODO Auto-generated method stub
		Karta k = kRepository.getOne(idl);
		if(k.isIzvrsena()) {
			return;
		}
		k.setIzvrsena(true);
		kRepository.save(k);
		Rezervacija re = new Rezervacija();
		re.setJePotvrdjena(false);
		re.setKarta(k);
		re.setMesto(k.getMesto());
		re.setProjekcija(k.getProjekcija());
		re.setRezervisao(ko);
		rRepository.save(re);
	}
}
