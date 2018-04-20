package com.isa.ISA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.aspectj.AspectJAdviceParameterNameDiscoverer.AmbiguousBindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.ISA.model.Admin;
import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.User;
import com.isa.ISA.model.VrstaAmbijenta;
import com.isa.ISA.repository.AdminRepository;
import com.isa.ISA.repository.PozoristeBioskopRepository;

@Service
public class PozoristeBioskopService {

	@Autowired
	private PozoristeBioskopRepository pbr;
	
	@Autowired
	private AdminRepository ar;
	
	public List<PozoristeBioskop> getAllPozoristeBioskop(){
        List<PozoristeBioskop> allPB = new ArrayList<>();
        pbr.findAll().forEach(allPB::add);
        return allPB;
	}
	
	public List<PozoristeBioskop> getAllPozoristeBioskop(User u){
        List<PozoristeBioskop> allPB = new ArrayList<>();
        pbr.findAll().forEach(allPB::add);
        List<PozoristeBioskop> temp = allPB;
        Admin a = ar.findById(u.getId());
        if(a.getTip()!=u.getTip()) {
        	return null;
        }
        if(a!=null) {
        	for(int i = 0; i<allPB.size(); i++) {
        		if(!allPB.get(i).getAdmini().contains(a)) {
        			temp.remove(allPB.get(i));
        		}
        	}
        }
        return temp;
	}
	
	public void addPozoristeBioskop(PozoristeBioskop pb){
        pbr.save(pb);
    }

    public PozoristeBioskop getPozoristeBioskop(Long l){
        return pbr.findOne(l);
    }

    public void updatePozoristeBioskop(PozoristeBioskop pb){
        pbr.save(pb);
    }

    public void deletePozoristeBioskop(Long id){
        pbr.delete(id);
    }

    public List<PozoristeBioskop> getAllPozoriste(){
    	return pbr.findByVrstaAmbijenta(VrstaAmbijenta.Pozoriste);
    }

    public List<PozoristeBioskop> getAllBioskop(){
        return pbr.findByVrstaAmbijenta(VrstaAmbijenta.Bioskop);
    }

    public void updateOcena(Long id, int ocena){
        PozoristeBioskop pb = pbr.findOne(id);
        int broj = pb.getBrojOcena();
        double prosecna = pb.getProsecnaOcena();
        double nova = (prosecna*broj + ocena)/(broj+1);
        pb.setBrojOcena(broj+1);
        pb.setProsecnaOcena(nova);
        pbr.save(pb);
    }


    public List<PozoristeBioskop> getPozoristeBioskop(String naziv){
        List<PozoristeBioskop> foundPB = new ArrayList<>();
        pbr.findByNaziv(naziv).forEach(foundPB::add);
        return foundPB;

}
}
