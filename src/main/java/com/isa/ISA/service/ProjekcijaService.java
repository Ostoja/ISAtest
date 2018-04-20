package com.isa.ISA.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.ISA.model.FilmPredstava;
import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.Projekcija;
import com.isa.ISA.model.Sala;
import com.isa.ISA.model.DTO.ProjekcijaDTO;
import com.isa.ISA.repository.FilmPredstavaRepository;
import com.isa.ISA.repository.KartaRepository;
import com.isa.ISA.repository.PozoristeBioskopRepository;
import com.isa.ISA.repository.ProjekcijaRepository;
import com.isa.ISA.repository.SalaRepository;

@Service
public class ProjekcijaService {

	@Autowired
	private ProjekcijaRepository pr;
	
	@Autowired
	private PozoristeBioskopRepository pbr;
	
	@Autowired
	private FilmPredstavaRepository fpr;

	@Autowired
	private KartaRepository kr;
	
	@Autowired
	private SalaRepository sr;

	public List<Projekcija> getAll() {
		List<Projekcija> allP = new ArrayList<>();
		pr.findAll().forEach(allP::add);
		return allP;
	}

	public Projekcija getProjekcija(Long id) {
		return pr.findOne(id);
	}

	public void deleteProjekcija(Long id2, Long id) {
		Projekcija p = pr.getOne(id);
		PozoristeBioskop pbio = pbr.getOne(id2);
		List<Projekcija> pom = pbio.getRepertoar();
		pom.remove(p);
		pbio.setRepertoar(pom);
		pbr.save(pbio);
		pr.delete(id);
	}

	public void updateProjekcija(Projekcija p) {
		if(kr.findByProjekcija(p).size()!=0) {
			return;
		}
		pr.save(p);
	}

	public void addProjekcija(Projekcija p) {
		System.out.println("A");
		pr.save(p);
	}

	public List<Projekcija> getProjekcije(FilmPredstava d) {
		List<Projekcija> allP = new ArrayList<>();
		pr.findByFilmPredstava(d).forEach(allP::add);
		return allP;
	}

	public List<Projekcija> getProjekcijeNakon(Date d) {
		List<Projekcija> allP = new ArrayList<>();
		pr.findByDatumAfter(d).forEach(allP::add);
		return allP;
	}

	public List<Projekcija> getProjekcijePre(Date d) {
		List<Projekcija> allP = new ArrayList<>();
		pr.findByDatumBefore(d).forEach(allP::add);
		return allP;
	}

	public List<Projekcija> getProjekcijeOdDo(Date od, Date dok) {
		List<Projekcija> allP = new ArrayList<>();
		pr.findByDatumBetween(od, dok).forEach(allP::add);
		return allP;
	}

	public List<Projekcija> getProjekcijeizSale(Sala s) {
		List<Projekcija> allP = new ArrayList<>();
		pr.findBySala(s).forEach(allP::add);
		return allP;
	}

	public List<Projekcija> getProjekcijeizSaleDogadjaja(Sala s, FilmPredstava d) {
		List<Projekcija> allP = new ArrayList<>();
		pr.findBySalaAndFilmPredstava(s, d).forEach(allP::add);
		return allP;
	}

	public List<Projekcija> getProjekcijeizSalePosle(Sala s, Date d) {
		List<Projekcija> allP = new ArrayList<>();
		pr.findBySalaAndDatumAfter(s, d).forEach(allP::add);
		return allP;
	}

	public List<Projekcija> getProjekcijeizSalePre(Sala s, Date d) {
		List<Projekcija> allP = new ArrayList<>();
		pr.findBySalaAndDatumBefore(s, d).forEach(allP::add);
		return allP;
	}

	public List<Projekcija> getProjekcijeizSaleIzmedju(Sala s, Date d, Date b) {
		List<Projekcija> allP = new ArrayList<>();
		pr.findBySalaAndDatumBetween(s, d, b).forEach(allP::add);
		return allP;
	}

	public ArrayList<Long> getProjekcijeToBeDeleted(Long id) {
		ArrayList<Long> ids = new ArrayList<>();
		Sala s = sr.findOne(id);
		List<Projekcija> projekcije = pr.findBySala(s);
		for (Projekcija p : projekcije) {
			ids.add(p.getId());
		}
		return ids;
	}

	
	public Projekcija converter(ProjekcijaDTO pp) {
		Projekcija p = new Projekcija();
		p.setCena(pp.getCena());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			p.setDatum(format.parse(pp.getDatum()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setFilmPredstava(fpr.getOne(pp.getFilmPredstava()));
		p.setTermin(pp.getTermin());
		p.setSala(sr.findOne(pp.getSala()));
		List<PozoristeBioskop> lpb = new ArrayList<>();
		pbr.findAll().forEach(lpb::add);
		PozoristeBioskop pb = null;
		for(int j=0; j<lpb.size(); j++) {
			if(lpb.get(j).getSale().contains(sr.findOne(pp.getSala()))) {
				pb = lpb.get(j);
				break;
			}
		}
		List<Projekcija> lp = pb.getRepertoar();
		lp.add(p);
		pr.save(p);
		pb.setRepertoar(lp);
		pbr.save(pb);
		return p;
	}
	
	public void deleteProjekcijaByIds(List<Long> ids){
        for(Long id:ids){
            pr.delete(id);
        }
}
	
	public void addProjekcija(ProjekcijaDTO pp) {
		// TODO Auto-generated method stub
		Projekcija p = converter(pp);
		int a = 2;
		a++;
		System.out.println(a);
		//addProjekcija(p);
	}
	
}
