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
import theMovieDbApi.dao.DaoUtilisateur;
import theMovieDbApi.model.Film;
import theMovieDbApi.model.Utilisateur;

@Transactional
@Repository
public class DaoUtilisateurJpa implements DaoUtilisateur{

	
	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	@Autowired
	private DaoFilm daoFilm;
	
	@Override
	@Transactional(readOnly=true)
	public Utilisateur find(Long id) {
		return em.find(Utilisateur.class, id);
	}

	@Override
	@Transactional
	public List<Utilisateur> findAll() {
		Query query = em.createQuery("select u from Utilisateur u");		
		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Utilisateur utilisateur) {
		//acteur = em.merge(acteur);
		em.persist(utilisateur);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Utilisateur update(Utilisateur utilisateur) {
		utilisateur = em.merge(utilisateur);
		return utilisateur;
	}

	@Override
	@Transactional(readOnly=true)
	public void delete(Utilisateur utilisateur) {
		
		utilisateur = em.merge(utilisateur);
		for(Film film : utilisateur.getFilms()){
			daoFilm.delete(film);
		}
		em.remove(utilisateur);
		
	}

	@Override
	@Transactional(readOnly=true)
	public void delete(Long id) {
		
		Utilisateur utilisateur = find(id);
		for(Film film : utilisateur.getFilms()){
			daoFilm.delete(film);
		}
		em.remove(utilisateur);
		
	}

	@Override
	public Utilisateur findByUsername(String username) {
		Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.username =:username");
		query.setParameter("username", username);
		return (Utilisateur) query.getSingleResult();
	}
	
	

}
