package com.isa.ISA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.ISA.model.Admin;
import com.isa.ISA.model.TipKorisnika;
import com.isa.ISA.repository.AdminRepository;;

@Service
public class AdminService {
	@Autowired
    private AdminRepository ar;


    public List<Admin> getAllAdmins(){
        List<Admin> allAdmins = new ArrayList<>();
        ar.findAll().forEach(allAdmins::add);
        return allAdmins;
    }
    
    public void addAdmin(Admin a){
        ar.save(a);
    }

    public Admin getAdmin(String username){

        return ar.findByUsername(username);
    }

    public Admin getAdmin(Long id){
        return ar.findById(id);
    }

    public void updateAdmin(Admin a){
        ar.save(a);
    }

    public void updatePassword(Admin a){
        a.setJeAktivan(true);
        ar.save(a);
    }

    public Admin getAdminByEmail(String email){
        return ar.findByEmail(email);
    }

    public void deleteAdmin(Admin a){
        ar.delete(a);
    }

    public List<Admin> findAllPBAdmin(){
        List<Admin> pbAdmins = new ArrayList<>();
        ar.findByTip(TipKorisnika.AdminBioPoz).addAll(pbAdmins);
        return pbAdmins;
    }

    public List<Admin> findAllSYSAdmin(){
        List<Admin> sysAdmin = new ArrayList<>();
        ar.findByTip(TipKorisnika.Admin).addAll(sysAdmin);
        return sysAdmin;
    }

    public List<Admin> findAllFanAdmin(){
        List<Admin> fanAdmin = new ArrayList<>();
        ar.findByTip(TipKorisnika.AdminFanZone).addAll(fanAdmin);
        return fanAdmin;
}
}
