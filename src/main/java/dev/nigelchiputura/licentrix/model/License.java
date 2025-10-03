package dev.nigelchiputura.licentrix.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private LicenseType licenseType;

    private LocalDate issueDate;
    private Integer validityYears;

    private Double applicationFee;
    private Double licenseFee;
    private Double annualFrequencyFee;//
    private Double annualContributionFee; //

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public License() {
    }

    public License(Integer id, LicenseType licenseType, LocalDate issueDate, Integer validityYears, Double applicationFee, Double licenseFee, Double annualFrequencyFee, Company company, Double annualContributionFee) {
        this.id = id;
        this.licenseType = licenseType;
        this.issueDate = issueDate;
        this.validityYears = validityYears;
        this.applicationFee = applicationFee;
        this.licenseFee = licenseFee;
        this.annualFrequencyFee = annualFrequencyFee;
        this.company = company;
        this.annualContributionFee = annualContributionFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Integer getValidityYears() {
        return validityYears;
    }

    public void setValidityYears(Integer validityYears) {
        this.validityYears = validityYears;
    }

    public Double getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(Double applicationFee) {
        this.applicationFee = applicationFee;
    }

    public Double getLicenseFee() {
        return licenseFee;
    }

    public void setLicenseFee(Double licenseFee) {
        this.licenseFee = licenseFee;
    }

    public Double getAnnualFrequencyFee() {
        return annualFrequencyFee;
    }

    public void setAnnualFrequencyFee(Double annualFrequencyFee) {
        this.annualFrequencyFee = annualFrequencyFee;
    }

    public Double getAnnualContributionFee() {
        return annualContributionFee;
    }

    public void setAnnualContributionFee(Double annualContributionFee) {
        this.annualContributionFee = annualContributionFee;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        License license = (License) o;
        return Objects.equals(id, license.id) && licenseType == license.licenseType && Objects.equals(issueDate, license.issueDate) && Objects.equals(validityYears, license.validityYears) && Objects.equals(applicationFee, license.applicationFee) && Objects.equals(licenseFee, license.licenseFee) && Objects.equals(annualFrequencyFee, license.annualFrequencyFee) && Objects.equals(annualContributionFee, license.annualContributionFee) && Objects.equals(company, license.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licenseType, issueDate, validityYears, applicationFee, licenseFee, annualFrequencyFee, annualContributionFee, company);
    }
}
