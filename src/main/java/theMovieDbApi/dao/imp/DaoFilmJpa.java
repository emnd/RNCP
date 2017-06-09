package theMovieDbApi.dao.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import theMovieDbApi.dao.DaoFilm;
import theMovieDbApi.model.Acteur;
import theMovieDbApi.model.Film;
import theMovieDbApi.model.Genre;

@Transactional
@Repository
public class DaoFilmJpa implements DaoFilm{

	
	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public Film find(Long id) {
		return em.find(Film.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Film> findAll() {
		Query query = em.createQuery("select f from Film f");		
		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Film film) {
		//film = em.merge(film);
		em.persist(film);
		
	}

	@Override
	@Transactional
	public Film update(Film film) {
		return em.merge(film);
	}

	@Override
	@Transactional
	public void delete(Film film) {
		em.remove(em.merge(film));
		
	}

	@Override
	public void delete(Long id) {
		em.remove(find(id));
		
		
	}
	
	@Override
	public List<Film> getByGenre(String genre)
	{
		Query query = em.createQuery("SELECT DISTINCT f FROM Film f JOIN f.genres g JOIN f.genres g1 WHERE g.name =:genre");
		query.setParameter("genre", genre);
		return query.getResultList();
	}

	@Override
	public List<Film> getByTitle(String titre) {
		Query query = em.createQuery("SELECT DISTINCT f FROM Film f where f.title LIKE :titre");
		query.setParameter("titre", "%"+titre+"%");
		return query.getResultList();
	}

	@Override
	public List<Film> getByActor(String actorName)
	{
//		Query query = em.createQuery("SELECT DISTINCT f FROM Film f JOIN f.acteurs a JOIN f.acteurs a1 WHERE a.name=:actorName");
//		query.setParameter("actorName", actorName);
		Query query = em.createQuery("SELECT DISTINCT f FROM Film f JOIN f.acteurs a JOIN f.acteurs a1 WHERE a.name like :actorName");
		query.setParameter("actorName", "%"+actorName+"%");
		return query.getResultList();
	}
	
	@Override
	public Set<Film> getDetailFilm(Long id)
	{
		Query query = em.createQuery("SELECT f.title,f.poster_path,f.release_date,f.overview,g.name,a.name " 
				+ "FROM Film f "
				+ "JOIN  f.genres g "
				+ "JOIN  f.acteurs a "
				+ " WHERE f.id =:id ");
		query.setParameter("id", id);		
		List<Film> films = query.getResultList();

		Set<Film> films1 = new HashSet<Film>(films);
		return films1;
		
	}

	@Override
	public Set<Film> getVideoTheque(String username) {
		Query query = em.createQuery("SELECT DISTINCT f FROM Film f JOIN f.utilisateurs u JOIN f.utilisateurs u1 WHERE u.username=:username");
		
		query.setParameter("username", username);
		
		 List<Film> films = query.getResultList();
		 Set<Film> films1 = new HashSet<Film>(films);
		 return films1;
	}
	
	
	
}
