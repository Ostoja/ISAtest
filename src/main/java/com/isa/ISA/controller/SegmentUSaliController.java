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
import com.isa.ISA.model.SegmentUSali;
import com.isa.ISA.model.TipSedista;
import com.isa.ISA.model.DTO.SalaDTO;
import com.isa.ISA.model.DTO.SegmentUSaliDTO;
import com.isa.ISA.repository.ProjekcijaRepository;
import com.isa.ISA.repository.SegmentUSaliRepository;
import com.isa.ISA.service.SegmentUSaliService;

@RestController
public class SegmentUSaliController {
	@Autowired
	private SegmentUSaliService  suss;
	@Autowired
	private SegmentUSaliRepository susr;
	
	@Autowired
	private ProjekcijaRepository pr;
	
	@RequestMapping("/segmenti")
	public List<SegmentUSali> getAllPozoristeBioskop(HttpServletRequest request) {
		Sala pb = (Sala) request.getSession().getAttribute("sala");
		System.out.println("Seg COnt, pb "+pb.getNaziv());
        List<SegmentUSali> allP = new ArrayList<>();
        susr.findAll().forEach(allP::add);
        System.out.println("SEg Cont allP "+allP.size());
        List<SegmentUSali> temp = allP;
        for(int i = 0; i<allP.size(); i++) {
        	/*System.out.println("ProjCont projekcija: "+allP.get(i).getSala().getPozoristeBioskop().getNaziv());
        	if(!allP.get(i).getSala().getPozoristeBioskop().equals(pb)) {
        		temp.remove(allP.get(i));
        	}*/
        	if(allP.get(i).getSala().getId()!=pb.getId()) {
        		temp.remove(allP.get(i));
        	}
        }
        return temp;
	}
	
	@RequestMapping("/segmenti2")
	public List<SegmentUSali> getAllPozoristeBioskop2(HttpServletRequest request) {
		Sala pb = (Sala) request.getSession().getAttribute("sala");
		System.out.println("Seg COnt, pb "+pb.getNaziv());
        List<SegmentUSali> allP = new ArrayList<>();
        susr.findAll().forEach(allP::add);
        System.out.println("SEg Cont allP "+allP.size());
        List<SegmentUSali> temp = allP;
        for(int i = 0; i<allP.size(); i++) {
        	/*System.out.println("ProjCont projekcija: "+allP.get(i).getSala().getPozoristeBioskop().getNaziv());
        	if(!allP.get(i).getSala().getPozoristeBioskop().equals(pb)) {
        		temp.remove(allP.get(i));
        	}*/
        	if(allP.get(i).getSala().getId()!=pb.getId()) {
        		temp.remove(allP.get(i));
        	}
        	if(allP.get(i).isJeZatvoreno()) {
        		temp.remove(allP.get(i));
        	}
        }
        return temp;
	}
	
	@RequestMapping("/segmentipr/{id}")
	public List<SegmentUSali> getAllPozoristeBioskop2(@PathVariable Long id, HttpServletRequest request) {
		//Sala pb = (Sala) request.getSession().getAttribute("sala");
		Projekcija p = pr.getOne(id);
		Sala pb = p.getSala();
		System.out.println("Seg COnt, pb "+pb.getNaziv());
        List<SegmentUSali> allP = new ArrayList<>();
        susr.findAll().forEach(allP::add);
        System.out.println("SEg Cont allP "+allP.size());
        List<SegmentUSali> temp = allP;
        for(int i = 0; i<allP.size(); i++) {
        	/*System.out.println("ProjCont projekcija: "+allP.get(i).getSala().getPozoristeBioskop().getNaziv());
        	if(!allP.get(i).getSala().getPozoristeBioskop().equals(pb)) {
        		temp.remove(allP.get(i));
        	}*/
        	if(allP.get(i).getSala().getId()!=pb.getId()) {
        		temp.remove(allP.get(i));
        	}
        }
        return temp;
	}
	@RequestMapping("/seggment") 
	public SegmentUSaliDTO getSegmentEdit(HttpServletRequest request) {
		SegmentUSali sus = (SegmentUSali) request.getSession().getAttribute("segment");
		sus = susr.getOne(sus.getId());
		SegmentUSaliDTO susDTO = new SegmentUSaliDTO();
		susDTO.setJeZatvoreno(sus.isJeZatvoreno());
		susDTO.setKolone(sus.getKolone());
		susDTO.setNaziv(sus.getNaziv());
		susDTO.setRedovi(sus.getRedovi());
		susDTO.setSala(sus.getSala().getId());
		susDTO.setTipSedista(sus.getTipSedista());
		return susDTO;
	}
	@RequestMapping("/segment/{id}")
	public SegmentUSali getSala(@PathVariable Long id) {
		return suss.getOne(id);
	}
	
	@RequestMapping("/segm/{id}")
    private String getAllProjekcijaPB(@PathVariable String id, HttpServletRequest request){
		System.out.println("SegmCont + " +id);
		Long idl = Long.parseLong(id);
		request.getSession().setAttribute("segment", susr.findOne(idl));
		System.out.println("segmCont "+ susr.findOne(idl).getNaziv());
        return "izabrao je salu" + idl;
	}
	
	@RequestMapping("/tipoviSedista")
	public List<TipSedista> tipoviSedista() {
		List<TipSedista> tips = new ArrayList<>();
		tips.add(TipSedista.Balkon);
		tips.add(TipSedista.Obicno);
		tips.add(TipSedista.Vip);
		return tips;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/segment/add")
	public void addSala(@RequestBody SegmentUSaliDTO s, HttpServletRequest request) {
		if(s.getKolone()<1||s.getRedovi()<1||s.getNaziv().equals("")) {
			return;
		}
		Sala pb = (Sala) request.getSession().getAttribute("sala");
		s.setSala(pb.getId());
		System.out.println("SegCont + "+s.toString());
		suss.addSala(s);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/segment/add")
	public void addSala2(@RequestBody SegmentUSaliDTO s, HttpServletRequest request) {
		SegmentUSali pb = (SegmentUSali) request.getSession().getAttribute("segment");
		s.setSala(susr.findOne(pb.getId()).getSala().getId());
		System.out.println("SegCont + "+s.toString());
		suss.editSala(s, pb.getId());
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/segment/edit/{id}")
	public void editSala(@RequestBody SegmentUSali s, @PathVariable Long id) {
		//suss.addSala(s, id);
	}

	 @RequestMapping(method = RequestMethod.DELETE, value = "/segment/{id}")
	    public void deleteSala(@PathVariable Long id, HttpServletRequest request){
	    	System.out.println("Deleting segment "+id);
	    	
	        suss.deleteSala(id);

	}

}
