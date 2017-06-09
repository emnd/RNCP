package theMovieDbApi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import theMovieDbApi.model.Film;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Genre {

	@Id 
	public Long id;
	
	@Column(name="Nom")
	public String name;
	
	@JsonIgnore
	 @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy="genres")
	List<Film> films = new ArrayList<Film>();

	public Genre() {}

	public Genre(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	

	@Override
	public String toString() {
		return "Acteur [id=" + id + ", name=" + name +  "]";
	}

		
	
	
	
}
