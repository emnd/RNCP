package theMovieDbApi.dao;

import theMovieDbApi.model.Utilisateur;

public interface DaoUtilisateur extends Dao<Utilisateur, Long>{

	Utilisateur findByUsername(String username);

}
