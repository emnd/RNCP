package theMovieDbApi.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import theMovieDbApi.dao.DaoActeur;
import theMovieDbApi.dao.DaoFilm;
import theMovieDbApi.dao.DaoGenre;
import theMovieDbApi.model.Acteur;
import theMovieDbApi.model.Film;
import theMovieDbApi.model.Genre;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@Rollback(true)
public class TheMovieDbApiTest {

	@Autowired 
	DaoFilm daoFilm;
	
	@Autowired 
	DaoGenre daoGenre;
	
	@Autowired 
	DaoActeur daoActeur;
	
	@Test
	public void filmTest()
	{
		System.out.println("BBBBBBB");
		
		Film f = daoFilm.find(295693L) !=null ? daoFilm.find(295693L) : new Film();
		System.out.println("le titre est : "+f.getTitle());
		Assert.assertEquals("Baby Boss",f.getTitle());
		
		Film f1 = new Film();
		f.setId(12L);
		f.setTitle("lhomme");
		daoFilm.create(f1);
		
	}
	
	@Test
	public void genreTest()
	{
		Genre g = daoGenre.find(28L);
		assertEquals("Action",g.getName());
	}
	
	@Test
	public void acteurTest()
	{
		Acteur a = daoActeur.find(131L);
		assertEquals("Jake Gyllenhaal",a.getName());
	}
}
