package ba.bac.server.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "notable_individuals")
public class NotableIndividualEntity implements Serializable {
    private static final long serialVersionUID = -184524766599396885L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 48)
    private String country;

    @Column(nullable = false, length=15)
    private String firstName;

    @Column(nullable = false, length=15)
    private String lastName;

    @Column(length=45)
    private String currentOccupation;

    @Column(length=45)
    private String importance;

    @Column(length=124)
    private String notes;

    private Date lastUpdated;

    public String getCountry() {
        return country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCurrentOccupation() {
        return currentOccupation;
    }

    public void setCurrentOccupation(String currentOccupation) {
        this.currentOccupation = currentOccupation;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
