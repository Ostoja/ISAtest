package com.isa.ISA.service;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.isa.ISA.model.FilmPredstava;
import static com.isa.ISA.constants.FilmPredstavaConstants.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmPredstavaServiceTest {
	@Autowired
	private FilmPredstavaService filmPredstavaService;
	
	@Test
	public void testFindAll() {
		List<FilmPredstava> projekcije = filmPredstavaService.getAllFilmPredstava();
		assertThat(projekcije).hasSize(DB_COUNT); 
	}
	
	@Test 
	public void testFindOne() {
		FilmPredstava dbFilmPredstava = filmPredstavaService.getFilmPredstava(DB_ID);
		assertThat(dbFilmPredstava).isNotNull();
		
		assertThat(dbFilmPredstava.getId()).isEqualTo(DB_ID);
		assertThat(dbFilmPredstava.getNaziv()).isEqualTo(DB_NAME);
	}
	
	
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		FilmPredstava dbFilmPredstava = filmPredstavaService.getFilmPredstava(DB_ID);
		
		dbFilmPredstava.setNaziv(NEW_NAZIV);
		dbFilmPredstava.setReditelj(NEW_REDITELJ);
		dbFilmPredstava.setSpisakGlumaca(NEW_SPISAKGLUMACA);
		
		dbFilmPredstava = filmPredstavaService.addFilmPredstava1(dbFilmPredstava);
		assertThat(dbFilmPredstava).isNotNull();
		
		//verify that database contains updated data
		dbFilmPredstava = filmPredstavaService.getFilmPredstava(DB_ID);
        assertThat(dbFilmPredstava.getNaziv()).isEqualTo(NEW_NAZIV);
        assertThat(dbFilmPredstava.getReditelj()).isEqualTo(NEW_REDITELJ);
        assertThat(dbFilmPredstava.getSpisakGlumaca()).isEqualTo(NEW_SPISAKGLUMACA);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = filmPredstavaService.getAllFilmPredstava().size();
		filmPredstavaService.deleteFilmPredstava(DB_ID_TO_DELETE);
		
		List<FilmPredstava> filmPredstavas = filmPredstavaService.getAllFilmPredstava();
		assertThat(filmPredstavas).hasSize(dbSizeBeforeRemove - 1);
		
		FilmPredstava dbFilmPredstava = filmPredstavaService.getFilmPredstava(DB_ID_TO_DELETE);
		assertThat(dbFilmPredstava).isNull();
	}
	
	
}
