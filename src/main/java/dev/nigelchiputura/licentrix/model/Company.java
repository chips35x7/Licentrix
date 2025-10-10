package dev.nigelchiputura.licentrix.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private Double latitude;
    private Double longitude;
    private LocalDate dateOfEstablishment;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<License> licenses;

    public Company() {
    }

    public Company(Integer id, String name, String email, Double latitude, Double longitude, List<License> licenses, LocalDate established) {
        this.id = id;
        this.name = name;
        this.dateOfEstablishment = established;
        this.email = email;
        this.longitude = longitude;
        this.licenses = licenses;
    }

    public LocalDate getDateOfEstablishment() {
        return dateOfEstablishment;
    }

    public void setDateOfEstablishment(LocalDate dateOfEstablishment) {
        this.dateOfEstablishment = dateOfEstablishment;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
        return Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(email, company.email) && Objects.equals(latitude, company.latitude) && Objects.equals(longitude, company.longitude) && Objects.equals(licenses, company.licenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, latitude, longitude, licenses);
    }
}