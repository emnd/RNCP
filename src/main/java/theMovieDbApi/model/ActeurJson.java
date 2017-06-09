package theMovieDbApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActeurJson {
	
	private Long id;

    private Long ordre;

    private String credit_id;

    private String name;

    private Long cast_id;

    private String profile_path;

    private String character;
    
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
    

	@Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", ordre = "+ordre+", credit_id = "+credit_id+", name = "+name+", cast_id = "+cast_id+", profile_path = "+profile_path+", character = "+character+"]";
    }
	
	
	

}
