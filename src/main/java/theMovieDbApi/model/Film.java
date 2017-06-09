package theMovieDbApi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
public class Film {

	@Id
	public Long id;
	
	@Column(name="Titre")
	public String title;
	
	@Column(name="TitreOriginal")
	public String original_title;
	
	@Column(name="Resume",length = 1024)
	public String overview;
	
	@Column(name="Date_Sortie")
	public Date release_date;
	
	@Column(name="Poster")
	public String poster_path;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="FILM_GENRE", joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
	public List<Genre> genres;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="FILM_Acteur", joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "acteur_id"))
	public List<Acteur> acteurs ;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="FILM_utilisateur", joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id"))
	public List<Utilisateur> utilisateurs ;
	
	
	
	public Film() {}

	public Film(String title, String overview, Date release_date) {
		this.overview = overview;
		this.release_date = release_date;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public List<Acteur> getActeurs() {
		return acteurs;
	}

	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}
	
	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	
	

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	
	
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", overview=" + overview + ", date_release=" + release_date
				+ ", poster=" + poster_path  + " ]";
	}

	
	
	
	
}
