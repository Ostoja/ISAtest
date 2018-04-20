package com.isa.ISA;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isa.ISA.model.Admin;
import com.isa.ISA.model.FilmPredstava;
import com.isa.ISA.model.Korisnik;
import com.isa.ISA.model.Mesto;
import com.isa.ISA.model.PozoristeBioskop;
import com.isa.ISA.model.Projekcija;
import com.isa.ISA.model.Sala;
import com.isa.ISA.model.TipKorisnika;
import com.isa.ISA.model.TipSedista;
import com.isa.ISA.model.VrstaAmbijenta;
import com.isa.ISA.repository.MestoRepository;
import com.isa.ISA.repository.SalaRepository;
import com.isa.ISA.service.AdminService;
import com.isa.ISA.service.FilmPredstavaService;
import com.isa.ISA.service.KorisnikService;
import com.isa.ISA.service.PozoristeBioskopService;
import com.isa.ISA.service.ProjekcijaService;

@Component
public class TestData {
	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private MestoRepository mr;
	
	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private PozoristeBioskopService pbService;

	@Autowired
	private FilmPredstavaService filmPredstavaService;

	@Autowired
	private ProjekcijaService projekcijaService;

	@PostConstruct
	public void init() {
		Korisnik k = new Korisnik();
		k.setUsername("niko");
		k.setPassword("rozberg1");
		k.setIme("Nikos");
		k.setPrezime("Galis");
		k.setEmail("niko@niko.com");
		k.setJeAktivan(false);
		k.setTip(TipKorisnika.Obican);
		System.out.println("Kreiran korisnik: " + k.getUsername());
		korisnikService.addUser(k);
		
		Korisnik k1 = new Korisnik();
		k1.setUsername("niko1");
		k1.setPassword("rozberg1");
		k1.setIme("Nikos");
		k1.setPrezime("Galis");
		k1.setEmail("niko1@niko.com");
		k1.setJeAktivan(false);
		k1.setTip(TipKorisnika.Obican);
		System.out.println("Kreiran korisnik: " + k1.getUsername());
		korisnikService.addUser(k1);
		
		Admin a = new Admin();
        a.setUsername("admin");
        a.setPassword("admini");
        a.setTip(TipKorisnika.AdminBioPoz);
        a.setEmail("admin@admin.com");
        a.setIme("Adam");
        a.setPrezime("Adamovic");
        a.setGrad("Aleksinac");
        a.setJeAktivan(true);
        System.out.println("Kreiran korisnik: " + a.getUsername());
        adminService.addAdmin(a);
        
        Admin a1 = new Admin();
        a1.setUsername("admin1");
        a1.setPassword("admini");
        a1.setTip(TipKorisnika.AdminBioPoz);
        a1.setEmail("admin1@admin.com");
        a1.setIme("Adam");
        a1.setPrezime("Adamovic");
        a1.setGrad("Aleksinac");
        a1.setJeAktivan(false);
        System.out.println("Kreiran korisnik: " + a1.getUsername());
        adminService.addAdmin(a1);
        
        PozoristeBioskop p1 = new PozoristeBioskop();
        p1.setBrojOcena(0);
        p1.setProsecnaOcena(0);
        List<Admin> adminList = new ArrayList<>();
        adminList.add(a);
        p1.setAdmini(adminList);
        p1.setNaziv("ABC Cinema");
        p1.setPromotivniOpis("Vrlo mainstream bioskop");
        p1.setVrstaAmbijenta(VrstaAmbijenta.Bioskop);
        p1.setAdresa("Gondolin, Tolkinova 43");

        PozoristeBioskop p2 = new PozoristeBioskop();
        p2.setBrojOcena(0);
        p2.setProsecnaOcena(0);
        List<Admin> adminList2 = new ArrayList<>();
        adminList2.add(a);
        adminList2.add(a1);
        p2.setAdmini(adminList2);
        p2.setNaziv("SNP");
        p2.setPromotivniOpis("U centru pozoriste");
        p2.setVrstaAmbijenta(VrstaAmbijenta.Pozoriste);
        p2.setAdresa("Bri, Tolkinova 43");
        
        PozoristeBioskop p3 = new PozoristeBioskop();
        p3.setBrojOcena(0);
        p3.setProsecnaOcena(0);
        List<Admin> adminList3 = new ArrayList<>();
        adminList3.add(a1);
        p3.setAdmini(adminList3);
        p3.setNaziv("Arena");
        p3.setPromotivniOpis("U centru bioskop");
        p3.setVrstaAmbijenta(VrstaAmbijenta.Bioskop);
        p3.setAdresa("Bri, Tolkinova 43");
        //pbService.addPozoristeBioskop(p1);
        Sala s1 = new Sala();
        s1.setBrojKolona(10);
        s1.setBrojRedova(10);
        s1.setNaziv("A1");
        List<Sala> sale = new ArrayList<>();

   /*

        List<Mesto> mesta = new ArrayList<>();
        for(int i =0; i<=99; i++){
            Mesto mesto = new Mesto();
            mesto.setKolona(i%10);
            mesto.setRed( Math.floorDiv(i, 10));
            mesto.setTipSedista(TipSedista.Obicno);
            mesta.add(mesto);
            mr.save(mesto);
        }
*/
      //  s1.setMesta(mesta);
        salaRepository.save(s1);
        sale.add(s1);
        p1.setSale(sale);
        s1.setPozoristeBioskop(p1);
        List<Projekcija> projekcije = new ArrayList<>();
        Projekcija p = new Projekcija();
        p.setCena(300);
        p.setSala(s1);
        p.setDatum(new Date());
        FilmPredstava fp = new FilmPredstava();
        fp.setBrojOcena(0);
        fp.setProsecnaOcena(0);
        fp.setNosiBodova(1);
        fp.setNaziv("Transformers 25");
        fp.setOpis("25. nastavak.");
        fp.setReditelj("Christopher Nolan");
        fp.setSpisakGlumaca("The Rock, Jackie Chan");
        fp.setZanr("CGI mess");
        fp.setTrajanje(180);
     
        FilmPredstava fp2 = new FilmPredstava();
        fp2.setBrojOcena(0);
        fp2.setProsecnaOcena(0);
        fp2.setNosiBodova(1);
        fp2.setNaziv("Goodfellas");
        fp2.setOpis("Klasik");
        fp2.setReditelj("Martin Scorcese");
        fp2.setSpisakGlumaca("Ray Liotta, Joe Pesci");
        fp2.setZanr("Gangster");
        fp2.setTrajanje(172);

        
        filmPredstavaService.addFilmPredstava1(fp);
        filmPredstavaService.addFilmPredstava1(fp2);

        p.setFilmPredstava(fp);
        projekcijaService.addProjekcija(p);

        projekcije.add(p);
        p1.setRepertoar(projekcije);

        pbService.addPozoristeBioskop(p1);
        pbService.addPozoristeBioskop(p2);
        pbService.addPozoristeBioskop(p3);
        fp.setMestoOdrzavanja(p1);
        filmPredstavaService.updateFilmPredstava(fp);
        
	}
}
