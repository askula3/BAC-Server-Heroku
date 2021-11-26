package ba.bac.server.shared.dto;

import java.io.Serializable;

public class CountryDto implements Serializable {
    private static final long serialVersionUID = -6009325226579835622L;
    private long id;
    private String country;
    private String region;
    private boolean disabled;

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
