package com.isa.ISA.controller;

import java.text.DateFormat;
import java.text.ParseException;
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

import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.Projekcija;
import com.isa.ISA.model.DTO.ProjekcijaDTO;
import com.isa.ISA.repository.FilmPredstavaRepository;
import com.isa.ISA.repository.PozoristeBioskopRepository;
import com.isa.ISA.repository.ProjekcijaRepository;
import com.isa.ISA.repository.SalaRepository;
import com.isa.ISA.service.ProjekcijaService;
import com.isa.ISA.service.SegmentUSaliService;

@RestController
public class ProjekcijaController {

	@Autowired
	private ProjekcijaService ps;
	
	@Autowired
	private SegmentUSaliService suss;
	
	@Autowired
	private PozoristeBioskopRepository pbr;
	
	@Autowired
	private ProjekcijaRepository pror;
	
	@Autowired
	private FilmPredstavaRepository fpr;
	
	@Autowired
	private SalaRepository sr;
	
	@RequestMapping("/projekcije")
    private List<ProjekcijaDTO> getAllProjekcija(HttpServletRequest request){
		PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		List<ProjekcijaDTO> lpdto = new ArrayList<>();
		System.out.println("Proj COnt, pb "+pb.getNaziv());
        List<Projekcija> allP = new ArrayList<>();
        ps.getAll().forEach(allP::add);
        PozoristeBioskop pbio = pbr.getOne(pb.getId());
        System.out.println("Proj Cont allP "+allP.size());
        List<Projekcija> temp = pbio.getRepertoar();
        for(int i = 0; i<temp.size(); i++) {
        	ProjekcijaDTO elm = new ProjekcijaDTO();
        	elm.setCena(temp.get(i).getCena());
        	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        	elm.setDatum(DATE_FORMAT.format(temp.get(i).getDatum()));
        	elm.setTermin(temp.get(i).getTermin());
        	elm.setsName(temp.get(i).getSala().getNaziv());
        	elm.setId(temp.get(i).getId());
        	elm.setNaziv(temp.get(i).getFilmPredstava().getNaziv());
        	elm.setBrMesta(suss.getBrojMesta(temp.get(i).getSala(), temp.get(i)));
        	lpdto.add(elm);
        }
        /*for(int i = 0; i<allP.size(); i++) {
        	Sala sala = sr.getOne((allP.get(i).getSala().getId()));
        	//System.out.println("ProjCont projekcija: "+allP.get(i).getSala().getPozoristeBioskop().getNaziv());
        	if(!sala.getPozoristeBioskop().equals(pb)) {
        		temp.remove(allP.get(i));
        	}
        }*/
        return lpdto;
	}

	@RequestMapping("/filmsala/{id}")
    private String getAllProjekcijaPB2(@PathVariable String id, HttpServletRequest request){
		System.out.println("FPCont + " +id);
		Long idl = Long.parseLong(id);
		Projekcija p = pror.findOne(idl);
		request.getSession().setAttribute("sala", p.getSala());
		request.getSession().setAttribute("projekc", pror.findOne(idl));
		System.out.println("FPCont "+ pror.findOne(idl).getFname());
        return "izabrao je projekciju" + idl;
	}
	
	@RequestMapping("/projekcije/{id}")
    private String getAllProjekcijaPB(@PathVariable String id, HttpServletRequest request){
		System.out.println("ProjCont + " +id);
		Long idl = Long.parseLong(id);
		request.getSession().setAttribute("pozbio", pbr.findOne(idl));
		System.out.println("ProjCont "+ pbr.findOne(idl).getNaziv());
        return "izabrao je projekciju" + idl;
	}
	
	
	/*@RequestMapping(method = RequestMethod.POST, value = "/pb/{id}/projekcije")
    public void addProjekcija(@RequestBody Projekcija p, @PathVariable Long id){
        ps.addProjekcija(p);
    }*/
	
	@RequestMapping(method = RequestMethod.POST, value = "/pb/projekcije")
    public void addProjekcija(@RequestBody ProjekcijaDTO p){
		//PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		//p.setPozoristeBioskop(pb.getId());
		if(p.getCena()<0||p.getDatum().equals("")||p.getTermin().equals("")||p.getSala()==0||p.getFilmPredstava()==0)
		{
			return;
		}
			ps.addProjekcija(p);
    }
	
	@RequestMapping("/projekcijaedit")
	public ProjekcijaDTO editProjekcija(HttpServletRequest request){
		Projekcija p = (Projekcija) request.getSession().getAttribute("projekc");
		ProjekcijaDTO pp = new ProjekcijaDTO();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		pp.setCena(p.getCena());
		pp.setDatum(DATE_FORMAT.format(p.getDatum()));
		System.out.println("Proj cont datum: "+DATE_FORMAT.format(p.getDatum()));
		pp.setTermin(p.getTermin());
		pp.setFilmPredstava(p.getFilmPredstava().getId());
		pp.setSala(p.getSala().getId());
		return pp;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/pb/projekcijeedit")
    public void updateProjekcija2(@RequestBody ProjekcijaDTO pro, HttpServletRequest request){
		Projekcija pr = pror.getOne(((Projekcija)request.getSession().getAttribute("projekc")).getId()); 
        pr.setCena(pro.getCena());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			pr.setDatum(format.parse(pro.getDatum()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pr.setFilmPredstava(fpr.findOne(pro.getFilmPredstava()));
		pr.setTermin(pro.getTermin());
		pr.setSala(sr.findOne(pro.getSala()));
		ps.updateProjekcija(pr);
    }
	
	
    @RequestMapping(method = RequestMethod.DELETE, value = "/pb/projekcije/{id}")
    public void deleteProjekcija(@PathVariable Long id, HttpServletRequest request){
    	System.out.println("AADDDDD"+id);
    	PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
    	long id2=pb.getId();
        ps.deleteProjekcija(id2, id);

}

	 
}
