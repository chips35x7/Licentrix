package dev.nigelchiputura.licentrix.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String gpsCoordinates;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<License> licenses;

    public Company() {
    }

    public Company(String name, Integer id, String email, String gpsCoordinates, List<License> licenses) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.gpsCoordinates = gpsCoordinates;
        this.licenses = licenses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGpsCoordinates() {
        return gpsCoordinates;
    }

    public void setGpsCoordinates(String gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(email, company.email) && Objects.equals(gpsCoordinates, company.gpsCoordinates) && Objects.equals(licenses, company.licenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, gpsCoordinates, licenses);
    }
}