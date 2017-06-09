package theMovieDbApi.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import theMovieDbApi.dao.DaoFilm;
import theMovieDbApi.dao.DaoFilmGenre;
import theMovieDbApi.model.Film;
import theMovieDbApi.model.FilmGenre;

@Transactional
@Repository
public class DaoFilmGenreJpa implements DaoFilmGenre{

	
	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public FilmGenre find(Long id) {
		return em.find(FilmGenre.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<FilmGenre> findAll() {
		Query query = em.createQuery("select f from FilmGenre f");		
		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(FilmGenre filmGenre) {
		//film = em.merge(film);
		em.persist(filmGenre);
		
	}

	@Override
	@Transactional
	public FilmGenre update(FilmGenre filmGenre) {
		return em.merge(filmGenre);
	}

	@Override
	@Transactional
	public void delete(FilmGenre filmGenre) {
		em.remove(em.merge(filmGenre));
		
	}

	@Override
	public void delete(Long id) {
		em.remove(find(id));
		
		
	}

}
