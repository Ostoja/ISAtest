package com.isa.ISA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.ISA.model.Admin;
import com.isa.ISA.model.TipKorisnika;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Admin findByUsername(String username);
    Admin findById(Long id);
    List<Admin> findByTip(TipKorisnika tip);
    Admin findByEmail(String email);
}
