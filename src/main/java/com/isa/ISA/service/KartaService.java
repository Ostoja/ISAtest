package com.isa.ISA.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.ISA.model.Karta;
import com.isa.ISA.model.DTO.KartaDTO;
import com.isa.ISA.repository.KartaRepository;
import com.isa.ISA.repository.MestoRepository;
import com.isa.ISA.repository.PozoristeBioskopRepository;
import com.isa.ISA.repository.ProjekcijaRepository;

@Service
public class KartaService {

	@Autowired
	private KartaRepository kRepo;
	
	@Autowired
	private ProjekcijaRepository prRepo;
	
	@Autowired
	private PozoristeBioskopRepository pbRepo;
	
	@Autowired
	private MestoRepository mRepo;
	
	public List<Karta> getAll() {
		List<Karta> allP = new ArrayList<>();
		kRepo.findAll().forEach(allP::add);
		return allP;
	}
	
	public Karta converter(KartaDTO pp) {
		Karta p = new Karta();
		p.setPunaCena(pp.getPunaCena());
		p.setPopust(pp.getPopust());
		p.setProjekcija(prRepo.getOne(pp.getProjekcija()));
		p.setTermin(pp.getTermin());
		p.setPozoristeBioskop(pbRepo.getOne(pp.getPozoristeBioskop()));
		p.setMesto(mRepo.getOne(pp.getMesto()));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			p.setVremeOdrzavanja(format.parse(pp.getVremeOdrzavanja()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public void addKarta(KartaDTO pp) {
		// TODO Auto-generated method stub
		Karta p = converter(pp);
		int a = 2;
		a++;
		System.out.println(a);
		if(kRepo.findByProjekcijaAndMesto(p.getProjekcija(), p.getMesto()).size()>0) {
			return;
		}
		addKarta(p);
	}
	
	public void addKarta(Karta p) {
		System.out.println("A");
		kRepo.save(p);
	}

	public void deleteKarta(Long id) {
		if(kRepo.getOne(id).isIzvrsena()) {
			return;
		}
		kRepo.delete(id);
		
	}
}
