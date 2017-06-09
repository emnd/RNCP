package theMovieDbApi.dao;

import java.util.List;
import java.util.Set;

import theMovieDbApi.model.Film;

public interface DaoFilm extends Dao<Film, Long>{
	public List<Film> getByGenre(String genre);
	public List<Film> getByTitle(String titre);
	public List<Film> getByActor(String actorName);
	public Set<Film> getDetailFilm(Long id);
	public Set<Film> getVideoTheque(String username);;

}
