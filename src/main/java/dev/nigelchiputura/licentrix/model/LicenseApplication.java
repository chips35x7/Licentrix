package dev.nigelchiputura.licentrix.model;

import jakarta.persistence.*;
import java.time.LocalDate;

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

    // getters/setters, equals/hashCode...
}
