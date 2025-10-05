package dev.nigelchiputura.licentrix.service;

import dev.nigelchiputura.licentrix.model.*;
import dev.nigelchiputura.licentrix.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class LicenseApplicationService {
    private final LicenseApplicationRepository appRepo;
    private final LicenseRepository licenseRepo;
    private final UserRepository userRepo;
    private final CompanyRepository companyRepo;

    public LicenseApplicationService(LicenseApplicationRepository appRepo,
                                     LicenseRepository licenseRepo,
                                     UserRepository userRepo,
                                     CompanyRepository companyRepo) {
        this.appRepo = appRepo;
        this.licenseRepo = licenseRepo;
        this.userRepo = userRepo;
        this.companyRepo = companyRepo;
    }

    @Transactional
    public LicenseApplication apply(Integer applicantId, Integer companyId, LicenseType type) {
        User applicant = userRepo.findById(applicantId).orElseThrow();
        Company company = companyRepo.findById(companyId).orElseThrow();

        LicenseApplication app = new LicenseApplication();
        app.setApplicant(applicant);
        app.setCompany(company);
        app.setLicenseType(type);
        app.setSubmittedAt(LocalDate.now());
        return appRepo.save(app);
    }

    @Transactional
    public License approve(Integer applicationId, Integer validityYearsIfPRSL) {
        LicenseApplication app = appRepo.findById(applicationId).orElseThrow();
        app.setStatus(LicenseApplicationStatus.APPROVED);
        app.setReviewedAt(LocalDate.now());

        License license = new License();
        license.setCompany(app.getCompany());
        license.setLicenseType(app.getLicenseType());
        license.setIssueDate(LocalDate.now());

        if (app.getLicenseType() == LicenseType.CTL) {
            license.setValidityYears(15);
            license.setApplicationFee(800.0);
            license.setLicenseFee(100_000_000.0);
            license.setAnnualContributionFee(3_000.0);
        } else {
            license.setValidityYears(validityYearsIfPRSL);
            license.setApplicationFee(350.0);
            license.setLicenseFee(2_000_000.0);
            license.setAnnualFrequencyFee(2_000.0);
        }

        license = licenseRepo.save(license);
        appRepo.save(app);
        return license;
    }

    @Transactional
    public void reject(Integer applicationId, String comment) {
        LicenseApplication app = appRepo.findById(applicationId).orElseThrow();
        app.setStatus(LicenseApplicationStatus.REJECTED);
        app.setAdminComment(comment);
        app.setReviewedAt(LocalDate.now());
        appRepo.save(app);
    }

    public List<LicenseApplication> getApplicationsByUser(User user) {
        return appRepo.findByApplicant(user);
    }

}
