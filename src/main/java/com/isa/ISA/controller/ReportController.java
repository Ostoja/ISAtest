package com.isa.ISA.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.ISA.model.FilmPredstava;
import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.DTO.DatumDTO;
import com.isa.ISA.model.DTO.ReportDTO;
import com.isa.ISA.service.ReportService;

@RestController
public class ReportController {
	@Autowired
	private ReportService reportService;

	@RequestMapping("/izvestaj/pb") //da li mi ovo stvarno treba?
	private double getOcenaPB(HttpServletRequest request) {
		PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		return reportService.getOcenaPB(pb.getId());
	}

	@RequestMapping("/izvestaj/fp") //da li mi ovo stvarno treba?
	private double getOcenaFP(HttpServletRequest request) {
		FilmPredstava fp = (FilmPredstava) request.getSession().getAttribute("film");
		return reportService.getOcenaFP(fp.getId());
	}

	@RequestMapping("/izvestaj/prihod")
    private int getPrihodIzmedju(@RequestBody DatumDTO d, HttpServletRequest request){
		PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		long id = pb.getId();
		if(d.getKa()!=null && d.getOd()!=null) {
			return reportService.getPrihodIzmedju(id, d.getOd(), d.getKa());
		}
		else if(d.getKa()==null && d.getOd()==null) {
			return -1;
		}
		else if(d.getKa()==null) {
			return reportService.getPrihodPosle(id, d.getOd());
		}
		else {
			return reportService.getPrihodPre(id, d.getKa());
		}
	}
	
	@RequestMapping("/izvestaj/posecenost")
    private List<ReportDTO> getPosecenostIzmedju(@RequestBody DatumDTO d, HttpServletRequest request){
		PozoristeBioskop pb = (PozoristeBioskop) request.getSession().getAttribute("pozbio");
		long id = pb.getId();
		System.out.println("Posecenost ");
		if(d.getKa()!=null && d.getOd()!=null) {
			return reportService.getPoseteIzmedju(id, d.getOd(), d.getKa());
		}
		else if(d.getKa()==null && d.getOd()==null) {
			return null;
		}
		else if(d.getKa()==null) {
			return reportService.getPosetePosle(id, d.getOd());
		}
		else {
			return reportService.getPosetePre(id, d.getKa());
		}
}
}
