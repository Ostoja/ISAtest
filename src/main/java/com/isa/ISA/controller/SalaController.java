package com.isa.ISA.controller;

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
import com.isa.ISA.model.Sala;
import com.isa.ISA.model.DTO.SalaDTO;
import com.isa.ISA.repository.PozoristeBioskopRepository;
import com.isa.ISA.repository.SalaRepository;
import com.isa.ISA.service.ProjekcijaService;
import com.isa.ISA.service.SalaService;

@RestController
public class SalaController {

	@Autowired
	private SalaService sService; 
	
	@Autowired
	private ProjekcijaService ps;
	
	@Autowired
	private PozoristeBioskopRepository pbr;
	
	@Autowired
	private SalaRepository sr;
	@RequestMapping("/sale")
	public List<Sala> getAllPozoristeBioskop() {
		return sService.getAll();
	}
	@RequestMapping("/salla")
	public SalaDTO vratiSaluZaEditovanje(HttpServletRequest request) {
		Sala pb = (Sala) request.getSession().getAttribute("sala");
		SalaDTO s = new SalaDTO();
		s.setNaziv(pb.getNaziv());
		return s;
	}
	@RequestMapping("/segsala/{id}")
    private String getAllProjekcijaPB(@PathVariable String id, HttpServletRequest request){
		System.out.println("SalaCont + " +id);
		Long idl = Long.parseLong(id);
		request.getSession().setAttribute("sala", sr.findOne(idl));
		System.out.println("salaCont "+ sr.findOne(idl).getNaziv());
        return "izabrao je salu" + idl;
	}
	
	@RequestMapping("/hale")
	public List<Sala> getAllPozoristeBioskop2(HttpServletRequest request) {
		PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		System.out.println("Sala COnt, pb "+pb.getNaziv());
        List<Sala> allP = new ArrayList<>();
        sService.getAll().forEach(allP::add);
        System.out.println("Sala Cont allP "+allP.size());
        List<Sala> temp = allP;
        PozoristeBioskop pk = pbr.getOne(pb.getId());
        List<Sala> sale = pk.getSale();
        
        /*for(int i = 0; i<allP.size(); i++) {
        	//System.out.println("ProjCont projekcija: "+allP.get(i).getSala().getPozoristeBioskop().getNaziv());
        	Sala s = sService.getOne(allP.get(i).getId());
        	if(!s.getPozoristeBioskop().equals(pb)) {
        		temp.remove(allP.get(i));
        	}
        }*/
        return sale;
	}
	
	@RequestMapping("/sala/{id}")
	public Sala getSala(@PathVariable Long id) {
		return sService.getOne(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sala/add")
	public void addSala(@RequestBody SalaDTO s, HttpServletRequest request) {
		if(s.getNaziv().equals("")) {
			return;
		}
		PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		s.setPozoristeBioskop(pb.getId());
		sService.addSala(s);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/sala/add")
	public void editujSala(@RequestBody SalaDTO s, HttpServletRequest request) {
		Sala pb = (Sala) request.getSession().getAttribute("sala");
		long id = pb.getId();
		PozoristeBioskop pb2 = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		s.setPozoristeBioskop(pb2.getId());
		sService.editSala(s, id);
	}

	
	@RequestMapping(method = RequestMethod.DELETE, value = "/saladelete/{id}")
	public void deleteSala(@PathVariable Long id, HttpServletRequest request) {
		PozoristeBioskop pb = (PozoristeBioskop)request.getSession().getAttribute("pozbio");
		long pbId = pb.getId();
		ArrayList<Long> ids = ps.getProjekcijeToBeDeleted(id);
		sService.deleteProjekcijeFromPB(ids, pbId);
		ps.deleteProjekcijaByIds(ids);
		sService.deleteSala(id, pbId, ids);

	}
}