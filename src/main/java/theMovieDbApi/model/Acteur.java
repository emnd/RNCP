package theMovieDbApi.model;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Acteur {

		@Id
		public Long id;

	    public Long ordre;

	    public String credit_id;

	    public String name;

	    public Long cast_id;

	    public String profile_path;

	    public String character;
	    
	    @JsonIgnore
		@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy="acteurs")
		List<Film> films = new ArrayList<Film>();

	    public Long getId ()
	    {
	        return id;
	    }

	    public void setId (Long id)
	    {
	        this.id = id;
	    }

	    public Long getOrdre ()
	    {
	        return ordre;
	    }

	    public void setOrdre (Long ordre)
	    {
	        this.ordre = ordre;
	    }

	    public String getCredit_id ()
	    {
	        return credit_id;
	    }

	    public void setCredit_id (String credit_id)
	    {
	        this.credit_id = credit_id;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public Long getCast_id ()
	    {
	        return cast_id;
	    }

	    public void setCast_id (Long cast_id)
	    {
	        this.cast_id = cast_id;
	    }

	    public String getProfile_path ()
	    {
	        return profile_path;
	    }

	    public void setProfile_path (String profile_path)
	    {
	        this.profile_path = profile_path;
	    }

	    public String getCharacter ()
	    {
	        return character;
	    }

	    public void setCharacter (String character)
	    {
	        this.character = character;
	    }
	    
	    public List<Film> getFilms() {
			return films;
		}

		public void setFilms(List<Film> films) {
			this.films = films;
		}

		@Override
	    public String toString()
	    {
	        return "Acteur [id = "+id+", ordre = "+ordre+", credit_id = "+credit_id+", name = "+name+", cast_id = "+cast_id+", profile_path = "+profile_path+", character = "+character+"]";
	    }
	
}
