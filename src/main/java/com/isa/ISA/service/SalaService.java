package com.isa.ISA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.Projekcija;
import com.isa.ISA.model.Sala;
import com.isa.ISA.model.SegmentUSali;
import com.isa.ISA.model.DTO.SalaDTO;
import com.isa.ISA.repository.PozoristeBioskopRepository;
import com.isa.ISA.repository.SalaRepository;
import com.isa.ISA.repository.SegmentUSaliRepository;

@Service
public class SalaService {

	@Autowired
	private SalaRepository sRepo;
	@Autowired
	private SegmentUSaliService susService;
	@Autowired
	private SegmentUSaliRepository susRepo;
	@Autowired
	private PozoristeBioskopRepository pbRepo;

	public List<Sala> getAll() {
		List<Sala> allDog = new ArrayList<>();
		sRepo.findAll().forEach(allDog::add);
		return allDog;
	}

	public Sala getOne(Long id) {
		return sRepo.findOne(id);
	}

	public Sala converter(SalaDTO s) {
		Sala sala = new Sala();
		sala.setNaziv(s.getNaziv());
		sala.setPozoristeBioskop(pbRepo.findOne(s.getPozoristeBioskop()));
		return sala;
		
	}
	
	public void addSala(SalaDTO s) {
		Sala sala = converter(s);
		sala = sRepo.save(sala);
		PozoristeBioskop pb = pbRepo.findOne(sala.getPozoristeBioskop().getId());
		pb.getSale().add(sala);
		pbRepo.save(pb);

	}

	public void editSala(Sala s, Long id) {
		if (sRepo.findOne(id) != null) {
			Sala sala = s;
			sala.setId(id);
			sRepo.save(sala);

		}
	}

	public void deleteProjekcijeFromPB(List<Long> ids, Long idpb) {
		PozoristeBioskop pb = pbRepo.findOne(idpb);
		int i = 0;
		for (Long projId : ids) {
			i = 0;
			for (Projekcija p : pb.getRepertoar()) {
				if (p.getId() == projId) {
					pb.getRepertoar().remove(i);
					break;
				}
				i++;
			}
		}
		pbRepo.save(pb);
	}

	public void deleteSala(Long id, Long idpb, List<Long> projekcije) {
		PozoristeBioskop pb = pbRepo.findOne(idpb);
		int i = 0;
		for (Sala s : pb.getSale()) {
			if (s.getId() == id) {
				pb.getSale().remove(i);
				break;
			}
			i++;
		}

		pbRepo.save(pb);
		List<SegmentUSali> ss = new ArrayList<>();
		Sala sa = sRepo.getOne(id);
		sa.setSegmenti(null);
		susRepo.findBySala(sa).forEach(ss::add);
		int k = ss.size();
		for(int j = k-1; j>=0; j--) {
			susService.deleteSala(ss.get(j).getId());
		}
	
		sRepo.delete(id);
	}

	public void editSala(SalaDTO s, long id) {
		// TODO Auto-generated method stub
		Sala sala = converter(s);
		sala.setId(id);
		sala = sRepo.save(sala);
	}
}