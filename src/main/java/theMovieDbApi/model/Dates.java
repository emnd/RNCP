package theMovieDbApi.model;

import java.util.Date;

public class Dates
{
    private Date minimum;

    private Date maximum;

    public Date getMinimum ()
    {
        return minimum;
    }

    public void setMinimum (Date minimum)
    {
        this.minimum = minimum;
    }

    public Date getMaximum ()
    {
        return maximum;
    }

    public void setMaximum (Date maximum)
    {
        this.maximum = maximum;
    }

    @Override
    public String toString()
    {
        return "Dates [minimum = "+minimum+", maximum = "+maximum+"]";
    }
}