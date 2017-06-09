package theMovieDbApi.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActeursPlayingJson {
	
		public Long id;

	    public ActeurJson[] cast;


	    public Long getId ()
	    {
	        return id;
	    }

	    public void setId (Long id)
	    {
	        this.id = id;
	    }

	    public ActeurJson[] getCast ()
	    {
	        return cast;
	    }

	    public void setCast (ActeurJson[] cast)
	    {
	        this.cast = cast;
	    }

		@Override
		public String toString() {
			return "ActeursPlayingJson [id=" + id + ", acteurs=" + Arrays.toString(cast) + "]";
		}

	    
	

}
