package theMovieDbApi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmNowPlayinJson {
	
	public List<Film> results;
	
	
	public FilmNowPlayinJson() {}


	public List<Film> getResults() {
		return results;
	}

	public void setResults(List<Film> results) {
		this.results = results;
	}



	
	

}
