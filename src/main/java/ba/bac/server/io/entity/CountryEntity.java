package ba.bac.server.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countries")
public class CountryEntity implements Serializable {
    private static final long serialVersionUID = 2289506448236485208L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, length = 48)
    private String country;

    @Column(length = 48)
    private String region;

    @Column(nullable = false)
    private boolean disabled = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
