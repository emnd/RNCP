package theMovieDbApi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonActeur {
	
public List<Acteur> results;
	
	public JsonActeur() {}


	public List<Acteur> getResults() {
		return results;
	}

	public void setResults(List<Acteur> results) {
		this.results = results;
	}
	

}
