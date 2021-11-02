package ba.bac.server.shared.dto;

import java.io.Serializable;

public class MutualRelationshipsDto implements Serializable {

    private static final long serialVersionUID = -7217458928736475904L;
    private long id;
    private String country;
    private String timeline;
    private String details;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
