package dev.nigelchiputura.licentrix.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class LicenseApplication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    private LicenseType licenseType;

    @Enumerated(EnumType.STRING)
    private LicenseApplicationStatus status = LicenseApplicationStatus.PENDING;

    private LocalDate submittedAt = LocalDate.now();
    private LocalDate reviewedAt;
    private String adminComment;

    public LicenseApplication() {
    }

    public LicenseApplication(Integer id, User applicant, Company company, LicenseType licenseType, LicenseApplicationStatus status, LocalDate submittedAt, LocalDate reviewedAt, String adminComment) {
        this.id = id;
        this.applicant = applicant;
        this.company = company;
        this.licenseType = licenseType;
        this.status = status;
        this.submittedAt = submittedAt;
        this.reviewedAt = reviewedAt;
        this.adminComment = adminComment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }

    public LicenseApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(LicenseApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDate submittedAt) {
        this.submittedAt = submittedAt;
    }

    public LocalDate getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(LocalDate reviewedAt) {
        this.reviewedAt = reviewedAt;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LicenseApplication that = (LicenseApplication) o;
        return Objects.equals(id, that.id) && Objects.equals(applicant, that.applicant) && Objects.equals(company, that.company) && licenseType == that.licenseType && status == that.status && Objects.equals(submittedAt, that.submittedAt) && Objects.equals(reviewedAt, that.reviewedAt) && Objects.equals(adminComment, that.adminComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicant, company, licenseType, status, submittedAt, reviewedAt, adminComment);
    }
}
