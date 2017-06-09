package theMovieDbApi.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import theMovieDbApi.dao.DaoActeur;
import theMovieDbApi.dao.DaoFilm;
import theMovieDbApi.model.Acteur;
import theMovieDbApi.model.Film;

@Transactional
@Repository
public class DaoActeurJpa implements DaoActeur{

	
	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	@Autowired
	private DaoFilm daoFilm;
	
	@Override
	@Transactional(readOnly=true)
	public Acteur find(Long id) {
		return em.find(Acteur.class, id);
	}

	@Override
	@Transactional
	public List<Acteur> findAll() {
		Query query = em.createQuery("select a from Acteur a");		
		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Acteur acteur) {
		//acteur = em.merge(acteur);
		em.persist(acteur);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Acteur update(Acteur acteur) {
		acteur = em.merge(acteur);
		return acteur;
	}

	@Override
	@Transactional(readOnly=true)
	public void delete(Acteur acteur) {
		
		acteur = em.merge(acteur);
		for(Film film : acteur.getFilms()){
			daoFilm.delete(film);
		}
		em.remove(acteur);
		
	}

	@Override
	@Transactional(readOnly=true)
	public void delete(Long id) {
		
		Acteur acteur = find(id);
		for(Film film : acteur.getFilms()){
			daoFilm.delete(film);
		}
		em.remove(acteur);
		
	}

}
