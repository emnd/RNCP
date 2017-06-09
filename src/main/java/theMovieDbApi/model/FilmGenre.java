package theMovieDbApi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


//@Entity
public class FilmGenre {

	@Id
	@GeneratedValue
	public Long id;
	
	@ManyToOne
	@JoinColumn(name="Film_FilmGenre")
	public Film film;
	
	@ManyToOne
	@JoinColumn(name="Genre_FilmGenre")
	public Genre genre;
	
	public FilmGenre() {}

	public FilmGenre(Film film, Genre genre) {
		this.film = film;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	
	
	
}
