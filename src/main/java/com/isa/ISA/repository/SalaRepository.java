package com.isa.ISA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{

	public List<Sala> findByPozoristeBioskop(PozoristeBioskop pb); 
}
