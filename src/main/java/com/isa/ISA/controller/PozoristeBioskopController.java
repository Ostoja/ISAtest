package com.isa.ISA.controller;

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
import com.isa.ISA.model.User;
import com.isa.ISA.service.PozoristeBioskopService;

@RestController
public class PozoristeBioskopController {

	@Autowired
	private PozoristeBioskopService pbs;
	
	@RequestMapping("/pb")
    public List<PozoristeBioskop> getAllPozoristeBioskop(){
        return pbs.getAllPozoristeBioskop();
    }

	@RequestMapping("/pba")
    public List<PozoristeBioskop> getAllPozoristeBioskopA(HttpServletRequest request){
		User u = (User) request.getSession().getAttribute("loggedUser");
        return pbs.getAllPozoristeBioskop(u);
    }
	
    @RequestMapping("/p")
    public List<PozoristeBioskop> getAllPozoriste(){
        return pbs.getAllPozoriste();
    }

    @RequestMapping("/b")
    public List<PozoristeBioskop> getAllBioskop(){
        return pbs.getAllBioskop();
    }

    @RequestMapping("/pb/{id}")
    public PozoristeBioskop getPozoristeBioskop(@PathVariable Long id){ 
        return pbs.getPozoristeBioskop(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/pb")
    public void addPozoristeBioskop(@RequestBody PozoristeBioskop pb){
        pbs.addPozoristeBioskop(pb);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/pb/{id}")
    public void updatePozoristeBioskop(@RequestBody PozoristeBioskop pb, @PathVariable Long id){
        pbs.updatePozoristeBioskop(pb);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pb/{id}")
    public void deletePozoristeBioskop(@PathVariable Long id ){
        pbs.deletePozoristeBioskop(id);
    }
    
    @RequestMapping("/pb/{id}/projekcije")
    public List<Projekcija> getPozoristeBioskopProj(@PathVariable Long id){ 
        PozoristeBioskop pb = pbs.getPozoristeBioskop(id);
        return pb.getRepertoar();
    }

}
