package dev.nigelchiputura.licentrix.service;

import dev.nigelchiputura.licentrix.model.License;
import dev.nigelchiputura.licentrix.model.LicenseType;
import dev.nigelchiputura.licentrix.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LicenseFeeService {

    private final LicenseRepository licenseRepo;

    public LicenseFeeService(LicenseRepository licenseRepo) {
        this.licenseRepo = licenseRepo;
    }

    // Calculate base fee based on type and validity
    public double calculateFee(LicenseType type, int years) {
        double base = switch (type) {
            case CTL -> 2000.0;   // Control License
            case PRSL -> 1200.0;  // Personal License
        };

        return base * years;
    }


    // Recalculate and update fee for a license
    public License updateFee(Integer licenseId) {
        Optional<License> opt = licenseRepo.findById(licenseId);
        if (opt.isEmpty()) throw new RuntimeException("License not found");

        License lic = opt.get();
        double newFee = calculateFee(lic.getLicenseType(), lic.getValidityYears());
        lic.setLicenseFee(newFee);

        return licenseRepo.save(lic);
    }

    // Compare two licenses
    public boolean areEqual(Integer id1, Integer id2) {
        License l1 = licenseRepo.findById(id1).orElseThrow(() -> new RuntimeException("License not found: " + id1));
        License l2 = licenseRepo.findById(id2).orElseThrow(() -> new RuntimeException("License not found: " + id2));

        return l1.equals(l2);
    }
}
