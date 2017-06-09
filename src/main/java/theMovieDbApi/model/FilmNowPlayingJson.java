package theMovieDbApi.model;



public class FilmNowPlayingJson {

	private Results[] results;

    private Dates dates;

    private Long page;

    private Long total_pages;

    private Long total_results;

    public Results[] getResults ()
    {
        return results;
    }

    public void setResults (Results[] results)
    {
        this.results = results;
    }

    public Dates getDates ()
    {
        return dates;
    }

    public void setDates (Dates dates)
    {
        this.dates = dates;
    }

    public Long getPage ()
    {
        return page;
    }

    public void setPage (Long page)
    {
        this.page = page;
    }

    public Long getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (Long total_pages)
    {
        this.total_pages = total_pages;
    }

    public Long getTotal_results ()
    {
        return total_results;
    }

    public void setTotal_results (Long total_results)
    {
        this.total_results = total_results;
    }

    @Override
    public String toString()
    {
        return "FilmNowPlaying [results = "+results+", dates = "+dates+", page = "+page+", total_pages = "+total_pages+", total_results = "+total_results+"]";
    }
	
	
}
