package com.isa.ISA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.ISA.model.Karta;
import com.isa.ISA.model.Mesto;
import com.isa.ISA.model.Projekcija;
import com.isa.ISA.model.Sala;
import com.isa.ISA.model.SegmentUSali;
import com.isa.ISA.model.TipSedista;
import com.isa.ISA.model.DTO.SalaDTO;
import com.isa.ISA.model.DTO.SegmentUSaliDTO;
import com.isa.ISA.repository.KartaRepository;
import com.isa.ISA.repository.MestoRepository;
import com.isa.ISA.repository.PozoristeBioskopRepository;
import com.isa.ISA.repository.SalaRepository;
import com.isa.ISA.repository.SegmentUSaliRepository;

@Service
public class SegmentUSaliService {
	@Autowired
	private SegmentUSaliRepository susr;
	@Autowired
	private SalaRepository sRepo;
	@Autowired
	private MestoRepository mr;
	@Autowired
	private KartaRepository kRepo;

	public List<SegmentUSali> getAll() {
		return null;
	}

	public SegmentUSali getOne(Long id) {
		return susr.findOne(id);
	}

	public SegmentUSali converter(SegmentUSaliDTO s) {
		SegmentUSali sala = new SegmentUSali();
		sala.setNaziv(s.getNaziv());
		sala.setSala(sRepo.findOne(s.getSala()));
		sala.setRedovi(s.getRedovi());
		sala.setKolone(s.getKolone());
		sala.setJeZatvoreno(s.isJeZatvoreno());
		sala.setTipSedista(s.getTipSedista());
		return sala;

	}

	public void addSala(SegmentUSaliDTO s) {
		SegmentUSali sus = converter(s);
		System.out.println("SegSer + "+sus.toString());
		susr.save(sus);
		int k =0;
		List<Mesto> mesta = new ArrayList<>();
		for (int i = 1; i <= s.getRedovi(); i++) {
			for (int j = 1; j <= s.getKolone(); j++) {
				Mesto mesto = new Mesto();
				k++;
				mesto.setKolona(j);
				mesto.setRed(i);
				mesto.setBroj(k);
				mesto.setTipSedista(sus.getTipSedista());
				mesta.add(mesto);
				mesto.setSegmentUSali(sus);
				mr.save(mesto);
			}
		}

		sus.setMesta(mesta);
		susr.save(sus);

	}

	public void addSala(SegmentUSaliDTO s, long id) {
		// TODO Auto-generated method stub
		SegmentUSali sus = converter(s);
		sus.setId(id);
		susr.save(sus);
		int k = 0;
		List<Mesto> mesta = new ArrayList<>();
		for (int i = 1; i <= s.getRedovi(); i++) {
			for (int j = 1; j <= s.getKolone(); j++) {
				Mesto mesto = new Mesto();
				mesto.setKolona(j);
				k++;
				mesto.setRed(i);
				mesto.setTipSedista(sus.getTipSedista());
				mesta.add(mesto);
				mesto.setSegmentUSali(sus);
				mesto.setBroj(k);
				mr.save(mesto);
			}
		}

		sus.setMesta(mesta);
		susr.save(sus);
	}

	public void deleteSala(Long id) {
		// TODO Auto-generated method stub
		List<Mesto> mesta = new ArrayList<>();
		mr.findBySegmentUSali(susr.getOne(id)).forEach(mesta::add);;
		SegmentUSali s = susr.getOne(id);
		s.setMesta(null);
		susr.save(s);
		int k = mesta.size();
		for(int i = k-1; i>=0; i--) {
			mr.delete(mesta.get(i).getId());
		}
		
		susr.delete(id);
		
	}

	public void addSala(SegmentUSali s, Long id) {
		// TODO Auto-generated method stub
		
	}

	public int getBrojMesta(Sala sala, Projekcija pr) {
		List<SegmentUSali> ls = new ArrayList<>();
		susr.findBySala(sala).forEach(ls::add);
		int sum = 0;
		for(int i = 0; i<ls.size(); i++) {
			if(ls.get(i).isJeZatvoreno()) {
				continue;
			}
			sum+=(ls.get(i).getKolone()*ls.get(i).getRedovi());
			List<Mesto> lm = new ArrayList<>();
			mr.findBySegmentUSali(ls.get(i)).forEach(lm::add);
			for(int j = 0; j<lm.size(); j++) {
				List<Karta> lk = new ArrayList<>();
				kRepo.findByProjekcijaAndMesto(pr, lm.get(j)).forEach(lk::add);
				if(lk.size()!=0) {
					Karta k = lk.get(0);
					if(k.isIzvrsena()) {
						sum--;
					}
				}
			}
		}
		return sum;
	}

	public void editSala(SegmentUSaliDTO s, long id) {
		// TODO Auto-generated method stub
		SegmentUSali sus = converter(s);
		
		List<Mesto> mestl = new ArrayList<>();
		mr.findBySegmentUSali(susr.getOne(id)).forEach(mestl::add);;
		SegmentUSali a = susr.getOne(id);
		a.setMesta(null);
		susr.save(a);
		int w = mestl.size();
		for(int i = w-1; i>=0; i--) {
			mr.delete(mestl.get(i).getId());
		}
		sus.setId(id);
		int k = 0;
		List<Mesto> mesta = new ArrayList<>();
		for (int i = 1; i <= s.getRedovi(); i++) {
			for (int j = 1; j <= s.getKolone(); j++) {
				Mesto mesto = new Mesto();
				mesto.setKolona(j);
				k++;
				mesto.setRed(i);
				mesto.setTipSedista(sus.getTipSedista());
				mesta.add(mesto);
				mesto.setSegmentUSali(sus);
				mesto.setBroj(k);
				mr.save(mesto);
			}
		}

		sus.setMesta(mesta);
		susr.save(sus);
	}

}
