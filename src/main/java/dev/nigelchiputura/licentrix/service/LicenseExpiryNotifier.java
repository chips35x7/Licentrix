package dev.nigelchiputura.licentrix.service;

import dev.nigelchiputura.licentrix.model.License;
import dev.nigelchiputura.licentrix.repository.LicenseRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class LicenseExpiryNotifier {

    private final LicenseRepository licenseRepo;
    private final EmailService emailService;

    public LicenseExpiryNotifier(LicenseRepository licenseRepo, EmailService emailService) {
        this.licenseRepo = licenseRepo;
        this.emailService = emailService;
    }

    /**
     * Run daily at 9:00 AM
     */
    @Scheduled(cron = "0 0 9 * * ?")
    public void notifyExpiringLicenses() {
        LocalDate today = LocalDate.now();
        LocalDate threshold = today.plusDays(30); // warn 30 days before expiry

        List<License> all = licenseRepo.findAll();
        for (License lic : all) {
            LocalDate expiryDate = lic.getIssueDate().plusYears(lic.getValidityYears());
            if (!expiryDate.isBefore(today) && !expiryDate.isAfter(threshold)) {
                String companyEmail = lic.getCompany().getEmail();
                String subject = "License Expiry Reminder";
                String body = String.format(
                        "Dear %s,\n\nYour %s license (ID: %d) will expire on %s.\n" +
                                "Please renew it to avoid penalties.\n\nBest regards,\nLicentrix Team",
                        lic.getCompany().getName(),
                        lic.getLicenseType(),
                        lic.getId(),
                        expiryDate
                );
                emailService.sendEmail(companyEmail, subject, body);
            }
        }
    }
}
