package com.isa.ISA.controller;

import static org.mockito.Matchers.longThat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.ISA.model.Karta;
import com.isa.ISA.model.Mesto;
import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.Projekcija;
import com.isa.ISA.model.Sala;
import com.isa.ISA.model.SegmentUSali;
import com.isa.ISA.model.DTO.KartaDTO;
import com.isa.ISA.model.DTO.ProjekcijaDTO;
import com.isa.ISA.repository.KartaRepository;
import com.isa.ISA.repository.MestoRepository;
import com.isa.ISA.repository.PozoristeBioskopRepository;
import com.isa.ISA.repository.ProjekcijaRepository;
import com.isa.ISA.repository.SalaRepository;
import com.isa.ISA.repository.SegmentUSaliRepository;
import com.isa.ISA.service.KartaService;

@RestController
public class KartaController {

	@Autowired
	private KartaRepository kr;

	@Autowired
	private KartaService ks;

	@Autowired
	private PozoristeBioskopRepository pbr;

	@Autowired
	private ProjekcijaRepository pr;

	@Autowired
	private SalaRepository sr;

	@Autowired
	private SegmentUSaliRepository susr;

	@Autowired
	private MestoRepository mr;

	@RequestMapping("/karte")
	private List<KartaDTO> getAllKarta(HttpServletRequest request) {
		PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		System.out.println("Kart COnt, pb " + pb.getNaziv());
		List<Karta> allP = new ArrayList<>();
		ks.getAll().forEach(allP::add);
		PozoristeBioskop pbio = pbr.getOne(pb.getId());
		System.out.println("Kart Cont allP " + allP.size());
		List<Karta> temp = allP;
		/*
		 * for(int i = 0; i<allP.size(); i++) { Sala sala =
		 * sr.getOne((allP.get(i).getSala().getId()));
		 * //System.out.println("ProjCont projekcija: "+allP.get(i).getSala().
		 * getPozoristeBioskop().getNaziv()); if(!sala.getPozoristeBioskop().equals(pb))
		 * { temp.remove(allP.get(i)); } }
		 */
		List<KartaDTO> tempDTO = new ArrayList<>();
		for (int i = 0; i < temp.size(); i++) {
			System.out.println("Kart Cont " + allP.get(i).getPozoristeBioskop().getNaziv());
			if (allP.get(i).getPozoristeBioskop().getId() == pb.getId()) {
				if (!allP.get(i).isIzvrsena()) {
					tempDTO.add(converter(temp.get(i)));
					System.out.println("USAO KARTACONT + " + tempDTO.size());
					System.out.println(tempDTO.get(0));
				}
			} else {
				System.out.println("Nije usao");
				System.out.println();
			}
		}
		return tempDTO;
	}

	private KartaDTO converter(Karta karta) {
		KartaDTO k = new KartaDTO();
		k.setPozoristeBioskop(karta.getPozoristeBioskop().getId());
		k.setMesto(karta.getMesto().getId());
		k.setPopust(karta.getPopust());
		k.setProjekcija(karta.getProjekcija().getId());
		k.setTermin(karta.getTermin());
		k.setSala(karta.getMesto().getSegmentUSali().getSala().getNaziv());
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		k.setVremeOdrzavanja(DATE_FORMAT.format(karta.getVremeOdrzavanja()));
		k.setPunaCena(karta.getPunaCena());
		k.setId(karta.getId());
		k.setFilm(karta.getProjekcija().getFilmPredstava().getNaziv());
		k.setMes(karta.getMesto().getBroj());
		k.setSeg(karta.getMesto().getSegmentUSali().getNaziv());
		k.setPb(karta.getPozoristeBioskop().getNaziv());
		k.setTip(karta.getMesto().getTipSedista());
		k.setRed(karta.getMesto().getRed());
		return k; // bilo je return null, wow
	}

	@RequestMapping(method = RequestMethod.POST, value = "/pickmesto/{id}")
	public void addKarta3(@PathVariable String id, HttpServletRequest request) {
		Long idl = Long.parseLong(id);
		request.getSession().setAttribute("mesto", mr.findOne(idl));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/pick")
	public void addKarta2(@RequestBody KartaDTO ka, HttpServletRequest request) {
		if(ka.getPopust()<0||ka.getPopust()>100) {
			return;
		}
		PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		Mesto mesto = (Mesto) request.getSession().getAttribute("mesto");
		KartaDTO p = new KartaDTO();
		p.setProjekcija(((Projekcija) request.getSession().getAttribute("projekc")).getId());
		p.setSegmentUsali(((SegmentUSali) request.getSession().getAttribute("segment")).getId());
		PozoristeBioskop pbo = pbr.getOne(pb.getId());
		p.setPozoristeBioskop(pbo.getId());
		Projekcija proj = pr.getOne(p.getProjekcija());
		Sala sala = proj.getSala();
		SegmentUSali sus = susr.getOne(p.getSegmentUsali());
		// long pom = p.getMesto();
		List<Mesto> mesta = mr.findBySegmentUSali(sus);
		p.setMesto(mesto.getId());
		p.setPunaCena(proj.getCena());
		p.setTermin(proj.getTermin());
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		p.setVremeOdrzavanja(DATE_FORMAT.format(proj.getDatum()));
		p.setPopust(ka.getPopust());
		ks.addKarta(p);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/pb/karte")
	public void addKarta(@RequestBody KartaDTO p, HttpServletRequest request) {
		if(p.getPopust()<0||p.getPopust()>100) {
			return;
		}
		PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		PozoristeBioskop pbo = pbr.getOne(pb.getId());
		p.setPozoristeBioskop(pbo.getId());
		Projekcija proj = pr.getOne(p.getProjekcija());
		Sala sala = proj.getSala();
		SegmentUSali sus = susr.getOne(p.getSegmentUsali());
		long pom = p.getMesto();
		List<Mesto> mesta = mr.findBySegmentUSali(sus);
		for (int i = 0; i < mesta.size(); i++) {
			if (mesta.get(i).getBroj() == pom) {
				p.setMesto(mesta.get(i).getId());
				break;
			}
		}
		p.setPunaCena(proj.getCena());
		p.setTermin(proj.getTermin());
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		p.setVremeOdrzavanja(DATE_FORMAT.format(proj.getDatum()));
		ks.addKarta(p);
	}

	@RequestMapping(value = "/mesta")
	public List<Mesto> vratiMesta(HttpServletRequest request) {
		SegmentUSali sus = (SegmentUSali) request.getSession().getAttribute("segment");
		Projekcija proj = (Projekcija) request.getSession().getAttribute("projekc");
		List<Mesto> lm = mr.findBySegmentUSali(sus);
		List<Mesto> lmt = lm;
		List<Karta> lk = kr.findAll();
		for (int i = 0; i < lk.size(); i++) {
			for (int j = 0; j < lm.size(); j++) {
				if (lm.get(j).getId() == lk.get(i).getMesto().getId()) {
					if (lk.get(i).getProjekcija().getId() == proj.getId()) {
						lmt.remove(lm.get(j));
					}
				}
			}
		}
		return lmt;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/kartadelete/{id}")
    public void deleteSala(@PathVariable Long id, HttpServletRequest request){
    	System.out.println("Deleting karta "+id);
    	
        ks.deleteKarta(id);

}
}
