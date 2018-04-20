package com.isa.ISA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.ISA.model.Sala;
import com.isa.ISA.model.SegmentUSali;

public interface SegmentUSaliRepository extends JpaRepository<SegmentUSali, Long> {

	List<SegmentUSali> findBySala(Sala s);
}
