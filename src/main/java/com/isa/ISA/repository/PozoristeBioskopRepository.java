package com.isa.ISA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.VrstaAmbijenta;

public interface PozoristeBioskopRepository extends JpaRepository<PozoristeBioskop, Long> {

	List<PozoristeBioskop> findByVrstaAmbijenta(VrstaAmbijenta vrstaAmbijenta);

	List<PozoristeBioskop> findByNaziv(String naziv);

}
