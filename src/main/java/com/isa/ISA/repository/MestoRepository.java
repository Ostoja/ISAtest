package com.isa.ISA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.ISA.model.Mesto;
import com.isa.ISA.model.SegmentUSali;

public interface MestoRepository extends JpaRepository<Mesto, Long> {
	List<Mesto> findBySegmentUSali(SegmentUSali sus);
}
