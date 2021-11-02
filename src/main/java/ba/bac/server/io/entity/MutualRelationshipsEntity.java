package ba.bac.server.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="mutual_relations")
public class MutualRelationshipsEntity implements Serializable {

    private static final long serialVersionUID = 2490851084378918936L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, length = 48)
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

    public void setTimeline(String firstName) {
        this.timeline = firstName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String lastName) {
        this.details = lastName;
    }
}
