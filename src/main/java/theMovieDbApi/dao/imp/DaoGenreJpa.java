package theMovieDbApi.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import theMovieDbApi.dao.DaoFilm;
import theMovieDbApi.dao.DaoGenre;
import theMovieDbApi.model.Film;
import theMovieDbApi.model.Genre;

@Transactional
@Repository
public class DaoGenreJpa implements DaoGenre{

	
	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public Genre find(Long id) {
		return em.find(Genre.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Genre> findAll() {
		Query query = em.createQuery("select g from Genre g");	
		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Genre genre) {
		em.persist(genre);
		
	}

	@Override
	@Transactional
	public Genre update(Genre genre) {
		return em.merge(genre);
	}

	@Override
	@Transactional
	public void delete(Genre genre) {
		em.remove(em.merge(genre));
		
	}

	@Override
	public void delete(Long id) {
		em.remove(find(id));
		
		
	}

}
