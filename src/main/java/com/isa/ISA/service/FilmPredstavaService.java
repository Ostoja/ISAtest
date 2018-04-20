package com.isa.ISA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.ISA.model.FilmPredstava;
import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.Projekcija;
import com.isa.ISA.model.Sala;
import com.isa.ISA.model.DTO.FilmPredstavaDTO;
import com.isa.ISA.repository.FilmPredstavaRepository;
import com.isa.ISA.repository.PozoristeBioskopRepository;
import com.isa.ISA.repository.ProjekcijaRepository;
import com.isa.ISA.repository.SalaRepository;

@Service
public class FilmPredstavaService {

	@Autowired
	private FilmPredstavaRepository fpr;

	@Autowired
	private ProjekcijaRepository pr;
	
	@Autowired
	private ProjekcijaService ps;
	@Autowired
	private SalaRepository sr;
	@Autowired
	private PozoristeBioskopRepository pbs;

	public List<FilmPredstava> getAllFilmPredstava() {
		List<FilmPredstava> allFP = new ArrayList<>();
		fpr.findAll().forEach(allFP::add);
		return allFP;
	}

	private boolean check(FilmPredstava fp) {

		if (fp.getBrojOcena() >= 0)
			if (fp.getNosiBodova() >= 0)
				if (fp.getSpisakGlumaca().length() > 0)
					if (fp.getNaziv().length() > 0)
						if (fp.getOpis().length() > 0)
							if (fp.getReditelj().length() > 0)
								if (fp.getTrajanje() > 0)
									if (fp.getZanr().length() > 0)
										return true;
		return false;

	}

	private FilmPredstava converter(FilmPredstavaDTO d) {
		FilmPredstava fp = new FilmPredstava();
		fp.setTrajanje(d.getTrajanje());
		fp.setZanr(d.getZanr());
		fp.setReditelj(d.getReditelj());
		fp.setOpis(d.getOpis());
		fp.setNaziv(d.getNaziv());
		System.out.println(fp.getOpis()+"ÄAAAAAA"+fp.getNaziv());
		fp.setSpisakGlumaca(d.getSpisakGlumaca());
		fp.setNosiBodova(d.getNosiBodova());
		fp.setProsecnaOcena(d.getProsecnaOcena());
		fp.setBrojOcena(d.getBrojOcena());
		fp.setPoster(d.getPoster());
		fp.setNosiBodova(d.getNosiBodova());
		return fp;

	}

	public FilmPredstava addFilmPredstava1(FilmPredstava fp) {
		if (check(fp)) {
			fpr.save(fp);
			return fp;
		}
		return null;
	}

	public void addFilmPredstava(FilmPredstavaDTO fp2) {
		FilmPredstava fp = converter(fp2);
		if (check(fp))
			fpr.save(fp);
	}

	public void updateFilmPredstava(FilmPredstava fp) {
		fpr.save(fp);
	}

	public void deleteFilmPredstava(Long id) {
		FilmPredstava fp = fpr.getOne(id);
		List<Projekcija> lp = new ArrayList<Projekcija>();
		pr.findByFilmPredstava(fp).forEach(lp::add);
		fp.setProjekcije(null);
		long id2 = 15;
		fpr.save(fp);
		int k = lp.size();
		for(int i = k-1; i>=0; i--) {
			Sala s = sr.findOne(lp.get(i).getSala().getId());
			System.out.println("SALA "+s.getNaziv());
			List<PozoristeBioskop> lpb = new ArrayList<>();
			pbs.findAll().forEach(lpb::add);
			for(int j=0; j<lpb.size(); j++) {
				if(lpb.get(j).getSale().contains(s)) {
					id2 = lpb.get(j).getId();
					break;
				}
			}
			ps.deleteProjekcija(id2, lp.get(i).getId());
		}
		fpr.delete(id);
	}

	public FilmPredstava getFilmPredstava(Long l) {
		return fpr.findOne(l);
	}

	public List<FilmPredstava> getAllFilmPredstava(String s) {
		return fpr.findByNaziv(s);
	}

	public void oceniFilmPredstava(int projekcijaOcena,
			Long FilmPredstavaID) {
		FilmPredstava fp = fpr.findOne(FilmPredstavaID);
		int broj = fp.getBrojOcena();
		broj++;
		double prosecna = fp.getProsecnaOcena();
		if (broj == 1) {
			fp.setBrojOcena(broj);
			fp.setProsecnaOcena(projekcijaOcena);
		} else {
			double nova = (prosecna * broj + projekcijaOcena) / broj;
			fp.setBrojOcena(broj);
			fp.setProsecnaOcena(nova);
		}
		fpr.save(fp);
	}

	public void updateFilmPredstava(FilmPredstavaDTO fp, Long id) {
		// TODO Auto-generated method stub
		FilmPredstava fp1 = converter(fp);
		fp1.setId(id);
		if (check(fp1))
			fpr.save(fp1);
	}
}
